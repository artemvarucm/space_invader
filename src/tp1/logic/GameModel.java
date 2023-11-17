package tp1.logic;

// Se usa en Controller y en cada Command
public interface GameModel {
	public void update();
	public boolean isFinished();
	
	public void reset();
	public void exit();
	public boolean move(Move move);
	public void printGameObjectsList();
	public boolean shootLaser();
	public boolean shockWave();
}
