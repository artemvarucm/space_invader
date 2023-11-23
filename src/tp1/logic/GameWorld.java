package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Weapon;

// Se usa en Controller y en cada Command
public interface GameWorld {
	
	// Para random
	public Level getLevel();
	public Random getRandom();
	
	// Para anadir/eliminar de lista
	public void addObject(GameObject object);
	public void removeObject(GameObject object);
 
	// Para weapon
	public void performAttack(Weapon weapon);
	public void receivePoints(int points);
	
	// Para laser
	public void enableLaser();
	public void enableSuperLaser();
	public boolean isCycleDescend();
	
	// Para ufo
	public void enableShockWave();
}
