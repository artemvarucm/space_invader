package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	private DestroyerAlien owner;
	public Bomb(DestroyerAlien owner, GameWorld game, Position pos) {
		// La direccion de movimiento es hacia abajo
		super(Move.DOWN, game, new Position(pos), ARMOR);
		this.owner = owner;
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return ARMOR;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII de la Bomba
		return Messages.BOMB_SYMBOL;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return Bomb.DAMAGE;
	}

	@Override
	public void onDelete() {
		// Si se ha muerto, mandar senal al destroyer de que puede volver a lanzar la bomba
		owner.enableBomb();
	}
}
