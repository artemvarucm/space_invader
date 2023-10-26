package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class ShockWave {
	public static int DAMAGE = 1;
	
	private Game game;
	int life;
	public ShockWave(Game game) {
		this.life = 1;
		this.game = game;
	}
	
	public String toString(Position pos) {
		String str = "";
		return str;
	}
	
	public boolean isAlive() {
		return this.life > 0;
	}

	// PERFORM ATTACK METHODS
	private void onDelete() {
		
	}
	
	public void die() {
		//TODO fill your code
		this.life = 0;
		onDelete();
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
		if (isAlive() && other.isAlive()) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(RegularAlien.POINTS);
			}
		}
		return res;
	}
	
	public boolean performAttack(DestroyerAlien other) {
		//TODO fill your code
		boolean res = false;
		if (isAlive() && other.isAlive()) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				game.receivePoints(DestroyerAlien.POINTS);
			}
		}
		return res;
	}
	
	
	private boolean weaponAttack(RegularAlien other) {
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(DestroyerAlien other) {
		return other.receiveAttack(this);	
	}
}
