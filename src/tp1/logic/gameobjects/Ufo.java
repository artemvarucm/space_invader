package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Ufo extends EnemyShip {
	private static final int ARMOR = 1;
	private static final int POINTS = 25;
	private boolean enabled;
	
	public Ufo (Game game) {
		// La direccion de movimiento es hacia izquierda
		super(Move.LEFT, new Position(Game.DIM_X, 0), ARMOR, game);
		this.enabled = false;
	}
	
	public String toString(Position pos) {
		// Devuelve la representacion de la Bomba
		String str = "";
		if (isAlive() && isOnPosition(pos)) {
			str = " " + getSymbol() + "[" + String.format("%02d", life) + "]";
		}
		return str;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del UFO
		return Messages.UFO_SYMBOL;
	}
	
	public boolean receiveAttack(UCMLaser laser) {
		// Recibe el ataque del laser
		// Devuelve true si se ha muerto
		receiveDamage(UCMLaser.DAMAGE);
		if (!isAlive()) {
			// Inhabilitamos shockwave
			game.enableShockWave();
		}
		return !isAlive();
	}
	
	@Override
	public void computerAction() {
		// Intenta generar el ufo
		if(!enabled && canGenerateRandomUfo()) {
			// Si se puede, lo inhabilita
			enable();
		}
	}
	
	@Override
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
	
	@Override
	public void receiveDamage(int dam) {
		// Recibe el danio indicado en dam
		this.life -= dam;
	}
	
	@Override
	protected int getDamage() {
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
	
	private boolean isOut() {
		// Devuelve true si esta fuera del tablero
		return !pos.isOnBoard();
	}
	
	private void die() {
		// Sirve para matar de forma explicita
		this.life = 0;
	}
	
	@Override
	public void onDelete() {
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

	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
