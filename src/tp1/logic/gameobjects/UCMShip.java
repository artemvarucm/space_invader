package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.logic.lists.DestroyerAlienList;
import tp1.logic.lists.RegularAlienList;
import tp1.view.Messages;

public class UCMShip {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
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
	
	public String toString(Position pos) {
		String str = "";
		if (isOnPosition(pos)) {
			str = getSymbol();
		}
		return str;
	}
	
	public String getSymbol() {
		String res = "";
		if (isAlive()) {
			res = Messages.UCMSHIP_SYMBOL;
		} else {
			res = Messages.UCMSHIP_DEAD_SYMBOL;
		}
		return res;
	}

	public boolean performMovement(Move dir) {
		boolean validMove = true;
		Position hPos = pos.move(dir);
		validMove = hPos.isOnBoard();
		if (validMove) {
			this.pos = hPos;
		} else {
			System.out.println(Messages.invalidPosition(hPos.getCol(), hPos.getRow()));
		}
		
		return validMove;
	}
	
	public boolean move(String direction) {
		// a�adir check si esta dentro del tablero NxM
		boolean validMove = true;
		Move dir = null;
		try {
			dir = Move.valueOf(direction);
			if (dir.equals(Move.UP) || dir.equals(Move.DOWN)) { // No se puede ir arriba o abajo
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			validMove = false;
			System.out.println(Messages.UNKNOWN_COMMAND);
		}
		if (validMove) {
			validMove = performMovement(dir);
		}
		return validMove;
	}
	
	public boolean shootLaser(Game game) {
		boolean res = false;
		if (canShoot) {
			res = true;
			UCMLaser laser = new UCMLaser(game, pos, true);
			game.addObject(laser);
			this.canShoot = false;
		}
		return res;
	}
	
	public boolean executeShockWave(Game game, RegularAlienList regularAliens, DestroyerAlienList destroyerAliens) {
		boolean res = false;
		if (hasShockWave()) {
			//game.addObject(shockWave);
			disableShockWave();
			ShockWave shockWave = new ShockWave(game);
			regularAliens.checkAttacks(shockWave);
			destroyerAliens.checkAttacks(shockWave);
			shockWave.die();
			res = true;
		}
		return res;
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public static String getInfo() {
		return Messages.ucmShipDescription(getDescription(), DAMAGE, ARMOR);
	}
	
	public static String getDescription() {
		return Messages.UCMSHIP_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
		return pos.equals(this.pos);
	}
	
	public static int getDamage() {
		return DAMAGE;
	}

	public void receiveDamage(int damage) {
		this.life -= damage;
	}

	public void die() {
		life = 0;
		onDelete();
	}

	public Position getPos() {
		return pos;
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
	
	public void onDelete() {

	}

	public int getPoints() {
		return points;
	}

	public void receivePoints(int points) {
		this.points += points;
	}

	private boolean hasShockWave() {
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

	public String stateToString() {
		StringBuilder result = new StringBuilder();
		result.append("Life: ").append(life).append(Messages.LINE_SEPARATOR);
		result.append("Points: ").append(points).append(Messages.LINE_SEPARATOR);
		String shock = "OFF";
		if (hasShockWave()) {
			shock = "ON";
		}
		result.append("ShockWave: ").append(shock).append(Messages.LINE_SEPARATOR);
		
		return result.toString();
	}
/*	

	public void receiveAttack() {

	}
	
	public void computerAction() {
	}

	public void automaticMove() {
	}
*/
}
