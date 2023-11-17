package tp1.logic.gameobjects;

import tp1.logic.GameWorld;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class ShockWave extends UCMWeapon {
	private static int DAMAGE = 1;
	public ShockWave(GameWorld game) {
		super(null, null, 1, game);
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
	public void onDelete() {
		// vacio	
	}
	
	@Override
	public void automaticMove() {
		// vacio
	}
}
