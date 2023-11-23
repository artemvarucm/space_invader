package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMSuperLaser extends UCMLaser {
	private static int ARMOR = 1;
	public static int DAMAGE = 2;
	public UCMSuperLaser(GameWorld game, Position pos) {
		super(game, new Position(pos));
	}
	
	@Override
	protected int getArmour() {
		return 0;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return UCMSuperLaser.DAMAGE;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.SUPER_LASER_SYMBOL;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	@Override
	public void onDelete() {
		// Si se ha muerto, la nave puede volver a lanzar el laser
		game.enableSuperLaser();
		game.removeObject(this);
	}
}
