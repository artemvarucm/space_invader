package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Weapon;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}
	
	public void explotion(Position pos, int damage) {		
		// Realiza la revision si hay algun objeto en la posicion pos y le quita danio damage en caso afirmativa
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (object.isOnPosition(pos)) {
				object.receiveDamage(damage);
			}
		}
	} 
	
	public String toString(Position pos) {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			// Mostrar si esta vivo
			if (object.isAlive() && object.isOnPosition(pos))
				builder.append(object.toString());
		}
		return builder.toString();
		
	}

	public void automaticMoves() {
		// Realiza los movimientos de cada elemento
		for (int i=0;i<objects.size();i++) {
				GameObject object = objects.get(i);
				object.automaticMove();
		}
		removeDead();
	}

	public void computerActions() {
		// Realiza las computer actions de cada elemento
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.computerAction();
		}
	}
	
	public void checkAttacks(Weapon weapon) {
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (!weapon.equals(object)) {
				weapon.performAttack(object);
			}
		}
	}
	
	public void removeDead() {
		// Elimina a objetos muertos de la lista
		for (int i=objects.size() - 1; i >= 0; i--) {
			GameObject object = objects.get(i);
			if (!object.isAlive()) {
				object.onDelete();
				remove(object);
			}

		}
	}
}
