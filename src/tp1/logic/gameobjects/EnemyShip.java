package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyShip extends Ship {
	protected Move dir;
	public EnemyShip (Move dir, GameWorld game, Position pos, int life) {
		super(game, pos, life);
		this.dir = dir;
	}
	
	public EnemyShip() {
		super();
	}
	
	@Override
	public String toString() {
		// Devuelve la representacion del DestroyerAlien
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return " " + getSymbol() + "[" + String.format("%02d", life) + "]";
	}
	
	abstract protected int getPoints();
	
	@Override
	public boolean receiveAttack(Explotion weapon) {
		// Recibe ataque del explotion
		UCMWeapon ucmWeapon = weapon;
		return receiveAttack(ucmWeapon);
	}
}
