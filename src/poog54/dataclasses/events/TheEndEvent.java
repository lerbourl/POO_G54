/**
 * 
 */
package poog54.dataclasses.events;

import java.util.zip.DataFormatException;

import poog54.io.Simulator;

/**
 * @author louis
 *
 */
public class TheEndEvent extends DiscreteEvent {

	public TheEndEvent(int date) throws DataFormatException {
		super(date);
	}

	@Override
	public void execute(Simulator sim) {
		sim.clearEvents();
		System.out.println("END OF SIMULATION ! WELL DONE !\nduration : " + (this.date - 1) + "s");
	}

}
