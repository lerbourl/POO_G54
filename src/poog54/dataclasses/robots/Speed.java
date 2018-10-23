package poog54.dataclasses.robots;
/**
 * Provide every speed that can reach a robot
 * A fire does not change the speed of a particular field
 */

/**
 * @author POO_G54
 * 
 */
public class Speed {

	/** Empty field speed */
	private int empty_field;

	/** Forest speed */
	private int forest;

	/** Rock speed */
	private int rock;

	/** Water speed */
	private int water;

	/** Urban speed */
	private int house;

	/** Constructor */
	Speed(int empty_field, int forest, int rock, int water, int house) {
		this.empty_field = empty_field;
		this.forest = forest;
		this.rock = rock;
		this.water = water;
		this.house = house;
	}
	
	public int getSpeedWater() {
		return water;
	}
	public int getSpeedForest() {
		return forest;
	}
	public int getSpeedRock	() {
		return rock;
	}
	public int getSpeedHouse() {
		return house;
	}
	public int getSpeedEmptyField() {
		return empty_field;
	}
	
	
	
}