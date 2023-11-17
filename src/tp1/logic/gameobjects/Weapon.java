package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	protected Move dir;
	public Weapon(Move dir, GameWorld game, Position pos, int armor) {
		super(game, pos, armor);
		this.dir = dir;
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		// Realiza el ataque. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			weaponAttack(other);
			// Eliminamos weapon
			this.life = 0;
		}
		return res;
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
	
	protected abstract boolean weaponAttack(GameItem other);
}
