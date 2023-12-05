package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
		new HelpCommand(),
		new MoveCommand(),
		new NoneCommand(),
		new ShootCommand(),
		new ShockWaveCommand(),
		new ListCommand(),
		new ExitCommand(),
		new ResetCommand(),
		new SuperLaserCommand()
	);

	public static Command parse(String[] commandWords) {		
		Command command = null;
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
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}
