package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;

public abstract class Ship extends GameObject{

	public Ship(Game game, Position pos, int armor) {
		super(game, pos, armor);
	}
	// anadir abstract getdescription
}
