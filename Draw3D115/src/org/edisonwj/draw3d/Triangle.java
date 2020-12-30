package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Triangle class extends MeshView and provides for creation of a Triangle mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Triangle extends MeshView implements Draw3DDefaults{
	private final TriangleMesh mesh;
	private Point3D[] v;		// array of vertices in pixel coordinates
	
	/**
	 * Constructs a Triangle MeshView object.
	 * 
	 * @param v		Point3D[] Array containing the vertices of the triangle
	 * 				in pixels (screen coordinates)
	 */
	public Triangle(Point3D[] v) {
		this.v = v.clone();
		mesh = buildTriangle(this.v);
		this.setMesh(mesh);
	}
	
    /**
     * Creates a TriangleMesh for the Triangle with specified vertices
     *
     * @param	v		Point3D array of vertices in pixels (screen coordinates)
     * @return	mesh	TriangleMesh for the specified Triangle
     */
	private TriangleMesh buildTriangle(Point3D[] v) {
		
		TriangleMesh mesh = new TriangleMesh();
	
		// Create points of Triangle
		for (int i = 0; i < 3; i++) {
			mesh.getPoints().addAll((float)v[i].getX(), (float)v[i].getY(), (float)v[i].getZ());
		}
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh
		mesh.getFaces().addAll(
					0,0,2,0,1,0);
		return mesh;
	}
}