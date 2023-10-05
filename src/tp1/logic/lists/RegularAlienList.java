package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.RegularAlien;
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
	
	/*public void setNum(int num) {
	 * FIXME no deberia de hacer falta
		this.num = num;
	}*/
	
	public int size() { return num; }
	
	public RegularAlien get(int pos) {
		if((pos < 0) || (pos > num - 1)) return null;
		return objects[pos];
	}

	
	public boolean full() { return num == MAX; }

	
	public boolean add(RegularAlien alien) {
		// añadimos alien al final de la lista
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
