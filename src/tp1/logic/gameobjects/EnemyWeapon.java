package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon{
	protected Move dir;
	public EnemyWeapon (Move dir, Position pos, int armor, Game game) {
		super(game, pos, armor);
		this.dir = dir;
	}
	
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(isOut()) {
			// Si ha salido fuera del tablero, muere
			die();
		} else {
			// Intenta atacar a alguien despues de moverse
			//game.performAttack(this);
		}
	}
	
	private boolean isOut() {
		// Devuelve true si el objeto esta fuera del tablero
		return !pos.isOnBoard();
	}
	
	
	
	public String toString() {
		/* Devuelve la representacion
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
	}
	
	// PERFORM ATTACK METHODS
	@Override
	protected boolean weaponAttack(GameObject other) {
		return other.receiveAttack(this);	
	}
	
	
}
