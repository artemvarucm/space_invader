package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	private Move dir;
	public Weapon(Move dir, GameWorld game, Position pos, int life) {
		super(game, pos, life);
		this.dir = dir;
	}
	
	@Override
	public String toString() {
		/* 
		 Devuelve la representacion
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
	}
	
	private void die() {
		// Quita toda la vida (explicitamente)
		this.life = 0;
		onDelete();
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		// Realiza el ataque. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si ha podido atacar al objeto
			if (weaponAttack(other)) {
				// Eliminamos weapon
				die();
			}
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
	
	protected abstract boolean weaponAttack(GameItem other);
}
