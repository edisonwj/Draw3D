package org.edisonwj.draw3d;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
/**
* Polygon3D class captures information defining a Polygon3D instantiation with:
* - Point3D array defining the vertices of the polygon (in user coordinate scale)
* - Specified or computed a, b, c, d coefficients of the plane equation: ax + by + cz = d
* - Computed distance to origin from associated plane
* - normal vector of the plane
* - unit normal vector of the plane
* - Boolean flag, drawEdges, indicating whether to draw polygon edges
* - Boolean flag, drawVertices, indicating whether to draw polygon vertices
* - Boolean flag, drawVertexLabels, indicating whether to draw polygon vertex labels
* - PhongMaterial (optional) for filling polygon
* - PhongMaterial (optional) for edge color
* - DrawMode setting (optional)
* - Cullface setting (optional)
* The Polygon3D object is drawn using a custom polygon MeshView.
*
* The class implements defaults specified in Draw3DDefaults.
*
* @author William Edison
* @version 1.15 November 2020
*
*/
public class Polygon3D implements Draw3DDefaults {
//	private Hull h;					/* Convex hull for the polygon */

	private Point3D[] v;			// Array of vertices
	private double a;				// x coefficient for plane equation - ax + by + cz = d
	private double b;				// y coefficient for plane equation
	private double c;				// z coefficient for plane equation
	private double d;				// d coefficient for plane equation
	private double p;				// distance to origin from associated plane
	private double volumeSign;
	private Vector3D normal;		// normal vector
	private Vector3D unitNormal;	// unit normal vector
	private boolean drawEdges;
	private boolean drawVertices;
	private boolean drawVertexLabels;
	private PhongMaterial m;
	private PhongMaterial edgeMaterial = new PhongMaterial(Color.BLACK);
	private DrawMode dm;
	private CullFace cf;

	/**
	 * Constructs a Polygon3D object given a Point3d[] Array containing polygon vertices.
	 *
	 * @param	v		Point3D[] Array containing the polygon vertices
	 */
	public Polygon3D(Point3D[] v) {
		this.v = v.clone();
		if (!isPlanar()) {
			System.out.println("Polygon3D: Polygon vertices are not planar.");
		}
		findNormals();
		setOrientation();
	}

	/**
	 * Constructs a Polygon3D object given a Point3d[] Array containing the polygon vertices
	 * and PhongMaterial.
	 *
	 * @param	v		Point3D[] Array containing the polygon vertices
	 * @param	m		PhongMaterial determining polygon color
	 */
	public Polygon3D(Point3D[] v, PhongMaterial m) {
		this(v);
		this.m = m;
	}

	/**
	 * Constructs a Polygon3D object given a Point3d[] Array containing the polygon vertices,
	 * drawEdges flag, drawVertices flag, drawVertexLabels, and PhongMaterial.
	 *
	 * @param	v			Point3D[] Array containing the polygon vertices
	 * @param	drawEdges			Flag indicating to draw polygon edges
	 * @param	drawVertices		Flag indicating to draw polygon vertices
	 * @param	drawVertexLabels	Flag indicating to draw polygon vertex labels
	 */
	public Polygon3D(Point3D[] v,
					 boolean drawEdges, boolean drawVertices, boolean drawVertexLabels) {
		this(v);
		this.drawEdges = drawEdges;
		this.drawVertices = drawVertices;
		this.drawVertexLabels = drawVertexLabels;
	}

