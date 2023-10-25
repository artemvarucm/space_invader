package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Ufo { // TODO PREGUNTAR SI HACEN FALTA LOS GETTERS DE LIFE, POSITION, etc o dejarlos encapsulados

	//TODO fill your code
	private static final int ARMOR = 1;
	private static final int POINTS = 25;
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
			str = getSymbol() + "[" + String.format("%02d", life) + "]";
		}
		return str;
	}
	
	private String getSymbol() {
		return Messages.UFO_SYMBOL;
	}
	
	//TODO fill your code
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			die();
		}
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
	
	public static int getDamage() {
		return 0;
	}
	
	public static int getPoints() {
		return POINTS;
	}
	
	public static String getInfo() {
		return Messages.alienDescription(getDescription(), POINTS, 0, ARMOR);
	}
	
	private static String getDescription() {
		return Messages.UFO_DESCRIPTION;
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
		pos = pos.move(dir);
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
