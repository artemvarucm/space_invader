package tp1.logic.exceptions;

import tp1.view.Messages;

// Error se produce al inicializar con una configuracion custom

public class LaserInFlightException extends GameModelException {
	public LaserInFlightException() {
		super(Messages.LASER_ALREADY_SHOT);
	}
}