package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
	public UCMWeapon (Move dir, Position pos, int armor, Game game) {
		super(dir, game, pos, armor);
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion del Laser
		return getSymbol();
	}
	
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}
}