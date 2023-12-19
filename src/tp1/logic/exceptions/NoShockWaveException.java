package tp1.logic.exceptions;

import tp1.view.Messages;

/**
 * 
 * Clase que representa la excepcion que se produce al intentar activar shockwave cuando no lo tienes
 *
 */

public class NoShockWaveException extends GameModelException {
	public NoShockWaveException() {
		super(Messages.NO_SHOCKWAVE_ERROR);
	}
}