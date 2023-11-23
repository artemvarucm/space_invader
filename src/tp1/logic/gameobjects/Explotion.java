package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class Explotion extends EnemyWeapon {
	private static int DAMAGE = 1;
	public Explotion(GameWorld game, Position pos) {
		super(Move.NONE, game, pos, 1);
		this.game = game;
	}
	
	@Override
	protected int getArmour() {
		return 0;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el danio
		return DAMAGE;
	}
	
	@Override
	protected String getSymbol() {
		return null;
	}

	@Override
	public String toString() {
		// No hace falta por ahora
		String str = "";
		return str;
	}

	
	@Override
	public boolean performAttack(GameItem other) {
		// Ataca a other
		if (other.isAlive()) {
			Position otherPos = other.getPos();
			int deltaY = otherPos.getCol() - pos.getCol();
			int deltaX = otherPos.getRow() - pos.getRow();
			if (deltaY < 2 && deltaY > -2 && deltaX < 2 && deltaX > -2) {
				// Si se cumplen las condiciones
				weaponAttack(other);
			}
		}
		return false;
	}	
	
	@Override
	protected boolean weaponAttack(GameItem other) {
		return other.receiveAttack(this);	
	}
	
	@Override
	public void computerAction() {
		// Realiza los ataques y muere
		game.performAttack(this);
		die();
	}

	
	@Override
	public void onDelete() {
		game.removeObject(this);
	}
	
	@Override
	public void automaticMove() {
		// vacio
	}
}
