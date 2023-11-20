package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
	public UCMWeapon (Move dir, GameWorld game, Position pos, int life) {
		super(dir, game, pos, life);
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion UCMWeapon
		return getSymbol();
	}
	
	@Override
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon del enemigo
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			die();
		}
		return !isAlive();
	}
}