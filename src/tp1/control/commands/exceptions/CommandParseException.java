package tp1.control.commands.exceptions;

// Error se produce al parsear el comando

public class CommandParseException extends Exception {
	public CommandParseException(String msg) {
		super(msg);
	}
}