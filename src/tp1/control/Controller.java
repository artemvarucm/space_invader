package tp1.control;

import static tp1.view.Messages.debug;
import tp1.logic.gameobjects.RegularAlien;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		//TODO fill your code
		boolean end = false;
		while(!end && !game.playerWin() && !game.aliensWin()) {
			printGame();
			String[] comandos = prompt();
			end = action(comandos);
			game.update();
			// game.action && game.update
		}
		printGame();
		printEndMessage();
	}
	
	public boolean action(String[] comandos) {
		//if (comandos.length == 1) {
			boolean exit = false;
			switch(comandos[0].toLowerCase()) {
				case "m":
				case "move": {
					if (!game.move(comandos[1].toLowerCase())) {
						comandos = prompt();
						action(comandos);
					}
				}
					break;
				case "s":
				case "shoot": {
						if (!game.shootLaser()) {
							System.out.println(Messages.LASER_ERROR);
							comandos = prompt();
							action(comandos);
						}
					}
					break;
				case "w":
				case "shockWave": {
						if (!game.shockWave()) {
							System.out.println(Messages.SHOCKWAVE_ERROR);
							comandos = prompt();
							action(comandos);
						}
					}
					break;
				case "l":
				case "list": {
						printGameObjectsList();
						comandos = prompt();
						action(comandos); // FIXME reemplaar por skip update
					}
					break;
				case "h":
				case "help": {
					System.out.println(Messages.HELP);
					comandos = prompt();
					action(comandos);
				}
					break;
				case "r":
				case "reset": {
					
				}
					break;
				case "e":
				case "exit": {
					exit = true;
				}
					break;
				case "":
				case "n":
				case "none": {
					System.out.println("none");
				}
					break;
				default:
					System.out.println(Messages.UNKNOWN_COMMAND);
			}
		/*} else {
			
		}*/
			return exit;
	}

	/**
	 * Draw / paint the game
	 */
	private void printGame() {
		System.out.println(printer);
	}
	
	/**
	 * Prints the final message once the game is finished
	 */
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	
	public void printGameObjectsList() {
		System.out.println(printer.gameObjectsList());
	}
	
}
