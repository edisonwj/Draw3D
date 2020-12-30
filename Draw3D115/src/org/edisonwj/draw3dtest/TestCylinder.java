package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;

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
public class TestCylinder extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestCylinder");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		
		Cylinder3D b3d1 = new Cylinder3D(new Point3D(0, 0, 0), 2, 4, 0, 0, 0, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawCylinder3D(b3d1);
	
		Cylinder3D b3d2 = new Cylinder3D(new Point3D(6, 0, 0), 2, 4, 45, 0, 0, new PhongMaterial(Color.SALMON));
		dt.drawCylinder3D(b3d2);
		
		Cylinder3D b3d3 = new Cylinder3D(new Point3D(-6, 0, 0), 2, 4, 0, 45, 0, new PhongMaterial(Color.TEAL));
		dt.drawCylinder3D(b3d3);
		
		Cylinder3D b3d4 = new Cylinder3D(new Point3D(0, 0, 6), 2, 4, 0, 90, 45, new PhongMaterial(Color.CRIMSON));
		dt.drawCylinder3D(b3d4);
		
		Cylinder3D b3d5 = new Cylinder3D(new Point3D(0, 8, 6), 2, 4, 0, 0, 0, new PhongMaterial(Color.GREY));
		dt.drawCylinder3D(b3d5);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}