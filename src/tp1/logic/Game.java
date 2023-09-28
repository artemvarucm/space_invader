package tp1.logic;

import java.util.Random;
import tp1.logic.lists.*;
import tp1.logic.gameobjects.*;

// TODO implementarlo
public class Game {
	private static final String NEW_LINE = System.lineSeparator();

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	//TODO fill your code
	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private RegularAlienList regularAliens;
	//private DestroyerAlienList destroyerAliens;
	//private BombList bombs;
	private UCMShip player;
	private UCMLaser laser;
	private Ufo ufo;
	private boolean doExit;
	private AlienManager alienManager;
	
	public Game(Level level, long seed) {
		//TODO fill your code
		this.player = new UCMShip();
	}

	public String stateToString() {
		//TODO fill your code
		StringBuilder result = new StringBuilder();
		result.append("Life: ").append(player.getLife()).append(NEW_LINE);
		result.append("Points: ").append(player.getPoints()).append(NEW_LINE);
		String shock = "OFF"; // ShockWave class
		if (player.hasShockWave()) {
			shock = "ON";
		}
		result.append("ShockWave: ").append(shock).append(NEW_LINE);
		
		return result.toString();
	}

	public int getCycle() {
		//TODO fill your code
		return 0;
	}

	public int getRemainingAliens() {
		//TODO fill your code
		return 0;
	}

	public String positionToString(int col, int row) {
		//TODO fill your code
		return "";
	}

	public boolean playerWin() {
		//TODO fill your code
		return false;
	}

	public boolean aliensWin() {
		//TODO fill your code
		return false;
	}

	public void enableLaser() {
		//TODO fill your code		
	}

	public Random getRandom() {
		//TODO fill your code
		return null;
	}

	public Level getLevel() {
		//TODO fill your code
		return null;
	}

}
