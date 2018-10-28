package poog54.runnables;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import poog54.dataclasses.SimulationData;
import poog54.io.*;
import poog54.strategies.Path;

public class AStarTest {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java TestOurDataReader <nomDeFichier>");
			System.exit(1);
		}
		try {
			SimulationData data = OurDataReader.DataFromFile(args[0]);
			
			//rob #1
			Path p;
			for (int i = 0; i < 8; i++) {
				p = data.getRobotList().get(0).getPathToPoint(new Point(i,3));
				System.out.println(i + " 3 " + p.toString());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (DataFormatException e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}

}
