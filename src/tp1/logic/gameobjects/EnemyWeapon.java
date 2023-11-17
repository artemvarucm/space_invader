 package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
	public EnemyWeapon (Move dir, GameWorld game, Position pos, int life) {
		super(dir, game, pos, life);
	}
	
	@Override
	public String toString() {
		/* Devuelve la representacion
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
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
		if (!isAlive()) {
			this.life = 0; // FIXME die
		}
		return !isAlive();
	}
}
