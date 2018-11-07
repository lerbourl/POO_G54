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
	private int emptyField;

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
		this.emptyField = empty_field;
		this.forest = forest;
		this.rock = rock;
		this.water = water;
		this.house = house;
	}
	
	/**
	 * @return the speed on a water tile
	 */
	public int getSpeedWater() {
		return water;
	}
	/**
	 * @return the speed on a forest tile
	 */
	public int getSpeedForest() {
		return forest;
	}
	/**
	 * @return the speed on a rock tile
	 */
	public int getSpeedRock	() {
		return rock;
	}
	/**
	 * @return the speed on a house tile
	 */
	public int getSpeedHouse() {
		return house;
	}
	/**
	 * @return the speed on an empty field tile
	 */
	public int getSpeedEmptyField() {
		return emptyField;
	}
	
	
	
}