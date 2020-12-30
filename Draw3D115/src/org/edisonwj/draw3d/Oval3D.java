package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Oval3D class captures information defining an Oval instantiation with specified:
* - xyz location of the oval center (user coordinate scale)
* - major radius (user coordinate scale)
* - minor radius (user coordinate scale)
* - xyz rotations (optional)
* - number of divisions
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Oval3D object is drawn with a custom Oval MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Oval3D implements Draw3DDefaults {
	private Point3D p;
	private double majorRadius;
	private double minorRadius;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private int ovalDivisions = OVAL_DIVISIONS;
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs an Oval3D object given its center point location, major radius, and minor radius.
	 *  
	 * @param	p			Point3D oval center in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 */	
	public Oval3D(Point3D p, double majorRadius, double minorRadius) {
		this.p = p;
		this.majorRadius = majorRadius;
		this.minorRadius = minorRadius;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs an Oval3D object given its center point location, major radius, minor radius
	 * and PhongMaterial.
	 *  
	 * @param	p			Point3D oval center in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	m			PhongMaterial determining oval color
	 */	
	public Oval3D(Point3D p, double majorRadius, double minorRadius,
					   PhongMaterial m) {
		this(p, majorRadius, minorRadius);
		this.m = m;
	}
	
	/**
	 * Constructs an Oval3D object given its center point location, major radius, minor radius
	 * and x, y, z rotations.
	 *  
	 * @param	p			Point3D oval center in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	xr			Rotation in degrees about the x axis
	 * @param	yr			Rotation in degrees about the y axis
	 * @param	zr			Rotation in degrees about the z axis
	 */	
	public Oval3D(Point3D p, double majorRadius, double minorRadius,
					   double xr, double yr, double zr) {
		this(p, majorRadius, minorRadius);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs an Oval3D object given its center point location, major radius, minor radius,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p			Point3D oval center in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	xr			Rotation in degrees about the x axis
	 * @param	yr			Rotation in degrees about the y axis
	 * @param	zr			Rotation in degrees about the z axis
	 * @param	m			PhongMaterial determining oval color
	 */	
	public Oval3D(Point3D p, double majorRadius, double minorRadius,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(p, majorRadius, minorRadius, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs an Oval3D object given its center point coordinates, radius, and height.
	 * 
	 * @param	x			Center point x coordinate in user coordinates
	 * @param	y			Center point y coordinate in user coordinates
	 * @param	z			Center point z coordinate in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 */
	public Oval3D(double x, double y, double z, double majorRadius, double minorRadius) {
		this.p = new Point3D(x, y, z);
		this.majorRadius = majorRadius;
		this.minorRadius = minorRadius;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs an Oval3D object given its center point coordinates, radius, height
	 * and PhongMaterial.
	 * 
	 * @param	x			Center point x coordinate in user coordinates
	 * @param	y			Center point y coordinate in user coordinates
	 * @param	z			Center point z coordinate in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	m			PhongMaterial determining oval color
	 */
	public Oval3D(double x, double y, double z, double majorRadius, double minorRadius,
					   PhongMaterial m) {
		this(x, y, z, majorRadius, minorRadius);
		this.m = m;
	}
	
	/**
	 * Constructs an Oval3D object given its center point coordinates, radius, height
	 * and x, y, z rotations.
	 * 
	 * @param	x			Center point x coordinate in user coordinates
	 * @param	y			Center point y coordinate in user coordinates
	 * @param	z			Center point z coordinate in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	xr			Rotation in degrees about the x axis
	 * @param	yr			Rotation in degrees about the y axis
	 * @param	zr			Rotation in degrees about the z axis
	 */
	public Oval3D(double x, double y, double z, double majorRadius, double minorRadius,
					   double xr, double yr, double zr) {
		this(x, y, z, majorRadius, minorRadius);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs an Oval3D object given its center point coordinates, radius, height,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x			Center point x coordinate in user coordinates
	 * @param	y			Center point y coordinate in user coordinates
	 * @param	z			Center point z coordinate in user coordinates
	 * @param	majorRadius	Major radius of the oval (ellipse) in user coordinates
	 * @param	minorRadius	Minor radius of the oval (ellipse) in user coordinates
	 * @param	xr			Rotation in degrees about the x axis
	 * @param	yr			Rotation in degrees about the y axis
	 * @param	zr			Rotation in degrees about the z axis
	 * @param	m			PhongMaterial determining oval color
	 */
	public Oval3D(double x, double y, double z, double majorRadius, double minorRadius,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		this(x, y, z, majorRadius, minorRadius, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs an Oval3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Oval: format data string
	 */
	public Oval3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Oval")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.majorRadius = Double.valueOf(st.nextToken()).doubleValue();
			this.minorRadius = Double.valueOf(st.nextToken()).doubleValue();
			this.ovalDivisions = Integer.valueOf(st.nextToken());
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
     * Gets the 3D Oval location
     *
     * @return	p	Point3D location of the center of the Oval
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Oval MajorRadius
     *
     * @return	majorRadius	Double value of the major radius in user coordinate space
     */
	public double getMajorRadius() {
		return majorRadius;
	}
	
    /**
     * Gets the Oval MinorRadius
     *
     * @return	minorRadius	Double value of the minor radius in user coordinate space
     */
	public double getMinorRadius() {
		return minorRadius;
	}
	
    /**
     * Gets the number of Oval divisions
     *
     * @return	ovalDivisions	Integer value of the number of Oval divisions
     */
	public int getOvalDivisions() {
		return ovalDivisions;
	}
	
    /**
     * Sets the number of Oval divisions
     *
     * @param	ovalDivisions	Integer value of the number of Oval divisions
     */
	public void setOvalDivisions(int ovalDivisions) {
		this.ovalDivisions = ovalDivisions;
	}
	
    /**
     * Gets the Oval x axis rotation
     *
     * @return	xr	Double value of the Oval x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Oval y axis rotation
     *
     * @return	yr	Double value of the Oval y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Oval z axis rotation
     *
     * @return	zr	Double value of the Oval z axis rotation
     */
	public double getZr() {
		return zr;
	}
	
    /**
     * Gets the Oval PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Oval
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Oval PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Oval
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Oval DrawMode property
     *
     * @return	dm	DrawMode property of the Oval
     */
	public DrawMode getDrawMode() {
		return dm;
	}

    /**
     * Sets the Oval DrawMode property
     *
     * @param	dm	DrawMode property of the Oval
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Oval CullFace property
     *
     * @return	cf	CullFace property of the Oval
     */
	public CullFace getCullFace() {
		return cf;
	}

    /**
     * Sets the Oval CullFace property
     *
     * @param	cf	CullFace property of the Oval
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Oval3D values for saving to a data file.
     * The String is processed by the Oval3D(String) constructor to recreate
     * an Oval3D object when reading a saved data file.
     * 
     * @return	String capturing all Oval3D field values
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
		return "Oval: " + p.getX() + ", " + p.getY() + ", " + p.getZ() + ", " + majorRadius + ", " + minorRadius +
				", " + ovalDivisions +
				", " + xr + ", " + yr + ", " + zr + ", " +
			    ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Oval3D values
     *
     * @return	String for printing all Oval3D field values
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
		return "Oval3D: p= " + p.toString() + ", majorRadius = " + majorRadius + ", minorRadius= " + minorRadius +
				", ovalDivisions= " + ovalDivisions +
				", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
				", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}