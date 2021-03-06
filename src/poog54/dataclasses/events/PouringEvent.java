/**
 * This event is generated when a robot is able to pour its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.WildFire;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class PouringEvent extends DiscreteEvent {
	private Robot robot;
	private WildFire fire;

	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}

	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * @param rob
	 * @throws DataFormatException 
	 */
	public PouringEvent(Robot rob) throws DataFormatException {
		super(rob.getNextFreeTime() + rob.getPourTime());
		this.robot = rob;
		this.fire = rob.getTargetFire().getFire();
		this.robot.setNextFreeTime(this.date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		if (sim.getData().getWfList().contains(this.robot.getTargetFire().getFire())) {
			// the fire is still here
			this.robot.pourOut();
			try {
				if (this.fire.getIntensity() <= 0) {
					// The fire is successfully extinguished !
					System.out.println("t=" + this.date + ": " + this.fire + " is successfully extinguished");
					sim.removeFire(this.fire);
					if (sim.getData().getWfList().isEmpty()) {
						sim.clearAllEvents();
						System.out.println("Bravo ! fin de la simulation.\n duration : " + this.date + "s");
					} else {
						if (robot.getWaterLevel() <= 0) {
							// the tank is empty
							robot.locateClosestWaterTile();
							sim.addEvent(new MoveToWaterEvent(this.robot));
						} else
							sim.getFiremanMaster().orderRobotToFire(robot, sim);
					}
				} else {
					if (robot.getWaterLevel() <= 0) {
						// the tank is empty
						robot.locateClosestWaterTile();
						sim.addEvent(new MoveToWaterEvent(this.robot));
					} else {
						// pour another time
						sim.addEvent(new PouringEvent(this.robot));
					}
				}
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		} else {
			// No fire left, we have to change target
			sim.getFiremanMaster().orderRobotToFire(robot, sim);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.robot.getTargetFire() == null) {
			return "pouring order on " + fire + " canceled for " + this.robot;
		} else {
			return this.robot + " pours " + this.robot.getWaterAmount() + "L of water on " + fire;
		}
	}
}
