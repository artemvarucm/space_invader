package tp1.logic.exceptions;

/**
 * 
 * Clase que representa la excepcion que se produce al inicializar con una configuracion custom
 *
 */

public class InitializationException extends GameModelException {
	public InitializationException(String msg) {
		super(msg);
	}
}