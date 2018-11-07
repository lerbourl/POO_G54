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
public class MoveToFireEvent extends MoveEvent {

	/**
	 * Constructor move event : move a robot along its specified target path.
	 * 
	 * @param robot
	 * @throws DataFormatException 
	 */
	public MoveToFireEvent(Robot robot) throws DataFormatException {
		super(robot, robot.getTargetFire().getPath().dequeueFirst());
	}

	@Override
	public void execute(Simulator sim) {
		if (sim.getData().getWfList().contains(this.robot.getTargetFire().getFire())) {
			// The fire is still here
			if (this.robot.getCrossingTileTime(p) != Integer.MAX_VALUE) {
				sim.moveRobot(this.robot, this.p);
				try {
					if (robot.getTargetFire().getPath().isEmpty()) {
						// this robot has reached the fire tile
						sim.addEvent(new PouringEvent(robot));
						robot.setNextFreeTime(date + robot.getPourTime() + 1);
					} else {
						// target not reached, continue along the path...
						sim.addEvent(new MoveToFireEvent(robot));
					}
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			} else {
				// this fire is not reachable
				// remains on the same tile
				this.robot.getTargetFire().getPath().addFirst(this.p);
				try {
					sim.addEvent(new MoveToFireEvent(robot));
				} catch (DataFormatException e) {
					e.printStackTrace();
				}
			}
		} else {
			// No fire left, we have to change target
			sim.getFiremanMaster().orderRobotToFire(robot, sim);
		}
	}

	/*
	 * Print displacement details
	 */
	@Override
	public String toString() {
		if (robot.getTargetFire() == null) {
			return "fire assignment canceled for " + this.robot;
		} else {
			return "Move " + this.robot + " to " + this.robot.getTargetFire().getFire();
		}
	}

}
