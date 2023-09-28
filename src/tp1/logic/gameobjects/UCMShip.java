package tp1.logic.gameobjects;
import tp1.logic.*;
public class UCMShip {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
	private Position pos;
	private int life;
	private Game game;
	private int points;
	private boolean hasShockWave;
	private boolean canShoot;
	public UCMShip() {
		
	}
	// UNKNOWN START
	public void isOnPoisition() {
		
	}
	
	public void performMovement() {
		
	}
	public String toString() {
		return null;
	}
	
	public String stateToString() {
		return null;
	}
	
	public void onDelete() {
		
	}
	
	public void move() {}
	
	public void executeShockWave() {}
	
	
	public void receiveAttack() {
		
	}
	
	// UNKNOWN END
	
	// LOGICA START
	 public void computerAction() {}
	 public void automaticMove() {}
	
	// LOGICA END
	
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
