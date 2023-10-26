package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.lists.BombList;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.UCMLaser;

/**
 * Container of regular aliens, implemented as an array with a counter
 * It is in charge of forwarding petitions from the game to each regular alien
 * 
 */
public class DestroyerAlienList {
	private static final int MAX = 4;

	private DestroyerAlien[] objects;
	private int num;
	
	public DestroyerAlienList() {
		this.num = 0;
		objects = new DestroyerAlien[MAX];
	}
	
	public String toString(Position pos) {
		String str = "";
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < num) {
			if (objects[i].isOnPosition(pos)) {
				encontrado = true;
				str = objects[i].toString();
			} else {
				i++;
			}
		}
		return str;
	}
	
	
	
	public void computerActions() {
		for (int i = 0; i < num; i ++) {
			objects[i].computerAction();
		}
	}
	
	
	public void automaticMoves() {
		for (int i = 0; i < num; i++) {
			objects[i].automaticMove();
		}
	}
	
	public void checkAttacks(ShockWave shockWave) {
		for (int i = 0; i < num; i++) {
			shockWave.performAttack(objects[i]);
		}
	}
	
	public boolean checkAttacks(UCMLaser laser) {
		int i = 0;
		boolean collapsed = false;
		while (!collapsed && i < num) {
			collapsed = laser.performAttack(objects[i]);
			i++;
		}
		return collapsed;
	}
	
	public void removeDead() {
		for (int i = num - 1; i >= 0; i--) {
			if (!objects[i].isAlive()) {
				remove(i);
			}
		}
	}
	
	public int size() { return num; }
	
	public boolean full() { return num == MAX; }

	public boolean add(DestroyerAlien alien) {
		// a�adimos alien al final de la lista
		if (full()) return false;
		objects[num] = alien;
		num++;
		return true;
	}
	
	private void remove(int pos){
		// Eliminamos alien en la posicion pos de la lista

		for(int i=pos; i < num - 1; i++)
			objects[i] = objects[i+1];
		
		num--;
	}
}