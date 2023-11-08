package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public ExecutionResult execute(Game game) {
		//TODO fill with your code
		if (!game.move(move)) {
			return new ExecutionResult(Messages.MOVEMENT_ERROR);
		} else {
			return new ExecutionResult(true);
		}
	}


	@Override
	public Command parse(String[] commandWords) {
		Command com = null;
		if (commandWords.length == 2 && this.matchCommandName(commandWords[0].toLowerCase())) {
			boolean validDir = true;
			Move dir = null;
			try {
				// Convertimos a letras mayusculas
				dir = Move.valueOf(commandWords[1].toUpperCase());
				if (dir.equals(Move.UP) || dir.equals(Move.DOWN)) { 
					// No se puede ir arriba o abajo
					validDir = false;
				}
			} catch (IllegalArgumentException e) {
				validDir = false;
			}
			
			if (!validDir) {
				// Error en la direccion
				System.out.println(Messages.DIRECTION_ERROR + commandWords[1]);
			} else {
				// Devolvemos el nuevo comando creado
				com = new MoveCommand(dir);
			}
			
		}
		return com;
	}

}
