package tp1.logic.exceptions;

import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

// Error se produce al inicializar con una configuracion custom

public class OffWorldException extends GameModelException {
	public OffWorldException(Move dir, Position pos) {
		super(Messages.OFF_WORLD_MESSAGE.formatted(dir.toString(), pos.toString()));
	}
}