package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 * 
 */
public class RegularAlien extends AlienShip {
	public static final int ROW_INI_OFFSET = 1; // donde empezamos a dibujarles (filas)
	private static final int ARMOR = 2;
	private static final int POINTS = 5;
	
	public RegularAlien (Position pos, int speed, GameWorld game, AlienManager alienManager) {
		super(pos, speed, ARMOR, game, alienManager);
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
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
		return Messages.REGULAR_ALIEN_SYMBOL;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de RegularAlien
		return Messages.alienDescription(getDescription(), POINTS, 0, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.REGULAR_ALIEN_DESCRIPTION;
	}
}