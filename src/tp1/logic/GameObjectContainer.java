package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.EnemyWeapon;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMWeapon;

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
	
	public String toString(Position pos) {
		StringBuilder builder = new StringBuilder();
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (object.isOnPosition(pos))
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
	}

	public void computerActions() {
		// Realiza las computer actions de cada elemento
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.computerAction();
		}
	}
	
	public void checkAttacks(EnemyWeapon weapon) {
		// Elimina a objetos muertos de la lista
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (!weapon.equals(object)) {
				weapon.performAttack(object);
			}

		}
	}
	
	public void checkAttacks(UCMWeapon weapon) {
		// Elimina a objetos muertos de la lista
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (!weapon.equals(object)) {
				weapon.performAttack(object);
			}

		}
	}
}
