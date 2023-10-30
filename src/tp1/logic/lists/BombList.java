package tp1.logic.lists;

import tp1.logic.Position;
import tp1.logic.gameobjects.Bomb;
import tp1.logic.gameobjects.UCMLaser;
import tp1.logic.gameobjects.UCMShip;


public class BombList {
	private static final int MAX = 4;

	private Bomb[] objects;
	private int num;
	
	public BombList() {
		this.num = 0;
		objects = new Bomb[MAX];
	}
	
	public String toString(Position pos) {
		// Devuelve la la representacion de la bomba si esta en la posicion pos
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
				// Realiza el onDelete, para avisar al destroyer
				objects[i].onDelete();
				remove(i);
			}
		}
	}
	
	public int size() { return num; } // devuelve tamanio
	
	public boolean full() { return num == MAX; } // devuelve true si esta lleno

	public boolean add(Bomb bomb) {
		// a�adimos bomba al final de la lista
		if (full()) return false;
		objects[num] = bomb;
		num++;
		return true;
	}
	
	private void remove(int pos){
		// Eliminamos bomba en la posicion pos de la lista
		for(int i=pos; i < num - 1; i++)
			objects[i] = objects[i+1];
		
		num--;
	}
}