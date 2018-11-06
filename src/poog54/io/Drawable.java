/**
 * Drawable objects that return their drawable Image !
 * Note : for the gui, x = col and y = lig !
 */
package poog54.io;

import java.awt.Point;

import gui.GUISimulator;
import gui.ImageElement;

/**
 * This class makes it's childclasses drawable.
 * 
 * @author POO_G54
 *
 */
public abstract class Drawable {

	/* attributes */
	private String imageFilePath;
	private Point coord;
	private int graphicPriority; // 0 for a tile, 1 for a fire, 2 for a robot. 0 is drawn first, 2 is drawn last.

	/* methods */
	protected Drawable(int xCoord, int yCoord) {
		this.coord = new Point(xCoord, yCoord);
	}

	protected Drawable(String ImageFilePath, int graphic_priority, int xCoord, int yCoord) {
		this(xCoord, yCoord);
		this.graphicPriority = graphic_priority;
		this.imageFilePath = ImageFilePath;
	}

	/**
	 * @return the point
	 */
	public Point getCoord() {
		return this.coord;
	}

	/**
	 * @return the graphic_priority
	 */
	public int getGraphic_priority() {
		return graphicPriority;
	}

	/**
	 * @param gui
	 * @param rowsNumber
	 * @return the ImageElement to be drawn by the gui
	 */
	public ImageElement getImage(GUISimulator gui, int rowsNumber) {
		int size = gui.getPanelHeight() / rowsNumber;
		return new ImageElement(coord.x * size, coord.y * size, imageFilePath, size, size, gui);
	}

	/**
	 * @return imageFilePath
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}

	/**
	 * @param object
	 */
	public void setCoord(Drawable object) {
		this.coord = object.getCoord();
	}

	/**
	 * @param p
	 */
	public void setCoord(Point p) {
		this.coord = p;
	}

	/**
	 * @param graphic_priority
	 */
	public void setGraphic_priority(int graphic_priority) {
		this.graphicPriority = graphic_priority;
	}

	/**
	 * @param imageFilePath
	 */
	protected void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	@Override
	public String toString() {
		return "\nWildFire || filepath: \"" + this.getImageFilePath() + "\" | lin: " + this.coord.getY() + " | col: "
				+ this.coord.getX() + " ||";
	}
}
