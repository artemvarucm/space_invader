package tp1.logic;

import java.util.Random;
import tp1.logic.lists.*;
import tp1.logic.gameobjects.*;

// TODO implementarlo
public class Game {
	public static final int DIM_X = 9; // COLUMNAS
	public static final int DIM_Y = 8; // FILAS

	//TODO fill your code
	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private RegularAlienList regularAliens;
	private DestroyerAlienList destroyerAliens;
	//private BombList bombs;
	private UCMShip player;
	private UCMLaser laser;
	private Ufo ufo;
	private boolean doExit;
	private AlienManager alienManager;
	
	public Game(Level level, long seed) {
		//TODO fill your code
		this.level = level;
		this.currentCycle = 0;
		this.player = new UCMShip(this);
		this.laser = new UCMLaser(this, player.getPos(), false);
		this.alienManager = new AlienManager(this, level);
		this.ufo = new Ufo(this);
		this.regularAliens = alienManager.initializeRegularAliens();
		this.destroyerAliens = alienManager.initializeDestroyerAliens();
		this.rand = new Random(seed);
	}

	public String stateToString() {
		//TODO fill your code
		StringBuilder result = new StringBuilder();
		result.append(player.stateToString());
		
		return result.toString();
	}
	
	public String positionToString(int col, int row) {
		StringBuilder str = new StringBuilder();
		Position pos = new Position(col, row);
		
		str.append(player.toString(pos));
		str.append(regularAliens.toString(pos));
		str.append(destroyerAliens.toString(pos));
		str.append(laser.toString(pos));
		str.append(ufo.toString(pos));
		
		return str.toString();
	}
	
	public void update() {
		currentCycle++;
		computerActions();
		automaticMoves();
		// despues de que todos se han movido, vemos si el lase coincide con alguien en la posicion
		performAttack(laser);
		removeDead();
	}
	
	public void computerActions() {
		// FIXME anadir computer actions de todos
		ufo.computerAction();
	}
	
	public void automaticMoves() {
		// movemos el laser, intentamos matar a alguien
		laser.automaticMove();
		regularAliens.automaticMoves();
		destroyerAliens.automaticMoves();
		ufo.automaticMove();
	}
	
	public void performAttack(UCMLaser laser) {
		regularAliens.checkAttacks(laser);
		destroyerAliens.checkAttacks(laser);
		if (laser.performAttack(ufo)) {
			enableShockWave();
		}
	}
	
	public boolean move(String direction) {
		// Mueve player(UCMShip)
		return player.move(direction);
	}
	
	public void receivePoints(int points) {
		player.receivePoints(points);
	}
	
	public boolean shootLaser() {
		return player.shootLaser(this);
	}
	
	public boolean shockWave() {
		return player.executeShockWave(this, regularAliens, destroyerAliens);
	}

	public int getCycle() {
		//TODO fill your code
		return currentCycle;
	}

	public int getRemainingAliens() {
		//TODO fill your code
		return alienManager.getRemainingAliens();
	}
	
	public boolean playerWin() {
		//TODO fill your code
		return player.isAlive() && alienManager.allAlienDead();
	}

	public boolean aliensWin() {
		//TODO fill your code
		return !player.isAlive() || alienManager.haveLanded();
	}
	
	public boolean isFinished() {
		return doExit || playerWin() || aliensWin();
	}

	public void enableLaser() {
		//TODO fill your code
		player.enableLaser();
	}
	
	public void enableShockWave() {
		//TODO fill your code
		player.enableShockWave();
	}

	public Random getRandom() {
		//TODO fill your code
		return this.rand;
	}

	public Level getLevel() {
		//TODO fill your code
		return this.level;
	}
	
	public void removeDead() {
		regularAliens.removeDead();
		destroyerAliens.removeDead();
	}
	
	public void addObject(UCMLaser laser) {
		this.laser = laser;
	}
	
	public void exit() {
		doExit = true;
	}
	
	public void reset() {		
		this.currentCycle = 0;
		this.player = new UCMShip(this);
		this.laser = new UCMLaser(this, player.getPos(), false);
		this.alienManager = new AlienManager(this, level);
		this.ufo = new Ufo(this);
		this.regularAliens = alienManager.initializeRegularAliens();
		this.destroyerAliens = alienManager.initializeDestroyerAliens();
		this.rand = new Random(seed);
	}
}
