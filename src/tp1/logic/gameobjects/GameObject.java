package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int life;
	protected Game game;
	
	public GameObject(Game game, Position pos, int life) {	
		this.pos = pos;
		this.game = game;
		this.life = life;
	}
	
	@Override
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return this.life > 0;
	}

	protected int getLife() {
		// Devuelve la vida actual
		return this.life;
	}
	
	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
	
	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public Position getPos() {
		// Devuelve la posicion actual
		return pos;
	}
	
	public boolean isOnPosition(Position pos) {
		// Devuelve si las posiciones son iguales
		return pos.equals(this.pos);
	}
	
	protected void performMovement(Move dir) {
		// Realiza el movimiento en la direccion dir
		pos = pos.move(dir);
	}

	//TODO fill with your code

	
	protected abstract String getSymbol();
	protected abstract int getDamage();
	protected abstract int getArmour();
	public abstract String toString();
	
			
	public abstract void onDelete();
	public abstract void automaticMove();
	public void computerAction() {};
	
	//TODO fill with your code
	
	@Override
	public boolean performAttack(GameItem other) {
		return false;
	}
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe el ataque del enemyweapon
		// Devuelve true si se ha muerto el atacado
		receiveDamage(weapon.getDamage());
		return !isAlive();
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		return false;
	}

}
