package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien { /// TODO toString hace falta String builder? 
	public static final int ROW_INI_OFFSET = 1; // donde empezamos a dibujarles (filas)
	private static final int ARMOR = 2;
	private static final int POINTS = 5;
	//TODO fill your code
	private int cyclesToMove; // cuantos quedan para moverse (cambia cada jugada)
	private int speed; // constante a la cual se reinicia despues de moverse
	private Move dir;
	private Position pos;
	private int life;
	private Game game;
	private AlienManager alienManager;
	//TODO fill your code
	public RegularAlien (Position pos, int speed, Game game, AlienManager alienManager) {
		this.pos = new Position(pos);
		this.dir = Move.LEFT;
		this.life = ARMOR;
		this.cyclesToMove = speed;
		this.speed = speed;
		this.game = game;
		this.alienManager = alienManager;
	}
	
	public String toString() {
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return getSymbol() + "[" + String.format("%02d", life) + "]";
	}
	
	private String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
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
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
			return pos.equals(this.pos);
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	
	public void automaticMove() {
		//TODO fill your code
		if (isAlive()) {
			if (cyclesToMove == 0) {
				performMovement(dir);
				if (isInBorder()) {
					alienManager.shipOnBorder();
				}
				cyclesToMove = speed;
			} else if (alienManager.readyToDescend()) {
				descend();	
			} else {
				cyclesToMove--;
			}
		}
	}
	
	private void descend() {
		//TODO fill your code
		performMovement(Move.DOWN);
		this.dir = dir.flip();
		
		alienManager.decreaseOnBorder();
		
		if (isInFinalRow()) {
			alienManager.finalRowReached();
		}
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		pos = pos.move(dir);
	}
	
	private boolean isInBorder() {
		//TODO fill your code
		return (dir.equals(Move.RIGHT) && pos.getCol() == Game.DIM_X - 1) 
				|| 
				(dir.equals(Move.LEFT) && pos.getCol() == 0);
	}
	
	public boolean isInFinalRow() {
		return pos.getRow() == Game.DIM_Y - 1;
	}	
	
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public boolean receiveAttack(ShockWave shockWave) {
		//TODO fill your code
		receiveDamage(shockWave.getDamage());
		if (!isAlive()) {
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public void receiveDamage(int dam) {
		this.life -= dam;
	}
}