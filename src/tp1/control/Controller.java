package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.logic.Game;
import tp1.logic.Move;
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
		while(1==1) {
			printGame();
			String[] comandos = prompt();
			action(comandos);
			// game.action && game.update
		}
	}
	
	public void action(String[] comandos) {
		//if (comandos.length == 1) {
			switch(comandos[0].toLowerCase()) {
				case "m":
				case "move": {
					game.move(comandos[1].toLowerCase());
					}
					break;
				case "s":
				case "shoot": {
						
					}
					break;
				case "w":
				case "shockWave": {
						
					}
					break;
				case "l":
				case "list": {
						
					}
					break;
				case "h":
				case "help": {
					System.out.println("""
						[m]ove <left|lleft|right|rright>: Moves UCM-Ship to the indicated direction.
						[s]hoot: UCM-Ship launches a laser.
						shock[W]ave: UCM-Ship releases a shock wave.
						[l]ist: Prints the list of available ships.
						[r]eset: Starts a new game.
						[h]elp: Prints this help message.
						[e]xit: Terminates the program.
						[n]one: Skips one cycle.
					""");
				}
					break;
				case "r":
				case "reset": {
					
				}
					break;
				case "e":
				case "exit": {
					
				}
					break;
				case "":
				case "n":
				case "none": {
					System.out.println("none");
				}
					break;
				default:
					System.out.println("Comando irreconocido");
			}
		/*} else {
			
		}*/
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
	
}
