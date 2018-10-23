/**
 * This event is generated and queued when a robot reaches his destination
 * (fire tile or water tile)
 */
package poog54.dataclasses.events;

import java.awt.Point;
import java.util.zip.DataFormatException;

import poog54.dataclasses.robots.*;

/**
 * @author POO_G54
 *
 */
public class DestinationReachedEvent extends DiscreteEvent {
	protected Robot robot;
	protected Point destination;

	/**
	 * @return the robot
	 */
	public Robot getRobot() {
		return robot;
	}

	/**
	 * @return the destination
	 */
	public Point getDestination() {
		return destination;
	}

	/**
	 * @param date
	 * @param destination
	 */
	public DestinationReachedEvent(int date, Point destination) throws DataFormatException {
		super(date);
		this.destination = destination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return robot + " has reached " + destination;
	}

}
