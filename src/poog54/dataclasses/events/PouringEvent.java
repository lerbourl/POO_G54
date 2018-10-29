/**
 * This event is generated when a robot is able to pour its water tank
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.dataclasses.WildFire;
import poog54.dataclasses.robots.*;
import poog54.enums.RobotState;
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
	 * @param date
	 * @param robot
	 * @param amountOfWater
	 * @param fire
	 */
	public PouringEvent(int date, Robot robot, WildFire fire) throws DataFormatException {
		super(date);
		this.robot = robot;
		this.fire = fire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		if (this.robot.getTargetFire() == null ||
			this.fire.getIntensity() <=0 ) {
			// the fire assignment is cancelled
			this.robot.setState(RobotState.IDLE);
			this.robot.setTargetFire(null);
		} else {
			// the fire assignment has not been cancelled
			this.robot.setState(RobotState.POURING);
			this.robot.pourOut();
			try {
				if (robot.getWater_level() <= 0) {
					// the tank is empty
					robot.setState(RobotState.MOVING_TO_WATER);
					robot.locateClosestWaterTile();
					try {
						sim.addEvent(new MoveToWaterEvent(this.robot));
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
				} else {
					// pour another time
					// this order will be cancelled if the fire is extinguished
					sim.addEvent(new PouringEvent(this.robot.getNext_free_time() + this.robot.getPourTime(), this.robot, this.fire));
					robot.setNext_free_time(this.robot.getNext_free_time() + this.robot.getPourTime() + 1);
				}
				if (this.fire.getIntensity() <= 0) {
					// this fire is now extinguished
					sim.addEvent(new FireExtinguishedEvent(this.date+1, this.fire, sim));
				}
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
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
		}else{
			return this.robot + " pours " + this.robot.getWater_amount() + "L of water on " + fire;
		}
	}
}
