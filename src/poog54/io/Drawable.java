/**
 * Drawable objects that return their drawable Image !
 * Note : for the gui, x = col and y = lig !
 */
package poog54.io;


import java.awt.Point;
import gui.GUISimulator;
import gui.ImageElement;
import poog54.enums.CardinalPoints;

/**
 * @author louis
 *
 */
public abstract class Drawable {

	private String imageFilePath;
	private Point coord;

	protected Drawable(int xCoord, int yCoord) {
		this.coord = new Point(xCoord, yCoord);
	}
	protected Drawable(String ImageFilePath, int xCoord, int yCoord) {
		this(xCoord, yCoord);
		this.imageFilePath = ImageFilePath;
	}
	

	/**
	 * @return the imageFilePath
	 */
	public String getImageFilePath() {
		return imageFilePath;
	}
	/**
	 * @param imageFilePath the imageFilePath to set
	 */
	protected void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	/**
	 * @return the point
	 */
	public Point getCoord() {
		return this.coord;
	}
	
	public void setCoord(int xCoord, int yCoord) {
		this.coord.x = xCoord;
		this.coord.y = yCoord;
	}
	
	public void setCoord(Drawable object) {
		this.coord = object.getCoord();
	}
	
	public int getx() {
		return this.coord.x;
	}
	public int gety() {
		return this.coord.y;
	}

	protected void translate(CardinalPoints dir) {
		switch (dir) {

		/** North */
		case NORTH:
			this.coord.translate(0, -1);

			/** South */
		case SOUTH:
			this.coord.translate(0, 1);
			
			/** East */
		case EAST:
			this.coord.translate(1, 0);
			
			/** West */
		case WEST:
			this.coord.translate(-1, 0);
		}
	}

	public ImageElement getImage(GUISimulator gui, int rowsNumber, int factor) {
		int size = gui.getPanelHeight() / rowsNumber / factor;
		return new ImageElement(coord.x * size, coord.y * size, imageFilePath, size, size, gui);
	}

}
