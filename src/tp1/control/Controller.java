package tp1.control;

import static tp1.view.Messages.debug;

import java.util.Scanner;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.control.commands.exceptions.CommandExecuteException;
import tp1.control.commands.exceptions.CommandParseException;
import tp1.logic.Game;
import tp1.logic.GameModel;
import tp1.view.BoardPrinter;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */

public class Controller {
	private GameModel game;
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
		String[] words = line.trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	/**
	 * Runs the game logic
	 */
	public void run() {
		// Bucle del juego
		// Imprimimos el estado inicial
		printGame();
		// Mientras el juego no ha acabado
		while(!game.isFinished()) {
			// Pedimos accion al usuario
			String[] comandos = prompt();
			try {
				// Detectamos el caracter del comando solicitado
				Command command = CommandGenerator.parse(comandos);
				// Vemos si tenemos que realizar un ciclo de juego y pintar el tablero
				boolean draw = command.execute(game);
				if (draw) {
					game.update();
					printGame();
				} 
			} catch (CommandParseException | CommandExecuteException e) {
				// Si hay alguna excepcion durante la lectura o ejecucion del comando
	 			System.out.println(e.getMessage());
	 			Throwable cause = e.getCause();
	 			if (cause != null) 
	 			    System.out.println(cause.getMessage());
	 		}
		}
		
		// Mostramos mensaje de despedida
		printEndMessage();
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
