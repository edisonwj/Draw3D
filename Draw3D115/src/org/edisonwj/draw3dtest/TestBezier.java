package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
* 
*/
public class TestBezier extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestBezier");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		
	    Point3D[] p = {
	    		new Point3D(0, 2, 0),
	    		new Point3D(0, 0, 5),
	    		new Point3D(2, 4, 2),
	    		new Point3D(-4, 6,4),
	    		new Point3D(0, 9, 5),
	    		new Point3D(2, 6, 0)};
	    dt.setDrawColor(Color.RED);
	    dt.drawBezier(p);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}