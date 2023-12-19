package tp1.logic.exceptions;

import tp1.view.Messages;

/**
 * 
 * Clase que representa la excepcion que se produce al lanzar laser y ya hay uno en tablero
 *
 */

public class LaserInFlightException extends GameModelException {
	public LaserInFlightException() {
		super(Messages.LASER_ALREADY_SHOT);
	}
}