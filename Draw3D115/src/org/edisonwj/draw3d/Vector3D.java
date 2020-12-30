package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
* Vector3D class captures information defining a Vector3D instantiation with specified:
* - xyz location of the ending point (arrow point) (user coordinate scale)
* - radius in pixels of the arrow line
* - PhongMaterial (optional)
* 
* Vectors are composite objects created from a JavaFX Cylinder for the line with a 
* custom MeshView Cone forming the arrow tip.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class Vector3D implements Draw3DDefaults {
    private Point3D p;
    private double vectorRadius = VECTOR_RADIUS;
    private PhongMaterial m;
    
	/**
	 * Constructs a Vector3D object given its end point coordinates.
	 * 
	 * @param	p	Point3D end point of the vector
	 */
    public Vector3D(Point3D p) {
        this.p  = p;
        this.m = null;
    }
    
	/**
	 * Constructs a Vector3D object given its end point coordinates and its PhongMaterial.
	 * 
	 * @param	p	Point3D end point of the vector
	 * @param	m	PhongMaterial determining vector color
	 */
    public Vector3D(Point3D p, PhongMaterial m) {
        this(p);
        this.m = m;
    }
    
	/**
	 * Constructs a Vector3D object given its end point coordinates.
	 * 
	 * @param	x	Point3D end point x coordinate of the vector
	 * @param	y	Point3D end point y coordinate of the vector
	 * @param	z	Point3D end point z coordinate of the vector
	 */
    public Vector3D(double x, double y, double z) {
		this.p = new Point3D(x, y, z);
        this.m = null;
    }
    
	/**
	 * Constructs a Vector3D object given its end point coordinates and its PhongMaterial.
	 * 
	 * @param	x	Point3D end point x coordinate of the vector
	 * @param	y	Point3D end point y coordinate of the vector
	 * @param	z	Point3D end point z coordinate of the vector
	 * @param	m	PhongMaterial determining vector color
	 */
    public Vector3D(double x, double y, double z, PhongMaterial m) {
		this(x, y, z);
        this.m = m;
    }
    
	/**
	 * Constructs a Vector3D object given its end point coordinates.
	 * 
	 * @param	v	array containing the x, y, z end point coordinates of the vector
	 */
    public Vector3D(double[] v) {
		this.p = new Point3D(v[0], v[1], v[2]);
        this.m = null;
    }
    
	/**
	 * Constructs a Vector3D object given its end point coordinates and its PhongMaterial.
	 * 
	 * @param	v	array containing the x, y, z end point coordinates of the vector
	 * @param	m	PhongMaterial determining vector color
	 */
    public Vector3D(double[] v, PhongMaterial m) {
		this(v);
        this.m = m;
    }
    
	/**
	 * Constructs a Vector3D object given specification of the parameters
	 * in Save Data File format.
	 * 
	 * @param	lineString	Vector: format data string
	 */
    public Vector3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Vector")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.vectorRadius = Double.valueOf(st.nextToken()).doubleValue();
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
     * Gets the Vector3D x coordinate (in user coordinate space)
     *
     * @return	p.getX()	x coordinate of Point3D p
     */
    public double getX() {
        return p.getX();
    }
    
    /**
     * Gets the Vector3D y coordinate (in user coordinate space)
     *
     * @return	p.getY()	y coordinate of Point3D p
     */
    public double getY() {
        return p.getY();
    }
    
    /**
     * Gets the Vector3D z coordinate (in user coordinate space)
     *
     * @return	p.getZ()	z coordinate of Point3D p
     */
    public double getZ() {
        return p.getZ();
    }
    
    /**
     * Gets the Vector3D PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Vector
     */
    public PhongMaterial getMaterial() {
		return m;
	}
	
    /**
     * Sets the Vector3D PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Vector
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}
	
    /**
     * Gets the Vector3D line radius (in pixels)
     *
     * @return	vectorRadius	Double value of the Vector radius
     */
	public double getVectorRadius() {
		return vectorRadius;
	}
	
    /**
     * Sets the Vector3D line radius (in pixels)
     *
     * @param	vectorRadius	Double value of the Vector radius
     */
	public void setVectorRadius(double vectorRadius) {
		this.vectorRadius = vectorRadius;
	}
    
    /**
     * Provides the Vector3D length (magnitude) (in user coordinate space)
     *
     * @return	length	Double value of the Vector length
     */
	public double length() {
		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();
		return Math.sqrt(x*x + y*y + z*z);
	}
	
    /**
     * Provides the Vector3D line mid-point (in user coordinate space)
     *
     * @return	midpoint	Point3D location of the Vector midpoint
     */
	public Point3D midpoint() {
		double x, y, z;		
		x = p.getX() / 2.0;
		y = p.getY() / 2.0;
		z = p.getZ() / 2.0;
		return new Point3D(x, y, z);
	}
    
    /**
     * Provides the projected Vector3D angle between xz axes
     *
     * @return	theta	Double value of angle of projected xz Vector line
     */
    public double theta() {
		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();
		return Math.atan2(y, x);
	}
	
    /**
     * Provides the Vector3D angle with y axis
     *
     * @return	phi	Double value of angle of Vector line to y axis
     */
	public double phi() {
		double x = p.getX();
		double y = p.getY();
		double z = p.getZ();
		double rho = Math.sqrt(x*x + y*y + z*z);
		return Math.acos(z/rho);
	}
    
    /**
     * Computes a new Vector3D by applying the supplied transform matrix
     *
     * @param	tmatrix	Double[][] transform matrix
     * @return	Vector3D transformed vector
     */
    public Vector3D transform(double[][] tmatrix) {
    	double[] v1 = new double[3];
    	v1[0] = p.getX();
    	v1[1] = p.getY();
    	v1[2] = p.getZ();
    	double[] v2 = matrixMultiply(v1, tmatrix);
    	return new Vector3D(v2[0], v2[1], v2[2], this.m);
    }
    
    /**
     * Performs matrix multiplication to compute vector transformation
     *
     * @param	v	double[] 	defining input vector
     * @param	m	double[][]	matrix defining vector transformation
     * 				m must be of size v.length x v.length
     * @return	t	double[]	transformed vector
     */
    private double[] matrixMultiply(double[] v, double[][] m) {
    	double[] t = new double[3];
    	t[0] = v[0]*m[0][0] + v[1]*m[1][0] + v[2]*m[2][0];
    	t[1] = v[0]*m[0][1] + v[1]*m[1][1] + v[2]*m[2][1];
    	t[2] = v[0]*m[0][2] + v[1]*m[1][2] + v[2]*m[2][2];
    	return t;
    }
    
    /**
     * Performs matrix multiplication to compute vector transformation
     *
     * @param	v	double[] 	defining input vector
     * @param	m	double[][]	matrix defining vector transformation
     * 				m must be of size v.length x v.length
     * @return	t	double[]	transformed vector
     */
//   public Vector3D Xadd(Vector3D v) {
//   	return new Vector3D(v.getX()+p.getX(), v.getY()+p.getY(), v.getZ()+p.getZ(), m);
//   }
   
//   public Vector3D Xdifference(Vector3D v) {
//   	return new Vector3D(v.getX()-p.getX(), v.getY()-p.getY(), v.getZ()-p.getZ(), m);
//   }
 
//    public Vector3D XcrossProduct(Vector3D v) {
//        double tx = p.getY() * v.getZ() - p.getZ() * v.getY();
//        double ty = p.getZ() * v.getX() - p.getX() * v.getZ();
//        double tz = p.getX() * v.getY() - p.getY() * v.getX();      
//        return new Vector3D(tx, ty, tz, m);
//    }
    
//	public Vector3D XscalarProduct(double t) {
//		return new Vector3D(t*p.getX(), t*p.getY(), t*p.getZ(), m);
//	}
    
//   public double XdotProduct(Vector3D v) {
//       return p.getX() * v.getX() + p.getY() * v.getY() + p.getZ() * v.getZ();
//   }
    
    /**
     * Returns a boolean value indicating equality of two vectors
     *
     *	@param	v	Vector3D input vector
     *	@return	boolean	true if  this.v equal input v, else false
     */
    public boolean equals(Vector3D v) {
        return (p.getX() == v.getX() && p.getY() == v.getY() && p.getZ() == v.getZ());
    }
    
    /**
     * Returns a String containing all Vector3D values for saving to a data file.
     * The String is processed by the Vector3D(String) constructor to recreate a Vector3D
     * object when reading a saved data file.
     * 
     * @return	String capturing all Vector3D field values
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
		return "Vector: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
				", " + vectorRadius + ", " + ms;
	}
	
    /**
     * Returns a String for printing all Vector3D values
     *
     * @return	String for printing all Vector3D field values
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
		return "Vector3D: p1= " + p.toString()
				+ " vectorRadius= " + vectorRadius+ ms;
	}
	
    /**
     * Returns the vector sum of the two input vectors
     *
     * @param	v1	Vector3D	input vector
     * @param	v2	Vector3D	input vector
     * @return	new Vector3D	vector sum of v1 and v2
     */
    static public Vector3D add(Vector3D v1, Vector3D v2) {
    	return new Vector3D(v1.getX()+v2.getX(), v1.getY()+v2.getY(), v1.getZ()+v2.getZ());
    }
    
    /**
     * Returns the vector difference of the two input vectors
     *
     * @param	v1	Vector3D	input vector
     * @param	v2	Vector3D	input vector
     * @return	new Vector3D	vector difference of v1 and v2, i.e. v1 - v2
     */
	static public Vector3D subtract(Vector3D v1, Vector3D v2) {
		return new Vector3D(v1.getX()-v2.getX(), v1.getY()-v2.getY(), v1.getZ()-v2.getZ());
	}
	
    /**
     * Returns the vector difference of the two input vectors
     *
     * @param	p1	Point3D		input vector defined by its end point
     * @param	p2	Point3D		input vector defined by its end point
     * @return	new Vector3D	vector difference of p1 and p2, i.e. p1 - p2
     */
	static public Vector3D subtract(Point3D p1, Point3D p2) {
		return new Vector3D(p1.getX()-p2.getX(), p1.getY()-p2.getY(), p1.getZ()-p2.getZ());
	}
	
    /**
     * Returns the scalar product of a vector and scalar quantity
     *
     * @param	t	double		scalar quantity
     * @param	v	Vector3D	input vector
     * @return	new Vector3D	scalar product of t  and v
     */
	static public Vector3D multiply(double t, Vector3D v) {
        return new Vector3D(t*v.getX(), t*v.getY(), t*v.getZ());	
	}
	
    /**
     * Returns the vector dot product of the two input vectors
     *
     * @param	v1	Vector3D	input vector
     * @param	v2	Vector3D	input vector
     * @return	new double		dot product of v1 and v2
     */
	static public double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();	
	}
	
    /**
     * Returns the vector cross product of the two input vectors
     *
     * @param	v1	Vector3D	input vector
     * @param	v2	Vector3D	input vector
     * @return	new Vector3D	cross product of v1 and v2
     */
    static public Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        double tx = v2.getY() * v1.getZ() - v2.getZ() * v1.getY();
        double ty = v2.getZ() * v1.getX() - v2.getX() * v1.getZ();
        double tz = v2.getX() * v1.getY() - v2.getY() * v1.getX();      
        return new Vector3D(tx, ty, tz);
    }
}