package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new ExitCommand(),
		new ListCommand(),
		new NoneCommand(),
		new ResetCommand(),
		new ShockWaveCommand(),
		new ShootCommand()
	);

	public static Command parse(String[] commandWords) {		
		Command command = null;
		// FIXME: reeemplazar por while command != null
		for (Command c: availableCommands) {
			// Intenta convertir al comando
			if (command == null) {
				command = c.parse(commandWords);
			}
		}
		return command;
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();	
		for (Command c: availableCommands) {
			//TODO fill with your code
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
