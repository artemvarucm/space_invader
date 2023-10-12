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
	
	//TODO fill your code
	private Move dir = Move.UP;
	private Game game;
	private Position pos;
	int life;
	public UCMLaser(Game game, Position pos) {
		this.life = ARMOR;
		this.pos = new Position(pos);
		this.game = game;
	}
	
	public String toString(Position pos) {
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = Messages.LASER_SYMBOL;
		}
		return str;
	}
	
	/**
	 *  Implements the automatic movement of the laser	
	 */
	public void automaticMove () {
		performMovement(dir);
		if(isOut()) {
			die();
		}
	}
	
	public boolean isOnPosition(Position pos) {
		return pos.equals(this.pos);
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	public boolean isAlive() {
		return this.life > 0;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		game.enableLaser();
	}

	// PERFORM ATTACK METHODS
	
	private void die() {
		//TODO fill your code
		this.life = 0;
		onDelete();
	}

	private boolean isOut() {
		//TODO fill your code
		return !pos.isOnBoard();
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		pos.move(dir);
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(RegularAlien other) {
		//TODO fill your code
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = other.receiveAttack(this);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(RegularAlien.POINTS);
			}
			die();
		}
		return res;
	}
	
	public boolean performAttack(Ufo other) {
		//TODO fill your code
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = other.receiveAttack(this);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(Ufo.POINTS);
			}
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

}
