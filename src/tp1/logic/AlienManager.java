package tp1.logic;

import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;

/**
 * 
 * Manages alien initialization and
 * used by aliens to coordinate movement
 *
 */
public class AlienManager {
	
	private Level level;
	private Game game;
	private int remainingAliens;
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
		this.onBorder = false;
	}
		
	// INITIALIZER METHODS
	
	/**
	 * Initializes the list of regular aliens
	 * @return the initial list of regular aliens according to the current level
	 */
	protected RegularAlienList initializeRegularAliens() {
		//TODO fill your code
		RegularAlienList list = new RegularAlienList();
		
		int num = level.getNumRegularAliens() / 4;  // maximo cuatro en fila (1 en EASY, 2 en HARD)
		// Lo colocamos en el medio
		int colInitialOffset = Game.DIM_X/2 - 2;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 4; j++) {
				Position pos = new Position(colInitialOffset + j, RegularAlien.ROW_INI_OFFSET + i);
				RegularAlien templateAlien = new RegularAlien(pos, level.getNumCyclesToMoveOneCell(), game, this);
				list.add(templateAlien);
			}
		}
		remainingAliens += level.getNumRegularAliens();
		return list;
	}

	/**
	 * Initializes the list of destroyer aliens
	 * @return the initial list of destroyer aliens according to the current level
	 */
	protected DestroyerAlienList initializeDestroyerAliens() {
		DestroyerAlienList list = new DestroyerAlienList();
		 
		int numD = level.getNumDestroyerAliens();
		int numFilasR = level.getNumRegularAliens() / 4;
		// Lo colocamos en el medio
		int colInitialOffset = Game.DIM_X/2 - numD/2;
		for (int i = 0; i < numD; i++) {
			Position pos = new Position(colInitialOffset + i, RegularAlien.ROW_INI_OFFSET + numFilasR);
			DestroyerAlien templateAlien = new DestroyerAlien(pos , level.getNumCyclesToMoveOneCell(), game, this);	
			list.add(templateAlien);
		}
		remainingAliens += level.getNumDestroyerAliens();
		return list;
	}
	
	// CONTROL METHODS
	public int getRemainingAliens() {
		return this.remainingAliens;
	}
	
	public boolean allAlienDead() {
		return remainingAliens == 0;
	}
	
	public void alienDead() {
		remainingAliens--;
		decreaseOnBorder();
	}
	
	public boolean onBorder() {
		// TODO Auto-generated method stub
		return this.onBorder;
	}
	
	public void finalRowReached() {
		squadInFinalRow = true;
	}
	
	public boolean haveLanded() {
		return squadInFinalRow;
	}
	
	public boolean readyToDescend() {
		// FIXME diferencia entre este y onBorder()?
		return onBorder;
	}
	
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
	}
	
	public void decreaseOnBorder() {
		if (shipsOnBorder > 0) {
			shipsOnBorder--;
			if (shipsOnBorder == 0) { 
				this.onBorder = false;
			}
		}
	}
}
