package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Level;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a Destroyer Alien
 *
 */
public class DestroyerAlien {
	public static final int COL_INI_OFFSET = 3; // AJUSTAR A LOS NIVELES DE DIFICULTAD
	public static final int ARMOR = 1;
	public static final int POINTS = 10;
	//TODO fill your code
	private int cyclesToMove; // cuantos quedan para moverse (cambia cada jugada)
	private int speed; // constante a la cual se reinicia despues de moverse
	private Move dir;
	private Position pos;
	private int life;
	private Game game;
	private AlienManager alienManager;
	public boolean canShootBomb = true;
	//TODO fill your code
	public DestroyerAlien (Position pos, int speed, Game game, AlienManager alienManager) {
		this.pos = new Position(pos);
		this.dir = Move.LEFT;
		this.life = ARMOR;
		this.cyclesToMove = speed;
		this.speed = speed;
		this.game = game;
		this.alienManager = alienManager;
	}
	
	public String toString() {
		// Hasta aqui solo llegan los vivos, no hace falta isAlive()
		return Messages.DESTROYER_ALIEN_SYMBOL + "[" + String.valueOf(life) + "]";
	}
	
	public boolean isAlive() {
		return life > 0;
	}
	
	public boolean isOnPosition(Position pos) {
			return pos.equals(this.pos);
	}
	
	public void computerAction() {
		if (canShootBomb && canGenerateRandomBomb()) {
			enableBomb(game);
		}
	}
	
	public void canShootBomb() {
		canShootBomb = true;
	}
	
	private void enableBomb(Game game) {
		Position pos = new Position(this.pos);
		Bomb templateBomb = new Bomb(game, pos, this);
		game.addObject(templateBomb);
		canShootBomb = false;
	}
	
	private boolean canGenerateRandomBomb() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
	public void automaticMove() {
		//TODO fill your code
		if (isAlive()) {
			if (cyclesToMove == 0 || alienManager.onBorder()) {
				dir = alienManager.movement();
				if (alienManager.readyToDescend()) {
					descend();
					if (isInFinalRow()) {
						alienManager.finalRowReached();
					}
				} else {
					performMovement(dir);
					if (isInBorder()) {
						alienManager.shipOnBorder();
					}
				}
				cyclesToMove = speed;
			} else {
				cyclesToMove--;
			}
		}
	}
	
	private void descend() {
		//TODO fill your code
		performMovement(Move.DOWN);
		alienManager.decreaseOnBorder();
	}

	private void performMovement(Move dir) {
		//TODO fill your code
		pos.move(dir);
	}
	
	private boolean isInBorder() {
		//TODO fill your code
		return (dir.equals(Move.RIGHT) && pos.getCol() == Game.DIM_X - 1) 
				|| 
				(dir.equals(Move.LEFT) && pos.getCol() == 0);
	}
	
	public boolean isInFinalRow() {
		return pos.getRow() == Game.DIM_Y - 1;
	}	
	
	public boolean receiveAttack(UCMLaser laser) {
		//TODO fill your code
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public boolean receiveAttack(ShockWave shockWave) {
		//TODO fill your code
		receiveDamage(ShockWave.DAMAGE);
		if (!isAlive()) {
			alienManager.alienDead();
		}
		return !isAlive();
	}
	
	public void receiveDamage(int dam) {
		this.life -= dam;
	}
}