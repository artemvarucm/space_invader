package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo {

	//TODO fill your code
	public static final int ARMOR = 1;
	public static final int POINTS = 25;
	private int life;
	private Position pos;
	private boolean enabled;
	private Game game;
	
	public Ufo (Game game) {
		// FIXME
		this.game = game;
		this.life = 0;
	}
	
	public String toString() {
		return Messages.UFO_SYMBOL + "[" + ARMOR + "]";
	}
	
	//TODO fill your code
	public boolean isOnPosition(int col, int row) {
		return pos.getCol() == col && pos.getRow() == row;
	}
	
	public boolean isAlive() {
		return life > 0;
	}

	public void computerAction() {
		if(!enabled && canGenerateRandomUfo()) {
			enable();
		}
	}
	
	public void automaticMove() {
		if (isAlive()) {
			performMovement(Move.LEFT);
			if (isOut())
				die();
		}
	}
	
	private void die() {
		this.life = 0;
		enabled = false;
	}
	
	private void enable() {
		//TODO fill your code
		life = ARMOR;
		this.pos = new Position(Game.DIM_X, 0); // FIXME: invalid position esquina superior derecha
		enabled = true;
	}

	public void onDelete() {
		//TODO fill your code
	}
	
	private boolean isOut() {
		//TODO fill your code
		return !pos.isOnBoard();
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		return !isAlive();
	}
	
	public void receiveDamage(int dam) {
		this.life -= dam;
	}
	
	private void performMovement(Move dir) {
		//TODO fill your code
		pos.move(dir);
	}

	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}
	
}
