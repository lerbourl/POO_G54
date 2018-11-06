/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.dataclasses.robots.*;
import java.util.zip.DataFormatException;

/**
 * @author POO_G54
 *
 */
public class MoveToWaterEvent extends MoveEvent {
	/**
	 * Constructor
	 * move event : move a robot along its specified target path.
	 * @param robot
	 */
	public MoveToWaterEvent(Robot robot) throws DataFormatException {
		super(robot, robot.getTargetWater().getPath().dequeueFirst());
	}
	
	@Override
	public void execute(Simulator sim) {
		sim.moveRobot(this.robot, this.p);
		try {
			if (robot.getTargetWater().getPath().isEmpty()) {
				// this robot has reached the water tile
				sim.addEvent(new TankupEvent(robot));
			} else {
				// target not reached, continue along the path...
				sim.addEvent(new MoveToWaterEvent(robot));
			}
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		return "Move " + this.robot + " to water tile [" + this.robot.getTargetWater().getLocation().x + "," + this.robot.getTargetWater().getLocation().y + "]";
	}
}