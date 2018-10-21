/**
 * This event must be the first to be instantiated.
 * It triggers the execution of the fire extinction strategy,
 * and generate new events in the list
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

/**
 * @author POO_54
 *
 */
public class CarryOutStrategy extends Event {

	/**
	 * @param date
	 */
	public CarryOutStrategy(int date) throws DataFormatException {
		super(date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute() {
		// @TODO
	}

	/*
	 *
	 */
	@Override
	public String toString() {
		return "Carry out strategy";
	}

}
