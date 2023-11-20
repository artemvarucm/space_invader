package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class ShockWave extends UCMWeapon {
	private static int DAMAGE = 1;
	public ShockWave(GameWorld game) {
		super(Move.NONE, game, null, 1);
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
		// TODO Auto-generated method stub
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
		if (other.isAlive()) {
			// Si se cumplen las condiciones
			weaponAttack(other);
		}
		return false;
	}	
	
	@Override
	public void computerAction() {
		// realiza los ataques y muere
		game.performAttack(this);
		onDelete();
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
