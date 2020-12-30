package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Drone3D;
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
* @version 1.00 July 2015
* 
*/
public class TestDrone extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDrone");
		primaryStage.show();
	}
	
	private void buildData() {
		
		Sphere3D s1 = new Sphere3D(3, 3, 3, 2);
		System.out.println("s1 =" + s1.toString());
		PhongMaterial m11 = new PhongMaterial(Color.RED);
		PhongMaterial m12 = new PhongMaterial(Color.BROWN);
		PhongMaterial m21 = new PhongMaterial(Color.BLUE);
		PhongMaterial m22 = new PhongMaterial(Color.BLACK);
		Drone3D d1 = new Drone3D(new Point3D(3, 3, 3), 48, 8, 0, 0, 0, m11, m12);
		Drone3D d2 = new Drone3D(new Point3D(8, 8, 8), 48, 8, 0, 0, 0, m21, m22);
		System.out.println("d1= " + d1);
		System.out.println("d2= " + d2);
		dt.drawDrone3D(d1);
		dt.drawDrone3D(d2);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}