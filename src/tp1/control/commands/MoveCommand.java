package tp1.control.commands;

import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.control.commands.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.logic.exceptions.GameModelException;
import tp1.logic.exceptions.NotAllowedMoveException;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}
	
	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		// Realiza el movimiento de la UCMShip
		try {
			game.move(move);
		} catch(NotAllowedMoveException e) {
			// Direccion incorrecta
			throw new CommandExecuteException(Messages.DIRECTION_ERROR + move.toString(), e);
		} catch (GameModelException e){
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, e);
		}
		return true;
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
	public Command parse(String[] commandWords) throws CommandParseException {
		// Vemos si player ha ordenado el comando move
		Command com = null;
		if (this.matchCommandName(commandWords[0])) {
			if (commandWords.length == 2) {
				try {
					// Convertimos a letras mayusculas
					Move dir = Move.valueOf(commandWords[1].toUpperCase());
					
					com = new MoveCommand(dir);
				} catch (IllegalArgumentException e) {
					throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1] + "\n" + Messages.ALLOWED_MOVES_MESSAGE);
				}
			} else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		}
		return com;
	}
}
