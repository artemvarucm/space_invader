package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	private Move dir;
	private Game game;
	private Position pos;
	int life;
	public UCMLaser(Game game, Position pos, boolean alive) {
		if (alive) {
			// Lo inicializamos con vida
			this.life = ARMOR;
		} else {
			// Lo inicializamos muerto(para no trabajar con null)
			this.life = 0;
		}
		this.pos = new Position(pos);
		this.game = game;
		// La direccion de movimiento es hacia arriba
		this.dir = Move.UP;
	}
	
	public String toString(Position pos) {
		// Devuelve la representacion del Laser
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = getSymbol();
		}
		return str;
	}
	
	private String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.LASER_SYMBOL;
	}
	
	/**
	 *  Implements the automatic movement of the laser	
	 */
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(isOut()) {
			// Si ha salido fuera del tablero, muere
			die();
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	public boolean isOnPosition(Position pos) {
		// Devuelve si las posiciones son iguales
		return pos.equals(this.pos);
	}
	
	public int getDamage() {
		// Devuelve el dano
		return UCMLaser.DAMAGE;
	}
	
	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public Position getPosition() {
		// Devuelve la posicion actual
		return this.pos;
	}
	
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return this.life > 0;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		// Si se ha muerto, la nave puede volver a lanzar el laser
		game.enableLaser();
	}

	// PERFORM ATTACK METHODS
	
	private void die() {
		// Sirve para matar al objeto de forma explicita
		this.life = 0;
		onDelete();
	}

	private boolean isOut() {
		// Devuelve true si el objeto esta fuera del tablero
		return !pos.isOnBoard();
	}

	private void performMovement(Move dir) {
		// Realiza el movimiento en la direccion dir
		pos = pos.move(dir);
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(RegularAlien other) {
		// Realiza el ataque sobre RegularAlien. Devuelve true si el RegularAlien esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(RegularAlien.getPoints());
			}
			// Eliminamos laser despues del ataque
			die();
		}
		return res;
	}
	
	public boolean performAttack(DestroyerAlien other) {
		// Realiza el ataque sobre DestroyerAlien. Devuelve true si el DestroyerAlien esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(DestroyerAlien.getPoints());
			}
			// Eliminamos laser despues del ataque
			die();
		}
		return res;
	}
	
	public boolean performAttack(Ufo other) {
		// Realiza el ataque sobre Ufo. Devuelve true si el UFo esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(Ufo.getPoints());
			}
			// Eliminamos laser despues del ataque
			die();
		}
		return res;
	}
	
	public boolean performAttack(Bomb other) {
		// Realiza el ataque sobre Bomba. Devuelve true si la Bomba esta muerta
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = weaponAttack(other);
			// Eliminamos laser despues del ataque
			die();
		}
		return res;
	}

	/**
	 * Method that implements the attack by the laser to a destroyer alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the destroyer alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	/*
	public boolean performAttack(DestroyerAlien other) {
		//TODO fill your code
		return false;
	}
	*/
	
	//TODO fill your code


	//ACTUAL ATTACK METHODS
	

	/**
	 * 
	 * @param other regular alien under attack by the laser
	 * @return always returns <code>true</code>
	 */
	private boolean weaponAttack(RegularAlien other) {
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(Ufo other) {
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(DestroyerAlien other) {
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(Bomb other) {
		return other.receiveAttack(this);	
	}

	//TODO fill your code


	// RECEIVE ATTACK METHODS
	
	/**
	 * Method to implement the effect of bomb attack on a laser
	 * @param weapon the received bomb
	 * @return always returns <code>true</code>
	 */
	/*
	public boolean receiveAttack(Bomb weapon) {
		receiveDamage(weapon.getDamage());
		return true;
	}
	*/
	
	public boolean receiveAttack(Bomb bomb) {
		// Recibe el ataque de la bomba
		// Devuelve true si se ha muerto el laser
		receiveDamage(bomb.getDamage());
		// Despues de la colision muere el laser
		die();
		return !isAlive();
	}

	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
}
