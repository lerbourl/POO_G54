/**
 * This class stores all the data of the simulation
 * 
 */
package poog54.dataclasses;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
/**
 * @author lerbourl
 *
 */
public class SimulationData {
	/**
	 *	TODO liste d'incendies et de robots, et carte
	 */
	private List<Robot> robotList;
	private List<WildFire> wfList;
	private Map map;
	
	/** Constructor
	 * 
	 */
	public SimulationData(List<Robot> robotList, List<WildFire> wfList, Map map) {
		this.robotList = robotList;
		this.wfList = wfList;
		this.map = map;
	}
	
	public SimulationData() {
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
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
