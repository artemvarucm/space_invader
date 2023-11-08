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
public class UCMLaser extends UCMWeapon {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	private Move dir;
	public UCMLaser(Game game, Position pos) {
		super(new Position(pos), ARMOR, game);
		this.dir = Move.UP;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.LASER_SYMBOL;
	}
	
	public int getDamage() {
		// Devuelve el dano
		return UCMLaser.DAMAGE;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	public void onDelete() {
		// Si se ha muerto, la nave puede volver a lanzar el laser
		game.enableLaser();
	}
	
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(isOut()) {
			// Si ha salido fuera del tablero, muere
			die();
		} else {
			// Intenta atacar a alguien despues de moverse
			//game.performAttack(this);
		}
	}

	// PERFORM ATTACK METHODS

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

	//ACTUAL ATTACK METHODS
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
}
