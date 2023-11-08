package tp1.view;


import static tp1.util.MyStringUtils.repeat;
import tp1.logic.Game;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.util.MyStringUtils;


import tp1.logic.GameStatus;

public abstract class GamePrinter {
	
	protected GameStatus game;
	
	public GamePrinter(GameStatus game) {
		this.game = game;
	}

	public abstract String endMessage();
	
}

