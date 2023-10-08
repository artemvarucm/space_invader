package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

public class UCMShip {
	public static final int ARMOR = 3;
	public static final int DAMAGE = 1;
	private Position pos;
	private int life;
	private Game game;
	private int points;
	private boolean hasShockWave;
	private boolean canShoot;

	public UCMShip(Game game) {
		// Posicion inicial - ultima fila en la columna media
		this.pos = new Position(Game.DIM_X / 2, Game.DIM_Y - 1);
		this.life = ARMOR;
		this.game = game;
		this.canShoot = true;
	}
	// UNKNOWN START

	public void performMovement() {

	}

	public String toString() {
		return Messages.UCMSHIP_SYMBOL;
	}

	public String stateToString() {
		return null;
	}

	public void onDelete() {

	}

	public boolean move(String direction) {
		// a�adir check si esta dentro del tablero NxM
		Move move = Move.NONE;
		boolean moved = true;
		switch (direction) {
		case "left": {
			move = Move.LEFT;
		}
			break;
		case "right": {
			move = Move.RIGHT;
		}
			break;
		case "lleft": {
			move = Move.LLEFT;
		}
			break;
		case "rright": {
			move = Move.RRIGHT;
		}
			break;
		default:
			moved = false;
		}
		if (moved) {
			if (move.validate(pos)) {
				pos.move(move);
			} else {
				moved = false;
				System.out.println(Messages.invalidPosition(pos.getCol() + move.getX(), pos.getRow()));
			}
		} else {
			System.out.println(Messages.UNKNOWN_COMMAND);
		}
		return moved;
	}

	public boolean executeShockWave() {
		boolean res = false;
		if (hasShockWave()) {
			
			res = true;
		}
		return res;
	}

	public void receiveAttack() {

	}

	// UNKNOWN END

	// LOGICA START
	public void computerAction() {
	}

	public void automaticMove() {
	}

	// LOGICA END
	public boolean isOnPoisition(int col, int row) {
		return pos.getCol() == col && pos.getRow() == row;
	}

	public static void getInfo() {

	}

	public static void getDescription() {

	}

	public static int getDamage() {
		return DAMAGE;
	}

	public void receiveDamage(int damage) {
		this.life -= damage;
	}

	public boolean isAlive() {
		return life > 0;
	}

	public void die() {
		life = 0;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getLife() {
		return life;
	}

	public Game getGame() {
		return game;
	}

	public void enableLaser() {
		this.canShoot = true;
	}

	public boolean shootLaser(Game game) {
		boolean res = false;
		if (canShoot) {
			res = true;
			UCMLaser laser = new UCMLaser(game, pos);
			game.addObject(laser);
			this.canShoot = false;
		}
		return res;
	}

	public int getPoints() {
		return points;
	}

	public void receivePoints(int points) {
		this.points += points;
	}

	public boolean hasShockWave() { // FIXME private
		return hasShockWave;
	}

	public void enableShockWave() {
		this.hasShockWave = true;
	}

	public void disableShockWave() {
		this.hasShockWave = false;
	}

	public boolean isCanShoot() {
		return canShoot;
	}

}
