/**
 * 
 */
package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.zip.DataFormatException;

import gui.*;
import gui.Simulable;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.Robot;

/**
 * @author louis
 *
 */
public class Simulator implements Simulable {
	private String filepath;
	/** L'interface graphique associée */
	private GUISimulator gui;

	/** Les données de simulation associées */
	private SimulationData data;

	/**
	 * @param gui
	 * @throws DataFormatException 
	 * @throws FileNotFoundException 
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.filepath = filepath;
		loadData();
		gui.setSimulable(this); // association a la gui!
		drawTheMapOnFire();
	}
	
	private void loadData() throws FileNotFoundException, DataFormatException {
		this.data = OurDataReader.DataFromFile(this.filepath);
	}

	private void drawTheMapOnFire() {
		ListIterator<Drawable> drawit = data.getDrawablesIt();
		while (drawit.hasNext()) {
			gui.addGraphicalElement(drawit.next().getImage(gui, data.getMap().getNbLines(), 1));
		}
	}
	private void moveRobot(Robot rob, Point p) {
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		rob.move(p);
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		gui.addGraphicalElement(rob.getImage(gui, data.getMap().getNbLines(), 1));
	}
	private void removeFire(WildFire wf) {
		if(data.getWfList().contains(wf)) {
			Tile t = data.getMap().getTile(wf.getCoord());
			data.getWfList().remove(wf);
			gui.addGraphicalElement(t.getImage(gui, data.getMap().getNbLines(), 1));
		}
	}
	
	@Override
	public void next() {
		Robot rob = data.getRobotList().get(0);
		WildFire wf = data.getWfList().get(0);
		moveRobot(rob, new Point(0,0));
		removeFire(wf);
	}

	@Override
	public void restart() {
		try {
			loadData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
		drawTheMapOnFire();
	}

}
