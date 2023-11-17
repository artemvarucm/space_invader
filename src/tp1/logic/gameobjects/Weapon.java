package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {
	protected Move dir;
	public Weapon(Move dir, Game game, Position pos, int armor) {
		super(game, pos, armor);
		this.dir = dir;
	}
}
