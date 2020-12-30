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
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestBox extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestBox");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
			
		Box3D b3d1 = new Box3D(new Point3D(0, 0, 0), 4, 2, 1, 0, 0, 0, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawBox3D(b3d1);
	
		Box3D b3d2 = new Box3D(new Point3D(6, 0, 0), 4, 2, 1, 45, 0, 0, new PhongMaterial(Color.SALMON));
		dt.drawBox3D(b3d2);
		
		Box3D b3d3 = new Box3D(new Point3D(-6, 0, 0), 4, 2, 1, 0, 45, 0, new PhongMaterial(Color.TEAL));
		dt.drawBox3D(b3d3);
		
		Box3D b3d4 = new Box3D(new Point3D(0, 0, 6), 4, 2, 1, 0, 0, 45, new PhongMaterial(Color.CRIMSON));
		dt.drawBox3D(b3d4);
		
		Box3D b3d5 = new Box3D(new Point3D(0, 8, 6), 4, 2, 1, 0, 0, 0, new PhongMaterial(Color.GREY));
		dt.drawBox3D(b3d5);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}