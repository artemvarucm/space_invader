package tp1.logic.exceptions;

// Error se produce al inicializar con una configuracion custom

public class InitializationException extends GameModelException {
	public InitializationException(String msg) {
		super(msg);
	}
}