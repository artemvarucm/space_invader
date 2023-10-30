package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Ufo {

	private static final int ARMOR = 1;
	private static final int POINTS = 25;
	private int life;
	private Position pos;
	private boolean enabled;
	private Game game;
	private Move dir;
	
	public Ufo (Game game) {
		this.game = game;
		this.life = 0;
		// La direccion de movimiento es hacia izquierda
		this.dir = Move.LEFT;
	}
	
	public String toString(Position pos) {
		// Devuelve la representacion de la Bomba
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = " " + getSymbol() + "[" + String.format("%02d", life) + "]";
		}
		return str;
	}
	
	private String getSymbol() {
		// Devuelve la representacion ASCII del UFO
		return Messages.UFO_SYMBOL;
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		// Recibe el ataque del laser
		// Devuelve true si se ha muerto
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			onDelete();
		}
		return !isAlive();
	}
	
	public void computerAction() {
		// Intenta generar el ufo
		if(!enabled && canGenerateRandomUfo()) {
			// Si se puede, lo inhabilita
			enable();
		}
	}
	
	public void automaticMove() {
		// Realiza el movimiento
		if (isAlive()) {
			performMovement(Move.LEFT);
			if (isOut())
				die();
		}
	}
	
	private void enable() {
		// Inhabilita el UFO
		// Reinicia la vida y la posicion
		life = ARMOR;
		this.pos = new Position(Game.DIM_X, 0);
		enabled = true;
	}
	
	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
	
	public boolean isAlive() {
		// Devuelve true, si esta vivo
		return life > 0;
	}
	
	public int getLife() {
		// Devuelve la vida actual
		return life;
	}

	public Game getGame() {
		// Devuelve el juego
		return game;
	}
	
	public Position getPosition() {
		// Devuelve la posicion actual
		return this.pos;
	}
	
	public static int getDamage() {
		// Devuelve el dano
		return 0;
	}
	
	public static int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de UFO
		return Messages.alienDescription(getDescription(), POINTS, 0, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.UFO_DESCRIPTION;
	}
	
	public boolean isOnPosition(Position pos) {
		// Devuelve si las posiciones son iguales
		return pos.equals(this.pos);
	}
	
	private boolean isOut() {
		// Devuelve true si esta fuera del tablero
		return !pos.isOnBoard();
	}
	
	private void performMovement(Move dir) {
		// Realiza el movimiento en direccion dir
		pos = pos.move(dir);
	}
	
	private void die() {
		// Sirve para matar de forma explicita
		this.life = 0;
		onDelete();
	}
	
	public void onDelete() {
		// Inhabilitamos shockwave
		game.enableShockWave();
		// Al morir, puede volver a generarse
		enabled = false;
	}
	
	/**
	 * Checks if the game should generate an ufo.
	 * 
	 * @return <code>true</code> if an ufo should be generated.
	 */
	private boolean canGenerateRandomUfo(){
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}
	
}
