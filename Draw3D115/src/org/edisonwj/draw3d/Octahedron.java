package org.edisonwj.draw3d;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Octahedron class extends MeshView and provides for creation of an Octahedron mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.06 February 2016
* 
*/
public class Octahedron extends MeshView implements Draw3DDefaults{
	private final TriangleMesh octahedronMesh;
	private final double size;
	
	/**
	 * Constructs an Octahedron MeshView object.
	 * 
	 * @param	size		Double size in pixels (screen coordinates), edge-length is size*Sqrt(2)
	 */
	public Octahedron(double size) {
		this.size = size;
		octahedronMesh = buildOctahedron(this.size);
		this.setMesh(octahedronMesh);
	}
	
    /**
     * Creates an Octahedron mesh centered at the origin (0,0,0).
     *
     * @param	s		Double size (in pixels)
     * 
     * @return	mesh	TriangleMesh for the specified Octahedron
     */
	private TriangleMesh buildOctahedron(double s) {
		
		TriangleMesh mesh = new TriangleMesh();
	
		// Define vertices of the Octahedron
		Point3D[] vertices = {  new Point3D( 0,  0,  s),
								new Point3D( s,  0,  0),
								new Point3D( 0,  s,  0),
								new Point3D(-s,  0,  0),
								new Point3D( 0, -s,  0),
								new Point3D( 0,  0, -s)
							 };

		for (int i = 0; i < vertices.length; i++) {
			mesh.getPoints().addAll((float)vertices[i].getX(),
									(float)vertices[i].getY(),
									(float)vertices[i].getZ());
		}
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh - Front faces
		mesh.getFaces().addAll(0, 0, 2, 0, 3, 0);
		mesh.getFaces().addAll(0, 0, 1, 0, 2, 0);
		mesh.getFaces().addAll(0, 0, 3, 0, 4, 0);
		mesh.getFaces().addAll(0, 0, 4, 0, 1, 0);
		
		// Build mesh - Back faces
		mesh.getFaces().addAll(5, 0, 2, 0, 1, 0);
		mesh.getFaces().addAll(5, 0, 3, 0, 2, 0);
		mesh.getFaces().addAll(5, 0, 1, 0, 4, 0);
		mesh.getFaces().addAll(5, 0, 4, 0, 3, 0);

		return mesh;
	}
	
	/**
	 * Returns the set of vertices of the Octahedron.
	 * 
	 * @return	points	Point3D[] array of Octahedron vertices
	 */
	public Point3D[] getPoints() {
		ObservableFloatArray pts = octahedronMesh.getPoints();
		int numPoints = octahedronMesh.getPointElementSize()+1;
		// Seems to be a JavaFX 8 bug - returns one less than the number of points
		Point3D[] points = new Point3D[numPoints];
		float x=0, y=0, z=0;
		for (int i = 0; i < 3*numPoints; i=i+3) {
			x = pts.get(i);
			y = pts.get(i+1);
			z = pts.get(i+2); 
			points[i/3] = new Point3D(x, y, z);
		}
		return points;
	}
}