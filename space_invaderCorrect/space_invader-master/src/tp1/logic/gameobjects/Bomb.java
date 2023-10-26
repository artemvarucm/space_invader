package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Bomb {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	
	//TODO fill your code
	private Move dir = Move.DOWN;
	private Game game;
	private Position pos;
	int life;
	private DestroyerAlien owner;
	public Bomb(Game game, Position pos, DestroyerAlien owner) {
		this.life = ARMOR;
		this.pos = new Position(pos);
		this.game = game;
		this.owner = owner;
	}
	
	public String toString(Position pos) {
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = Messages.BOMB_SYMBOL;
		}
		return str;
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			die();
		}
		return !isAlive();
	}
	
	/**
	 *  Implements the automatic movement of the bomb	
	 */
	public void automaticMove () {
		performMovement(dir);
		if(isOut()) {
			die();
		}
	}
	
	public boolean isOnPosition(Position pos) {
		return pos.equals(this.pos);
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	public int getDamage() {
		return Bomb.DAMAGE;
	}
	
	public void receiveDamage(int dam) {
		this.life -= dam;
	}
	
	public boolean isAlive() {
		return this.life > 0;
	}

	/**
	 *  Method called when the bomb disappears from the board
	 */
	public void onDelete() {
		game.enableLaser();
		owner.canShootBomb();		
	}

	// PERFORM ATTACK METHODS
	
	private void die() {
		//TODO fill your code
		this.life = 0;
		onDelete();
	}

	private boolean isOut() {
		//TODO fill your code
		return !pos.isOnBoard();
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		pos.move(dir);
	}

	
	public boolean performAttack(UCMShip other) {
		//TODO fill your code
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			res = other.receiveAttack(this);
			die();
		}
		return res;
	}
	
}