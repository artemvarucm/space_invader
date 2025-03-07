package tp1.control.commands;

import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.control.commands.exceptions.CommandParseException;
import tp1.logic.GameModel;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	  protected abstract String getName();
	  protected abstract String getShortcut();
	  protected abstract String getDetails();
	  protected abstract String getHelp();
	  
	  /**
		 * Execute the command.
		 * 
		 * @param game Where to execute the command.
		 * 
		 * @return true if the board must be printed
		 */
	  public abstract boolean execute(GameModel game) throws CommandExecuteException;	 
	  
	  public abstract Command parse(String[] commandWords) throws CommandParseException;
	  
	  protected boolean matchCommandName(String name) {
		    return getShortcut().equalsIgnoreCase(name) || 
		        getName().equalsIgnoreCase(name);
	  }
	  
	  public String helpText(){
	    return getDetails() + " : " + getHelp() + "\n";
	  }
}
