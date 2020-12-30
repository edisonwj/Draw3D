package org.edisonwj.draw3dtest;

/**
 * Lined class objects define line segments
 * with specification of the line start and end points.
 * 
 * @author William Edison
 * @version 1.02 September 2015
 */
public class Lined {
	Pointd p1;
	Pointd p2;
	
	/**
	 * Constructs a Lined object given start point and end point.
	 * 
	 * @param	p1	Pointd start point of the line
	 * @param	p2	Pointd end point of the line
	 */
	public Lined(Pointd p1, Pointd p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * Constructs a Lined object given coordinates of start and end points.
	 * 
	 * @param	x1	Start x coordinate
	 * @param	y2	Start y coordinate
	 * @param	x2	End x coordinate
	 * @param	y2	End y coordinate
	 */
	private Lined(double x1, double y1, double x2, double y2) {
		this.p1 = new Pointd(x1, y1);
		this.p2 = new Pointd(x2, y2);
	}
		
    /**
     * Provides the Line3D line length (in user coordinate space)
     *
     * @return	length	Double value of the Line length
     */
	public double length() {
		return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)
						+ (p1.y-p2.y)*(p1.y-p2.y));
	}

    /**
     * Returns a String for printing all Lined values
     *
     * @return	String for printing all Lined field values
     */
	public String toString() {
		return "Lined: p1= " + p1.toString() + ", p2= " + p2.toString();
	}
}