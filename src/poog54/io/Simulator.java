/**
 * 
 */
package poog54.io;

import java.util.ListIterator;

import gui.*;
import gui.Simulable;
import poog54.dataclasses.*;

/**
 * @author louis
 *
 */
public class Simulator implements Simulable {
	/** L'interface graphique associée */
	private GUISimulator gui;

	/** Les données de simulation associées */
	private SimulationData data;
	
	/** Les données de simulation associées */
	private SimulationData initialData;

	/**
	 * @param gui
	 */
	public Simulator(GUISimulator gui, SimulationData data) {
		this.gui = gui;
		this.data = data;
		this.initialData = data;
		gui.setSimulable(this); // association a la gui!
		System.out.println(data.getWfList().toString());
		drawTheMapOnFire();
	}

	private void drawTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			gui.addGraphicalElement(drawit.next().getImage(gui, data.getMap().getNbLines(), 1));
		}
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		this.data = this.initialData;
		drawTheMapOnFire();
	}

}
