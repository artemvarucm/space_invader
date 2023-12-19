package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Weapon;

// Se usa en GameObjects
public interface GameWorld {
	
	// Para random
	public Level getLevel();
	public Random getRandom();
	
	// Para anadir a lista
	public void addObject(GameObject object);
 
	// Para weapon
	public void performAttack(Weapon weapon);
	public void receivePoints(int points);
	
	// Para laser
	public void enableLaser();
	public boolean isCycleDescend();
	
	// Para ufo
	public void enableShockWave();
	
	// Para explotion
	public void explosiveAttack(Position pos, int damage);
}
