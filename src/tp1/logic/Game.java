package tp1.logic;

import java.util.Random;
import tp1.logic.lists.*;
import tp1.logic.gameobjects.*;

/*
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;


public class Game implements GameStatus {

	//TODO fill with your code

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;
	
	private GameObjectContainer container;
	private UCMShip player;
	private AlienManager alienManager;
	private int currentCycle;
	
	//TODO fill with your code

	public Game (Level level, long seed){
		//TODO fill with your code
		initGame();
	}
		
	private void initGame () {	
		//TODO fill with your code
		this.container = alienManager.initialize();
		this.player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		//container.add(player);
	}

	//CONTROL METHODS
	
	public boolean isFinished() {
		// TODO fill with your code
		return false;
	}

	public void exit() {
		// TODO fill with your code
	}

	public void update() {
	    this.currentCycle++;
	    this.container.computerActions();
	    this.container.automaticMoves();
	}

	// TODO fill with your code

	//CALLBACK METHODS
	
	public void addObject(GameObject object) {
	    this.container.add(object);
	}

	// TODO fill with your code
	
	//VIEW METHODS
	
	public String positionToString(int col, int row) {
		return null;
		//return container.toString(new Position(col, row));
	}
	
	

	@Override
	public String infoToString() {
		// TODO fill with your code
		return null;
	}

	@Override
	public String stateToString() {
		// TODO fill with your code
		return null;
	}

	@Override
	public boolean playerWin() {
		// TODO fill with your code
		return false;
	}

	@Override
	public boolean aliensWin() {
		// TODO fill with your code
		return false;
	}

	@Override
	public int getCycle() {
		// TODO fill with your code
		return 0;
	}

	@Override
	public int getRemainingAliens() {
		// TODO fill with your code
		return 0;
	}

}

 * 
 * */

public class Game  {
	public static final int DIM_X = 9; // COLUMNAS
	public static final int DIM_Y = 8; // FILAS

	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private RegularAlienList regularAliens;
	private DestroyerAlienList destroyerAliens;
	private BombList bombs;
	private UCMShip player;
	private UCMLaser laser;
	private Ufo ufo;
	private boolean doExit;
	private AlienManager alienManager;
	
	public Game(Level level, long seed) {
		this.level = level;
		this.doExit = false;
		this.currentCycle = 0;
		this.seed = seed;
		this.player = new UCMShip(this);
		this.laser = new UCMLaser(this, player.getPos(), false);
		this.alienManager = new AlienManager(this, level);
		this.ufo = new Ufo(this);
		this.bombs = new BombList();
		this.regularAliens = alienManager.initializeRegularAliens();
		this.destroyerAliens = alienManager.initializeDestroyerAliens();
		this.rand = new Random(seed);
	}

	public String stateToString() {
		// Devuelve el estado del jugador en formato String
		return player.stateToString();
	}
	
	public String positionToString(int col, int row) {
		// Devuelve lo que hay que mostrar por pantalla para la posicion (col, row)
		StringBuilder str = new StringBuilder();
		Position pos = new Position(col, row);
		
		str.append(player.toString(pos));
		str.append(regularAliens.toString(pos));
		str.append(destroyerAliens.toString(pos));
		str.append(laser.toString(pos));
		str.append(bombs.toString(pos));
		str.append(ufo.toString(pos));
		
		return str.toString();
	}
	
	public void update() {
		// Ejecuta un ciclo del juego
		// Incrementamos contador de ciclos
		currentCycle++;
		// Realizamos computer actions
		computerActions();
		// Realizamos moviemiento de objetos
		automaticMoves();
		// Eliminamos objetos muertos en las listas
		removeDead();
	}
	
	public void computerActions() {
		// Realizamos computer actions
		regularAliens.computerActions();
		bombs.computerActions();
		destroyerAliens.computerActions();
		ufo.computerAction();
	}
	
	public void automaticMoves() {
		// Realizamos moviemiento de objetos
		
		// En este ciclo los aliens se moveran hacia abajo, 
		// necesitamos ver si el laser puede atacarles despues de moverlos
		boolean isDescending = alienManager.onBorder(); 
		
		regularAliens.automaticMoves();
		destroyerAliens.automaticMoves();
		if (isDescending) {
			// Atacamos con el laser, para que no se entrecruze la nave y el laser,
			// y sigan vivos, al enfrentarse
			performAttack(laser);
		}
		
		ufo.automaticMove();
		bombs.automaticMoves();
		laser.automaticMove();
	}
	
	public void performAttack(UCMLaser laser) {
		// Realiza el ataque del laser
		regularAliens.checkAttacks(laser);
		destroyerAliens.checkAttacks(laser);
		bombs.checkAttacks(laser);
		laser.performAttack(ufo);
	}
	
	public void performAttack(Bomb bomb) {
		// Realiza el ataque de la bomba
		bomb.performAttack(player);
		bomb.performAttack(laser);
	}
	
	public boolean move(String direction) {
		// Mueve player(UCMShip)
		return player.move(direction);
	}
	
	public void receivePoints(int points) {
		// Recibe puntos
		player.receivePoints(points);
	}
	
	public boolean shootLaser() {
		// Ejecuta disparo del laser
		return player.shootLaser(this);
	}
	
	public boolean shockWave() {
		// Ejecuta shockwave
		return player.executeShockWave(this, regularAliens, destroyerAliens);
	}

	public int getCycle() {
		// Devuelve ciclo actual
		return currentCycle;
	}

	public int getRemainingAliens() {
		// Devuelve numero de aliens restantes
		return alienManager.getRemainingAliens();
	}
	
	public boolean playerWin() {
		// Devuelve true si el jugador ha ganado
		return player.isAlive() && alienManager.allAlienDead();
	}

	public boolean aliensWin() {
		// Devuelve true si aliens han ganado
		return !player.isAlive() || alienManager.haveLanded();
	}
	
	public boolean isFinished() {
		// Devuelve true si el juego ha terminado
		return doExit || playerWin() || aliensWin();
	}

	public void enableLaser() {
		// Inhabilitamos laser
		player.enableLaser();
	}
	
	public void enableShockWave() {
		// Inhabilitamos shockWave (despues de matar al ufo)
		player.enableShockWave();
	}

	public Random getRandom() {
		// Devuelve el random
		return this.rand;
	}

	public Level getLevel() {
		// Devuelve el nivel
		return this.level;
	}
	
	public void removeDead() {
		// Elimina muertos de las listas
		regularAliens.removeDead();
		destroyerAliens.removeDead();
		bombs.removeDead();
	}
	
	public void addObject(UCMLaser laser) {
		// Anadimos laser al juego
		this.laser = laser;
	}
	
	public void addObject(Bomb bomb) {
		// Anadimos bomba a la lista
		bombs.add(bomb);
	}
	
	public void exit() {
		// Para salir del juego
		doExit = true;
	}
	
	public void reset() {		
		// Reseteamos partida
		this.currentCycle = 0;
		this.doExit = false;
		this.rand = new Random(seed);
		this.player = new UCMShip(this);
		this.laser = new UCMLaser(this, player.getPos(), false);
		this.alienManager = new AlienManager(this, level);
		this.ufo = new Ufo(this);
		this.bombs = new BombList();
		this.regularAliens = alienManager.initializeRegularAliens();
		this.destroyerAliens = alienManager.initializeDestroyerAliens();
	}
}
