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
	public UCMLaser(Game game, Position pos) {
		super(Move.UP, new Position(pos), ARMOR, game);
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return UCMLaser.DAMAGE;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.LASER_SYMBOL;
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
}
