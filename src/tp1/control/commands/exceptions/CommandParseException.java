package tp1.control.commands.exceptions;

/**
 * 
 * Clase que representa la excepcion que se produce al parsear el comando
 *
 */

public class CommandParseException extends Exception {
	public CommandParseException(String msg) {
		super(msg);
	}
}