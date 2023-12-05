package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class that represents the ShockWave
 *
 */
public class ShockWave extends UCMWeapon {
	private static int DAMAGE = 1;
	public ShockWave(GameWorld game) {
		// Posicion inexistente en vez de nulo para que no fallen los metodos que usan pos
		super(Move.NONE, game, new Position(-1, -1), 1);
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
			// Si se cumplen las condiciones
			weaponAttack(other);
		}
		return false;
	}	
	
	@Override
	public void computerAction() {
		// Realiza los ataques y muere
		game.performAttack(this);
		die();
	}
	
	@Override
	protected boolean weaponAttack(GameItem other) {
		// Ataque de Shockwave
		return other.receiveAttack(this);	
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
