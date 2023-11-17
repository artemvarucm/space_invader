package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
	public UCMWeapon (Move dir, Position pos, int armor, GameWorld game) {
		super(dir, game, pos, armor);
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion del Laser
		return getSymbol();
	}
	
	@Override
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			this.life = 0; // FIXME die
		}
		return !isAlive();
	}
}