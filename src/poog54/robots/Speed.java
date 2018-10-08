package poog54.robots;
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
	public int empty_field;
	
	/** Forest speed */
	public int forest;

	/** Rock speed */
	public int rock;
	
	/** Water speed */
	public int water;

	/** Urban speed */
	public int house;

	/** Constructor */
	Speed(int empty_field, int forest, int rock, int water, int house) {
		this.empty_field = empty_field;
		this.forest = forest;
		this.rock = rock;
		this.water = water;
		this.house = house;
	}
}