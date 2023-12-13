package tp1.control.commands;

import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class NoneCommand extends NoParamsCommand{
		// Saltamos un ciclo
		  		
		@Override
		public boolean execute(GameModel game) throws CommandExecuteException {
			return true;
		}

		@Override
		protected String getName() {
			return Messages.COMMAND_NONE_NAME;
		}

		@Override
		protected String getShortcut() {
			return Messages.COMMAND_NONE_SHORTCUT;
		}

		@Override
		protected String getDetails() {
			return Messages.COMMAND_NONE_DETAILS;
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_NONE_HELP;
		}
		@Override
		protected boolean matchCommandName(String name) {
			// Porque debe funcionar con comando vacio tambien
		    return super.matchCommandName(name) || name.isBlank();
	  }

	}
