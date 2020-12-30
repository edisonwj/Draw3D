package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Cone3D class captures information defining a Cone instantiation with specified:
* - xyz location of the cone tip (user coordinate scale)
* - radius (user coordinate scale)
* - height (user coordinate scale)
* - xyz rotations (optional)
* - number of divisions
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Cone3D object is drawn using a custom Cone MeshView constructed upwards from the tip.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Cone3D implements Draw3DDefaults {
	private Point3D p;
	private double radius;
	private double height;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private int coneDivisions = CONE_DIVISIONS;
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, and height.
	 *  
	 * @param	p		Point3D cone tip location in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 */	
	public Cone3D(Point3D p, double radius, double height) {
		this.p = p;
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height, and PhongMaterial.
	 *  
	 * @param	p		Point3D cone tip location in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	m		PhongMaterial determining cone color
	 */	
	public Cone3D(Point3D p, double radius, double height,
					   PhongMaterial m) {
		this(p, radius, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height, and
	 * x, y, z rotations.
	 *   
	 * @param	p		Point3D cone tip location in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Cone3D(Point3D p, double radius, double height,
					   double xr, double yr, double zr) {
		this(p, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D cone tip location in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining cone color
	 */
	public Cone3D(Point3D p, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, radius, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, and height.
	 * 
	 * @param	x		Tip point x coordinate in user coordinates
	 * @param	y		Tip point y coordinate in user coordinates
	 * @param	z		Tip point z coordinate in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 */
	public Cone3D(double x, double y, double z, double radius, double height) {
		this.p = new Point3D(x, y, z);
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height,
	 * and PhongMaterial.
	 * 
	 * @param	x		Tip point x coordinate in user coordinates
	 * @param	y		Tip point y coordinate in user coordinates
	 * @param	z		Tip point z coordinate in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	m		PhongMaterial determining cone color
	 */
	public Cone3D(double x, double y, double z, double radius, double height,
					   PhongMaterial m) {
		this(x, y, z, radius, height);
		this.m = m;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height and
	 * x, y, z rotations.
	 * 
	 * @param	x		Tip point x coordinate in user coordinates
	 * @param	y		Tip point y coordinate in user coordinates
	 * @param	z		Tip point z coordinate in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Cone3D(double x, double y, double z, double radius, double height,
					   double xr, double yr, double zr) {
		this(x, y, z, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Cone3D object given its tip point location, radius, height,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Tip point x coordinate in user coordinates
	 * @param	y		Tip point y coordinate in user coordinates
	 * @param	z		Tip point z coordinate in user coordinates
	 * @param	radius	Radius of the cone base in user coordinates
	 * @param	height	Height of the cone in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining cone color
	 */
	public Cone3D(double x, double y, double z, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, radius, height, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Cone3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Cone: format data string
	 */
	public Cone3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Cone")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.radius = Double.valueOf(st.nextToken()).doubleValue();
			this.height = Double.valueOf(st.nextToken()).doubleValue();
			this.coneDivisions = Integer.valueOf(st.nextToken());
			this.xr = 0.0;
			this.yr = 0.0; 
			this.zr = 0.0;
			if (st.hasMoreTokens()) {
				this.xr = Double.valueOf(st.nextToken()).doubleValue();
				this.yr = Double.valueOf(st.nextToken()).doubleValue();
				this.zr = Double.valueOf(st.nextToken()).doubleValue();
			}
			if (st.hasMoreTokens()) {
				dm = DrawMode.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				cf = CullFace.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				this.m = new PhongMaterial();
				String cs = st.nextToken();
				this.m.setDiffuseColor(Color.web(cs));
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
     * Gets the 3D Cone location
     *
     * @return	p	Point3D location of the tip of the Cone
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Cone radius
     *
     * @return	radius	Double value of the radius in user coordinate space
     */
	public double getRadius() {
		return radius;
	}
	
    /**
     * Gets the Cone height
     *
     * @return	height	Double value of the height in user coordinate space
     */
	public double getHeight() {
		return height;
	}
	
    /**
     * Gets the number of Cone divisions
     *
     * @return	coneDivisions	Integer value of the number of Cone divisions
     */
	public int getConeDivisions() {
		return coneDivisions;
	}
	
    /**
     * Sets the number of Cone divisions
     *
     * @param	coneDivisions	Integer value of the number of Cone divisions
     */
	public void setConeDivisions(int coneDivisions) {
		this.coneDivisions = coneDivisions;
	}
	
    /**
     * Gets the Cone x axis rotation
     *
     * @return	xr	Double value of the Cone x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Cone y axis rotation
     *
     * @return	yr	Double value of the Cone y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Cone z axis rotation
     *
     * @return	zr	Double value of the Cone z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Cone PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Cone
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Cone PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Cone
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Cone DrawMode property
     *
     * @return	dm	DrawMode property of the Cone
     */
	public DrawMode getDrawMode() {
		return dm;
	}

    /**
     * Sets the Cone DrawMode property
     *
     * @param	dm	DrawMode property of the Cone
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Cone CullFace property
     *
     * @return	cf	CullFace property of the Cone
     */
	public CullFace getCullFace() {
		return cf;
	}

    /**
     * Sets the Cone CullFace property
     *
     * @param	cf	CullFace property of the Cone
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Cone3D values for saving to a data file.
     * The String is processed by the Cone3D(String) constructor to recreate
     * a Cone3D object when reading a saved data file.
     * 
     * @return	String capturing all Cone3D field values
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
		return "Cone: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + radius + ", " + height +
			   ", " + coneDivisions +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Cone3D values
     *
     * @return	String for printing all Cone3D field values
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
		return "Cone3D: p= " + p.toString() +
			   ", radius = " + radius + ", height= " + height +
			   ", coneDivisions= " + coneDivisions +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}