package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
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
	public UCMLaser(GameWorld game, Position pos) {
		super(Move.UP, game, new Position(pos), ARMOR);
	}
	
	@Override
	protected int getArmour() {
		return ARMOR;
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
	}
	
	@Override
	public void automaticMove () {
		// Realiza el movimiento
		if (game.isCycleDescend()) {
			// si los aliens estan bajando, realizamos disparo al inicio
			game.performAttack(this);
		}
		super.automaticMove();
	}
	
}
