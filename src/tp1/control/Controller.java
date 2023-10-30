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
		boolean skipUpdate = false;
		while(!game.isFinished()) {
			if (!skipUpdate) {
				printGame();
			}
			String[] comandos = prompt();
			skipUpdate = action(comandos);
			
			if (!skipUpdate) {
				game.update();
			}
		}
		printGame();
		printEndMessage();
	}
	
	public boolean action(String[] comandos) {
		boolean skipUpdate = false;
		if (comandos.length == 1) {
			switch(comandos[0].toLowerCase()) {
				case "s":
				case "shoot": {
						if (!game.shootLaser()) {
							System.out.println(Messages.LASER_ERROR);
							skipUpdate = true;
						}
					}
					break;
				case "w":
				case "shockWave": {
						if (!game.shockWave()) {
							System.out.println(Messages.SHOCKWAVE_ERROR);
							skipUpdate = true;
						}
					}
					break;
				case "l":
				case "list": {
						printGameObjectsList();
						skipUpdate = true;
					}
					break;
				case "h":
				case "help": {
					System.out.println(Messages.HELP);
					skipUpdate = true;
				}
					break;
				case "r":
				case "reset": {
					game.reset();
					printGame();
					skipUpdate = true;
				}
					break;
				case "e":
				case "exit": {
					System.out.println(Messages.GAME_OVER);
					game.exit();
					skipUpdate = true;
				}
					break;
				case "":
				case "n":
				case "none": {
					// NO HACER NADA
				}
					break;
				default:
					System.out.println(Messages.UNKNOWN_COMMAND);
					skipUpdate = true;
			}
		} else {
			if (comandos.length > 2) {
				System.out.println(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			} else if (comandos[0].toLowerCase().equals("move") || comandos[0].toLowerCase().equals("m")) {
				if (!game.move(comandos[1].toUpperCase())) {
					skipUpdate = true;
				}
			} else {
				System.out.println(Messages.UNKNOWN_COMMAND);
				skipUpdate = true;
			}
		
		}
		return skipUpdate;
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
