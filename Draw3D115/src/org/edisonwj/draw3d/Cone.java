package org.edisonwj.draw3d;

import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
/**
* Cone class extends MeshView and provides for creation of a Cone mesh object.
* 
* The class implements defaults specified in Draw3DDefaults.
* 
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class Cone extends MeshView implements Draw3DDefaults{
	private final Mesh coneMesh;
	private final float radius;
	private final float height;
	private final int divisions;
	
	/**
	 * Constructs a Cone MeshView object.
	 * Divisions is set to default.
	 * 
	 * @param	radius	Radius of the cone in pixels (screen coordinates)
	 * @param	height	Height of the Cone in pixels (screen coordinates)
	 */
	public Cone(double radius, double height) {
		this(radius, height, CONE_DIVISIONS);
	}
	
	/**
	 * Constructs a Cone MeshView object.
	 * 
	 * @param	radius		Float radius of the cone in pixels (screen coordinates)
	 * @param	height		Float height of the Cone in pixels (screen coordinates)
	 * @param	divisions	Integer number of divisions in Cone construction	
	 */
	public Cone(double radius, double height, int divisions) {
		this.radius = (float)radius;
		this.height = (float)height;
		this.divisions = divisions;
		coneMesh = buildCone((float)this.radius, (float)this.height, this.divisions);
		this.setMesh(coneMesh);
	}
	
    /**
     * Creates a Cone mesh centered on the tip at the origin (0,0,0).
  	 * The Cone is constructed with the tip at (0,0,0) and the base upwards
  	 * (with Y axis increasing upwards).
     *
     * @param	rf		float radius (in pixels)
     * @param	hf		float height (in pixels)
     * @param	div		integer number of divisions
     * @return	mesh	TriangleMesh for the specified Cone
     */
	private TriangleMesh buildCone(float rf, float hf, int div) {
		
		TriangleMesh mesh = new TriangleMesh();
	
		// Create points of cone base
		double theta = 2.0*Math.PI;
		double decrement = 2.0*Math.PI/div;
		float xf = 0.0f;
		float yf = 0.0f;
		for (int i = div; i > 0; i--) {
			xf = (float)(rf*Math.cos(theta));
			yf = (float)(rf*Math.sin(theta));
			mesh.getPoints().addAll(xf, hf, yf);
			theta = theta - decrement;
		}
		
		// Place the peak of the cone at (0, 0, 0) and [div] index in the mesh points array.
		mesh.getPoints().addAll(0, 0, 0);
	
		// Place the center of the base at [div+1] in the mesh points array.
		mesh.getPoints().addAll(0, hf, 0);
	
		// Set default texture coordinates
		mesh.getTexCoords().addAll(0,0);
	
		// Build mesh
		for (int i = 0; i < div; i++) {
			mesh.getFaces().addAll(
					div,0,(i+1)%div,0,i,0,
					div+1,0,i,0,(i+1)%div,0 );
		}
		return mesh;
	}
}