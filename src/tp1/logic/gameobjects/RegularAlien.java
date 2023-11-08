package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;
/*
package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Position;

public class RegularAlien extends GameObject {

	public RegularAlien(Game game, Position pos, AlienManager alienManager) {
		// TODO fill with your code
		super(game, pos, 0);
	}

	@Override
	public boolean isOnPosition(Position pos) {
		// TODO fill with your code
		return false;
	}

	@Override
	protected String getSymbol() {
		// TODO fill with your code
		return null;
	}

	@Override
	protected int getDamage() {
		// TODO fill with your code
		return 0;
	}

	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void automaticMove() {
		// TODO Auto-generated method stub

	}

}

 * 
 * */
/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien {
	public static final int ROW_INI_OFFSET = 1; // donde empezamos a dibujarles (filas)
	private static final int ARMOR = 2;
	private static final int POINTS = 5;
	// cuantos ciclos quedan para moverse
	private int cyclesToMove; 
	// constante a la cual se reinicia cyclesToMove despues de moverse
	private int speed; 	
	private Move dir;
	private Position pos;
	private int life;
	private Game game;
	private AlienManager alienManager;
	public RegularAlien (Position pos, int speed, Game game, AlienManager alienManager) {
		this.pos = new Position(pos);
		this.dir = Move.LEFT;
		this.life = ARMOR;
		this.cyclesToMove = speed;
		this.speed = speed;
		this.game = game;
		this.alienManager = alienManager;
	}
	
	public String toString() {
		// Devuelve la representacion del RegularAlien
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return " " + getSymbol() + "[" + String.format("%02d", life) + "]";
	}
	
	private String getSymbol() {
		// Devuelve la representacion ASCII de RegularAlien
		return Messages.REGULAR_ALIEN_SYMBOL;
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
		return 0;
	}
	
	public static int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de RegularAlien
		return Messages.alienDescription(getDescription(), POINTS, 0, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
			// Devuelve si las posiciones son iguales
			return pos.equals(this.pos);
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	
	public void automaticMove() {
		// Realiza el movimiento del Regular
		if (isAlive()) {
			if (cyclesToMove == 0) {
				// Si se tiene que mover
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
				cyclesToMove--;
			}
		}
	}
	
	public void computerAction() {
		// vacio
	}
	
	private void descend() {
		// Baja una fila el Regular
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
		// Notifica al alienManager de alien muerto
		receiveDamage(shockWave.getDamage());
		if (!isAlive()) {
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