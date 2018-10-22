/**
 * 
 */
package poog54.io;

import java.util.ListIterator;

import gui.*;
import gui.Simulable;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.Robot;
import poog54.enums.CardinalPoints;

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
		Robot rob = data.getRobotList().get(0);
		System.out.println(rob.toString());
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		rob.move(CardinalPoints.NORTH);
		System.out.println(rob.toString());
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		gui.addGraphicalElement(rob.getImage(gui, data.getMap().getNbLines(), 1));

	}

	@Override
	public void restart() {
		this.data = this.initialData;
		drawTheMapOnFire();
	}

}
