package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Box3D class captures information defining a JavaFX Box instantiation with specified:
* - xyz location of center point (user coordinate scale)
* - width (user coordinate scale)
* - height (user coordinate scale)
* - depth (user coordinate scale)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* Box3D objects are drawn as javafx.scene.shape.box objects.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.14 March 2018
* 
*/
public class Box3D implements Draw3DDefaults {
	private Point3D p;
	private double width;
	private double height;
	private double depth;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Box3D object given its center point, width, height, and depth.
	 * 
	 * @param	p		Point3D center point of the box
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 */
	public Box3D(Point3D p, double width, double height, double depth) {
		this.p = p;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Box3D object given its center point, width, height, depth, and PhongMaterial.
	 * 
	 * @param	p		Point3D center point of the box
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	m		PhongMaterial determining box color
	 */
	public Box3D(Point3D p, double width, double height, double depth,
			 PhongMaterial m) {
		this(p, width, height, depth);
		this.m = m;
	}
	
	/**
	 * Constructs a Box3D object given its center point, width, height, depth,
	 * and x, y, z rotations.
	 * 
	 * @param	p		Point3D center point of the box
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Box3D(Point3D p, double width, double height, double depth,
		 	 double xr, double yr, double zr) {
		this(p, width, height, depth);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Box3D object given its center point, width, height, depth,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	p		Point3D center point of the box
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining box color
	 */
	public Box3D(Point3D p, double width, double height, double depth,
	 	 	double xr, double yr, double zr,
	 	 	PhongMaterial m) {
		this(p, width, height, depth, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Box3D object given its center point coordinates, width, height, and depth.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 */
	public Box3D(double x, double y, double z,
				 double width, double height, double depth) {
		this.p = new Point3D(x, y, z);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m = null;
	}
	
	/**
	 * Constructs a Box3D object given its center point coordinates, width, height, depth,
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	m		PhongMaterial determining box color
	 */
	public Box3D(double x, double y, double z,
			 double width, double height, double depth,
			 PhongMaterial m) {
		this(x, y, z, width, height, depth);
		this.m = m;
	}
	
	/**
	 * Constructs a Box3D object given its center point coordinates, width, height, depth,
	 * and x, y, z rotations.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Box3D(double x, double y, double z,
			 double width, double height, double depth,
			 double xr, double yr, double zr) {
		this(x, y, z, width, height, depth);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = null;
	}
	
	/**
	 * Constructs a Box3D object given its center point coordinates, width, height, depth,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	width	Width of the box in user coordinates
	 * @param	height	Height of the box in user coordinates
	 * @param	depth	Depth of the box in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining box color
	 */
	public Box3D(double x, double y, double z,
				 double width, double height, double depth,
				 double xr, double yr, double zr,
				 PhongMaterial m) {
		this(x, y, z, width, height, depth, xr, yr, zr);
		this.m = m;
	}
	
	/**
	 * Constructs a Box3D object given specification of the parameters
	 * in Save Data File format.
	 * 
	 * @param	lineString	Box: format data string
	 */
	public Box3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Box")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.width = Double.valueOf(st.nextToken()).doubleValue();
			this.height = Double.valueOf(st.nextToken()).doubleValue();
			this.depth = Double.valueOf(st.nextToken()).doubleValue();
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
     * Gets the Box location
     *
     * @return	p	Point3D location of the center point of the Box in user coordinates
     */
	public Point3D getP() {
		return p;
	}
	
    /**
     * Gets the Box width
     *
     * @return	width	Double value of the width in user coordinate space
     */
	public double getWidth() {
		return width;
	}
	
    /**
     * Gets the Box height
     *
     * @return	height	Double value of the height in user coordinate space
     */
	public double getHeight() {
		return height;
	}
	
    /**
     * Gets the Box depth
     *
     * @return	depth	Double value of the depth in user coordinate space
     */
	public double getDepth() {
		return depth;
	}
	
    /**
     * Gets the Box x axis rotation
     *
     * @return	xr	Double value of the Box x axis rotation
     */
	public double getXr() {
		return xr;
	}
	
    /**
     * Gets the Box y axis rotation
     *
     * @return	yr	Double value of the Box y axis rotation
     */
	public double getYr() {
		return yr;
	}
	
    /**
     * Gets the Box z axis rotation
     *
     * @return	zr	Double value of the Box z axis rotation
     */
	public double getZr() {
		return zr;
	}
	/**
	 * Returns the set of edges with the box centered at the origin
	 * 
	 * @return	edges	Point3D[] array of edges of the box
	 */
	public Line3D[] getEdges() {
		double w = .5*width;
		double h = .5*height;
		double d = .5*depth;
		
		Line3D[]  edges = {
				new Line3D( new Point3D( w, h,  d),		// 0 7-3
							new Point3D(-w, h,  d)),
				
				new Line3D( new Point3D( w,  h, d),		// 1 7-5
							new Point3D( w, -h, d)),
				
				new Line3D( new Point3D( w, h,  d),		// 2 7-6
							new Point3D( w, h, -d)),
				
				new Line3D( new Point3D(-w, -h,  d),	// 3 1-3
							new Point3D(-w,  h,  d)),
				
				new Line3D( new Point3D(-w, -h,  d),	// 4 1-5
							new Point3D( w, -h,  d)),

				new Line3D( new Point3D(-w, -h,  d),	// 5 1-0
							new Point3D(-w, -h, -d)),
				
				new Line3D( new Point3D( w, -h, -d),	// 6 4-5
							new Point3D( w, -h,  d)),
				
				new Line3D( new Point3D( w, -h, -d),	// 7 4-6
							new Point3D( w,  h, -d)),
				
				new Line3D( new Point3D( w, -h, -d),	// 8 4-0
							new Point3D(-w, -h, -d)),
				
				new Line3D( new Point3D(-w,  h, -d),	// 9 2-3
							new Point3D(-w,  h,  d)),
				
				new Line3D( new Point3D(-w,  h, -d),	// 10 2-6
							new Point3D( w,  h, -d)),							
				
				new Line3D( new Point3D(-w,  h, -d),	// 11 2-0
							new Point3D(-w, -h, -d))
		 };
		return edges;			
	}
	
    /**
     * Gets the Box PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Box
     */
	public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Box PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Box
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Box DrawMode property
     *
     * @return	dm	DrawMode property of the Box
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
    /**
     * Sets the Box DrawMode property
     *
     * @param	dm	DrawMode property of the Box
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}
	
    /**
     * Gets the Box CullFace property
     *
     * @return	cf	CullFace property of the Box
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Box CullFace property
     *
     * @param	cf	CullFace property of the Box
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Returns a String containing all Box3D values for saving to a data file.
     * The String is processed by the Box3D(String) constructor to recreate
     * a Box3D object when reading a saved data file.
     *
     * @return	String capturing all Box field values
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
		return "Box: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + width + ", " + height + ", " + depth +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Box3D values
     *
     * @return	String for printing all Box3D field values
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
		return "Box3D: p= " + p.toString() +
			   ", width = " + width + ", height= " + height + ", depth= " + depth +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}