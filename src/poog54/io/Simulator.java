/**
 * 
 */
package poog54.io;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
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
	private LinkedList<DiscreteEvent> eventList;	//events are ordered in a chronological way

	/**
	 * @param gui
	 * @throws DataFormatException 
	 * @throws FileNotFoundException 
	 */
	public Simulator(GUISimulator gui, String filepath) throws FileNotFoundException, DataFormatException {
		this.gui = gui;
		this.date = -1;
		this.filepath = filepath;
		gui.setSimulable(this); // association a la gui!
		restart();
		this.eventList = new LinkedList<DiscreteEvent>();
		this.eventList.add(new FireExtinguishedEvent(1, this.data.getWfList().get(0)));
		this.eventList.add(new FireExtinguishedEvent(1, this.data.getWfList().get(1)));
		this.eventList.add(new DestinationReachedEvent(1, this.data.getRobotList().get(0),new Point(1,1)));
		this.eventList.add(new DestinationReachedEvent(2, this.data.getRobotList().get(0),new Point(1,2)));
		this.eventList.add(new DestinationReachedEvent(3, this.data.getRobotList().get(0),new Point(1,3)));
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
		return this.eventList.isEmpty();
	}

	/**
	 * insert a new event in the list
	 * the correct location is deduced from the event date
	 */
	public void addEvent(DiscreteEvent event) {
		int eventIndex;
		boolean indexFound;
		ListIterator<DiscreteEvent> eventIterator;

		eventIndex = -1;
		indexFound = false;
		eventIterator = this.eventList.listIterator();
		while(!indexFound) {
			eventIndex=eventIterator.nextIndex();
			if (eventIterator.next().getDate() <= event.getDate()) {
				if(!eventIterator.hasNext()) {
					//insert at the end
					indexFound=true;
					this.eventList.addLast(event);
				}
			}
			else {
				//insert in the appropriate location
				indexFound=true;
				this.eventList.add(eventIndex, event);
			}
		}
	}
	
	/**
	 * runs all events in a chronological order until the current date 
	 */
	public void processEvents() {
		DiscreteEvent event;
		ListIterator<DiscreteEvent> eventIterator;
		eventIterator = this.eventList.listIterator();
		
		//browses events and executes those < current date
		//the event list is sorted in a chronological order
		while(eventIterator.hasNext()) {
			event = eventIterator.next();
			if (event.getDate()<=this.date){
				//this event must be processed and removed from the list
				event.execute(this);
				this.eventList.remove(event);
			}	
		}
	}
	
	@Override
	public void next() {
		if (!endOfSimulation()) {
			this.date++;
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
