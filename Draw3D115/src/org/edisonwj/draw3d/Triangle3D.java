package org.edisonwj.draw3d;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Triangle3D class captures information defining a JavaFX Triangle instantiation with specified:
* - Point3D array containing the vertices of the triangle (user coordinate scale)
* - xyz rotations (optional)
* - PhongMaterial (optional)
* - DrawMode setting (optional)
* - Cullface setting (optional)
* 
* Triangle3D is drawn using a custom Triangle MeshView object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.15 January 2019
* 
*/
public class Triangle3D implements Draw3DDefaults {
	private Point3D[] v;			// Array of vertices
	private double a;				// x coefficient for plane equation - ax + by + cz = d
	private double b;				// y coefficient for plane equation
	private double c;				// z coefficient for plane equation
	private double d;				// d coefficient for plane equation
	private double p;				// distance to origin from associated plane
	private Vector3D unitNormal;	// unit normal vector
	private double xr;				// x rotation
	private double yr;				// y rotation
	private double zr;				// z rotation
	private PhongMaterial m;
	private DrawMode dm;
	private CullFace cf;
	
	/**
	 * Constructs a Triangle3D object given three vertices.
	 *  
	 * @param	v		Point3D[] Array containing the triangle vertices
	 */	
	public Triangle3D(Point3D[] v) {
		this.v = v.clone();
		findNormals();
	}
	
	/**
	 * Constructs a Triangle3D object given three vertices and PhongMaterial.
	 *  
	 * @param	v		Point3D[] Array containing the triangle vertices
	 * @param	m		PhongMaterial determining the triangle color
	 */	
	public Triangle3D(Point3D[] v, PhongMaterial m) {
		this(v);
		this.m = m;
	}
	
	/**
	 * Constructs a Triangle3D object given three vertices.
	 *  
	 * @param	v1		Point3D triangle vertex 1
	 * @param	v2		Point3D triangle vertex 2
	 * @param	v3		Point3D triangle vertex 3
	 */	
	public Triangle3D(Point3D v1, Point3D v2, Point3D v3) {
		this.v = new Point3D[3];
		this.v[0] = v1;
		this.v[1] = v2;
		this.v[2] = v3;
		findNormals();
	}

	/**
	 * Constructs a Triangle3D object given three vertices and PhongMaterial.
	 *  
	 * @param	v1		Point3D triangle vertex 1
	 * @param	v2		Point3D triangle vertex 2
	 * @param	v3		Point3D triangle vertex 3
	 * @param	m		PhongMaterial determining the triangle color
	 */	
	public Triangle3D(Point3D v1, Point3D v2, Point3D v3, PhongMaterial m) {
		this.v = new Point3D[3];
		this.v[0] = v1;
		this.v[1] = v2;
		this.v[2] = v3;
		this.m = m;
		findNormals();
	}
	
	/**
	 * Constructs a Triangle3D object given three vertices, rotations and PhongMaterial.
	 *  
	 * @param	v1		Point3D triangle vertex 1
	 * @param	v2		Point3D triangle vertex 2
	 * @param	v3		Point3D triangle vertex 3
	 * @param	m		PhongMaterial determining the triangle color
	 */	
	public Triangle3D(Point3D v1, Point3D v2, Point3D v3,
					  double xr, double yr, double zr,
					  PhongMaterial m) {
		this.v = new Point3D[3];
		this.v[0] = v1;
		this.v[1] = v2;
		this.v[2] = v3;
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m = m;
		findNormals();		
	}
	
	/**
	 * Constructs a Triangle3D object given specification of the parameters
	 * in Save Data File format
	 * 
	 * @param	lineString	Triangle: format data string
	 */
	public Triangle3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Triangle")) {
			ArrayList<Point3D> vTemp = new ArrayList<>();
			while (st.hasMoreTokens()) {
				String next = st.nextToken();
				if (next.equals("#")) {
					break;
				}
				else {
					if (!next.equals("(")) {
						System.out.println("Triangle3D: Error reading Triangle data specificaiton");
						break;
					}
					double x = Double.valueOf(st.nextToken()).doubleValue();
					double y = Double.valueOf(st.nextToken()).doubleValue();
					double z = Double.valueOf(st.nextToken()).doubleValue();
					String paren = st.nextToken();
					if (!paren.equals(")")) {
						System.out.println("Triangle3D: Ill formed specification");
						break;
					}
					Point3D p = new Point3D(x, y, z);
					vTemp.add(p);
				}
			}
			v = vTemp.toArray(new Point3D[vTemp.size()] );	
			
