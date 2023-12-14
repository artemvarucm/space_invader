package tp1.control.commands.exceptions;

// Error se produce al parsear el comando

public class CommandExecuteException extends Exception {
	public CommandExecuteException(String msg) {
		super(msg);
	}
	
	public CommandExecuteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}