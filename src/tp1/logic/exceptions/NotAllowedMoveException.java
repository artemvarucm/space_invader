package tp1.logic.exceptions;

import tp1.view.Messages;

/**
 * 
 * Clase que representa la excepcion que se produce al intentar moverse en una direccion prohibida (para UCMShip)
 *
 */

public class NotAllowedMoveException extends GameModelException {
	public NotAllowedMoveException() {
		super(Messages.ALLOWED_MOVES_MESSAGE);
	}
}