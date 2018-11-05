package poog54.runnables;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;

import poog54.io.*;

public class SolveMapWithStrategy {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Syntax: java SolveMapWithStrategy <map_file_path> <fireman_rank>");
			System.out.println("<map_file_path>: path to .map file");
			System.out.println("<fireman_rank>: first_class / sergeant / captain / major / colonel / general\n");
			System.out.println("The firefighters' strategy depends on the rank of their chief: ");
			System.out.println("\t[first_class]: all robots are affected to the 1st available fire");
			System.out.println("\t[sergeant]: a robot is assigned to the 1st unassigned fire or the fire with the fewest assigned robots");
			System.out.println("\t[captain]: a robot is assigned to its closest fire");
			System.out.println("\t[major]: Walking robots assigned to their closest fires, Drone robots assigned to their farthest fires, other robots assigned to fires with the fewest affected robots");
			System.out.println("\t[colonel]: not implemented");
			System.out.println("\t[general]: not implemented");
			System.exit(1);
		}
		try {
			GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
			@SuppressWarnings("unused")
			Simulator sim = new Simulator(gui, args[0], args[1]);
		} catch (FileNotFoundException e) {
			System.out.println("\n\t**map file not found\"" + args[0] + "\"");
		} catch (DataFormatException e) {
			System.out.println("\n\t**bad map file \"" + args[0] + "\": " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("\n\t**strategy not found \"" + args[0] + "\": " + e.getMessage());
		}
	}

}
