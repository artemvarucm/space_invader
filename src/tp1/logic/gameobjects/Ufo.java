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
	
	public String toString(Position pos) {
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = Messages.UFO_SYMBOL + "[" + String.valueOf(life) + "]";
		}
		return str;
	}
	
	//TODO fill your code
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		return !isAlive();
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
	
	private void enable() {
		//TODO fill your code
		life = ARMOR;
		this.pos = new Position(Game.DIM_X, 0); // FIXME: invalid position esquina superior derecha
		enabled = true;
	}
	
	public void receiveDamage(int dam) {
		this.life -= dam;
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public boolean isOnPosition(Position pos) {
		return pos.equals(this.pos);
	}
	
	private boolean isOut() {
		//TODO fill your code
		return !pos.isOnBoard();
	}
	
	private void performMovement(Move dir) {
		//TODO fill your code
		pos.move(dir);
	}
	
	private void die() {
		this.life = 0;
		enabled = false;
	}
	
	public void onDelete() {
		//TODO fill your code
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
