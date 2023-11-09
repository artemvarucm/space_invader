package tp1.logic;

import java.util.Random;
import tp1.logic.lists.*;
import tp1.logic.gameobjects.*;

public class Game implements GameStatus {
	public static final int DIM_X = 9; // COLUMNAS
	public static final int DIM_Y = 8; // FILAS

	private int currentCycle;
	private Random rand;
	private Level level;
	private long seed;
	private UCMShip player;
	private GameObjectContainer container;
	private boolean doExit;
	private boolean reset;
	private AlienManager alienManager;
	
	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		initGame();
	}
	
	private void initGame () {	
		rand = new Random(seed);
		doExit = false;
		currentCycle = 0;
		
		alienManager = new AlienManager(this, level);
		container = alienManager.initialize();
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}

	
	@Override
	public String infoToString() {
		// Devuelve el estado del jugador en formato String
		return player.stateToString();
	}

	public String stateToString() {
		// Devuelve el estado del jugador en formato String
		return player.stateToString();
	}
	
	public String positionToString(int col, int row) {
		// Devuelve lo que hay que mostrar por pantalla para la posicion (col, row)
		return container.toString(new Position(col, row));	
	}
	
	public void update() {
		if (reset) {
			// Reseteamos partida
			initGame();
			reset = false;
		} else {
			// Ejecuta un ciclo del juego
			// Incrementamos contador de ciclos
			currentCycle++;
			// Realizamos computer actions
			container.computerActions();
			// Realizamos moviemiento de objetos
			container.automaticMoves();
			// Eliminamos objetos muertos en las listas
			//removeDead();
		}
	}
	
	/*public void automaticMoves() {
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
	}*/
	
	public void performAttack(UCMLaser laser) {
		// Realiza el ataque del laser
		container.checkAttacks(laser);
	}
	
	public void performAttack(Bomb bomb) {
		// Realiza el ataque de la bomba
		container.checkAttacks(bomb);
	}
	
	public boolean move(Move move) {
		// Mueve player(UCMShip)
		return player.move(move);
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
		return false;
		//return player.executeShockWave(this, regularAliens, destroyerAliens);
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
	
	public void addObject(GameObject object) {
		// Anadimos laser al juego
		container.add(object);
	}
	
	public void exit() {
		// Para salir del juego
		doExit = true;
	}
	
	public void printGameObjectsList() {
		// Imprime la lista de las naves con su descripcion
		StringBuilder str = new StringBuilder();
		// str.append(Messages.AVAILABLE_SHIPS).append(NEW_LINE);
		// FIXME: reemplazar por \n NEW_LINE
		str.append(UCMShip.getInfo()).append("\n");
		str.append(RegularAlien.getInfo()).append("\n");					
		str.append(DestroyerAlien.getInfo()).append("\n");
		str.append(Ufo.getInfo()).append("\n");
		System.out.print(str.toString());
	}
	
	public void reset() {		
		this.reset = true;
	}
}
