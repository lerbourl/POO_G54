package poog54.runnables;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;
import poog54.io.*;

public class MapDrawTest {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java TestOurDataReader <nomDeFichier>");
			System.exit(1);
		}
		try {
			GUISimulator gui = new GUISimulator(1024, 1024, Color.BLACK);
			@SuppressWarnings("unused")
			Simulator sim = new Simulator(gui, args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (DataFormatException e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}

}
