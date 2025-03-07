package tp1.control.commands;

import tp1.control.commands.exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {
	// Para el comando sin parametros
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		// Determina el comando segun commandWords
		Command com = null;
		if (this.matchCommandName(commandWords[0])) {
			if (commandWords.length == 1) {
				com = this;
			} else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		}
		return com;
	}
	
}
