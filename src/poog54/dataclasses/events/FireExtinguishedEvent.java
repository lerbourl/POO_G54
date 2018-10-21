/**
 * This event is generated and queued when robots has poured enough water to extinguish a fire
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;
import poog54.dataclasses.*;

/**
 * @author POO_54
 *
 */
public class FireExtinguishedEvent extends Event {
	private WildFire fire;

	/**
	 * @return the fire
	 */
	public WildFire getFire() {
		return fire;
	}

	/**
	 * @param date
	 * @param fire
	 * @throws DataFormatException
	 */
	public FireExtinguishedEvent(int date, WildFire fire) throws DataFormatException {
		super(date);
		this.fire = fire;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute() {
		// @TODO this.fire.getTile().draw();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return fire + " has been extinguished";
	}

}
