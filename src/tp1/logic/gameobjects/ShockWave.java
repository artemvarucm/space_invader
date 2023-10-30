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
	private static int DAMAGE = 1;
	private Game game;
	int life;
	public ShockWave(Game game) {
		this.life = 1;
		this.game = game;
	}
	/*
	public String toString(Position pos) {
		// No hace falta por ahora
		String str = "";
		return str;
	}
	*/
	public boolean isAlive() {
		// Devuelve si esta vivo
		return this.life > 0;
	}
	
	
	public int getDamage() {
		// Devuelve el danio
		return DAMAGE;
	}

	// PERFORM ATTACK METHODS
	private void onDelete() {
		// vacio	
	}
	
	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public void die() {
		// Sirve para matar de forma explicita
		this.life = 0;
	}

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */
	public boolean performAttack(RegularAlien other) {
		// Realiza el ataque a RegularAlien
		boolean res = false;
		if (isAlive() && other.isAlive()) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave, recibir putnos
				game.receivePoints(RegularAlien.getPoints());
			}
		}
		return res;
	}
	
	public boolean performAttack(DestroyerAlien other) {
		// Realiza el ataque a DestroyerAlien
		boolean res = false;
		if (isAlive() && other.isAlive()) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave, recibir putnos
				game.receivePoints(DestroyerAlien.getPoints());
			}
		}
		return res;
	}
	
	
	private boolean weaponAttack(RegularAlien other) {
		// Llama al metodo recibir ataque de RegularAlien
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(DestroyerAlien other) {
		// Llama al metodo recibir ataque de DestroyerAlien
		return other.receiveAttack(this);	
	}
}
