package org.edisonwj.draw3d;

import javafx.scene.paint.PhongMaterial;
/**
 * Interface Algorithm needs to be implemented by any
 * algorithm class that is to be executed by Draw3D.
 *
 * The interface allows for implementation of computational animations with the following methods:
 * - Object processAlgorithm(int iteration) - method defining algorithm execution at each iteration
 *                                            and returning an Object(s) as listed below
 *
 * Get/Set methods for the following:
 * 
 * - int Iterations - Integer number of iterations
 * - long Delay - Long time delay between iterations in milliseconds
 * - PhongMaterial Material - PhongMaterial (color) value associated with each iteration
 * 			  -	color can also be embedded in the returned objects
 * - boolean Clear flag - to preserve or clear the current view before displaying the new result
 * - Object Parms	- Object containing tailorable parameters for algorithm execution
 *
 * The following methods are only applicable for drone operation.
 * - boolean Drone flag - Get method only indicating whether the algorithm implements a Drone3D object
 * - int Id - For a Drone3D object, the drone id
 * - double[] Info - For a Drone3D object, info fields used for passing location information from one drone to the other
 *
 * Either individual objects or an array of mixed typed objects can be returned to Draw3D for display.
 *  Currently supported return types are:
 *	- Arrow3D
 *	- Box3D
 *	- Cone3D
 *	- Cylinder3D
 *	- Dodecahedron3D
 *	- Drone3D
 *	- Icosahedron3D
 *	- Line3D
 *	- Line3D[]
 *	- Mesh3D
 *	- Octahedron3D
 *	- Oval3D
 *	- Plane3D
 *	- Point3D
 *	- Point3D[]
 *	- Polygon3D
 *	- Rectangle3D
 *	- Sphere3D
 *	- Sphere3D[]
 *	- Tetrahedron3D
 *	- Text3D
 *	- Triangle3D
 *	- Vector3D
 *	- Object[] Array containing the above listed 3D objects
 *
 * @author William Edison
 * @version 1.14 October 2017
 *
 */
public interface Algorithm {

    public Object processAlgorithm(int iteration);

    public int getIterations();

    public void setIterations(int iterations);

    public long getDelay();

    public void setDelay(long delay);

    public PhongMaterial getMaterial(int iteration);

    public void setMaterial(PhongMaterial m);

    public Object getParms();

    public void setParms(Object parms);

    public boolean getClear();

    public void setClear(boolean clear);
    
    // The following methods are all associated with Drone operation

    public boolean isDrone();

	public int getId();

	public void setId(int id);

	public double[] getInfo();

	public void setInfo(double[] info);
}