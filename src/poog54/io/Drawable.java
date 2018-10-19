/**
 * Méthode commune aux objets dessinables !
 */
package poog54.io;

import gui.GUISimulator;
import gui.ImageElement;

/**
 * @author louis
 *
 */
public abstract class Drawable {
	
	private String imageFilePath;
	private int xCoord, yCoord;
	
	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}
	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}
	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	protected Drawable(String ImageFilePath, int xCoord, int yCoord) {
		this.imageFilePath = ImageFilePath;
		this. xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public ImageElement getImage(GUISimulator gui, int rowsNumber, int factor) {
		int size = gui.getPanelHeight()/rowsNumber/factor;
		return new ImageElement(xCoord * size, yCoord * size, imageFilePath, size, size, gui);
	}
	
}
