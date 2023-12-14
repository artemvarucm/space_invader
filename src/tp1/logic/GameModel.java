package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.control.InitializationException;

// Se usa en Controller y en cada Command
public interface GameModel {
	public void update();
	public boolean isFinished();
	
	public void reset(InitialConfiguration config) throws InitializationException;
	public void exit();
	public void move(Move move);
	public void printGameObjectsList();
	public boolean shootLaser();
	public boolean shootSuperLaser();
	public boolean shockWave();
}
