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
	public int getDamage() {
		// Devuelve el dano
		return UCMSuperLaser.DAMAGE;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.SUPER_LASER_SYMBOL;
	}	
}
