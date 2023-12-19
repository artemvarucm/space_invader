package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
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
	protected boolean bombReadyToFire;
	public DestroyerAlien (AlienManager alienManager, int speed, GameWorld game, Position pos) {
		super(alienManager, speed, game, pos, ARMOR);
		this.canShootBomb = true;
		this.bombReadyToFire = false;
	}
	public DestroyerAlien() {
		super();
	}
	
	@Override
	protected int getArmour() {
		// Devuelve armadura
		return ARMOR;
	}
	
	@Override
	protected int getDamage() {
		// Devuelve el dano
		return DAMAGE;
	}
	
	@Override
	protected int getPoints() {
		// Devuelve los puntos por matarlo
		return POINTS;
	}
	
	@Override
	protected String getSymbol() {
		// Devuelve la representacion ASCII de DestroyerAlien
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}
	
	
	@Override
	protected String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.DESTROYER_ALIEN_DESCRIPTION;
	}
	
	protected void enableBomb() {
		// Se ejecuta despues de que la bomba se muera
		// Puede disparar otra vez el Destroyer
		canShootBomb = true;
	}
	
	private boolean canGenerateRandomBomb() {
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();
	}
	
	@Override
	protected AlienShip copy(GameWorld game, int speed, Position pos, AlienManager am){
		return new DestroyerAlien(am, speed, game, pos);
	}
	
	@Override 
	public void automaticMove() {
		super.automaticMove();
		// si no se mueve horizontalmente y tiene bomba cargada, lanzar bomba
		if (isAlive() && cyclesToMove < speed && bombReadyToFire) {
			shootBomb();
		}
	}
	
	private void shootBomb() {
		// Si tiene que lanzar la bomba, la lanzas despues de realizar movimiento
		Bomb templateBomb = new Bomb(this, game, pos);
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
