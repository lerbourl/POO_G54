/**
 * Strategy Major:
 * -> a Walking robot is always assigned to its closest fire
 * -> a Drone robot is assigned to the fire which is the farthest from all the other robots
 * -> the other robots are assigned to fires which are the closest from the water tiles
 */

package poog54.strategies.masters;

import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import poog54.dataclasses.SimulationData;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

/**
 * @author POO_G54
 *
 */
public class FiremanMasterColonel extends FiremanMaster {
	// fires are sorted by proximity with water tiles
	// the distance cost depends on the robot type
	// so wheeled robots and tracked robots have a different priority queue
	private PriorityQueue<Target> wheeledRobFirePriority;
	private PriorityQueue<Target> trackedRobFirePriority;
	
	/**
	 * @param sim
	 */
	public FiremanMasterColonel(Simulator sim) {
		super(sim);
	}
	
	@Override
	public void setData(SimulationData data) {
		super.setData(data);
		
		// fires are sorted by proximity with water tiles
		// it does not depend on the current position of the robots
		// so the priority queues are initialized once at instantiation time
		Robot rob;
		rob = new WheeledRob(this.data.getMap(), 0, 0);
		this.wheeledRobFirePriority = getFirePriorityQueue(rob);
		rob = new TrackedRob(this.data.getMap(), 0, 0);
		this.trackedRobFirePriority = getFirePriorityQueue(rob);
		this.data.getWfList().sort((wf1, wf2) -> {
			return wf1.getIntensity() > wf2.getIntensity() ? -1 : wf1.getIntensity() < wf2.getIntensity() ? 1 : 0;
		});
	}
	
	@Override
	public void orderRobotToFire(Robot rob, Simulator sim) {
		Target assignedFire;

		if (!this.data.getWfList().isEmpty()) {
			// there are remaining fires
			
			// strategy depends on the robot type
			switch (rob.getClass().getName()) {
			
			case "poog54.dataclasses.robots.WalkingRob":
				// walking robots are assigned to their closest fire
				assignedFire = getClosestFire(rob);
				break;
				
			case "poog54.dataclasses.robots.DroneRob":
				// drone robots are assigned to fires the farthest from other robots
				assignedFire = getMostIsolatedFire(rob);
				break;

			case "poog54.dataclasses.robots.TrackedRob":
				if (rob.getWaterLevel() < rob.getWaterCapacity())
					// pour on the closest fire before tanking up
					// this event can occur when the assigned fire is extinguished by another robot
					assignedFire = getClosestFire(rob);
			else
				// get the fire which is the closest from a water tile
				// /!\ the priority queue depends on the robot type /!\
				assignedFire = getFireClosestFromWater(this.trackedRobFirePriority);
			break;
			
			case "poog54.dataclasses.robots.WheeledRob":
				if (rob.getWaterLevel()<rob.getWaterCapacity())
					// pour on the closest fire before tanking up
					// this event can occur when the assigned fire is extinguished by another robot
					assignedFire=getClosestFire(rob);
				else
					// get the fire which is the closest from a water tile
					// /!\ the priority queue depends on the robot type /!\
					assignedFire = getFireClosestFromWater(this.wheeledRobFirePriority);
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
