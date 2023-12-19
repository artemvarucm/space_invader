package tp1.logic.exceptions;

import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Clase que representa la excepcion que se produce al salir fuera de los bordes del tablero
 */

public class OffWorldException extends GameModelException {
	public OffWorldException(Move dir, Position pos) {
		super(Messages.OFF_WORLD_MESSAGE.formatted(dir.toString(), pos.toString()));
	}
}