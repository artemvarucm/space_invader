package tp1.logic.exceptions;

// Error se produce al inicializar con una configuracion custom

public class GameModelException extends Exception {
	public GameModelException(String msg) {
		super(msg);
	}
}