	/**
	 * Constructs a Polygon3D object given specification of the parameters
	 * in Save Data File format.
	 *
	 * @param	lineString	Polygon: format data string
	 */
	public Polygon3D(String lineString) {
		double x;
		double y;
		double z;
		ArrayList<Point3D> vTemp = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(lineString, ",;:");
		String next;
		String dataType = st.nextToken().trim();
		if (dataType.equals("Polygon")) {
			while (st.hasMoreTokens()) {
				next = st.nextToken().trim();
				if (next.equals("#")) {
					break;
				}
				else {
					x = Double.valueOf(next).doubleValue();
					y = Double.valueOf(st.nextToken()).doubleValue();
					z = Double.valueOf(st.nextToken()).doubleValue();
					Point3D p = new Point3D(x, y, z);
					vTemp.add(p);
				}
			}
			v = vTemp.toArray(new Point3D[vTemp.size()] );

			if (st.hasMoreTokens()) {
				this.drawEdges = Boolean.valueOf(st.nextToken().trim());
			}

			if (st.hasMoreTokens()) {
				this.drawVertices = Boolean.valueOf(st.nextToken().trim());
			}

			if (st.hasMoreTokens()) {
				this.drawVertexLabels = Boolean.valueOf(st.nextToken().trim());
			}

			if (st.hasMoreTokens()) {
				this.dm = DrawMode.valueOf(st.nextToken().trim());
			}

			if (st.hasMoreTokens()) {
				this.cf = CullFace.valueOf(st.nextToken().trim());
			}
			if (st.hasMoreTokens()) {
				this.m = new PhongMaterial();
				this.m.setDiffuseColor(Color.web(st.nextToken().trim()));
				if (st.hasMoreTokens()) {
					this.m.setSpecularColor(Color.web(st.nextToken().trim()));
				}
			}
			else {
				this.m = null;
			}
			if (!isPlanar()) {
				System.out.println("Polygon3D: Polygon vertices are not planar.");
			}
			findNormals();
			setOrientation();
		}
		else {
			System.out.println("Invalid Polygon3D data input.");
		}
	}

    /**
     * Gets the Point3D array of vertices defining the Polygon
     *
     * @return	v	Point3D array of Polygon vertices
     */
	public Point3D[] getV() {
		return v;
	}

	/**
     * Gets the Polygon drawEdges flag
     *
     * @return	drawEdges
     */
	public boolean getDrawEdges() {
		return drawEdges;
	}

    /**
     * Sets the Polygon drawEdges flag
     *
     * @param	b	drawEdges value
     */
	public void setDrawEdges(boolean b) {
		this.drawEdges = b;
	}

	/**
     * Gets the Polygon drawVertices flag
     *
     * @return	drawVertices
     */
	public boolean getDrawVertices() {
		return drawVertices;
	}

    /**
     * Sets the Polygon drawVertices flag
     *
     * @param	b	drawVertices value
     */
	public void setDrawVertices(boolean b) {
		this.drawVertices = b;
	}

	/**
     * Gets the Polygon drawVertexLabels flag
     *
     * @return	drawVertexLabels
     */
	public boolean getDrawVertexLabels() {
		return drawVertexLabels;
	}

    /**
     * Sets the Polygon drawVertexLabels flag
     *
     * @param	b	drawVertexLabels value
     */
	public void setDrawVertexLabels(boolean b) {
		this.drawVertexLabels = b;
	}

	/**
     * Gets the Polygon PhongMaterial property
     *
     * @return	m	PhongMaterial value of the Polygon
     */
	public PhongMaterial getMaterial() {
		return m;
	}

    /**
     * Sets the Polygon PhongMaterial property
     *
     * @param	m	PhongMaterial property of the Polygon
     */
	public void setMaterial(PhongMaterial m) {
		this.m = m;
	}

	/**
     * Gets the Polygon edgeMaterial property
     *
     * @return	edgeMaterial	PhongMaterial value for drawing polygon edges
     */
	public PhongMaterial getEdgeMaterial() {
		return edgeMaterial;
	}

    /**
     * Sets the Polygon edgeMaterial property
     *
     * @param	edgeMaterial	PhongMaterial value for drawing polygon edges
     */
	public void setEdgeMaterial(PhongMaterial edgeMaterial) {
		this.edgeMaterial = edgeMaterial;
	}

    /**
     * Gets the Polygon DrawMode property
     *
     * @return	dm	DrawMode property of the Polygon
     */
	public DrawMode getDrawMode() {
		return dm;
	}

    /**
     * Sets the Polygon DrawMode property
     *
     * @param	dm	DrawMode property of the Polygon
     */
	public void setDrawMode(DrawMode dm) {
		this.dm = dm;
	}

    /**
     * Gets the Polygon CullFace property
     *
     * @return	cf	CullFace property of the Polygon
     */
	public CullFace getCullFace() {
		return cf;
	}

    /**
     * Sets the Polygon CullFace property
     *
     * @param	cf	CullFace property of the Polygon
     */
	public void setCullFace(CullFace cf) {
		this.cf = cf;
	}

