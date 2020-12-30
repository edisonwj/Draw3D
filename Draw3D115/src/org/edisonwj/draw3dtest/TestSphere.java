package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestSphere extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestSphere");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		Sphere3D s3d1 = new Sphere3D(new Point3D(0, 0, 0), 2, 0, 0, 0, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawSphere3D(s3d1);
	
		Sphere3D s3d2 = new Sphere3D(new Point3D(8, 0, 0), 2, 0, 0, 0, new PhongMaterial(Color.SALMON));
		dt.drawSphere3D(s3d2);
		
		Sphere3D s3d3 = new Sphere3D(new Point3D(-8, 0, 0), 2, 0, 0, 0, new PhongMaterial(Color.TEAL));
		dt.drawSphere3D(s3d3);
		
		Sphere3D s3d4 = new Sphere3D(new Point3D(0, 0, 8), 2, 0, 0, 0, new PhongMaterial(Color.CRIMSON));
		dt.drawSphere3D(s3d4);
		
		Sphere3D s3d5 = new Sphere3D(new Point3D(0, 8, 6), 2, 0, 0, 0, new PhongMaterial(Color.GREY));
		dt.drawSphere3D(s3d5);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}