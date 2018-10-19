/**
 * 
 */
package poog54.io;

import java.util.ListIterator;

import gui.*;
import gui.Simulable;
import poog54.dataclasses.*;

/**
 * @author louis
 *
 */
public class Simulator implements Simulable{
	/** L'interface graphique associée */
    private GUISimulator gui;
    
    /** Les données de simulation associées */
    private SimulationData data;
    
	/**
	 * @param gui
	 */
	public Simulator(GUISimulator gui, SimulationData data) {
		this.gui = gui;
		this.data = data;
		gui.setSimulable(this);				// association a la gui!
		drawTheMapOnFire();
	}
	
	private void drawTheMapOnFire() {
		int i, j;
		TheMap map = this.data.getMap();
		for (i = 0 ; i < map.getNbLines(); i++) {
			for (j = 0; j < map.getnbColums(); j++) {
				gui.addGraphicalElement(map.getTile(i, j).getImage(gui, map.getNbLines(), 1));
			}
		}
		ListIterator<WildFire> WFit = data.getWfList().listIterator();

	      while(WFit.hasNext()){
	    	  gui.addGraphicalElement(WFit.next().getImage(gui, map.getNbLines(), 1));
	      }
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