			if (st.hasMoreTokens()) {
				this.dm = DrawMode.valueOf(st.nextToken());
			}
			if (st.hasMoreTokens()) {
				this.cf = CullFace.valueOf(st.nextToken());
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
		findNormals();
	}
	
    /**
     * Gets the Point3D array of Triangle vertices
     *
     * @return	v	Point3D array of Triangle vertices
     */
	public Point3D[] getV() {
		return v;
	}
		
    /**
     * Gets the Triangle PhongMaterial property
     *
     * @return	m	PhongMaterial property of the Triangle
     */
    public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Triangle PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Triangle
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
	/**
     * Gets the Triangle DrawMode property
     *
     * @return	dm	DrawMode property of the Triangle
     */
	public DrawMode getDrawMode() {
		return dm;
	}
	
	/**
     * Sets the Triangle DrawMode property
     *
     * @param	dm	DrawMode property of the Triangle
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Triangle CullFace property
     *
     * @return	cf	CullFace property of the Triangle
     */
	public CullFace getCullFace() {
		return cf;
	}
	
    /**
     * Sets the Triangle CullFace property
     *
     * @param	cf	CullFace property of the Triangle
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}
	
    /**
     * Computes the normal vector for the plane of the Triangle based on its vertices
     * Sets the a, b, c, d field values for the plane equation of the Triangle.
     * ax + by + cz = d 
     */
    private void findNormals() {
    	// Derive normal
    	Vector3D v1 = Vector3D.subtract(v[0], v[1]);
    	Vector3D v2 = Vector3D.subtract(v[0], v[2]);
    	Vector3D normal = Vector3D.crossProduct(v1, v2);
    	d = normal.getX()*(v[0].getX()) + normal.getY()*(v[0].getY()) + normal.getZ()*(v[0].getZ());
    	a = normal.getX();
    	b = normal.getY();
    	c = normal.getZ();
    	setUnitNormal();
    }
    
    /**
     * Computes the unit normal vector of the plane of the Triangle
     */
    private void setUnitNormal() {
    	double denom = Math.sqrt(a*a + b*b + c*c);
    	unitNormal = new Vector3D(a/denom, b/denom, c/denom);
    	p = d/denom;
    }
    
    /**
     * Returns unit normal vector of the plane of the Triangle
     *
     * @return	unitNormal	Vector3D unit normal vector
     */
    public Vector3D getUnitNormal() {
    	return unitNormal;
    }
    
    /**
     * Returns the d coefficient of the plane equation of the Triangle
     * ax + by + cz = d
     *
     * @return	d	Double coefficent d
     */
    public double getD() {
    	return d;
    }
    
    /**
     * Returns the distance to the origin from the plane of the Triangle
     *
     * @return	p	Double distance from the Triangle plane to the origin
     */
    public double getP() {
    	return p;
    }
	
    /**
     * Returns a String containing all Triangle3D values for saving to a data file.
     * The String is processed by the Triangle3D(String) constructor to recreate a Triangle3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Triangle3D field values
     */
    public String outString() {
		String vs = "Triangle: ";
		for (int i = 0; i < v.length; i++) {				
			vs = vs + "( " + Double.toString(v[i].getX()) + ", " + Double.toString(v[i].getY()) + ", " + Double.toString(v[i].getZ()) + " ), ";
		}
		vs = vs + "#, ";
		
		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
		}
		return vs + dm + ", " + cf + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Triangle3D values
     *
     * @return	String for printing all Triangle3D field values
     */
	public String toString() {
		String vs = "Triangle: ";
		for (int i = 0; i < v.length; i++) {
			vs = vs + "(" + Double.toString(v[i].getX()) + ", " + Double.toString(v[i].getY()) + ", " + Double.toString(v[i].getZ()) + "), ";
		}

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
		return vs + "dm= " + dm + ", cf= " + cf + ", ms= " + ms;
	}
}