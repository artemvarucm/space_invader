package tp1.logic.gameobjects;
import tp1.logic.*;
public class UCMShip {
	private static final String representation  = "^__^";
	public static final int SINGLE_MOVE_CELLS = 1;
	public static final int DOUBLE_MOVE_CELLS = 2;
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
	private Position pos;
	private int life;
	private Game game;
	private int points;
	private boolean hasShockWave;
	private boolean canShoot;
	public UCMShip(Game game) {
		// Posicion inicial - ultima fila en la columna media
		this.pos = new Position(Game.DIM_X/2, Game.DIM_Y - 1);
		this.life = ARMOR;
		this.game = game;
	}
	// UNKNOWN START
	
	public void performMovement() {
		
	}
	public String toString() {
		return representation;
	}
	
	public String stateToString() {
		return null;
	}
	
	public void onDelete() {
		
	}
	
	public void move(String direction) {
		// añadir check si esta dentro del tablero NxM
		int prevCol = pos.getCol();
		int newCol = 0;
		switch (direction) {
			case "left": {
				newCol = prevCol - UCMShip.SINGLE_MOVE_CELLS;
			}
			break;
			case "right": {
				newCol = prevCol + UCMShip.SINGLE_MOVE_CELLS;
			}
			break;
			case "lleft": {
				newCol = prevCol - UCMShip.DOUBLE_MOVE_CELLS;
			}
			break;
			case "rright": {
				newCol = prevCol + UCMShip.DOUBLE_MOVE_CELLS;
			}
			break;
		}
		if (newCol >= 0 && newCol < Game.DIM_X) {
			// FIXME: Hara falta hacerlo con setPos??
			pos.setCol(newCol);
		}
	}
	
	public void executeShockWave() {}
	
	
	public void receiveAttack() {
		
	}
	
	// UNKNOWN END
	
	// LOGICA START
	 public void computerAction() {}
	 public void automaticMove() {}
	
	// LOGICA END
	 public boolean isOnPoisition(int col, int row) {
		return pos.getCol() == col && pos.getRow() == row;
	}
	
	public static void getInfo() {
		
	}
	
	public static void getDescription() {
		
	}
	
	public static int getDamage() {
		return DAMAGE;
	}
	
	public void receiveDamage(int damage) {
		this.life -= damage;
	}
	
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public void die() {
		life = 0;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public int getLife() {
		return life;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void enableLaser() {
		this.canShoot = true;
	}
	
	public void shootLaser() {
		if (canShoot) {
			this.canShoot = false;
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	public void receivePoints(int points) {
		this.points += points;
	}
	
	public boolean hasShockWave() { // FIXME private
		return hasShockWave;
	}
	
	public void enableHasShockWave() {
		this.hasShockWave = true;
	}
	
	public void disableHasShockWave() {
		this.hasShockWave = false;
	}
	
	public boolean isCanShoot() {
		return canShoot;
	}
	
}
