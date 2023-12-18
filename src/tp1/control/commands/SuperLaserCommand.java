package tp1.control.commands;

import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.logic.exceptions.GameModelException;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand{
		
		@Override
		public boolean execute(GameModel game) throws CommandExecuteException {
			// Dispara super laser
			try {
				game.shootSuperLaser();
			} catch (GameModelException e) {
				// No puede disparar
				throw new CommandExecuteException(Messages.SUPER_LASER_ERROR, e);
			}
			return true;
		}

		@Override
		protected String getName() {
			return Messages.COMMAND_SUPER_LASER_NAME;
		}

		@Override
		protected String getShortcut() {
			return Messages.COMMAND_SUPER_LASER_SHORTCUT;
		}

		@Override
		protected String getDetails() {
			return Messages.COMMAND_SUPER_LASER_DETAILS;
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_SUPER_LASER_HELP;
		}

	}
