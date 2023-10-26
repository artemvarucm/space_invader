package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.UCMLaser;


public class BombList {
	private static final int MAX = 4;

	private Bomb[] objects;
	private int num;
	
	public BombList() {
		this.num = 0;
		objects = new Bomb[MAX];
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
	
	public void automaticMoves() {
		for (int i = 0; i < num; i++) {
			objects[i].automaticMove();
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

	public boolean add(Bomb bomb) {
		// a�adimos alien al final de la lista
		if (full()) return false;
		objects[num] = bomb;
		num++;
		return true;
	}
	
	private void remove(int pos){

		for(int i=pos; i < num - 1; i++)
			objects[i] = objects[i+1];
		
		num--;
	}
}