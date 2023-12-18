package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.logic.exceptions.LaserInFlightException;
import tp1.logic.exceptions.NoShockWaveException;
import tp1.logic.exceptions.NotAllowedMoveException;
import tp1.logic.exceptions.NotEnoughPointsException;
import tp1.logic.exceptions.OffWorldException;
import tp1.view.Messages;

public class UCMShip extends Ship {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;
	private static final List<Move> availableMoves = Arrays.asList(
			Move.LLEFT,
			Move.LEFT,
			Move.RIGHT,
			Move.RRIGHT
	);
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
	
	public static String allowedMoves(String separator) {
		StringBuilder sb = new StringBuilder();
		for (Move m : availableMoves) {
			sb.append(m.name() + separator);
		}
		String moves = sb.toString();
		return moves.substring(0, moves.length()-separator.length());
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

	@Override
	public void move(Move move) throws OffWorldException, NotAllowedMoveException {
		if (!availableMoves.contains(move)) {
			throw new NotAllowedMoveException();
		}
		super.move(move);
	}
	
	public void shootLaser(GameWorld game) throws LaserInFlightException {
		// Realiza el disparo del laser
		if (!canShoot) {
			throw new LaserInFlightException();
		}
		
		// Crea el nuevo laser en la posicion de UCMShip
		UCMLaser laser = new UCMLaser(game, pos);
		game.addObject(laser);
		this.canShoot = false;
	}
	
	public void shootSuperLaser(GameWorld game) throws LaserInFlightException, NotEnoughPointsException {
		// Realiza el disparo del super laser
		if (this.points < UCMSuperLaser.POINTS_REQUIRED) {
			throw new NotEnoughPointsException(this.points);
		}
		
		if (!canShoot) {
			throw new LaserInFlightException();
		}
		
		// "pagamos" puntos
		this.points -= UCMSuperLaser.POINTS_REQUIRED;
		// Crea el nuevo laser en la posicion de UCMShip
		UCMSuperLaser sLaser = new UCMSuperLaser(game, pos);
		game.addObject(sLaser);
		this.canShoot = false;
		
	}
	
	public void executeShockWave(GameWorld game) throws NoShockWaveException {
		// Ejecuta el disparo del ShockWave
		if (!hasShockWave()) {
			throw new NoShockWaveException();
		}
		
		disableShockWave();
		ShockWave shockWave = new ShockWave(game);
		game.addObject(shockWave);
	}
	
	
	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		// Recibe ataque del weapon
		receiveDamage(weapon.getDamage());
		return true;
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
