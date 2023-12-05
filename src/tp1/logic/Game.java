package tp1.logic;

import java.util.Random;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.logic.gameobjects.Weapon;

public class Game implements GameStatus, GameModel, GameWorld {
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
	private boolean cycleDescend; // en este ciclo van a bajar los aliens
	private InitialConfiguration config;
	
	public Game(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.config = InitialConfiguration.NONE;
		initGame();
	}
	
	private void initGame () {	
		rand = new Random(seed);
		doExit = false;
		currentCycle = 0;
		cycleDescend = false;
		
		alienManager = new AlienManager(this, level);
		container = alienManager.initialize(config);
		player = new UCMShip(this, new Position(DIM_X / 2, DIM_Y - 1));
		container.add(player);
	}


	@Override
	public String stateToString() {
		// Devuelve el estado del jugador en formato String
		return player.stateToString();
	}
	
	@Override
	public String positionToString(int col, int row) {
		// Devuelve lo que hay que mostrar por pantalla para la posicion (col, row)
		return container.toString(new Position(col, row));	
	}
	
	@Override
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
			
			cycleDescend = alienManager.onBorder(); // bajaremos o no en este ciclo (se usa en disparo del laser)
			// Realizamos moviemiento de objetos
			container.automaticMoves();
			// ciclo en el que bajan se acabo
			cycleDescend = false;
		}
	}
	
	@Override
	public void performAttack(Weapon weapon) {
		// Realiza el ataque de weapon
		container.checkAttacks(weapon);
	}
	
	@Override
	public boolean move(Move move) {
		// Mueve player(UCMShip)
		return player.move(move);
	}
	
	@Override
	public void receivePoints(int points) {
		// Recibe puntos
		player.receivePoints(points);
	}
	
	@Override
	public boolean shootLaser() {
		// Ejecuta disparo del laser
		return player.shootLaser(this);
	}
	
	@Override
	public boolean shootSuperLaser() {
		// Ejecuta disparo del super laser
		return player.shootSuperLaser(this);
	}
	
	@Override
	public void explosiveAttack(Position pos, int damage) {
		// realiza la explosion de la nave en la posicion pos, quitando danio damage a posiciones adyacentes
		int minX = pos.getCol() - 1 >= 0 ? pos.getCol() - 1 : 0;
		int maxX = pos.getCol() + 1 < DIM_X ? pos.getCol() + 1 : DIM_X - 1;
		
		int minY = pos.getRow() - 1 >= 0 ? pos.getRow() - 1 : 0;
		int maxY = pos.getRow() + 1 < DIM_Y ? pos.getRow() + 1 : DIM_Y - 1;
		
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				container.explotion(new Position(i,j), damage);
			}
		}

	}
	
	@Override
	public boolean shockWave() {
		// Ejecuta shockwave
		return player.executeShockWave(this);
	}

	@Override
	public int getCycle() {
		// Devuelve ciclo actual
		return currentCycle;
	}

	@Override
	public int getRemainingAliens() {
		// Devuelve numero de aliens restantes
		return alienManager.getRemainingAliens();
	}
	
	@Override
	public boolean playerWin() {
		// Devuelve true si el jugador ha ganado
		return player.isAlive() && alienManager.allAlienDead();
	}

	@Override
	public boolean aliensWin() {
		// Devuelve true si aliens han ganado
		return !player.isAlive() || alienManager.haveLanded();
	}
	
	@Override
	public boolean isFinished() {
		// Devuelve true si el juego ha terminado
		return doExit || playerWin() || aliensWin();
	}

	@Override
	public void enableLaser() {
		// Habilitamos laser
		player.enableLaser();
	}
	
	@Override
	public void enableShockWave() {
		// Habilitamos shockWave (despues de matar al ufo)
		player.enableShockWave();
	}

	@Override
	public Random getRandom() {
		// Devuelve el random
		return this.rand;
	}

	@Override
	public Level getLevel() {
		// Devuelve el nivel
		return this.level;
	}
	
	@Override
	public void addObject(GameObject object) {
		// Anadimos objeto al juego
		container.add(object);
	}
	
	@Override
	public void exit() {
		// Para salir del juego
		doExit = true;
	}
	
	@Override
	public void printGameObjectsList() {
		// Imprime la lista de las naves con su descripcion
		StringBuilder str = new StringBuilder();
		// str.append(Messages.AVAILABLE_SHIPS).append(NEW_LINE);
		str.append(UCMShip.getInfo()).append("\n");
		str.append(RegularAlien.getInfo()).append("\n");					
		str.append(DestroyerAlien.getInfo()).append("\n");
		str.append(Ufo.getInfo()).append("\n");
		str.append(ExplosiveAlien.getInfo()).append("\n");
		System.out.print(str.toString());
	}
	
	@Override
	public void reset(InitialConfiguration config) {
		// Reseteamos configuracion y partida
		this.config = config;
		this.reset = true;
	}
	
	@Override
	public boolean isCycleDescend() {
		return cycleDescend;
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
}
