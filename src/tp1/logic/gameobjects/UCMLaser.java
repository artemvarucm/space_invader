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
		super(Move.UP, new Position(pos), ARMOR, game);
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.LASER_SYMBOL;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return UCMLaser.DAMAGE;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	@Override
	public void onDelete() {
		// Si se ha muerto, la nave puede volver a lanzar el laser
		game.enableLaser();
		game.removeObject(this);
	}

	// PERFORM ATTACK METHODS

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	//ACTUAL ATTACK METHODS
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
}
