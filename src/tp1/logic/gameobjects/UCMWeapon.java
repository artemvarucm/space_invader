package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
	public UCMWeapon (Move dir, Position pos, int armor, Game game) {
		super(dir, game, pos, armor);
	}

	
	
	protected boolean isOut() {
		// Devuelve true si el objeto esta fuera del tablero
		return !pos.isOnBoard();
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion del Laser
		String str = "";
		if (isAlive()) {
			str = getSymbol();
		}
		return str;
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			onDelete();
		}
		return !isAlive();
	}
	
	public boolean performAttack(GameObject other) {
		// Realiza el ataque. Devuelve true si el UCMShip esta muerto
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
	
	protected boolean weaponAttack(GameObject other) {
		return other.receiveAttack(this);	
	}
}