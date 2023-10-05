package tp1.logic;

import java.util.Random;
import tp1.logic.lists.*;
import tp1.logic.gameobjects.*;

// TODO implementarlo
public class Game {
	private static final String NEW_LINE = System.lineSeparator();

	public static final int DIM_X = 9; // COLUMNAS
	public static final int DIM_Y = 8; // FILAS

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
		this.level = level;
		this.player = new UCMShip(this);
		this.alienManager = new AlienManager(this, level);
		this.regularAliens = alienManager.initializeRegularAliens();
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
	
	public void move(String direction) {
		// Mueve player(UCMShip)
		player.move(direction);
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
		StringBuilder str = new StringBuilder();
		if (player.isOnPoisition(col, row)) {
			str.append(player.toString());
		} else {
			boolean encontrado = false;
			int i = 0;
			while (!encontrado && i < regularAliens.size()) {
				encontrado = regularAliens.get(i).isOnPoisition(col, row);
				i++;
			}
			if (encontrado) {
				str.append(regularAliens.get(--i).toString());
			}
		}
		return str.toString();
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
