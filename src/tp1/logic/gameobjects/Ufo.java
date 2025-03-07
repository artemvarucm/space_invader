package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Ufo extends EnemyShip {
	private static final int ARMOR = 1;
	private static final int POINTS = 25;
	private boolean enabled;
	
	public Ufo (GameWorld game) {
		// La direccion de movimiento es hacia izquierda
		super(Move.LEFT, game, new Position(Game.DIM_X, 0), ARMOR);
		this.enabled = false;
	}
	
	public Ufo() {
		super();
	}
	
	@Override
	protected int getArmour() {
		return ARMOR;
	}
	
	@Override
	protected int getDamage() {
		// Devuelve el dano
		return 0;
	}
	
	@Override
	protected int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII del UFO
		return Messages.UFO_SYMBOL;
	}

	@Override
	public String getInfo() {
		// Devuelve la descripcion formateada de UFO
		return Messages.alienDescription(getDescription(), getPoints(), 0, getArmour());
	}
	
	@Override
	protected String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.UFO_DESCRIPTION;
	}
	
	
	@Override
	public String toString() {
		// Devuelve la representacion de UFO
		String str = "";
		if (enabled) {
			str = super.toString();
		}
		return str;
	}
	
	private boolean canGenerateRandomUfo(){
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}
	
	@Override
	public void onDelete() {
		// Al morir, puede volver a generarse
		enabled = false;
	}
	
	private void enable() {
		// Habilita el UFO
		// Reinicia la vida y la posicion
		life = ARMOR;
		this.pos = new Position(Game.DIM_X, 0);
		enabled = true;
	}
	
	@Override
	public void computerAction() {
		// Intenta generar el ufo
		if(!enabled && canGenerateRandomUfo()) {
			// Si se puede, lo habilita
			enable();
		}
	}
	
	private void die() {
		// Sirve para matar de forma explicita
		this.life = 0;
		onDelete();
	}
	
	@Override
	public void automaticMove() {
		// Realiza el movimiento
		if (isAlive()) {
			performMovement(dir);
			if (!pos.isOnBoard())
				die();
		}
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			game.enableShockWave();
			// recibe puntos
			game.receivePoints(getPoints());
		}
		return true;
	}
	
}