    /**
     * Computes the normal vector for the plane of the Polygon based on its
     * first three vertices. Sets the a, b, c, d field values for the plane
     * equation of the Polygon.
     * ax + by + cz = d
     */
    private void findNormals() {
    	// Derive unit normal
    	Vector3D v1 = Vector3D.subtract(v[1], v[0]);
    	Vector3D v2 = Vector3D.subtract(v[2], v[0]);
    	normal = Vector3D.crossProduct(v1, v2);
    	// Set unit normal coordinates
    	d = normal.getX()*(v[0].getX()) + normal.getY()*(v[0].getY()) + normal.getZ()*(v[0].getZ());
    	a = normal.getX();
    	b = normal.getY();
    	c = normal.getZ();
    	setUnitNormal();
    }

    /**
     * Computes the unit normal vector of the plane of the Polygon.
     */
    private void setUnitNormal() {
    	double denom = Math.sqrt(a*a + b*b + c*c);
    	unitNormal = new Vector3D(a/denom, b/denom, c/denom);
    	p = d/denom;
    }

    /**
     * Gets the normal vector for the Plane of the Polygon
     *
     * @return	normal	Vector3D normal vector of the Plane
     */
    public Vector3D getNormal() {
    	return normal;
    }

    /**
     * Gets the unit normal vector for the plane of the Polygon based on its vertices
     *
     * @return	unitNormal	Vector3D unit normal vector for the plane of the Polygon
     */
	public Vector3D getUnitNormal() {
    	return unitNormal;
    }

    /**
     * Returns the d coefficient of the plane equation of the Polygon
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
     * @return	p	Double distance from the Polygon plane to the origin
     */
    public double getP() {
    	return p;
    }

    /**
     * Sets the volumeSign field value that indicates the orientation of the Polygon
     * based on the z-axis coordinate value of the unit normal vector and the sign
     * of the coefficient d. The volumeSign field is used in interpreting the result
     * of the volume() to determine whether vertices are in clockwise or counterclockwise
     * order.
     */
	public void setOrientation() {
//		System.out.println("setOrientation: unit normal= " + unitNormal.toString());
//		System.out.println("a= " + a + ", b= " + b + ", c= " + c + ", d= " + d + ", p= " + p);
		if      (unitNormal.getZ() > 0.0 && d >= 0.0 ) {
			volumeSign = 1.0;
		}
		else if (unitNormal.getZ() > 0.0 && d <  0.0 ) {
			volumeSign = -1.0;
		}
		else if (unitNormal.getZ() <=  0.0 && d >= 0.0 ) {
			volumeSign = -1.0;
		}
		else if (unitNormal.getZ() <=  0.0 && d <  0.0 ) {
			volumeSign = 1.0;
		}
	}

    /**
     * Gets the volumeSign field for the Polygon.
     *
     * @return	volumeSign	Double value of the volumeSign field.
     */
	public double getVolumeSign() {
		return volumeSign;
	}

	/**
	 * Given a set of y and z coordinates, this method computes a corresponding x coordinate
	 * resulting in an update to v, the array of Polygon vertices. The resulting polygon
	 * is in the plane determined by the a, b, c, d input parameters that have the relationship
	 * ax + by + cz = d.
	 * @param a	Double value of the a coefficient of the plane equation
	 * @param b Double value of the b coefficient of the plane equation
	 * @param c Double value of the c coefficient of the plane equation
	 * @param d Double value of the d coefficient of the plane equation
	 */
	public void setX(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		double x, y, z;
		for (int i = 0; i < v.length; i++) {
			y = v[i].getY();
			z = v[i].getZ();
			x = (d - b*y - c*z)/a;
			v[i] = new Point3D(x, y, z);
		}
	}

	/**
	 * Given a set of x and z coordinates, this method computes a corresponding y coordinate
	 * resulting in an update to v, the array of Polygon vertices. The resulting polygon
	 * is in the plane determined by the a, b, c, d input parameters that have the relationship
	 * ax + by + cz = d.
	 * @param a	Double value of the a coefficient of the plane equation
	 * @param b Double value of the b coefficient of the plane equation
	 * @param c Double value of the c coefficient of the plane equation
	 * @param d Double value of the d coefficient of the plane equation
	 */
	public void setY(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		double x, y, z;
		for (int i = 0; i < v.length; i++) {
			x = v[i].getX();
			z = v[i].getZ();
			y = (d - a*x - c*z)/b;
			v[i] = new Point3D(x, y, z);
		}
		setUnitNormal();
		setOrientation();
	}

