package poog54.runnables;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import gui.GUISimulator;
import poog54.dataclasses.*;
import poog54.io.*;

public class TestAfficheMap {

	public static void main(String[] args) {
		if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            SimulationData data = LecteurDonnees.DataFromFile(args[0]);
            GUISimulator gui = new GUISimulator(1024, 1024, Color.BLACK);
            @SuppressWarnings("unused")
            Simulator sim = new Simulator(gui, data);
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
	}

}
