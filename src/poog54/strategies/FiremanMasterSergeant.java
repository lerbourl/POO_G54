/**
 * Strategy First Class:
 * the first available robot is assigned to the first available fire,
 * whatever are their relative positions on the map
 */

package poog54.strategies;

import java.util.*;
import java.util.zip.DataFormatException;

import poog54.enums.*;
import poog54.dataclasses.*;
import poog54.dataclasses.events.MoveToFireEvent;
import poog54.dataclasses.robots.*;
import poog54.io.Simulator;

public class FiremanMasterSergeant extends FiremanMaster {

	public FiremanMasterSergeant(Simulator sim) {
		super(sim);
	}

	@Override
	public void performStrategy() {
		//@TODO
	}
}