	/**
	 * Given a set of x and y coordinates, this method computes a corresponding z coordinate
	 * resulting in an update to v, the array of Polygon vertices. The resulting polygon
	 * is in the plane determined by the a, b, c, d input parameters that have the relationship
	 * ax + by + cz = d.
	 * @param a	Double value of the a coefficient of the plane equation
	 * @param b Double value of the b coefficient of the plane equation
	 * @param c Double value of the c coefficient of the plane equation
	 * @param d Double value of the d coefficient of the plane equation
	 */
	public void setZ(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		double x, y, z;
		for (int i = 0; i < v.length; i++) {
			x = v[i].getX();
			y = v[i].getY();
			z = (d - a*x - b*y)/c;
			v[i] = new Point3D(x, y, z);
		}
		setUnitNormal();
		setOrientation();
	}

	/**
	 * Modifies each vertex xyz coordinate tuple by adding to it the x, y, and z parameter values,
	 * updating the v array set of Polygon vertices.
	 * @param x	Double value to be added to each vertex x coordinate value
	 * @param y Double value to be added to each vertex y coordinate value
	 * @param z Double value to be added to each vertex z coordinate value
	 */
    public void changeXYZ(double x, double y, double z) {
    	for (int i = 0; i < v.length; i++) {
    		v[i] = new Point3D(v[i].getX() + x, v[i].getY() + y, v[i].getZ()+ z);
    	}
    	findNormals();
    }

	/**
	 * Modifies each vertex xyz coordinate tuple by multiplying it by the d parameter value,
	 * updating the v array set of Polygon vertices.
	 * @param d	Double value to be multiplied times each x, y, z coordinate value of the vertices
	 */
	public void scale(double d) {
		for (int i = 0; i < v.length; i++ ) {
			v[i] = new Point3D(v[i].getX()*d, v[i].getY()*d, v[i].getZ()*d);
		}
	}

	/**
	 * Reverses the order of vertices in array v
	 */
	public void reverse() {
		int n = v.length;
		Point3D tmp;
	    for (int i = 0; i < Math.floor((n-2)/2); i++) {
	        tmp = v[i];
	        v[i] = v[n-1-i];
	        v[n-1-i] = tmp;
	    }
	}

