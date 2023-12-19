package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

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
		// Devuelve la representacion del EnemyShip
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return " " + getSymbol() + "[" + String.format("%02d", life) + "]";
	}

	@Override
	public String getInfo() {
		// Devuelve la descripcion formateada de DestroyerAlien
		return Messages.alienDescription(getDescription(), getPoints(), getDamage(), getArmour());
	}
	
	abstract protected int getPoints();
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		return true;
	}
}
