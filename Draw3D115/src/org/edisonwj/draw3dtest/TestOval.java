package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Oval3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestOval extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestOval");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(true);
		
		Oval3D o3d1 = new Oval3D(new Point3D(0, 0, 0), 4, 2, 0, 0, 0, new PhongMaterial(Color.CORNFLOWERBLUE));
		dt.drawOval3D(o3d1);
	
		Oval3D o3d2 = new Oval3D(new Point3D(6, 0, 0), 4, 2, 45, 0, 0, new PhongMaterial(Color.SALMON));
		dt.drawOval3D(o3d2);
		
		Oval3D o3d3 = new Oval3D(new Point3D(-6, 0, 0), 4, 2, 0, 45, 0, new PhongMaterial(Color.TEAL));
		dt.drawOval3D(o3d3);
		
		Oval3D o3d4 = new Oval3D(new Point3D(0, 0, 6), 4, 2, 0, 90, 45, new PhongMaterial(Color.CRIMSON));
		dt.drawOval3D(o3d4);
		
		Oval3D o3d5 = new Oval3D(new Point3D(0, 8, 6), 4, 2, 0, 0, 0, new PhongMaterial(Color.GREY));
		dt.drawOval3D(o3d5);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}