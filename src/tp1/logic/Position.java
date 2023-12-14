package tp1.logic;

import tp1.view.Messages;

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

	public int getCol() {
		// Devuelve la columna
		return col;
	}
	
	public int getRow() {
		// Devuelve la fila
		return row;
	}
	
	@Override
	public String toString() {
		return Messages.POSITION.formatted(col, row);
	}
	
	public boolean isOnBoard() {
		// Devuelve true, si la posicion esta dentro de las dimensiones del tablero
		return 
		col >= 0 && row >= 0
			&&
		col < Game.DIM_X && row < Game.DIM_Y;
	}
	
	public Position move(Move dir) {
		// Recibe el movimiento que quiere realizar sobre la posicion actual.
		// Devuelve un nuevo objecto Posicion con el movimiento aplicado
		return new Position(col + dir.getX(), row + dir.getY());
	}

	public boolean equals(Position pos) {
		// Devuelve si la posicion en this es igual a la posicion en pos
		return this.col == pos.getCol() && this.row == pos.getRow();
	}
}
