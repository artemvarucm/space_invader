 package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
	public EnemyWeapon (Move dir, GameWorld game, Position pos, int life) {
		super(dir, game, pos, life);
	}
	
	// PERFORM ATTACK METHODS
	@Override
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		return true; // siempre muere despues de colisionar
	}
}
