package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	private DestroyerAlien owner;
	public Bomb(Game game, Position pos, DestroyerAlien owner) {
		// La direccion de movimiento es hacia abajo
		super(Move.DOWN, new Position(pos), ARMOR, game);
		this.owner = owner;
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
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
		game.removeObject(this);
	}
}
