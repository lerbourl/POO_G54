/**
 * 
 */
package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import gui.*;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.dataclasses.events.*;

/**
 * @author louisPOO_G54
 *
 */
public class Simulator implements Simulable {
	
	private String filepath;
	
	/** L'interface graphique associée */
	private GUISimulator gui;

	/** Les données de simulation associées */
	private SimulationData data;
	
	/** Discrete event management */
	private int date;						//current simulation date
	private PriorityQueue<DiscreteEvent> eventQueue;	//events are ordered in a chronological way

	/**
	 * @param gui
	 * @throws DataFormatException 
	 * @throws FileNotFoundException 
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.date = 0;
		this.filepath = filepath;
		gui.setSimulable(this); // association a la gui!
		restart();
		this.eventQueue = new PriorityQueue<DiscreteEvent>(11, (e1, e2) -> {
			return e1.getDate()<e2.getDate()?-1:e1.getDate()>e2.getDate()?1:0;
		});
		/* à tester : ça marche !
		this.eventQueue.add(new FireExtinguishedEvent(7, this.data.getWfList().get(1)));
		this.eventQueue.add(new FireExtinguishedEvent(7, this.data.getWfList().get(2)));
		this.eventQueue.add(new DestinationReachedEvent(1, this.data.getRobotList().get(0),new Point(1,1)));
		this.eventQueue.add(new DestinationReachedEvent(3, this.data.getRobotList().get(0),new Point(1,3)));
		this.eventQueue.add(new DestinationReachedEvent(2, this.data.getRobotList().get(0),new Point(1,2)));
		this.eventQueue.add(new FireExtinguishedEvent(1, this.data.getWfList().get(0)));*/
		System.out.println(this.eventQueue.toString());
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
	public void moveRobot(Robot rob, Point p) {
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		rob.move(p);
		gui.addGraphicalElement(rob.getTile().getImage(gui, data.getMap().getNbLines(), 1));
		gui.addGraphicalElement(rob.getImage(gui, data.getMap().getNbLines(), 1));
	}
	public void removeFire(WildFire wf) {
		if(!data.getWfList().isEmpty() && data.getWfList().contains(wf)) {
			Tile t = data.getMap().getTile(wf.getCoord());
			data.getWfList().remove(wf);
			gui.addGraphicalElement(t.getImage(gui, data.getMap().getNbLines(), 1));
		}
	}
	
	/**
	 * @return true if there is no more event to process
	 */
	public boolean endOfSimulation() {
		return this.eventQueue.isEmpty();
	}
	/**
	 * runs all events in a chronological order until the current date 
	 */
	public void processEvents() {
		while((this.eventQueue.peek() != null) && (this.eventQueue.peek().getDate() <= this.date)) {
			this.eventQueue.poll().execute(this);
		}
	}
	
	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date++;
			System.out.println(this.date);
			processEvents();
		}
		else System.out.println("\nFin des évenements !");
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
