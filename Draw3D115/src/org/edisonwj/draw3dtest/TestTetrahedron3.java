package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Tetrahedron3D;
import org.edisonwj.draw3d.Sphere3D;

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
* @version 1.08 January 2017
*
*/
public class TestTetrahedron3 extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestTetrahedron3");
		primaryStage.show();
	}

	private void buildData() {
		PhongMaterial blueMaterial = new PhongMaterial(Color.LIGHTBLUE);
		PhongMaterial redMaterial = new PhongMaterial(Color.RED);
        PhongMaterial lightgrayMaterial = new PhongMaterial(Color.LIGHTGRAY);

		Rand mr = new Rand();

		dt.setAmbientLight(false);
		dt.setPointLight(true);

		double os = 1.5;


		for (int i = 0; i < 100; i++) {

			// Pick location
			double ox = mr.uniform(-8, 8);			// random x coordinate
			double oy = mr.uniform(-8, 8);			// random y coordinate
			double oz = mr.uniform(-8, 8);			// random z coordinate

			// Pick rotations
			double rx = mr.uniform(0, 360);			// rotation about x axis
			double ry = mr.uniform(0, 360);			// rotation about y axis
			double rz = mr.uniform(0, 360);			// rotation about z axis

			Tetrahedron3D dod3d = new Tetrahedron3D(ox, oy, oz, os, rx, ry, rz, blueMaterial);
			dod3d.setDrawMode(DrawMode.FILL);
			dt.drawPolyhedron3D(dod3d, true);
			System.out.println(dod3d);
//			dt.incrSequence();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}