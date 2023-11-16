package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon{
	public UCMWeapon (Position pos, int armor, Game game) {
		super(game, pos, armor);
	}

	
	
	protected boolean isOut() {
		// Devuelve true si el objeto esta fuera del tablero
		return !pos.isOnBoard();
	}
	
	@Override
	protected void die() {
		// Sirve para matar al objeto de forma explicita
		this.life = 0;
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
	public boolean performAttack(GameObject other) {
		// Realiza el ataque sobre other. Devuelve true si other esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = weaponAttack(other);
			if (res) {
				// Si ha muerto la nave
				// FIXME puntos correspondientes a cada nave
				game.receivePoints(0);
			}
			// Matamos despues del ataque
			die();
		}
		return res;
	}
	
	@Override
	protected boolean weaponAttack(GameObject other) {
		return other.receiveAttack(this);	
	}
}