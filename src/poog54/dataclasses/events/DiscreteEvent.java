/**
 * Represents a generic event
 * It provides the event date (number of cycle or simulation step)
 * It provides a generic method execute() that child classes (specific events) must overload
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.io.Simulator;

/**
 * @author POO_G54
 * 
 */
public abstract class DiscreteEvent {

	/**
	 * Event timestamp (iteration / simulation step / cycle number)
	 */
	protected int date;

	/**
	 * Generic constructor
	 * 
	 * @param date
	 */
	public DiscreteEvent(int date) throws DataFormatException {
		if (date < 0) {
			throw new DataFormatException("Bad event timestamp...");
		}
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public int getDate() {
		return this.date;
	}

	/**
	 * Execute actions associated to an event
	 */
	public abstract void execute(Simulator sim);
}
