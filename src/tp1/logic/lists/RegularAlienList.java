package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.UCMLaser;

/**
 * Container of regular aliens, implemented as an array with a counter
 * It is in charge of forwarding petitions from the game to each regular alien
 * 
 */
public class RegularAlienList {
	private static final int MAX = 8;

	private RegularAlien[] objects;
	private int num;
	
	public RegularAlienList() {
		this.num = 0;
		objects = new RegularAlien[MAX];
	}
	
	public String toString(Position pos) {
		// Devuelve la la representacion del Regular si esta en la posicion pos
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
	
	public void automaticMoves() {
		// Realiza los movimientos de cada elemento
		for (int i = 0; i < num; i++) {
			objects[i].automaticMove();
		}
	}
	
	public void computerActions() {
		// Realiza las computer actions de cada elemento
		for (int i = 0; i < num; i++) {
			objects[i].computerAction();
		}
	}
	
	public void checkAttacks(ShockWave shockWave) {
		// Revisa los ataques del shockwave sobre la lista
		for (int i = 0; i < num; i++) {
			shockWave.performAttack(objects[i]);
		}
	}
	
	public boolean checkAttacks(UCMLaser laser) {
		// Revisa los ataques del laser sobre la lista
		int i = 0;
		boolean collapsed = false;
		while (!collapsed && i < num) {
			collapsed = laser.performAttack(objects[i]);
			i++;
		}
		return collapsed;
	}
	
	public void removeDead() {
		// Elimina a objetos muertos de la lista
		for (int i = num - 1; i >= 0; i--) {
			if (!objects[i].isAlive()) {
				objects[i].onDelete();
				remove(i);
			}
		}
	}
	
	public int size() { return num; } // devuelve tamanio
	
	public boolean full() { return num == MAX; } // devuelve true si esta lleno

	public boolean add(RegularAlien alien) {
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
