package poog54.runnables;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;

import poog54.io.*;

/**
 * This is the main class
 * 
 * @author POO_G54
 *
 */
public class SolveMapWithStrategy {

	/**
	 * @param args the file path, the strategy and the speedup factor to speed the simulation up
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Syntax: java SolveMapWithStrategy <map_file_path> <fireman_rank> <speedup_factor>");
			System.out.println("<map_file_path>: path to .map file");
			System.out.println("<fireman_rank>: first_class / sergeant / captain / major / colonel / general\n");
			System.out.println("The firefighters' strategy depends on the rank of their chief: ");
			System.out.println("\t[first_class]: all robots are affected to the 1st available fire");
			System.out.println("\t[sergeant]: a robot is assigned to the 1st unassigned fire or the fire with the fewest assigned robots");
			System.out.println("\t[captain]: a robot is assigned to its closest fire");
			System.out.println("\t[major]: Walking robots assigned to their closest fires, Drone robots assigned to their farthest fires, other robots assigned to fires with the fewest affected robots");
			System.out.println("\t[colonel]: Walking robots assigned to their closest fires, Drone robots assigned to the most isolated fires, other robots assigned to the fires closest to water tiles");
			System.out.println("\t[general]: not implemented");
			System.out.println("<speedup_factor>: factor that accelerates the simulation");
			System.exit(1);
		}
		try {
			GUISimulator gui = new GUISimulator(920, 920, Color.BLACK);
			@SuppressWarnings("unused")
			Simulator sim = new Simulator(gui, args[0], args[1], Integer.parseInt(args[2]));
		} catch (FileNotFoundException e) {
			System.out.println("\n\t**map file not found\"" + args[0] + "\": " + e.getMessage());
		} catch (DataFormatException e) {
			System.out.println("\n\t**bad map file \"" + args[0] + "\": " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("\n\t**strategy not found \"" + args[0] + "\": " + e.getMessage());
		}
	}

}
