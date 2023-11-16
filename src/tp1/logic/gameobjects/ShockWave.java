package tp1.logic.gameobjects;

import tp1.logic.Game;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class ShockWave extends UCMWeapon {
	private static int DAMAGE = 1;
	public ShockWave(Game game) {
		super(null, null, 1, game);
		this.game = game;
	}
	
	@Override
	public String toString() {
		// No hace falta por ahora
		String str = "";
		return str;
	}
	
	
	@Override
	public int getDamage() {
		// Devuelve el danio
		return DAMAGE;
	}

	// PERFORM ATTACK METHODS
	@Override
	public void onDelete() {
		// vacio	
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		if (other.isAlive()) {
			// Si se cumplen las condiciones
			weaponAttack(other);
		}
		return false;
	}	

	/**
	 * Method that implements the attack by the laser to a regular alien.
	 * It checks whether both objects are alive and in the same position.
	 * If so call the "actual" attack method {@link weaponAttack}.
	 * @param other the regular alien possibly under attack
	 * @return <code>true</code> if the alien has been attacked by the laser.
	 */

	@Override
	protected String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void automaticMove() {
		// TODO Auto-generated method stub
		
	}
}
