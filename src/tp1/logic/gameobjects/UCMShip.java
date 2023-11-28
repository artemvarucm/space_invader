package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMShip extends Ship {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
	private int points;
	private boolean hasShockWave;
	private boolean canShoot;

	public UCMShip(GameWorld game, Position position) {
		// Posicion inicial - ultima fila en la columna media
		super(game, position, ARMOR);		
		this.canShoot = true;
		this.points = 0;
		this.hasShockWave = false;
	}
	
	@Override
	public int getArmour() {
		return ARMOR;
	}
	
	@Override
	public int getDamage() {
		// Devuelve el dano
		return DAMAGE;
	}
	
	public int getPoints() {
		// Devuelve los puntos del jugador
		return points;
	}
	
	@Override
	public String getSymbol() {
		/* Devuelve el caracter/caracteres ASCII del UCMShip
		Cambia si esta vivo, o muerto
		*/
		String res = "";
		if (isAlive()) {
			res = Messages.UCMSHIP_SYMBOL;
		} else {
			res = Messages.UCMSHIP_DEAD_SYMBOL;
		}
		return res;
	}

	public static String getInfo() {
		// Devuelve la descripcion formateada de UCMShip
		return Messages.ucmShipDescription(getDescription(), DAMAGE, ARMOR);
	}
	
	public static String getDescription() {
		// Devuelve el texto de la descripcion 
		return Messages.UCMSHIP_DESCRIPTION;
	}
	
	public String stateToString() {
		/* Imprime el estado del jugador 
		 (vida, puntos, poder de shockwave)
		 */
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
	
	@Override
	public String toString() {
		// Devuelve la representacion de la UCMShip si se encuentra en la posicion pos
		return getSymbol();
	}
	
	public void enableLaser() {
		// Habilta el laser
		this.canShoot = true;
	}

	public void receivePoints(int points) {
		// Recibe los puntos indicados 
		this.points += points;
	}

	private boolean hasShockWave() {
		// Devuelve true si puede lanzar shockwave
		return hasShockWave;
	}

	public void enableShockWave() {
		// Habilita el shockwave
		this.hasShockWave = true;
	}

	public void disableShockWave() {
		// Deshabilta el shockwave
		this.hasShockWave = false;
	}

	public boolean isCanShoot() {
		// Devuelve true si puede lanzar laser
		return canShoot;
	}
	
	public boolean shootLaser(GameWorld game) {
		// Realiza el disparo del laser
		boolean res = false;
		if (canShoot) {
			// Si puede disparar
			res = true;
			// Crea el nuevo laser en la posicion de UCMShip
			UCMLaser laser = new UCMLaser(game, pos);
			game.addObject(laser);
			this.canShoot = false;
		}
		return res;
	}
	
	public boolean shootSuperLaser(GameWorld game) {
		// Realiza el disparo del super laser
		boolean res = false;
		if (canShoot && this.points >= 5) {
			// Si puede disparar
			res = true;
			// "pagamos" puntos
			this.points -= 5;
			// Crea el nuevo laser en la posicion de UCMShip
			UCMSuperLaser sLaser = new UCMSuperLaser(game, pos);
			game.addObject(sLaser);
			this.canShoot = false;
		}
		return res;
	}
	
	public boolean executeShockWave(GameWorld game) {
		// Ejecuta el disparo del ShockWave
		boolean res = false;
		if (hasShockWave()) {
			disableShockWave();
			ShockWave shockWave = new ShockWave(game);
			game.addObject(shockWave);
			res = true;
		}
		return res;
	}
	
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		return !isAlive();
	}
	
	@Override
	public boolean receiveAttack(Explotion weapon) {
		// Recibe ataque del explotion
		receiveDamage(weapon.getDamage());
		return !isAlive();
	}

	@Override
	public void automaticMove() {
		// vacio
	}
	
	@Override
	public void onDelete() {
		// vacio
	}
}
