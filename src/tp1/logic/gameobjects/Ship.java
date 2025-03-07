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
	
	protected abstract String getDescription();
	
	public abstract String getInfo();
	
	public void move(Move move) throws OffWorldException, NotAllowedMoveException {
		/* Realiza el movimiento en la direccion dir
		 * Devuelve true si queda dentro del tablero despues de moverse
		 */
		
		Position hPos = pos.move(move);
		
		if (!hPos.isOnBoard()) {
			throw new OffWorldException(move, pos);
		}
		
		performMovement(move);
	}
}
