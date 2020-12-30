package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Octahedron3D class captures information defining an Octahedron instantiation with specified:
* - xyz location of the Octahedron center (user coordinate scale)
* - size (user coordinate scale), edge-length is size*Sqrt(2)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Octahedron3D object is drawn using a custom Octahedron MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.07 March 2016
* 
*/
public class Octahedron3D extends Polyhedron3D implements Draw3DDefaults {
	
//	protected Point3D p;		// Polyhedron3D center location
//	protected double size;		// Polyhedron3D size parameter
//	protected double xr;		// Polyhedron3Dx rotation
//	protected double yr;		// Polyhedron3Dy rotation
//	protected double zr;		// Polyhedron3Dz rotation
//	protected PhongMaterial m;	// Polyhedron3D material
//	protected DrawMode dm;		// Polyhedron3D DrawMode
//	protected CullFace cf;		// Polyhedron3D CullFace
	
	/**
	 * Constructs an Octahedron3D object given its center point location and size.
	 *  
	 * @param	p		Point3D Octahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 */	
	public Octahedron3D(Point3D p, double size) {
		super(p, size);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size, and PhongMaterial.
	 *  
	 * @param	p		Point3D Octahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Octahedron color
	 */	
	public Octahedron3D(Point3D p, double size,
					   PhongMaterial m) {
		super(p, size, m);													
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size and
	 * x, y, z rotations.
	 *   
	 * @param	p		Point3D Octahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */	
	public Octahedron3D(Point3D p, double size,
					   double xr, double yr, double zr) {
		super(p, size, xr, yr, zr);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 *  
	 * @param	p		Point3D Octahedron center location in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Octahedron color
	 */
	public Octahedron3D(Point3D p, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(p, size, xr, yr, zr, m);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location and size.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 */
	public Octahedron3D(double x, double y, double z, double size) {
		super(x, y, z, size);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size,
	 * and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	m		PhongMaterial determining Octahedron color
	 */
	public Octahedron3D(double x, double y, double z, double size,
					   PhongMaterial m) {
		super(x, y, z, size, m);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size and
	 * x, y, z rotations.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 */
	public Octahedron3D(double x, double y, double z, double size,
					   double xr, double yr, double zr) {
		super(x, y, z, size, xr, yr, zr);
	}
	
	/**
	 * Constructs an Octahedron3D object given its center point location, size,
	 * x, y, z rotations and PhongMaterial.
	 * 
	 * @param	x		Center point x coordinate in user coordinates
	 * @param	y		Center point y coordinate in user coordinates
	 * @param	z		Center point z coordinate in user coordinates
	 * @param	size	Size in user coordinates
	 * @param	xr		Rotation in degrees about the x axis
	 * @param	yr		Rotation in degrees about the y axis
	 * @param	zr		Rotation in degrees about the z axis
	 * @param	m		PhongMaterial determining Octahedron color
	 */
	public Octahedron3D(double x, double y, double z, double size,
			   double xr, double yr, double zr,
			   PhongMaterial m) {
		super(x, y, z, size, xr, yr, zr, m);
	}
	
	/**
	 * Constructs an Octahedron3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Octahedron: format data string
	 */
	public Octahedron3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Octahedron")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.size = Double.valueOf(st.nextToken()).doubleValue();
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
     * Gets the Octahedron vertices
     *
     * @return	v 	Vertices of the Octahedron in user coordinates 
     * 				without translation or rotation
     */
	public Point3D[] getVertices() {
		double s = this.size;
		Point3D[] vertices = {  new Point3D( 0,  0,  s),
								new Point3D( s,  0,  0),
								new Point3D( 0,  s,  0),
								new Point3D(-s,  0,  0),
								new Point3D( 0, -s,  0),
								new Point3D( 0,  0, -s)
			 				};
		return vertices;
	}
	
    /**
     * Gets the Octahedron edges
     *
     * @return	e 	Edges of the Octahedron in user coordinates 
     * 				without translation or rotation
     */
	public Line3D[] getEdges() {
		Point3D[] v = getVertices();
		Line3D[]  e = { new Line3D( v[0], v[1]),
						new Line3D( v[0], v[2]),
						new Line3D( v[0], v[3]),
						new Line3D( v[0], v[4]),
						new Line3D( v[5], v[1]),
						new Line3D( v[5], v[2]),
						new Line3D( v[5], v[3]),
						new Line3D( v[5], v[4]),
						new Line3D( v[1], v[2]),
						new Line3D( v[2], v[3]),
						new Line3D( v[3], v[4]),
						new Line3D( v[4], v[1])
					 };
		return e;
	}
	
    /**
     * Returns a String containing all Octahedron3D values for saving to a data file.
     * The String is processed by the Octahedron3D(String) constructor to recreate
     * a Octahedron3D object when reading a saved data file.
     * 
     * @return	String capturing all Octahedron3D field values
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
		return "Octahedron: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + size +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Octahedron3D values
     *
     * @return	String for printing all Octahedron3D field values
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
		return "Octahedron3D: p= " + p.toString() +
			   ", size = " + size +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", dm= " + dm + ", cf= " + cf + ", " + ms;
	}
}