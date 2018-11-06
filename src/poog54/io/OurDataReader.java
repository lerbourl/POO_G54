/**
 * Our data reader, it can read a formated text and fill a data object from it.
 */
package poog54.io;

import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;
import poog54.dataclasses.*;
import poog54.dataclasses.robots.*;
import poog54.enums.*;

/**
 * @author louis
 *
 */
public class OurDataReader {
	/**
	 * @param txtFile
	 * @return
	 * @throws FileNotFoundException
	 * @throws DataFormatException
	 */
	public static SimulationData DataFromFile(String txtFile) throws FileNotFoundException, DataFormatException {
		System.out.println("\n == Reading file" + txtFile);
		OurDataReader reader = new OurDataReader(txtFile);
		SimulationData data = new SimulationData(reader.getRobots(), reader.getIncendies(), reader.map);
		scanner.close();
		System.out.println("\n == End of reading !");
		return data;
	}
	
	// The following is private
	private static Scanner scanner;
	private TheMap map;
	
	private OurDataReader(String txtFile) throws FileNotFoundException {
		scanner = new Scanner(new File(txtFile));
		scanner.useLocale(Locale.US);
		try {
			map = this.getCarte();
		} catch (DataFormatException e) {
			e.printStackTrace();
		}
	}
	

	/*
	 * Accesseurs pour remplir une simulationData
	 */

	/**
	 * Lit et retourne les donnees de la carte.
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private TheMap getCarte() throws DataFormatException {
		ignorerCommentaires();
		try {
			int nbLignes = scanner.nextInt();
			int nbColonnes = scanner.nextInt();
			int tailleCases = scanner.nextInt();
			System.out.println("Carte " + nbLignes + "x" + nbColonnes + "; taille des cases = " + tailleCases);
			Map<Point, Tile> TileMatrix = new LinkedHashMap<Point, Tile>();
			for (int lig = 0; lig < nbLignes; lig++) {
				for (int col = 0; col < nbColonnes; col++) {
					TileMatrix.put(new Point(col, lig), getCase(lig, col));
				}
			}
			return new TheMap(tailleCases, nbLignes, nbColonnes, TileMatrix);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbLignes nbColonnes tailleCases");
		}
	}

	/**
	 * Lit et retourne les donnees d'une case.
	 */
	private Tile getCase(int lig, int col) throws DataFormatException {
		ignorerCommentaires();
		System.out.print("Case (" + lig + "," + col + "): ");
		String chaineNature = new String();
		// NatureTerrain nature;

		try {
			chaineNature = scanner.next();
			// si NatureTerrain est un Enum, vous pouvez recuperer la valeur
			// de l'enum a partir d'une String avec:
			// NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

			verifieLigneTerminee();
			System.out.print("nature = " + chaineNature);
		} catch (NoSuchElementException e) {
			throw new DataFormatException("format de case invalide. " + "Attendu: nature altitude [valeur_specifique]");
		}
		System.out.println();
		/*
		 * Note : for the gui, x = col and y = lig !
		 */
		return new Tile(col, lig, TypeField.valueOf(chaineNature));
	}

	/**
	 * Lit et retourne les donnees des incendies.
	 */
	private List<WildFire> getIncendies() throws DataFormatException {
		ignorerCommentaires();
		List<WildFire> WFList = new ArrayList<WildFire>();
		try {
			int nbIncendies = scanner.nextInt();
			System.out.println("Nb d'incendies = " + nbIncendies);
			for (int i = 0; i < nbIncendies; i++) {
				WFList.add(getIncendie(i));
			}
			return WFList;

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbIncendies");
		}
	}

	/**
	 * Lit et retourne les donnees du i-eme incendie.
	 * 
	 * @param i
	 */
	private WildFire getIncendie(int i) throws DataFormatException {
		ignorerCommentaires();
		System.out.print("Incendie " + i + ": ");
		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			int intensite = scanner.nextInt();
			if (intensite <= 0) {
				throw new DataFormatException("incendie " + i + "nb litres pour eteindre doit etre > 0");
			}
			verifieLigneTerminee();

			System.out.println("position = (" + lig + "," + col + ");\t intensite = " + intensite);
			/*
			 * Note : for the gui, x = col and y = lig !
			 */
			return new WildFire(col, lig, intensite);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format d'incendie invalide. " + "Attendu: ligne colonne intensite");
		}
	}

	/**
	 * Lit et retourne les donnees des robots.
	 */
	private List<Robot> getRobots() throws DataFormatException {
		ignorerCommentaires();
		List<Robot> robotList = new ArrayList<Robot>();
		try {
			int nbRobots = scanner.nextInt();
			System.out.println("Nb de robots = " + nbRobots);
			for (int i = 0; i < nbRobots; i++) {
				robotList.add(getRobot(i));
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbRobots");
		}
		return robotList;
	}

	/**
	 * Lit et retourne les donnees du i-eme robot.
	 * 
	 * @param i
	 */
	private Robot getRobot(int i) throws DataFormatException {
		ignorerCommentaires();
		System.out.print("Robot " + i + ": ");
		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			System.out.print("position = (" + lig + "," + col + ");");
			String type = scanner.next();

			System.out.print("\t type = " + type);

			// lecture eventuelle d'une vitesse du robot (entier)
			System.out.print("; \t vitesse = ");
			String s = scanner.findInLine("(\\d+)"); // 1 or more digit(s) ?
			// pour lire un flottant: ("(\\d+(\\.\\d+)?)");
			Robot robot;
			switch (type) {
			/*
			 * Note : for the gui, x = col and y = lig !
			 */
			case "ROUES":
				robot = new WheeledRob(map, col, lig);
				break;
			case "PATTES":
				robot = new WalkingRob(map, col, lig);
				break;
			case "CHENILLES":
				robot = new TrackedRob(map, col, lig);
				break;
			case "DRONE":
				robot = new DroneRob(map, col, lig);
				break;
			default: // never happen
				throw new DataFormatException("Type de robot non reconnnu");
			}
			if (s == null) {
				System.out.print("valeur par defaut");
			} else {
				int vitesse = Integer.parseInt(s);
				System.out.print(vitesse);
				robot.setSpeed(Integer.parseInt(s));
			}
			verifieLigneTerminee();
			System.out.println();
			return robot;
		} catch (NoSuchElementException e) {
			throw new DataFormatException(
					"format de robot invalide. " + "Attendu: ligne colonne type [valeur_specifique]");
		}
	}

	/** Ignore toute (fin de) ligne commencant par '#' */
	private void ignorerCommentaires() {
		while (scanner.hasNext("#.*")) {
			scanner.nextLine();
		}
	}

	/**
	 * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private void verifieLigneTerminee() throws DataFormatException {
		if (scanner.findInLine("(\\d+)") != null) {
			throw new DataFormatException("format invalide, donnees en trop.");
		}
	}
}
