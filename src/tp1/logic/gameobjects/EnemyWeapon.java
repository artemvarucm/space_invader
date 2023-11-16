 package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
	public EnemyWeapon (Move dir, Position pos, int armor, Game game) {
		super(dir, game, pos, armor);
		//this.dir = dir;
	}
	
	@Override
	public String toString() {
		/* Devuelve la representacion
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
	}
	
	public boolean performAttack(GameObject other) {
		// Realiza el ataque. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			weaponAttack(other);
			// Eliminamos weapon
			onDelete();
		}
		return res;
	}	
	
	@Override
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(!pos.isOnBoard()) {
			// Si ha salido fuera del tablero, muere
			onDelete();
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	// PERFORM ATTACK METHODS
	
	protected boolean weaponAttack(GameObject other) {
		return other.receiveAttack(this);	
	}	
}
