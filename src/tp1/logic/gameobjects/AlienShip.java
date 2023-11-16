package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class AlienShip extends EnemyShip {
	// cuantos ciclos quedan para moverse
	protected int cyclesToMove; 
	// constante a la cual se reinicia cyclesToMove despues de moverse
	protected int speed; 	
	protected boolean bombReadyToFire;
	protected AlienManager alienManager;
	public AlienShip (Position pos, int speed, int armor, Game game, AlienManager alienManager) {
		super(Move.LEFT, pos, armor, game);
		this.bombReadyToFire = false;
		this.cyclesToMove = speed;
		this.speed = speed;
		this.alienManager = alienManager;
	}

	@Override
	public void automaticMove() {
		// Realiza el movimiento del Regular
		if (isAlive()) {
			if (cyclesToMove == 0) {
				// Si se tiene que mover
				performMovement(dir);
				if (isInBorder()) {
					// Si esta en el borde, avisa al manager
					alienManager.shipOnBorder();
				}
				// reiniciamos el "cooldown" del movimiento
				cyclesToMove = speed;
			} else { 
				if (alienManager.readyToDescend()) {
					// Si ya se ha movido anteriormente, vemos si tiene que descender
					descend();	
				} else {
					cyclesToMove--;
				}
				if (bombReadyToFire) {
					shootBomb();
				}
			}
		}
	}
	
	@Override
	public void onDelete() {
		alienManager.alienDead();
		// recibimos puntos
		game.receivePoints(getPoints());
		game.removeObject(this);
	}
	
	abstract protected int getPoints();
	
	protected void shootBomb() {
		// FIXME VACIO
	};
	
	private void descend() {
		// Baja una fila el Regular
		performMovement(Move.DOWN);
		// Cabia de sentido del movimiento
		this.dir = dir.flip();
		// Decrementa el contador de aliens por bajar del AlienManager
		alienManager.decreaseOnBorder();
		// Avisa si ha llegado al borde
		if (isInFinalRow()) {
			alienManager.finalRowReached();
		}
	}
	
	private boolean isInBorder() {
		// Dependiendo del sentido del movimiento devuelve si ha tocado borde o no
		return (dir.equals(Move.RIGHT) && pos.getCol() == Game.DIM_X - 1) 
				|| 
				(dir.equals(Move.LEFT) && pos.getCol() == 0);
	}
	
	public boolean isInFinalRow() {
		// Devuelve true si esta en la ultima fila(donde esta UCMShip)
		return pos.getRow() == Game.DIM_Y - 1;
	}	
}
