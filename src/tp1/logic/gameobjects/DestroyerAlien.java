package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a Destroyer Alien
 *
 */
public class DestroyerAlien {
	private static final int ARMOR = 1;
	private static final int POINTS = 10;
	private static final int DAMAGE = 1;
	// cuantos ciclos quedan para moverse
	private int cyclesToMove; 
	// constante a la cual se reinicia cyclesToMove despues de moverse
	private int speed; 
	private Move dir;
	private Position pos;
	private int life;
	private Game game;
	private AlienManager alienManager;
	public boolean canShootBomb;
	public boolean bombReadyToFire;
	public DestroyerAlien (Position pos, int speed, Game game, AlienManager alienManager) {
		this.pos = new Position(pos);
		this.dir = Move.LEFT;
		this.life = ARMOR;
		this.cyclesToMove = speed;
		this.speed = speed;
		this.game = game;
		this.alienManager = alienManager;
		this.canShootBomb = true;
		this.bombReadyToFire = false;
	}
	
	public String toString() {
		// Devuelve la representacion del DestroyerAlien
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return " " + getSymbol() + "[" + String.format("%02d", life) + "]";
	}
	
	private String getSymbol() {
		// Devuelve la representacion ASCII de DestroyerAlien
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}
	
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return life > 0;
	}
	
	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public Position getPos() {
		// Devuelve la posicion actual
		return pos;
	}
	
	public static int getDamage() {
		// Devuelve el dano
		return DAMAGE;
	}
	
	public static int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de DestroyerAlien
		return Messages.alienDescription(getDescription(), POINTS, DAMAGE, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.DESTROYER_ALIEN_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
			// Devuelve si las posiciones son iguales
			return pos.equals(this.pos);
	}
	
	public void computerAction() {
		// Realiza la revision si puede generar bomba
		if (canShootBomb && canGenerateRandomBomb()) {
			// Inhabilita la bomba en caso de que puede generarla
			canShootBomb = false;
			bombReadyToFire = true;
		}
	}
	
	public void enableBomb() {
		// Se ejecuta despues de que la bomba se muera
		// Puede disparar otra vez el Destroyer
		canShootBomb = true;
	}
	
	private boolean canGenerateRandomBomb() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
		
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	
	public void automaticMove() {
		// Realiza el movimiento del Destroyer
		if (isAlive()) {
			if (cyclesToMove == 0) {
				// Si se tinene que mover
				performMovement(dir);
				if (isInBorder()) {
					// Si esta en el borde, avisa al manager
					alienManager.shipOnBorder();
				}
				// reiniciamos el "cooldown" del movimiento
				cyclesToMove = speed;
			} else if (alienManager.readyToDescend()) {
				// Si ya se ha movido anteriormente, vemos si tiene que descender
				descend();	
			} else {
				if (bombReadyToFire) {
					// Si tiene que lanzar la bomba, la lanzas despues de realizar cualquier tipo de movimiento
					Bomb templateBomb = new Bomb(game, this.pos, this);
					game.addObject(templateBomb);
					bombReadyToFire = false;
				}
				cyclesToMove--;
			}
		}
	}
	
	private void descend() {
		// Baja una fila el Destroyer
		performMovement(Move.DOWN);
		// Cabia de sentido del movimiento
		this.dir = dir.flip();
		// Decrementa el contador de aliens por bajar del AlienManager
		alienManager.decreaseOnBorder();
		// Avisa si ha llegado al borde
		if (isInFinalRow()) {
			alienManager.finalRowReached();
		}
	}

	private void performMovement(Move dir) {
		// Realiza el movimiento en la direccion dir
		pos = pos.move(dir);
	}
	
	private boolean isInBorder() {
		// Dependiendo del sentido del movimiento devuelve si ha tocado borde o no
		return (dir.equals(Move.RIGHT) && pos.getCol() == Game.DIM_X - 1) 
				|| 
				(dir.equals(Move.LEFT) && pos.getCol() == 0);
	}
	
	public boolean isInFinalRow() {
		// Devuelve true si esta en la ultima fila(donde esta UCMShip)
		return pos.getRow() == Game.DIM_Y - 1;
	}	
	
	public boolean receiveAttack(UCMLaser laser) {
		// Recibe ataque del laser
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			// Notifica al alienManager de alien muerto
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public boolean receiveAttack(ShockWave shockWave) {
		// Recibe ataque del shockwave
		receiveDamage(shockWave.getDamage());
		if (!isAlive()) {
			// Notifica al alienManager de alien muerto
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
	
	public void onDelete() {
		// vacio
	}
}