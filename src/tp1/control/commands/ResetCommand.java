package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.control.InitialConfiguration;
import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.control.commands.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.logic.exceptions.InitializationException;
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
		public boolean execute(GameModel game) throws CommandExecuteException {
			// Reinicia el juego o bien con la configuracion por defecto(NONE)
			// o bien cargandolo desde el archivo especificado
			try {
				game.reset(config);
			} catch (InitializationException e) {
				throw new CommandExecuteException(Messages.INVALID_INI_CONF, e);
			}

			return true;
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
			return Messages.COMMAND_RESET_DETAILS;
		}

		@Override
		protected String getHelp() {
			return Messages.COMMAND_RESET_HELP;
		}
		
		@Override
		public Command parse(String[] commandWords) throws CommandParseException {
			// Reset vacio o del fichero seleccionado
			Command com = null;
			
			if (this.matchCommandName(commandWords[0])) {
				if (commandWords.length == 1) {
					// reset sin parametro
					com = new ResetCommand(); 
				} else if (commandWords.length == 2) {
					try {
						InitialConfiguration conf;
					    conf = InitialConfiguration.readFromFile(commandWords[1]);
						
					    com = new ResetCommand(conf);
					
					} catch(FileNotFoundException e) {
						throw new CommandParseException(Messages.FILE_NOT_FOUND.formatted(commandWords[1]));
					} catch(IOException e) {
						// FIXME - esta correcto
						throw new CommandParseException(Messages.READ_ERROR.formatted(e.getMessage()));
					}
				} else {
					throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
			}
			return com;
		}

	}
