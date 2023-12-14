package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.logic.exceptions.InitializationException;
import tp1.logic.exceptions.LaserInFlightException;
import tp1.logic.exceptions.NoShockWaveException;
import tp1.logic.exceptions.NotAllowedMoveException;
import tp1.logic.exceptions.NotEnoughPointsException;
import tp1.logic.exceptions.OffWorldException;

// Se usa en Controller y en cada Command
public interface GameModel {
	public void update();
	public boolean isFinished();
	
	public void reset(InitialConfiguration config) throws InitializationException;
	public void exit();
	public void move(Move move) throws OffWorldException, NotAllowedMoveException;
	public void printGameObjectsList();
	public void shootLaser() throws LaserInFlightException;
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException;
	public void shockWave() throws NoShockWaveException;
}
