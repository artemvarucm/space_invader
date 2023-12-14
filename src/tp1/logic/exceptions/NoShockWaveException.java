package tp1.logic.exceptions;

import tp1.view.Messages;

// Error se produce al inicializar con una configuracion custom

public class NoShockWaveException extends GameModelException {
	public NoShockWaveException() {
		super(Messages.NO_SHOCKWAVE_ERROR);
	}
}