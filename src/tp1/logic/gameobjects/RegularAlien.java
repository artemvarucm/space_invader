package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien {
	public static final int ROW_INI_OFFSET = 1; // donde empezamos a dibujarles (filas)
	public static final int COL_INI_OFFSET = 2; // donde empezamos a dibujarles (columnas)
	private static final int ARMOR = 2;
	private static final int POINTS = 5;
	private static final String representation  = "R";
	//TODO fill your code
	private int cyclesToMove; // cuantos quedan para moverse (cambia cada jugada)
	private int speed; // constante a la cual se reinicia despues de moverse
	private Move dir;
	private Position pos;
	private int life;
	private Game game;
	
	private AlienManager alienManager;

	//TODO fill your code
	public RegularAlien (int col, int row, int speed) {
		this.pos = new Position(col, row);
		this.life = ARMOR;
		this.cyclesToMove = speed;
		this.speed = speed;
	}
	
	public String toString() {
		return representation + "[" + String.valueOf(ARMOR) + "]";
	}
	
	public boolean isOnPoisition(int col, int row) {
			return pos.getCol() == col && pos.getRow() == row;
	}

	/**
	 *  Implements the automatic movement of the regular alien	
	 */
	public void automaticMove() {
		//TODO fill your code
	}

	private void descent() {
		//TODO fill your code
		
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		
	}

	private boolean isInBorder() {
		//TODO fill your code
		return false;
	}

	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		return false;
	}
	

}