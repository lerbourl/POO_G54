/**
 * 
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.io.Simulator;

/**
 * @author POO_G54
 */
public class TheEndEvent extends DiscreteEvent {

	public TheEndEvent(int date) throws DataFormatException {
		super(date);
	}

	@Override
	public void execute(Simulator sim) {
		sim.clearAllEvents();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "END OF SIMULATION ! WELL DONE !\nduration : " + (this.date - 1) + "s";
	}
}
