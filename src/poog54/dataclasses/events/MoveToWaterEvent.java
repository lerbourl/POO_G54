/**
 * Represents the displacement of a robot, one tile at a time.
 * It provides information about the source and destination tile.
 */
package poog54.dataclasses.events;

import poog54.io.*;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;

import java.awt.Point;
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
	public MoveToWaterEvent(Robot rob) throws DataFormatException {
		super(rob.getNext_free_time() + (int) rob.getTimeType(rob.getCoord()) + 1,
				rob, rob.getTargetWater().getPath().dequeueFirst());
		this.robot.setNext_free_time(this.date + 1);
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
}