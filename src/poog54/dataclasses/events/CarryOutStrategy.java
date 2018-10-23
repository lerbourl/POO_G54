/**
 * This event must be the first to be instantiated.
 * It triggers the execution of the fire extinction strategy,
 * and generate new events in the list
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.io.*;
import poog54.strategies.*;

/**
 * @author POO_G54
 *
 */
public class CarryOutStrategy extends DiscreteEvent {

	protected FiremanMaster master;
	/**
	 * @param date
	 */
	public CarryOutStrategy(int date, FiremanMaster master) throws DataFormatException {
		super(date);
		this.master = master;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see poog54.dataclasses.events.Event#execute()
	 */
	@Override
	public void execute(Simulator sim) {
		this.master.initStrategy();
	}

	/*
	 *
	 */
	@Override
	public String toString() {
		return "Carry out strategy";
	}

}
