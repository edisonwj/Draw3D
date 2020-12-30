package org.edisonwj.draw3dtest;

import org.edisonwj.draw3dtest.Pointd;

/**
* Camera class defines a camera object with:
* - location - Pointd coordinates of the camera location on a Wall
* - orientation - angle with respect to the x-axis of central camera view
*
* @author William Edison
* @version 1.08 January 2017
*
*/
public class RoomCamera {
	Pointd location;
	int orientation;

	/**
	 * Constructs a Camera object given a side start and end point.
	 *
	 * @param	location	Pointd location of the camera on a wall
	 * @param	orientation	Angle with respect to the x axis in degrees, 0 through 360
	 *
	 */
	public RoomCamera (Pointd location, int orientation) {
		this.location = location;
		int saveOrientation = orientation;
		if (orientation < 0)
			orientation += 360;
		if (orientation > 360)
			orientation -= 360;
		if (orientation >= 0 && orientation <= 360) {
			this. orientation = orientation;
		}
		else {
			this.orientation = 0;
			System.out.println("Invalid orientation:" + saveOrientation + ", orientation set to zero.");
		}
	}

    /**
     * Returns a String for printing Camera values
     *
     * @return	String for printing Camera field values
     */
    public String toString() {
    	return "location= " + location + ", orientation= " + orientation;
    }
}