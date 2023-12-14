package tp1.logic.exceptions;

import tp1.logic.Move;
import tp1.view.Messages;

// Error se produce al inicializar con una configuracion custom

public class NotAllowedMoveException extends GameModelException {
	public NotAllowedMoveException(Move dir) {
		super(Messages.DIRECTION_ERROR + dir.toString());
	}
}