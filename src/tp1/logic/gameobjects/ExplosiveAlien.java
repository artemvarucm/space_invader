package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing an explosive alien
 * 
 */
public class ExplosiveAlien extends AlienShip {
	//public static final int ROW_INI_OFFSET = 1; // donde empezamos a dibujarles (filas)
	private static final int ARMOR = 2;
	private static final int POINTS = 12;
	private static final int DAMAGE = 1; // danio despues de morir
	
	public ExplosiveAlien (AlienManager alienManager, int speed, GameWorld game, Position pos) {
		super(alienManager, speed, game, pos, ARMOR);
	}
	public ExplosiveAlien() {
		super();
	}

	@Override
	protected int getArmour() {
		// Devuelve armadura
		return 0;
	}
	
	@Override
	protected int getDamage() {
		// Devuelve el dano
		return 0;
	}
	
	@Override
	public int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
 
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII de RegularAlien
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de RegularAlien
		return Messages.alienDescription(getDescription(), POINTS, 0, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.EXPLOSIVE_ALIEN_DESCRIPTION;
	}
	
	@Override
	protected AlienShip copy(GameWorld game, int speed, Position pos, AlienManager am){
		return new ExplosiveAlien(am, speed, game, pos);
	}
	
	@Override
	public void onDelete() {
		game.explosiveAttack(this.pos, DAMAGE);
		super.onDelete();
	}
	
	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		if (!isAlive()) {
			// Los aliens o todos han bajado o nadie ha bajado en este momento
			// Shockwave ataca antes del automaticMoves
			// Laser ataca despues de automaticMove de aliens
			alienManager.alienDead();
			// recibimos puntos
			game.receivePoints(getPoints());
			// realizamos ataques a sus vecinos (good neighbour)
			// Explotion explotion = new Explotion(game, this.pos);
			// game.addObject(explotion);
			// explotion.computerAction();
		}
		return !isAlive();
	}
	
}