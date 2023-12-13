package tp1.control.commands;

import tp1.control.commands.exceptions.CommandParseException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		Command com = null;
		if (this.matchCommandName(commandWords[0].toLowerCase())) {
			if (commandWords.length == 1) {
				com = this;
			} else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		}
		return com;
	}
	
}
