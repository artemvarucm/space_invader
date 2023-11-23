package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	protected Move dir;
	public Weapon(Move dir, GameWorld game, Position pos, int life) {
		super(game, pos, life);
		this.dir = dir;
	}
	
	protected void die() {
		// Quita toda la vida (explicitamente)
		this.life = 0;
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		// Realiza el ataque. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			weaponAttack(other);
			// Eliminamos weapon
			die();
		}
		return res;
	}
	
	@Override
	public void automaticMove () {
		// Realiza el movimiento de Weapon
		performMovement(dir);
		if(!pos.isOnBoard()) {
			// Si ha salido fuera del tablero, muere
			die();
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	@Override
	public boolean receiveAttack(Explotion weapon) {
		// Recibe ataque del explotion
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			die();
		}
		return true; // siempre muere despues de colisionar
	}
	
	protected abstract boolean weaponAttack(GameItem other);
}
