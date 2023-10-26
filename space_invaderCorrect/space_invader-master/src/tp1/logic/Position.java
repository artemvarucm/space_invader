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
	public Position(Position pos) {
		this.col = pos.getCol();
		this.row = pos.getRow();
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
	public boolean isOnBoard() {
		return 
		col >= 0 && row >= 0
			&&
		col < Game.DIM_X && row < Game.DIM_Y;
	}
	
	public void move(Move dir) {
		dir.apply(this);
	}
	
	public boolean equals(Position pos) {
		return this.col == pos.getCol() && this.row == pos.getRow();
	}
}
