package tp1.control.commands;

import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand{
		// Dispara super laser
		@Override
		public boolean execute(GameModel game) throws CommandExecuteException {
			game.shootSuperLaser();
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
