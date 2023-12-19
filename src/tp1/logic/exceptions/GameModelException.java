package tp1.logic.exceptions;

/**
 * 
 * Clase que representa la excepcion que se produce dentro del Game
 *
 */

public class GameModelException extends Exception {
	public GameModelException(String msg) {
		super(msg);
	}
}