package org.edisonwj.draw3dart;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Icosahedron3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Octahedron3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.07 March 2016
* 
*/
public class TestOctahedron extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestOctahedron");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		// Get Octahedron Vertices
		double s = 2;
		double phi = (1 + Math.sqrt(5))/2;
		double p = s*phi;
		Octahedron3D o3d0 = new Octahedron3D(0, 0, 0, s);
		Point3D[] vertices = o3d0.getVertices();
		
		double elDistance = vertices[0].distance(vertices[1]);
		double elComputed = s*Math.sqrt(2);
		System.out.println("s= " + s +  ", edge-length-distance= " + elDistance +
										", edge-length-computed= " + elComputed);

		// Draw Front Octahedron Faces
		dt.setSequencingOn();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Octahedron3D o3d1 = new Octahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		o3d1.setDrawMode(DrawMode.LINE);
		o3d1.setCullFace(CullFace.BACK);
		dt.drawPolyhedron3D(o3d1, true);
		
		// Draw Back Octahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Octahedron3D o3d2 = new Octahedron3D(0, 0, 0, s, new PhongMaterial(Color.RED));
		o3d2.setDrawMode(DrawMode.LINE);
		o3d2.setCullFace(CullFace.FRONT);
		dt.drawPolyhedron3D(o3d2, true);
		
		// Draw All Octahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Octahedron3D o3d3 = new Octahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		o3d3.setDrawMode(DrawMode.LINE);
		o3d3.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(o3d3, true);
		
		// Draw Octahedron Faces with Circumscribing Sphere
		dt.incrSequence();
		Octahedron3D o3d4 = new Octahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		o3d4.setDrawMode(DrawMode.LINE);
		o3d4.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(o3d4, true);
		Sphere3D s3d1 = new Sphere3D(0, 0, 0, s, new PhongMaterial(Color.RED));
		s3d1.setDrawMode(DrawMode.LINE);
		s3d1.setSphereDivisions(16);
		dt.drawSphere3D(s3d1);

		// Draw Octahedrons with Rotations
		dt.incrSequence();
		Octahedron3D o3d5 = new Octahedron3D(0, 0, 0, s, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d5, true);
		
		dt.incrSequence();
		Octahedron3D o3d6 = new Octahedron3D(6, 0, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d6, true);
		Octahedron3D o3d7 = new Octahedron3D(-6, 0, 0, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d7, true);
			
		dt.incrSequence();
		Octahedron3D o3d8 = new Octahedron3D(0, 6, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d8, true);
		Octahedron3D o3d9 = new Octahedron3D(0, -6, 0, s, 0, 20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d9, true);
		
		dt.incrSequence();
		Octahedron3D o3d10 = new Octahedron3D(0, 0, 6, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d10, true);
		Octahedron3D o3d11 = new Octahedron3D(0, 0, -6, s, 0, 0, 20, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d11, true);
		
		dt.incrSequence();
		Octahedron3D o3d12 = new Octahedron3D(-5, -5, 5, s, 0, -20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d12, true);
		Octahedron3D o3d13 = new Octahedron3D(5, 5, -5, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(o3d13, true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}