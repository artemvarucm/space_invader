 package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
	public EnemyWeapon (Move dir, Position pos, int armor, Game game) {
		super(dir, game, pos, armor);
	}
	
	@Override
	public String toString() {
		/* Devuelve la representacion
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		// Realiza el ataque. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			weaponAttack(other);
			// Eliminamos weapon
			//onDelete();
			this.life = 0;
		}
		return res;
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
	
	@Override
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(!pos.isOnBoard()) {
			// Si ha salido fuera del tablero, muere
			this.life = 0;
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	// PERFORM ATTACK METHODS
	
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}	
}
