package tp1.logic.gameobjects;

import tp1.logic.Position;

public interface GameItem {
	
	public boolean performAttack(GameItem other);
	
	public boolean receiveAttack(EnemyWeapon weapon);
	public boolean receiveAttack(UCMWeapon weapon);
	public boolean receiveAttack(ShockWave weapon);
	public boolean receiveAttack(Explotion weapon);

	public boolean isAlive();
	public boolean isOnPosition(Position pos);
	public Position getPos();

}
