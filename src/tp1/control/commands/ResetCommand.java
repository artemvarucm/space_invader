package tp1.control.commands;

import tp1.control.ExecutionResult;
import tp1.control.InitialConfiguration;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends Command{
		private InitialConfiguration config;
		public ResetCommand() {
			this(InitialConfiguration.NONE);
		}
	
		protected ResetCommand(InitialConfiguration conf) {
			this.config = conf;
		}
		  		
		@Override
		public ExecutionResult execute(GameModel game) {
			game.reset(config);
			return new ExecutionResult(true);
		}

		@Override
		protected String getName() {
			return Messages.COMMAND_RESET_NAME;
		}

		@Override
		protected String getShortcut() {
			return Messages.COMMAND_RESET_SHORTCUT;
		}

		@Override
		protected String getDetails() {
			return Messages.COMMAND_RESET_DETAILS + " <" + InitialConfiguration.all("|") + ">";
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_RESET_HELP;
		}
		
		@Override
		public Command parse(String[] commandWords) {
			// Devuelve el tipo de reset seleccionado
			Command com = null;
			
			if (commandWords.length == 1) {
				// reset sin parametro
				com = new ResetCommand();
			} else if (commandWords.length == 2 && this.matchCommandName(commandWords[0].toLowerCase())) {
			    InitialConfiguration conf;
			    conf = InitialConfiguration.valueOfIgnoreCase(commandWords[1].toUpperCase());
				
				if (conf == null) {
					// Error en la direccion
					System.out.println(Messages.RESET_ERROR + commandWords[1]);
				} else {
					// Devolvemos el nuevo comando creado
					com = new ResetCommand(conf);
				}
			}
			return com;
		}

	}
