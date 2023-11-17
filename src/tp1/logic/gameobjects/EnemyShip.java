package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship {
	protected Move dir;
	public EnemyShip (Move dir, Position pos, int armor, GameWorld game) {
		super(game, pos, armor);
		this.dir = dir;
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion del DestroyerAlien
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return " " + getSymbol() + "[" + String.format("%02d", life) + "]";
	}
	
	abstract protected int getPoints();
}
// anadir receive attack weapon con weapon.getDamage()