	/**
	 * Sorts the array of vertices v
	 * Adapted from: Gernot Hoffman, Cube Plane Intersection, http://docs-hoffmann.de/cubeplane12112006.pdf
	 */
	public void sortVertices() {

		System.out.println("Sort Input");
		for (int i = 0; i < v.length; i++) {
			System.out.println("v[" + i + "]: " + v[i]);
		}

		// Find temporary unit normal
    	Vector3D v1 = Vector3D.subtract(v[0], v[2]);
    	Vector3D v2 = Vector3D.subtract(v[0], v[1]);
    	Vector3D tempNormal = Vector3D.crossProduct(v1, v2);
    	double a = tempNormal.getX();
    	double b = tempNormal.getY();
    	double c = tempNormal.getZ();
    	double denom = Math.sqrt(a*a + b*b + c*c);
    	Vector3D tempUnitNormal = new Vector3D(a/denom, b/denom, c/denom);

		if ( Math.abs(tempUnitNormal.getX()) >= Math.abs(tempUnitNormal.getY()) &&
				Math.abs(tempUnitNormal.getX()) >= Math.abs(tempUnitNormal.getZ()) ) {
			System.out.println("X is longest component.");
			double my = meanY(v);
			double mz = meanZ(v);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < v.length-1; i++) {
				ai = Math.atan2(v[i].getY()-my, v[i].getZ()-mz);
				for (int j = i+1; j < v.length; j++) {
					aj = Math.atan2(v[j].getY()-my, v[j].getZ()-mz);
					if (aj < ai) {
						Point3D t = v[i];
						v[i] = v[j];
						v[j] = t;
						ai = aj;
					}
				}
			}
		}
		else if ( Math.abs(tempUnitNormal.getY()) >= Math.abs(tempUnitNormal.getX()) &&
			Math.abs(tempUnitNormal.getY()) >= Math.abs(tempUnitNormal.getZ()) ) {
			System.out.println("Y is longest component.");
			double mx = meanX(v);
			double mz = meanZ(v);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < v.length-1; i++) {
				ai = Math.atan2(v[i].getZ()-mz, v[i].getX()-mx);
				for (int j = i+1; j < v.length; j++) {
					aj = Math.atan2(v[j].getZ()-mz, v[j].getX()-mx);
					if (aj < ai) {
						Point3D t = v[i];
						v[i] = v[j];
						v[j] = t;
						ai = aj;
					}
				}
			}
		}
		else if ( Math.abs(tempUnitNormal.getZ()) >= Math.abs(tempUnitNormal.getX()) &&
				Math.abs(tempUnitNormal.getZ()) >= Math.abs(tempUnitNormal.getY()) ) {
			System.out.println("Z is longest component.");
			double mx = meanX(v);
			double my = meanY(v);
			double ai = 0.0;
			double aj = 0.0;
			for (int i = 0; i < v.length-1; i++) {
				ai = Math.atan2(v[i].getY()-my, v[i].getX()-mx);
				for (int j = i+1; j < v.length; j++) {
					aj = Math.atan2(v[j].getY()-my, v[j].getX()-mx);
					if (aj < ai) {
						Point3D t = v[i];
						v[i] = v[j];
						v[j] = t;
						ai = aj;
					}
				}
			}
		}
		System.out.println("Sort Output");
		for (int i = 0; i < v.length; i++) {
			System.out.println("v[" + i + "]: " + v[i]);
		}
	}

	/**
	 * Finds the "main plane", i.e. the plane orthogonal to the longest component of the normal vector
	 * @return	result 1 if x coordinate is longest
	 *			result 2 if y coordinate is longest
	 *			result 3 if z coordinate is longest
	 */
	public int findLongestComponent() {
		int result = 0;
		if ( Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getY()) &&
				Math.abs(unitNormal.getX()) >= Math.abs(unitNormal.getZ()) ) {
			result = 1;  // X longest
		}
		else if ( Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getX()) &&
			Math.abs(unitNormal.getY()) >= Math.abs(unitNormal.getZ()) ) {
			result = 2;  // Y longest
		}
		else if ( Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getX()) &&
				Math.abs(unitNormal.getZ()) >= Math.abs(unitNormal.getY()) ) {
			result = 3;  // Z longest
		}
		return result;
	}

	/**
	 * Returns the mean x coordinate value from the provided set of Polygon vertices.
	 * @param	vi		Point3D array of vertices
	 * @return	meanX	Double mean x value from the provided set of Polygon vertices.
	 */
	public double meanX (Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getX();
		}
		return mean = mean/vi.length;
	}

	/**
	 * Returns the mean y coordinate value from the provided set of Polygon vertices.
	 * @param	vi		Point3D array of vertices
	 * @return	meanY	Double mean y value from the provided set of Polygon vertices.
	 */
	public double meanY (Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getY();
		}
		return mean = mean/vi.length;
	}

	/**
	 * Returns the mean z coordinate value from the provided set of Polygon vertices.
	 * @param	vi		Point3D array of vertices
	 * @return	meanZ	Double mean z value from the provided set of Polygon vertices.
	 */
	public double meanZ(Point3D[] vi) {
		double mean = 0.0;
		for (int i = 0; i < vi.length; i++) {
			mean = mean + vi[i].getZ();
		}
		return mean = mean/vi.length;
	}

	/**
	 * Provides an indication as to whether the Point3D array v contains a set of Polygon
	 * vertices that lie in the plane specified by coordinates a, b, c, d.
	 * @return	boolean value	True if the points lie in a plan, otherwise false.
	 */
	public boolean isPlanar() {
		for (int i = 0; i < v.length-1; i++) {
			double dd = a*v[i].getX() + b*v[i].getY() + c*v[i].getZ();
			if ( Math.abs(dd - d) > EPSILON )
				return false;
		}
		return true;
	}

	/**
	 * Provides an indication as to whether the Polygon determined by Point3D array v
	 * lies in a plane determined by the coordinate axes.
	 * @return	0	if not in axis plane
	 * @return	1	if in yz plane
	 * @return	2	if in xz plane
	 * @return	3	if in xy plane
	 */
	public int isInAxisPlane() {
		int result = 0;
		int countZero = 0;
		for (int i = 0; i < v.length; i++) {
			if (v[i].getX() == 0.0) {
				countZero++;
			}
		}
		if (countZero == v.length) {
			result = 1;
			return result;
		}

		countZero = 0;
		for (int i = 0; i < v.length; i++) {
			if (v[i].getY() == 0.0) {
				countZero++;
			}
		}
		if (countZero == v.length) {
			result = 2;
			return result;
		}

		countZero = 0;
		for (int i = 0; i < v.length; i++) {
			if (v[i].getZ() == 0.0) {
				countZero++;
			}
		}
		if (countZero == v.length) {
			result = 3;
			return result;
		}

		return result;
	}

	/**
	 * Provides an indication as to whether the Polygon specified by the Point3D array, v,
	 * of vertices is convex
	 * @return	boolean true	if Polygon is convex
	 * @return	boolean false	if Polygon is not convex
	 */
	public boolean isConvexPoly() {
		for (int i = 0; i < v.length-2; i++) {
			if (!checkLeft(v[i], v[(i+1)%v.length], v[(i+2)%v.length])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Provides an indication when traversing the Point3D Polygon vertices from a to b to c,
	 * as to whether the path turns left or right when going from the side ab to vertex c.
	 * The test determines whether the Polygon lies in an axes plane, and if so uses the sign of
	 * a two dimensional area calculation, and otherwise utilizes the sign of the volume() computation.
	 * Adapted from O'Rourke, Computational Geometry in C, p. 26-27.
	 *
	 * @param	a	Point3D Polygon vertex
	 * @param	b	Point3D Polygon vertex
	 * @param	c	Point3D Polygon vertex
	 * @return	boolean true	if left turn
	 * @return	boolean false	if right turn
	 */
	public boolean checkLeft(Point3D a, Point3D b, Point3D c) {
		if (isInAxisPlane() == 0) {			// not in axis plane
//			System.out.println("checkLeft: not in axis plane");
//			System.out.println("checkLeft: a= " + a + ", b= " + b + ", c= " + c);
			double vol = volume(a, b, c);
			boolean result = vol >= 0.0;
//			System.out.println("checkLeft: volume= " + vol + ", result= " + result);
			return result;
		}
		else if (isInAxisPlane() == 1) {	// in yz plane
//			System.out.println("in axis plane yz");
			double a2 = (a.getZ() - c.getZ()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getZ() - c.getZ());
			return a2 >= 0.0;
		}
		else if (isInAxisPlane() == 2) {	// in xz plane
//			System.out.println("in axis plane xz");
			double a2 = (a.getX() - c.getX()) * (b.getZ() - c.getZ()) - (a.getZ() - c.getZ()) * (b.getX() - c.getX());
			return a2 >= 0.0;
		}
		else if (isInAxisPlane() == 3) {	// in xy plane
//			System.out.println("in axis plane xy");
			double a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
			return a2 >= 0.0;
		}
		else
			return false;
	 }

	/**
	 * Provides an indication for the two dimensional cases when the polygon lies within an
	 * axis plane as to whether point c is to the left of side ab. The sign of the computed
	 * abc area is positive for a left turn, i.e. counter clockwise vertex orientation.
	 * Adapted from O'Rourke, Computational Geometry in C, p. 26-27.
	 *
	 * @param	a	Point3D Polygon vertex
	 * @param	b	Point3D Polygon vertex
	 * @param	c	Point3D Polygon vertex
	 * @return	a2	double value that is positive for left turn, negative for right
	 */
	private double area2(Point3D a, Point3D b, Point3D c) {
		double a2 = 0.0;
		if (isInAxisPlane() == 1) {	// in yz plane
			a2 = (a.getZ() - c.getZ()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getZ() - c.getZ());
		}
		else if (isInAxisPlane() == 2) {	// in xz plane
			a2 = (a.getX() - c.getX()) * (b.getZ() - c.getZ()) - (a.getZ() - c.getZ()) * (b.getX() - c.getX());
		}
		else if (isInAxisPlane() == 3) {	// in xy plane
			a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
		}
		return a2;
	}

	/**
	 * Indicates whether a point p is inside a triangle defined by vertices a, b, c.
	 * Adpapted from Ammeraal and Zang, Computer Graphics for Java Programmers, p. 44-45.
	 *
	 * @param a	Point3D Triangle vertex
	 * @param b	Point3D Triangle vertex
	 * @param c	Point3D Triangle vertex
	 * @param p Point3D point to be tested for being in the Triangle determined by points a, b, c
	 *
	 * @return	boolean true	if point p is in Triangle abc
	 *			boolean false	if point p is not in Triangle abc
	 */
	private boolean insideTriangle(Point3D a, Point3D b, Point3D c, Point3D p) { // ABC is assumed to be counter-clockwise
		boolean result = checkLeft(a, b, p) &&
						 checkLeft(b, c, p) &&
						 checkLeft(c, a, p) ;
//				System.out.println("insideTriangle a= " + a + ", b= " + b + ", c= " + c + ", result= " + result);
				return result;
	}

    /**
     * Returns the volume of the tetrahedron with vertices a, b, c, and d (the origin
     * at 0,0,0). The volume is signed and is positive if (a,b,c) form
     * a counterclockwise circuit when viewed from the side away from d, so that
     * the face normal determined by the right-hand rule points toward the outside.
     *
     * The volume(a, b, c) method is used to determine whether point c is to the left
     * or right of line (a,b), i.e. is counterclockwise.
     *
     * Adapted from O'Rourke, Computational Geometry in C, p. 26-27.
     *
     * @param	a	Point3D specifying vertex a of the tetrahedron
     * @param	b	Point3D	specifying vertex b of the tetrahedron
     * @param	c	Point3D specifying vertex c of the tetrahedron
     * @return	volume	Double signed value of the volume as described above
     */
	public double volume(Point3D a, Point3D b, Point3D c) {
		double a0 = a.getX();
		double a1 = a.getY();
		double a2 = a.getZ();
		double b0 = b.getX();
		double b1 = b.getY();
		double b2 = b.getZ();
		double c0 = c.getX();
		double c1 = c.getY();
		double c2 = c.getZ();
		double d0 = 0.0;
		double d1 = 0.0;
		double d2 = 0.0;
		double volume = - b0*c1*d2 + a0*c1*d2 + b1*c0*d2 - a1*c0*d2 - a0*b1*d2
						+ a1*b0*d2 + b0*c2*d1 - a0*c2*d1 - b2*c0*d1 + a2*c0*d1
						+ a0*b2*d1 - a2*b0*d1 - b1*c2*d0 + a1*c2*d0 - b2*c1*d0
						- a2*c1*d0 - a1*b2*d0 + a2*b1*d0 + a0*b1*c2 - a1*b0*c2
						- a0*b2*c1 + a2*b0*c1 + a1*b2*c0 - a2*b1*c0;
		double result = volumeSign * volume;
		return result;
	}

	public Triangle3D[] findPolygonTriangles(int[][] tri) {
		Triangle3D[] t = new Triangle3D[v.length-2];
		for (int i = 0; i < v.length-2; i++) {
			t[i] = new Triangle3D(v[tri[i][0]], v[tri[i][1]], v[tri[i][2]]);
		}
		return t;
	}

	/**
	 * Triangulate convex polygon
	 *
	 * @return	tri	int[][] array of indices of vertices forming the trianglulation of a convex polygon
	 */
	public int[][] triangulateConvexPolygon() {
//		System.out.println("Polygon3D triangulateConvexPolygon");
//		tri = new int[2*(v.length-2)][];
		int[][] tri = new int[v.length-2][];
		for (int i = 0; i < v.length-2; i++) {
			tri[i] = new int[3];
			tri[i][0] = 0;
			tri[i][1] = i+1;
			tri[i][2] = i+2;
//			System.out.println("Polygon3D: i= " + i + " " + tri[i][0] + ", " + tri[i][1] + ", " + tri[i][2]);
		}
		return tri;
	}

	/**
	 * Triangulate polygon defined by the Point3D[] v array of polygon vertices in counter clockwise order.
	 * Adpapted from Ammeraal and Zang, Computer Graphics for Java Programmers, p. 44-45.
	 *
	 * @return	tri	int[v.length-2][3] array of indices of vertices forming the trianglulation of the polygon
	 */

	public int[][] triangulatePolygon() {
		int [][] tri = new int[v.length-2][3];
	    int n = v.length, j = n - 1, iA=0, iB, iC;
	    int[] next = new int[n];
	    if (n == 3) {
	    	if (area2(v[0], v[1], v[2]) < 0) {
	    		reverse();
	    	}
	    	tri[0][0] = 0;
	    	tri[0][1] = 1;
	    	tri[0][2] = 2;
	    	return tri;
	    }
	    for (int i = 0; i < n; i++) {
	    	next[j] = i;
	        j = i;
	    }
	    for (int k = 0; k < n-2; k++) {
	    	// Find a suitable triangle, consisting of two edges
	    	// and an internal diagonal:
	    	Point3D a, b, c;
	    	boolean triaFound = false;
	    	int count = 0;
	    	while (!triaFound && ++count < n) {
	    		iB = next[iA]; iC = next[iB];
	            a = v[iA]; b = v[iB]; c = v[iC];
//	            System.out.println("k= " + k + ", iA= " + iA + ", iB= " + iB + ", iC= " + iC);
//            	System.out.println("checkLeft= " + checkLeft(a, b, c) + ", a= " + a + ", b= " + b + ", c= " + c);
//	            if (checkTurn(a, b, c) >= 0.0) {
            	if (checkLeft(a, b, c)) {
	            	// Edges AB and BC; diagonal AC.;
	                // Test to see if no other polygon vertex
	                // lies within triangle ABC:
	                j = next[iC];
	                while (j != iA && !insideTriangle(a, b, c, v[j])) {
	                    j = next[j];
	                }
                	if (j == iA) {
                		// Triangle ABC contains no other vertex:
                		tri[k][0] = iA;
                		tri[k][1] = iB;
                		tri[k][2] = iC;
                		next[iA] = iC;
                		triaFound = true;
//                		System.out.println("triangulatePolygon(): k= " + k + ", " +
//                							tri[k][0] + ", " + tri[k][1] + ", " + tri[k][2]);
//                		System.out.println("triangulatePolygon(): k= " + k + ", " +
//                							v[tri[k][0]] + ", " + v[tri[k][1]] + ", " + v[tri[k][2]]);
                	}
	            }
	            iA = next[iA];
	    	}
            if (count == n) {
            	System.out.println("Polygon3D: Not a simple polygon" +
            						" or vertex sequence not counter-clockwise.");
            	for (int m = 0; m < v.length; m++) {
            		System.out.println("Polygon3D: v[" + m + "] = " + v[m].toString());
            	}
            	System.exit(1);
            }
	    }
	    return tri;
	}

    /**
     * Returns a String containing all Polygon3D values for saving to a data file.
     * The String is processed by the Polygon3D(String) constructor to recreate a Polygon3D
     * object when reading a saved data file.
     *
     * @return	String capturing all Cylinder3D field values
     */
	public String outString() {
		String vs = "Polygon: ";
				for (int i = 0; i < v.length; i++) {
			vs = vs + Double.toString(v[i].getX()) + ", " + Double.toString(v[i].getY()) + ", " + Double.toString(v[i].getZ()) + "; ";
		}
		vs = vs + " #";
		vs = vs + ", " + drawEdges + ", " + drawVertices + ", " + drawVertexLabels;
		vs = vs + ", " +  dm + ", " + cf;

		String ms = null;
		if (m != null) {
			if (m.getDiffuseColor() != null) {
				ms = m.getDiffuseColor().toString();
			}
			if (m.getSpecularColor() != null) {
				ms = ms.concat(", " + m.getSpecularColor().toString());
			}
			vs = vs + ", " + ms;
		}
		return vs;
	}

    /**
     * Returns a String for printing all Polygon3D values
     *
     * @return	String for printing all Polygon3D field values
     */
	public String toString() {
		String vs = "Polygon: ";
		for (int i = 0; i < v.length; i++) {
			vs = vs + "(" + Double.toString(v[i].getX()) + ", " + Double.toString(v[i].getY()) + ", " + Double.toString(v[i].getZ()) + "), ";
		}
		vs = vs + " #";
		vs = vs + ", drawEdges= " + drawEdges + ", drawVertices= " + drawVertices + ", drawVertexLabels= " + drawVertexLabels;
		vs = vs + ", dm= " +  dm + ", cf= " + cf;

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
		return vs + ms;
	}
}