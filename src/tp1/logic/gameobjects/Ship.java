package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.exceptions.NotAllowedMoveException;
import tp1.logic.exceptions.OffWorldException;

public abstract class Ship extends GameObject{

	public Ship(GameWorld game, Position pos, int life) {
		super(game, pos, life);
	}
	
	public Ship() {
		super();
	}
	
	public void move(Move move) throws OffWorldException, NotAllowedMoveException {
		/* Realiza el movimiento en la direccion dir
		 * Devuelve true si queda dentro del tablero despues de moverse
		 */
		boolean validMove = true;
		Position hPos = pos.move(move);
		if (hPos.isOnBoard()) {
			performMovement(move);
		} else {
			throw new OffWorldException(move, pos);
		}
	}
}
