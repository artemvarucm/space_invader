package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class ShipFactory {
	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(
		new RegularAlien(),
		new DestroyerAlien(),
		new ExplosiveAlien()
	);
	
	public static AlienShip spawnAlienShip(String input, int speed, GameWorld game, Position pos, AlienManager am) {
		AlienShip alien = null;
		for (AlienShip a: AVAILABLE_ALIEN_SHIPS) {
			// Intenta convertir al alien
			if (alien == null) {
				if (a.getSymbol().equalsIgnoreCase(input)) {
					// Si el simbolo coincide con el input
					alien = a.copy(game, speed, pos, am);
				}
			}
		}
		return alien;
	}
	
}
