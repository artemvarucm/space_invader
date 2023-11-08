package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {

	public Weapon(Game game, Position pos, int armor) {
		super(game, pos, armor);
	}
	
	protected abstract boolean weaponAttack(GameObject other);
	
	public boolean performAttack(GameObject other) {
		// Realiza el ataque sobre UCMShip. Devuelve true si el UCMShip esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			res = weaponAttack(other);
			// Eliminamos bomba despues del ataque
			die();
		}
		return res;
	}
	
	protected void die() {
		// Sirve para matar al objeto de forma explicita
		this.life = 0;
	}
}
