package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.logic.Game;
import tp1.view.BoardPrinter;
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
		printer = new BoardPrinter(game);
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
	
	/*
	 public void run() {

		printGame();

		while (!game.isFinished()) {
			String[] parameters = prompt();

			Command command = CommandGenerator.parse(parameters);

			if (command != null) {
				ExecutionResult result = command.execute(game);
				if (result.success()) {
					if (result.draw())
						printGame();
				} 
				else
					System.out.println(result.errorMessage());
			} else {
				System.out.println(Messages.UNKNOWN_COMMAND);
			}
		}

		printEndMessage();
	}
	 * 
	 * */

	/**
	 * Runs the game logic
	 */
	public void run() {
		// Bucle del juego
		// este booleano se encarga determinar si aplicar el update y mostrar tablero
		boolean skipUpdate = false;
		// Mientras el juego no ha acabado
		while(!game.isFinished()) {
			if (!skipUpdate) {
				// Mostramos por pantalla el estado de juego (tablero)
				printGame();
			}
			String[] comandos = prompt();
			// Pedimos ordenes al usuario y las ejecutamos
			skipUpdate = action(comandos);
			
			if (!skipUpdate) {
				// Ciclo del juego
				game.update();
			}
		}
		if (!skipUpdate) {
			// Mostramos por pantalla el ultimo estado de juego (tablero)
			printGame();
		}
		// Mostramos mensaje de despedida
		printEndMessage();
	}
	
	public boolean action(String[] comandos) {
		// Si devuelve true, hay que saltarse el update
		boolean skipUpdate = false;
		if (comandos.length == 1) {
			switch(comandos[0].toLowerCase()) {
				case "s":
				case "shoot": {
						// Disparamos el laser
						if (!game.shootLaser()) {
							System.out.println(Messages.LASER_ERROR);
							skipUpdate = true;
						}
					}
					break;
				case "w":
				case "shockWave": {
						// Disparamos el shockwave
						if (!game.shockWave()) {
							System.out.println(Messages.SHOCKWAVE_ERROR);
							skipUpdate = true;
						}
					}
					break;
				case "l":
				case "list": {
						// Muestra la lista de las naves con su descripcion
						printGameObjectsList();
						skipUpdate = true;
					}
					break;
				case "h":
				case "help": {
						// Muestra ayuda de comandos
						System.out.println(Messages.HELP);
						skipUpdate = true;
					}
					break;
				case "r":
				case "reset": {
						// Reseteamos el estado del juego (comenzamos desde el inicio)
						game.reset();
						printGame();
						skipUpdate = true;
					}
					break;
				case "e":
				case "exit": {
						// Salimos del juego
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
					// Comando irreconocible
					System.out.println(Messages.UNKNOWN_COMMAND);
					skipUpdate = true;
			}
		} else {
			if (comandos.length > 2) {
				// No hay comandos con mas de 2 palabras
				System.out.println(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				skipUpdate = true;
			} else if (comandos[0].toLowerCase().equals("move") || comandos[0].toLowerCase().equals("m")) {
				if (!game.move(comandos[1])) {
					// Si el movimiento es invalido, no realizamos update
					skipUpdate = true;
				}
			} else {
				// Comando irreconocible
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
		// Imprime la lista de las naves con su descripcion
		System.out.print(printer.gameObjectsList());
	}
	
}
