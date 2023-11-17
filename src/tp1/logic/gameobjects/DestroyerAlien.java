package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a Destroyer Alien
 *
 */
public class DestroyerAlien extends AlienShip {
	private static final int ARMOR = 1;
	private static final int POINTS = 10;
	private static final int DAMAGE = 1;
	public boolean canShootBomb;
	public DestroyerAlien (Position pos, int speed, Game game, AlienManager alienManager) {
		super(pos, speed, ARMOR, game, alienManager);
		this.canShootBomb = true;
	}
	
	@Override
	protected int getArmour() {
		// TODO Auto-generated method stub
		return ARMOR;
	}
	
	@Override
	protected int getDamage() {
		// Devuelve el dano
		return DAMAGE;
	}
	
	@Override
	public int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII de DestroyerAlien
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}
	
	public static String getInfo() {
		// Devuelve la descripcion formateada de DestroyerAlien
		return Messages.alienDescription(getDescription(), POINTS, DAMAGE, ARMOR);
	}
	
	private static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.DESTROYER_ALIEN_DESCRIPTION;
	}
	
	public void enableBomb() {
		// Se ejecuta despues de que la bomba se muera
		// Puede disparar otra vez el Destroyer
		canShootBomb = true;
	}
	
	private boolean canGenerateRandomBomb() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
	@Override 
	public void automaticMove() {
		super.automaticMove();
		// si no se mueve horizontalmente y tiene bomba cargada, lanzar bomba
		if (isAlive() && cyclesToMove < speed && bombReadyToFire) {
			shootBomb();
		}
	}
	
	protected void shootBomb() {
		// Si tiene que lanzar la bomba, la lanzas despues de realizar movimiento
		Bomb templateBomb = new Bomb(game, this.pos, this);
		game.addObject(templateBomb);
		bombReadyToFire = false;
	}
	
	@Override
	public void computerAction() {
		// Realiza la revision si puede generar bomba
		if (cyclesToMove != 0 && canShootBomb && canGenerateRandomBomb()) {
			// Habilita la bomba en caso de que puede generarla
			canShootBomb = false;
			bombReadyToFire = true;
		}
	}
}
