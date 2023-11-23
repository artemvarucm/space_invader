package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Ship extends GameObject{

	public Ship(GameWorld game, Position pos, int life) {
		super(game, pos, life);
	}
	
	public Ship() {
		super();
	}
	
	public boolean move(Move move) {
		/* Realiza el movimiento en la direccion dir
		 * Devuelve true si queda dentro del tablero despues de moverse
		 */
		boolean validMove = true;
		Position hPos = pos.move(move);
		validMove = hPos.isOnBoard();
		if (validMove) {
			performMovement(move);
		}
		return validMove;
	}
}
