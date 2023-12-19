package tp1.control.commands.exceptions;


/**
 * 
 * Clase que representa la excepcion que se produce al ejecutar el comando
 *
 */

public class CommandExecuteException extends Exception {
	public CommandExecuteException(String msg) {
		super(msg);
	}
	
	public CommandExecuteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}