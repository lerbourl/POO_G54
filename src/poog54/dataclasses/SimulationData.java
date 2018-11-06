package poog54.dataclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import poog54.dataclasses.robots.Robot;
import poog54.io.Drawable;

/**
 * @author POO_G54
 * This class stores all the data of the simulation
 */
public class SimulationData {
	
	/* attributes */
	private List<Robot> robotList;
	private List<WildFire> wfList;
	private TheMap theMap;
	
	/* methods */
	/**
	 * Constructor
	 * @param robotList
	 * @param wfList
	 * @param theMap
	 */
	public SimulationData(List<Robot> robotList, List<WildFire> wfList, TheMap theMap) {
		this.robotList = robotList;
		this.wfList = wfList;
		this.theMap = theMap;
	}
	
	/**
	 * @return an iterator on all the Drawables of the simulation
	 */
	public ListIterator<Drawable> getDrawablesIt() {
		List<Drawable> AllDrawables = new ArrayList<Drawable>();
		AllDrawables.addAll(this.theMap.getDrawableList());
		AllDrawables.addAll(this.robotList);
		AllDrawables.addAll(this.wfList);
		return AllDrawables.listIterator();
	}
	
	/**
	 * @return theMap
	 */
	public TheMap getMap() {
		return theMap;
	}
	
	/**
	 * @return robotList
	 */
	public List<Robot> getRobotList() {
		return robotList;
	}
	
	/**
	 * @return wfList
	 */
	public List<WildFire> getWfList() {
		return wfList;
	}
	
	/**
	 * @param theMap
	 */
	public void setMap(TheMap theMap) {
		this.theMap = theMap;
	}
	
	/**
	 * @param robotList
	 */
	public void setRobotList(List<Robot> robotList) {
		this.robotList = robotList;
	}
	
	/**
	 * @param wfList
	 */
	public void setWfList(List<WildFire> wfList) {
		this.wfList = wfList;
	}
}
