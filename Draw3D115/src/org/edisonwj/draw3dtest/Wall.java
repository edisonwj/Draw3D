package org.edisonwj.draw3dtest;

import org.edisonwj.draw3dtest.Pointd;

/**
* Wall class defines a wall, either external surrounding a space, or internal
* separating the space. A Wall is defined by a line with start and end points.
* 
* The line specified is taken to be the lower side of a horizontal wall or the
* left side of a vertical wall. This has the effect that for external walls,
* the top wall is specified as located at (0,hiboundY-1) to (hiboundX,hiboundY-1).
* Similarly the right external wall is (hiboundX-1,0) to (hiboundX-1,hiboundY).
* 
* Walls have a thickness of one unit. So the lines defining the four sides of 
* a wall can be generated from the single side.
* 
* Lines must be horizontal or vertical. Diagonal or angled walls are not allowed.
* 
* The start coordinates must be less than the end coordinates.
* 
* @author William Edison
* @version 1.02 September 2015
* 
*/
public class Wall {

    Pointd start;
    Pointd end;
    
	/**
	 * Constructs a Wall object given a side start and end point.
	 * 
	 * @param	start	Pointd start point of the side
	 * @param	end		Pointd end point of the side
	 * 
	 * For a horizontal wall the side specified is the lower side.
	 * For a vertical wall the side specified is the left side.
	 */
    public Wall (Pointd start, Pointd end) {
    	this.start = start;
    	this.end = end;
		// Check if legal: the end's x or y have to as high as the start's.
		if ((end.getx() < start.getx()) || (end.gety() < start.gety())) {
		    // Proper order.
		    System.out.println ("ERROR: illegal wall coords: start=" + start + " end=" + end);
		    System.exit (0);
		}
		if ((start.getx() != end.getx()) && (start.gety() != end.gety())) {
		    // Can't do diagonals.
		    System.out.println ("ERROR: illegal wall coords: start=" + start + " end=" + end);
		    System.exit (0);
		}
    }

    /**
     * Returns a String for printing Wall values
     *
     * @return	String for printing Wall field values
     */
    public String toString () {
    	return "Wall: " + start + " to " + end;
    }
}
