package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */


public class AlienManager {
	
	private Level level;
	private GameWorld game;
	private int remainingAliens;
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(GameWorld game, Level level) {
		this.level = level;
		this.game = game;
	}
	
	public GameObjectContainer initialize(InitialConfiguration config) {
		this.remainingAliens = 0;
		this.onBorder = false;
		GameObjectContainer container = new GameObjectContainer();
		
		// Ufo spawnea independientemente de configuracion
		initializeOvni(container);
		
		if (config.equals(InitialConfiguration.NONE)) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);		
		} else {
			costumedInitialization(container, config);
		}
		
		return container;
	}
	
	private void initializeOvni(GameObjectContainer container) {
		container.add(new Ufo(game));
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	private void initializeRegularAliens(GameObjectContainer container) {
		// Anadimos a la lista los regulars
		
		int num = level.getNumRegularAliens() / 4;  // maximo cuatro en fila (num = 1 en EASY, num = 2 en HARD/INSANE)
		// Lo colocamos en el medio
		int colInitialOffset = Game.DIM_X/2 - 2;
		
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 4; j++) {
				// Insertamos alien
				Position pos = new Position(colInitialOffset + j, RegularAlien.ROW_INI_OFFSET + i);
				RegularAlien templateAlien = new RegularAlien(this, level.getNumCyclesToMoveOneCell(), game, pos);
				container.add(templateAlien);
			}
		}
		// Incrementamos contador de aliens
		remainingAliens += level.getNumRegularAliens();
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	private void initializeDestroyerAliens(GameObjectContainer container) {
		// Anadimos a la lista los destroyers
		 
		int numD = level.getNumDestroyerAliens();
		// Filas ocupadas por regulars, para insertar por debajos
		int numFilasR = level.getNumRegularAliens() / 4;
		// Para colocarlos en el medio
		int colInitialOffset = Game.DIM_X/2 - numD/2;
		
		for (int i = 0; i < numD; i++) {
			// Insertamos alien
			Position pos = new Position(colInitialOffset + i, RegularAlien.ROW_INI_OFFSET + numFilasR);
			DestroyerAlien templateAlien = new DestroyerAlien(this, level.getNumCyclesToMoveOneCell(), game, pos);	
			container.add(templateAlien);
		}
		// Incrementamos contador de aliens
		remainingAliens += level.getNumDestroyerAliens();
	}
	
	private void costumedInitialization(GameObjectContainer container, InitialConfiguration conf) {
 		for (String shipDescription : conf.getShipDescription()) {
 			String[] words = shipDescription.toLowerCase().trim().split("\\s+");
 			if (words.length == 3) {
 				AlienShip ship = ShipFactory.spawnAlienShip(words[0], level.getNumCyclesToMoveOneCell(), game, new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2])), this);
				container.add(ship);
 				this.remainingAliens++;
 			}
 		}
 	}

	
	// CONTROL METHODS
	public int getRemainingAliens() {
		// Devuelve el numero de aliens vivos
		return this.remainingAliens;
	}
	
	public boolean allAlienDead() {
		// Devuelve si todos los aliens estan muertos
		return remainingAliens == 0;
	}
	
	public void alienDead() {
		// Disminuye el contador de aliens
		remainingAliens--;
		decreaseOnBorder();
	}
	
	public boolean onBorder() {
		// TODO Auto-generated method stub
		return this.onBorder;
	}
	
	public void finalRowReached() {
		// Aliens han llegado a la fila de UCMShip
		squadInFinalRow = true;
	}
	
	public boolean haveLanded() {
		// Devuelve true si los aliens han llegado a la fila de UCMShip
		return squadInFinalRow;
	}
	
	public boolean readyToDescend() {
		// Devuelve true si tiene que bajar el alien
		return onBorder;
	}
	
	public void shipOnBorder() {
		// Se ejecuta cuando un alien llega al borde
		if(!onBorder) {
			onBorder = true;
			// Contador - numero de aliens que quedan por bajar 
			shipsOnBorder = remainingAliens;
		}
	}
	
	public void decreaseOnBorder() {
		if (shipsOnBorder > 0) {
			// Decrementamos el numero de aliens que quedan por bajar  
			shipsOnBorder--;
			if (shipsOnBorder == 0) { 
				this.onBorder = false;
			}
		}
	}
}
