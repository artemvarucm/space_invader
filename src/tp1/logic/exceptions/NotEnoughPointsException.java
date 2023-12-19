package tp1.logic.exceptions;

import tp1.logic.gameobjects.UCMSuperLaser;
import tp1.view.Messages;
 
/**
 * 
 * Clase que representa la excepcion que se produce al intentar disparar super laser sin puntos suficientes
 */

public class NotEnoughPointsException extends GameModelException {
	public NotEnoughPointsException(int points) {
		super(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(points, UCMSuperLaser.POINTS_REQUIRED));
	}
}