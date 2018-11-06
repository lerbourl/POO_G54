/**
 * This class stores all the data of the simulation
 * 
 */
package poog54.dataclasses;

import poog54.dataclasses.robots.*;
import poog54.io.Drawable;

import java.util.*;

/**
 * @author lerbourl
 *
 */
public class SimulationData {
	private List<Robot> robotList;
	private List<WildFire> wfList;
	private TheMap theMap;

	/**
	 * Constructor
	 * 
	 */
	public SimulationData(List<Robot> robotList, List<WildFire> wfList, TheMap theMap) {
		this.robotList = robotList;
		this.wfList = wfList;
		this.theMap = theMap;
	}
	/**
	 * 
	 */
	public SimulationData() {
	}
	
	/*
	 * Return an iterator on all the Drawable objects
	 */
	/**
	 * @return
	 */
	public ListIterator<Drawable> getDrawablesIt(){
		List<Drawable> AllDrawables = new ArrayList<Drawable>();
		AllDrawables.addAll(this.theMap.getDrawableList());
		AllDrawables.addAll(this.robotList);
		AllDrawables.addAll(this.wfList);
		return AllDrawables.listIterator();
	}
	
	/**
	 * @return the theMap
	 */
	public TheMap getMap() {
		return theMap;
	}

	/**
	 * @param theMap the theMap to set
	 */
	public void setMap(TheMap theMap) {
		this.theMap = theMap;
	}

	/**
	 * @param robotList the robotList to set
	 */
	public void setRobotList(List<Robot> robotList) {
		this.robotList = robotList;
	}

	/**
	 * @param wfList the wfList to set
	 */
	public void setWfList(List<WildFire> wfList) {
		this.wfList = wfList;
	}

	/**
	 * @return the robotList
	 */
	public List<Robot> getRobotList() {
		return robotList;
	}

	/**
	 * @return the wfList
	 */
	public List<WildFire> getWfList() {
		return wfList;
	}

}
