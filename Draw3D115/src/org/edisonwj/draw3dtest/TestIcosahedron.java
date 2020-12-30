package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Dodecahedron3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Icosahedron3D;

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
public class TestIcosahedron extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestIcosahedron");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setZaxisUp(true);

		// Get Icosahedron Vertices
		double s = 2;
		double phi = (1 + Math.sqrt(5))/2;
		double p = s*phi;
		Icosahedron3D i3d0 = new Icosahedron3D(0, 0, 0, s);
		Point3D[] vertices = i3d0.getVertices();

		double elDistance = vertices[0].distance(vertices[1]);
		double elComputed = 2*s;
		System.out.println("s= " + s +  ", edge-length-distance= " + elDistance +
										", edge-length-computed= " + elComputed);

		// Draw Front Icosahedron Faces
		dt.setSequencingOn();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Icosahedron3D i3d1 = new Icosahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		i3d1.setDrawMode(DrawMode.LINE);
		i3d1.setCullFace(CullFace.BACK);
		dt.drawPolyhedron3D(i3d1);

		// Draw Back Icosahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Icosahedron3D i3d2 = new Icosahedron3D(0, 0, 0, s, new PhongMaterial(Color.RED));
		i3d2.setDrawMode(DrawMode.LINE);
		i3d2.setCullFace(CullFace.FRONT);
		dt.drawPolyhedron3D(i3d2);

		// Draw All Icosahedron Faces
		dt.incrSequence();
		dt.setLabelFontSize(12);
		for (int i = 0; i < vertices.length; i++) {
			dt.drawSphere3D(new Sphere3D(vertices[i], .05));
			dt.drawLabel(vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), "  v"+i);
		}
		Icosahedron3D i3d3 = new Icosahedron3D(0, 0, 0, s, new PhongMaterial(Color.TRANSPARENT));
		i3d3.setDrawMode(DrawMode.LINE);
		i3d3.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(i3d3, true);

		// Draw Icosahedron Faces with Circumscribing Sphere
		dt.incrSequence();
		Icosahedron3D i3d4 = new Icosahedron3D(0, 0, 0, s, new PhongMaterial(Color.BLUE));
		i3d4.setDrawMode(DrawMode.LINE);
		i3d4.setCullFace(CullFace.NONE);
		dt.drawPolyhedron3D(i3d4);
		Sphere3D s3d1 = new Sphere3D(0, 0, 0, Math.sqrt(s*s+p*p), new PhongMaterial(Color.RED));
		s3d1.setDrawMode(DrawMode.LINE);
		s3d1.setSphereDivisions(16);
		dt.drawSphere3D(s3d1);

		// Draw Icosahedrons with Rotations
		dt.incrSequence();
		Icosahedron3D i3d5 = new Icosahedron3D(0, 0, 0, s, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d5, true);

		dt.incrSequence();
		Icosahedron3D i3d6 = new Icosahedron3D(6, 0, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d6, true);
		Icosahedron3D i3d7 = new Icosahedron3D(-6, 0, 0, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d7, true);

		dt.incrSequence();
		Icosahedron3D i3d8 = new Icosahedron3D(0, 6, 0, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d8, true);
		Icosahedron3D i3d9 = new Icosahedron3D(0, -6, 0, s, 0, 20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d9, true);

		dt.incrSequence();
		Icosahedron3D i3d10 = new Icosahedron3D(0, 0, 6, s, 0, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d10, true);
		Icosahedron3D i3d11 = new Icosahedron3D(0, 0, -6, s, 0, 0, 20, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d11, true);

		dt.incrSequence();
		Icosahedron3D i3d12 = new Icosahedron3D(-5, -5, 5, s, 0, -20, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d12, true);
		Icosahedron3D i3d13 = new Icosahedron3D(5, 5, -5, s, 20, 0, 0, new PhongMaterial(Color.LIGHTGRAY));
		dt.drawPolyhedron3D(i3d13, true);

	}

	public static void main(String[] args) {
		launch(args);
	}
}