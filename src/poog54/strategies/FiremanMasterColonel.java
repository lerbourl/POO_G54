/**
 * Strategy Major:
 * -> a Walking robot is always assigned to its closest fire
 * -> a Drone robot is assigned to the fire which is the farthest from all the other robots
 * -> the other robots are assigned to fires which are the closest from the water tiles
 */

package poog54.strategies;

import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import poog54.dataclasses.SimulationData;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterColonel extends FiremanMaster {
	// fires are sorted by proximity with water tiles
	private PriorityQueue<Target> wheeledRobFirePriority;
	private PriorityQueue<Target> trackedRobFirePriority;
	
	public FiremanMasterColonel(Simulator sim) {
		super(sim);
	}
	
	@Override
	public void setData(SimulationData data) {
		super.setData(data);
		
		Robot rob;
		rob = new WheeledRob(this.data.getMap(), 0, 0);
		this.wheeledRobFirePriority = getFirePriorityQueue(rob);
		rob = new TrackedRob(this.data.getMap(), 0, 0);
		this.trackedRobFirePriority = getFirePriorityQueue(rob);
	}
	
	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target assignedFire;

		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			
			// strategy depends on the robot type
			switch (rob.getClass().getName()) {
			
			case "poog54.dataclasses.robots.WalkingRob":
				// get closest fire
				assignedFire = getClosestFire(rob);
				break;
				
			case "poog54.dataclasses.robots.DroneRob":
				// get farthest fire
				assignedFire = getMostIsolatedFire(rob);
				break;

			case "poog54.dataclasses.robots.TrackedRob":
				// get the fire which is the closest from a water tile
				assignedFire = getFireClosestFromWater(rob, this.trackedRobFirePriority);
				break;
				
			case "poog54.dataclasses.robots.WheeledRob":
				// get the fire which is the closest from a water tile
				assignedFire = getFireClosestFromWater(rob, this.wheeledRobFirePriority);
				break;

			default:
				assignedFire = getClosestFire(rob);
			}
			
			// assigns the selected fire to the robot
			System.out.println("t=" + sim.getDate() + ": " + rob + " assigned to " + assignedFire.getFire());
			rob.setTargetFire(assignedFire.getFire());
			try {
				sim.addEvent(new MoveToFireEvent(rob));
			} catch (DataFormatException e) {
				e.printStackTrace();
			}
		}
	}
}
