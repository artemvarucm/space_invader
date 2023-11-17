package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	public UCMLaser(Game game, Position pos) {
		super(Move.UP, new Position(pos), ARMOR, game);
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return UCMLaser.DAMAGE;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del Laser
		return Messages.LASER_SYMBOL;
	}

	/**
	 *  Method called when the laser disappears from the board
	 */
	@Override
	public void onDelete() {
		// Si se ha muerto, la nave puede volver a lanzar el laser
		game.enableLaser();
		game.removeObject(this);
	}
	
	@Override
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		
		if (game.isCycleDescend()) {
			// si los aliens estan bajando, realizamos disparo al inicio
			game.performAttack(this);
		}
		performMovement(dir);
		if(!pos.isOnBoard()) {
			// Si ha salido fuera del tablero, muere
			this.life = 0;
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		// Realiza el ataque. Devuelve true si el UCMShip esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			weaponAttack(other);
			// Eliminamos weapon
			this.life = 0; // FIXME die
		}
		return res;
	}	
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			this.life = 0; // FIXME die
		}
		return !isAlive();
	}
}
