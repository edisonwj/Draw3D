package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
* Arrow3D class captures information defining an Arrow3D instantiation with specified:
* - xyz location of the starting point (user coordinate scale)
* - xyz location of the ending point (arrow point) (user coordinate scale)
* - radius in pixels of the arrow line
* - PhongMaterial (optional)
* 
* Arrows are composite objects created from a JavaFX Cylinder for the line with a 
* custom MeshView Cone forming the arrow tip.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class Arrow3D implements Draw3DDefaults {
	private Point3D p1;
	private Point3D p2;
	private double arrowRadius = ARROW_RADIUS;
	private PhongMaterial m;
	
	/**
	 * Constructs an Arrow3D object given start point and end point.
	 * 
	 * @param	p1	Point3D start point of the arrow
	 * @param	p2	Point3D end point of the arrow
	 */
	public Arrow3D(Point3D p1, Point3D p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.m = null;
	}
	
	/**
	 * Constructs an Arrow3D object given start point, end point, and PhongMaterial.
	 * 
	 * @param	p1	Point3D start point of the arrow
	 * @param	p2	Point3D end point of the arrow
	 * @param	m	PhongMaterial determining arrow color
	 */
	public Arrow3D(Point3D p1, Point3D p2, PhongMaterial m) {
		this(p1, p2);
		this.m = m;
	}
	
	/**
	 * Constructs an Arrow3D object given start point and end point.
	 * 
	 * @param	x1	x coordinate of the start point
	 * @param	y1	y coordinate of the start point
	 * @param	z1	z coordinate of the start point
	 * @param	x2	x coordinate of the end point
	 * @param	y2	y coordinate of the end point
	 * @param	z2	z coordinate of the end point
	 */
	public Arrow3D(double x1, double y1, double z1,
			  	  double x2, double y2, double z2) {
		this.p1 = new Point3D(x1, y1, z1);
		this.p2 = new Point3D(x2, y2, z2);
		this.m = null;
	}
	
	/**
	 * Constructs an Arrow3D object given start point, end point, and PhongMaterial.
	 * 
	 * @param	x1	x coordinate of the start point
	 * @param	y1	y coordinate of the start point
	 * @param	z1	z coordinate of the start point
	 * @param	x2	x coordinate of the end point
	 * @param	y2	y coordinate of the end point
	 * @param	z2	z coordinate of the end point
	 * @param	m	PhongMaterial determining arrow color
	 */
	public Arrow3D(double x1, double y1, double z1,
			  double x2, double y2, double z2,
			  PhongMaterial m) {
		this(x1, y1, z1, x2, y2, z2);
		this.m = m;
	}
	
	/**
	 * Constructs an Arrow3D object given specification of the parameters
	 * in Save Data File format.
	 * 
	 * @param	lineString	Arrow: format data string
	 */
	public Arrow3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Arrow")) {
			double x1 = Double.valueOf(st.nextToken()).doubleValue();
			double y1 = Double.valueOf(st.nextToken()).doubleValue();
			double z1 = Double.valueOf(st.nextToken()).doubleValue();
			this.p1 = new Point3D(x1, y1, z1);
			double x2 = Double.valueOf(st.nextToken()).doubleValue();
			double y2 = Double.valueOf(st.nextToken()).doubleValue();
			double z2 = Double.valueOf(st.nextToken()).doubleValue();
			this.p2 = new Point3D(x2, y2, z2);
			this.arrowRadius = Double.valueOf(st.nextToken()).doubleValue();
			if (st.hasMoreTokens()) {
				this.m = new PhongMaterial();
				this.m.setDiffuseColor(Color.web(st.nextToken()));
				if (st.hasMoreTokens()) {
					this.m.setSpecularColor(Color.web(st.nextToken()));
				}
			}
			else {
				this.m = null;
			}
		}
	}
	
    /**
     * Gets the Arrow3D starting point
     *
     * @return	p	Point3D location of Arrow starting point
     */
	public Point3D getp1() {
		return p1;
	}
	
    /**
     * Gets the Arrow3D ending point
     *
     * @return	p	Point3D location of Arrow end (tip)
     */
	public Point3D getp2() {
		return p2;
	}
	
    /**
     * Gets the Arrow3D PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Arrow
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Arrow3D PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Arrow
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Arrow3D line radius (in pixels)
     *
     * @return	arrowRadius	Double value of the Arrow radius
     */
	public double getArrowRadius() {
		return arrowRadius;
	}
	
    /**
     * Sets the Arrow3D line radius (in pixels)
     *
     * @param	arrowRadius	Double value of the Arrow radius
     */
	public void setArrowRadius(double arrowRadius) {
		this.arrowRadius = arrowRadius;
	}
	
    /**
     * Provides the Arrow3D line length (in user coordinate space)
     *
     * @return	length	Double value of the Arrow length
     */
	public double length() {
		return Math.sqrt((p1.getX()-p2.getX())*(p1.getX()-p2.getX())
						+ (p1.getY()-p2.getY())*(p1.getY()-p2.getY())
						+ (p1.getZ()-p2.getZ())*(p1.getZ()-p2.getZ()));
	}
	
    /**
     * Provides the Arrow3D line mid-point (in user coordinate space)
     *
     * @return	midpoint	Point3D location of the Arrow midpoint
     */
	public Point3D midpoint() {
		double x, y, z;		
		x = (p1.getX()+p2.getX()) / 2.0;
		y = (p1.getY()+p2.getY()) / 2.0;
		z = (p1.getZ()+p2.getZ()) / 2.0;

		return new Point3D(x, y, z);
	}
	
    /**
     * Provides the projected Arrow3D angle between xz axes
     *
     * @return	theta	Double value of angle of projected xz arrow line
     */
	public double theta() {
		// Relocate line start point to origin
		double x = p2.getX() - p1.getX();
		double y = p2.getY() - p1.getY();
		double z = p2.getZ() - p1.getZ();
		
		// Compute theta
		return Math.atan2(y, x);
	}
	
    /**
     * Provides the Arrow3D angle with y axis
     *
     * @return	phi	Double value of angle of arrow line to y axis
     */
	public double phi() {
		double x, y, z;
		// Relocate line start point to origin
		x = p2.getX() - p1.getX();
		y = p2.getY() - p1.getY();
		z = p2.getZ() - p1.getZ();
		
		// Compute phi
		double rho = Math.sqrt(x*x + y*y + z*z);
		return Math.acos(z/rho);
	}
	
    /**
     * Creates a new Arrow3D object scaled by the value t
     * @param	t	double scalar value to multiply Arrow line components 
     * @return	Arrow3D scaled by t
     */
	public Arrow3D multiply(double t) {
		double x2 = t*(p2.getX()-p1.getX()) + p1.getX();
		double y2 = t*(p2.getY()-p1.getY()) + p1.getY();
		double z2 = t*(p2.getZ()-p1.getZ()) + p1.getZ();
		Point3D newp = new Point3D(x2, y2, z2);
		Arrow3D newa = new Arrow3D(p1, newp);
		newa.setMaterial(m);
		newa.setArrowRadius(arrowRadius);
		return newa;
	}
	
    /**
     * Returns a String containing all Arrow3D values for saving to a data file.
     * The String is processed by the Arrow3D(String) constructor to recreate a Arrow3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Arrow3D field values
     */
	public String outString() {
		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
		}
		return "Arrow: " + p1.getX() + ", " + p1.getY() + ", " + p1.getZ() +
			   ", " + p2.getX() + ", " + p2.getY() + ", " + p2.getZ() +
			   ", " + arrowRadius + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Arrow3D values
     *
     * @return	String for printing all Arrow3D field values
     */
	public String toString() {
		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = ", m= " + m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
		}
		else {
			ms = ", m= " + m;
		}
		return "Arrow3D: p1= " + p1.toString() + ", p2= " + p2.toString()
				+ " arrowRadius= " + arrowRadius + ms;
	}
}