package tp1.logic.exceptions;

import tp1.view.Messages;

// Error se produce al inicializar con una configuracion custom

public class NotAllowedMoveException extends GameModelException {
	public NotAllowedMoveException() {
		super(Messages.ALLOWED_MOVES_MESSAGE);
	}
}