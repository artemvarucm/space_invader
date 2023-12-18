package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.exceptions.InitializationException;
import tp1.view.Messages;

public class ShipFactory {
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
		new RegularAlien(),
		new DestroyerAlien(),
		new ExplosiveAlien()
	);
	
	public static AlienShip spawnAlienShip(String input, int speed, GameWorld game, Position pos, AlienManager am) throws InitializationException {
		if (!pos.isOnBoard()) {
			throw new InitializationException(Messages.OFF_WORLD_POSITION.formatted(pos.toString()));
		}
		
		for (AlienShip a: AVAILABLE_ALIEN_SHIPS) {
			// Intenta convertir al alien
			if (a.getSymbol().equalsIgnoreCase(input)) {
				// Si el simbolo coincide con el input
				return a.copy(game, speed, pos, am);
			}
		}
		
		// si no ha devuelto nada, error
		throw new InitializationException(Messages.UNKNOWN_SHIP.formatted(input));
	}
	
}
