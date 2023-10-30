package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

public class UCMShip {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
	private Position pos;
	private int life;
	private Game game;
	private int points;
	private boolean hasShockWave;
	private boolean canShoot;

	public UCMShip(Game game) {
		// Posicion inicial - ultima fila en la columna media
		this.pos = new Position(Game.DIM_X / 2, Game.DIM_Y - 1);
		this.life = ARMOR;
		this.game = game;
		this.canShoot = true;
	}
	
	public String toString(Position pos) {
		// Devuelve la representacion de la UCMShip si se encuentra en la posicion pos
		
		String str = "";
		if (isOnPosition(pos)) {
			str = getSymbol();
		}
		return str;
	}
	
	public String getSymbol() {
		/* Devuelve el caracter/caracteres ASCII del UCMShip
		Cambia si esta vivo, o muerto
		*/
		String res = "";
		if (isAlive()) {
			res = Messages.UCMSHIP_SYMBOL;
		} else {
			res = Messages.UCMSHIP_DEAD_SYMBOL;
		}
		return res;
	}

	public boolean performMovement(Move dir) {
		/* Realiza el movimiento en la direccion dir
		 * Devuelve true si queda dentro del tablero despues de moverse
		 */
		boolean validMove = true;
		Position hPos = pos.move(dir);
		validMove = hPos.isOnBoard();
		if (validMove) {
			this.pos = hPos;
		} else {
			//System.out.println(Messages.invalidPosition(hPos.getCol(), hPos.getRow()));
			System.out.println(Messages.MOVEMENT_ERROR);
		}
		
		return validMove;
	}
	
	public boolean move(String direction) {
		// Intenta realizar el movimiento en la direccion direction
		boolean validMove = true;
		Move dir = null;
		try {
			dir = Move.valueOf(direction);
			if (dir.equals(Move.UP) || dir.equals(Move.DOWN)) { 
				// No se puede ir arriba o abajo
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			validMove = false;
			System.out.println(Messages.UNKNOWN_COMMAND);
		}
		if (validMove) {
			validMove = performMovement(dir);
		}
		return validMove;
	}
	
	public boolean shootLaser(Game game) {
		// Realiza el disparo del laser
		boolean res = false;
		if (canShoot) {
			// Si puede disparar
			res = true;
			// Crea el nuevo laser en la posicion de UCMShip
			UCMLaser laser = new UCMLaser(game, pos, true);
			game.addObject(laser);
			this.canShoot = false;
		}
		return res;
	}
	
	public boolean executeShockWave(Game game, RegularAlienList regularAliens, DestroyerAlienList destroyerAliens) {
		// Ejecuta el disparo del ShockWave
		boolean res = false;
		if (hasShockWave()) {
			disableShockWave();
			ShockWave shockWave = new ShockWave(game);
			regularAliens.checkAttacks(shockWave);
			destroyerAliens.checkAttacks(shockWave);
			shockWave.die();
			res = true;
		}
		return res;
	}
	
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return life > 0;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de UCMShip
		return Messages.ucmShipDescription(getDescription(), DAMAGE, ARMOR);
	}
	
	public static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.UCMSHIP_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
		// Devuelve si las posiciones son iguales
		return pos.equals(this.pos);
	}
	
	public static int getDamage() {
		// Devuelve el dano
		return DAMAGE;
	}

	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}

	public void die() {
		// Sirve para matar al objeto de forma explicita
		life = 0;
	}

	public Position getPos() {
		// Devuelve la posicion actual
		return pos;
	}

	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public void enableLaser() {
		// Inhabilta el laser
		this.canShoot = true;
	}
	
	public void onDelete() {
		// vacio
	}

	public int getPoints() {
		// Devuelve los puntos del jugador
		return points;
	}

	public void receivePoints(int points) {
		// Recibe los puntos indicados 
		this.points += points;
	}

	private boolean hasShockWave() {
		// Devuelve true si puede lanzar shockwave
		return hasShockWave;
	}

	public void enableShockWave() {
		// Inhabilta el shockwave
		this.hasShockWave = true;
	}

	public void disableShockWave() {
		// Deshabilta el shockwave
		this.hasShockWave = false;
	}

	public boolean isCanShoot() {
		// Devuelve true si puede lanzar laser
		return canShoot;
	}

	public String stateToString() {
		/* Imprime el estado del jugador 
		 (vida, puntos, poder de shockwave)
		 */
		StringBuilder result = new StringBuilder();
		result.append("Life: ").append(life).append(Messages.LINE_SEPARATOR);
		result.append("Points: ").append(points).append(Messages.LINE_SEPARATOR);
		String shock = "OFF";
		if (hasShockWave()) {
			shock = "ON";
		}
		result.append("ShockWave: ").append(shock).append(Messages.LINE_SEPARATOR);
		
		return result.toString();
	}
	
	public boolean receiveAttack(Bomb other) {
		/* Recibe el dano de la bomba
		 * Devuelve true si esta muerta la UCMShip
		 */
		receiveDamage(Bomb.DAMAGE);
		return !isAlive();
	}
}
