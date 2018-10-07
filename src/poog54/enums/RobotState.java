package poog54.enums;

/**
 * Etat d'un robot pompier
 */

/**
 * @author POO_G54 
 *
 */
public enum RobotState {
	IDLE,
	MOVING_TO_TARGET_FIRE, 
	POURING_WATER,
	MOVING_TO_WATER_RESERVE, 
	TANK_UP,
	PATROL; //future implementation
}
