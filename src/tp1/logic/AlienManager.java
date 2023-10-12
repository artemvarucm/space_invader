package tp1.logic;

//import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
//import tp1.logic.lists.DestroyerAlienList;
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
	//
	private Move dir;
	private int movedAliens;
	private boolean readyToDescend;
	//
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;

	public AlienManager(Game game, Level level) {
		this.level = level;
		this.game = game;
		this.remainingAliens = 0;
		this.movedAliens = 0;
		this.onBorder = false;
		this.readyToDescend = false;
		this.dir = Move.LEFT;
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
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 4; j++) {
				RegularAlien templateAlien = new RegularAlien(j + RegularAlien.COL_INI_OFFSET, i + RegularAlien.ROW_INI_OFFSET, level.getNumCyclesToMoveOneCell(), game, this);
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
//	protected  DestroyerAlienList initializeDestroyerAliens() {
//		//TODO fill your code
//	}
	
	// CONTROL METHODS
	public Move movement() {
		if (shipsOnBorder == 0) {
			onBorder = false;
			readyToDescend = false;
		}
		if (movedAliens == remainingAliens) {
			movedAliens = 0;
			if (onBorder) {
				readyToDescend = true;
				if (this.dir.equals(Move.LEFT)) {
					this.dir = Move.RIGHT;
				} else {
					this.dir = Move.LEFT;
				}
			}
		}
		movedAliens++;
		return dir;
	}
	
	public int getRemainingAliens() {
		return this.remainingAliens;
	}
	
	public boolean allAlienDead() {
		return remainingAliens == 0;
	}
	
	public void alienDead() {
		remainingAliens--;
		movedAliens--;
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
		return readyToDescend;
	}
	
	public void shipOnBorder() {
		if(!onBorder) {
			onBorder = true;
			shipsOnBorder = remainingAliens;
		}
	}
	
	public void decreaseOnBorder() {
		shipsOnBorder--;
		if (shipsOnBorder == 0) { 
			this.onBorder = false;
		}
	}
}
