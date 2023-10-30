package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;


public class Bomb {
	private static int ARMOR = 1;
	public static int DAMAGE = 1;
	
	private Move dir;
	private Game game;
	private Position pos;
	int life;
	private DestroyerAlien owner;
	public Bomb(Game game, Position pos, DestroyerAlien owner) {
		this.life = ARMOR;
		this.pos = new Position(pos);
		this.game = game;
		this.owner = owner;
		// La direccion de movimiento es hacia abajo
		this.dir = Move.DOWN;
	}
	
	
	
	public String toString() {
		/* Devuelve la representacion de la Bomba
		 Hasta aqui solo llegan los vivos, no hace falta isAlive()
		 */
		return getSymbol();
	}
	
	private String getSymbol() {
		// Devuelve la representacion ASCII de la Bomba
		return Messages.BOMB_SYMBOL;
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		// Recibe el ataque del laser
		// Devuelve true si se ha muerto la bomba
		receiveDamage(UCMLaser.DAMAGE);
		return !isAlive();
	}
	
	/**
	 *  Implements the automatic movement of the bomb	
	 */
	public void automaticMove () {
		// Realiza el movimiento de la bomba
		performMovement(dir);
		if(isOut()) {
			// Si ha salido fuera del tablero, muere
			die();
		} else {
			// Intenta atacar a alguien despues de moverse
			game.performAttack(this);
		}
	}
	
	public void computerAction() {
		// vacio
	}
	
	public boolean isOnPosition(Position pos) {
		// Devuelve si las posiciones son iguales
		return pos.equals(this.pos);
	}
	
	public Position getPosition() {
		// Devuelve la posicion actual
		return this.pos;
	}
	
	public int getDamage() {
		// Devuelve el dano
		return Bomb.DAMAGE;
	}
	
	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
	
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return this.life > 0;
	}

	/**
	 *  Method called when the bomb disappears from the board
	 */
	public void onDelete() {
		// Si se ha muerto, mandar senal al destroyer de que puede volver a lanzar la bomba
		owner.enableBomb();		
	}

	// PERFORM ATTACK METHODS
	
	private void die() {
		// Sirve para matar al objeto de forma explicita
		this.life = 0;
	}

	private boolean isOut() {
		// Devuelve true si el objeto esta en dentro del tablero
		return !pos.isOnBoard();
	}

	private void performMovement(Move dir) {
		// Realiza el movimiento en la direccion dir
		pos = pos.move(dir);
	}

	
	public boolean performAttack(UCMShip other) {
		// Realiza el ataque sobre UCMShip. Devuelve true si el UCMShip esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			res = weaponAttack(other);
			// Eliminamos bomba despues del ataque
			die();
		}
		return res;
	}
	
	public boolean performAttack(UCMLaser other) {
		// Realiza el ataque sobre UCMLaser. Devuelve true si el UCMLaser esta muerto
		boolean res = false;
		if (isAlive() && other.isAlive() && other.isOnPosition(pos)) {
			// Si se cumplen las condiciones
			res = weaponAttack(other);
			// Eliminamos bomba despues del ataque
			die();
		}
		return res;
	}
	
	private boolean weaponAttack(UCMLaser other) {
		return other.receiveAttack(this);	
	}
	
	private boolean weaponAttack(UCMShip other) {
		return other.receiveAttack(this);	
	}
	
}