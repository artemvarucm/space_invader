package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;
	public Position(int col, int row) {
		this.col = col;
		this.row = row;
	}
	//TODO fill your code
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	public boolean validate() {
		return 
		col >= 0 && row >= 0
			&&
		col < Game.DIM_X && row < Game.DIM_Y;
	}
}
