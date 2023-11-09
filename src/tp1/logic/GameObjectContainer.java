package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.EnemyShip;
import tp1.logic.gameobjects.EnemyWeapon;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.UCMWeapon;
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
			//TODO fill with your code
		}
		//TODO fill with your code
		removeDead();
	}

	public void computerActions() {
		// Realiza las computer actions de cada elemento
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			object.computerAction();
		}
	}

	public void removeDead() {
		// Elimina a objetos muertos de la lista
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			// No podemos eliminar a UCMShip del tablero
			if (!object.isAlive() && !(object instanceof UCMShip)) {
				// Realiza el onDelete, para avisar al destroyer
				object.onDelete();
				remove(object);
			}

		}
	}
	
	public void checkAttacks(UCMWeapon weapon) {
		// Elimina a objetos muertos de la lista
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (weapon.isAlive() && object.isAlive() && weapon != object) {
				object.receiveAttack(weapon);
			}

		}
	}
	
	public void checkAttacks(EnemyWeapon weapon) {
		// Elimina a objetos muertos de la lista
		for (int i=0;i<objects.size();i++) {
			GameObject object = objects.get(i);
			if (weapon.isAlive() && object.isAlive() && weapon != object) {
				object.receiveAttack(weapon);
			}

		}
	}
}
