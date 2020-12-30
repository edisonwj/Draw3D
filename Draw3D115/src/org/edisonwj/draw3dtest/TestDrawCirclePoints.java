package org.edisonwj.draw3dtest;

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
* @version 1.02 September 2015
* 
*/
public class TestDrawCirclePoints extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDrawCirclePoints");
		primaryStage.show();
	}
	
	private void buildData() {
		int numPoints = 1000;
		dt.setYaxisUp(true);
		dt.setXYZRange(1.0,  1.0,  1.0);
		dt.setAmbientLight(false);
		dt.setPointLight(true);	
		dt.setDrawColor(Color.CRIMSON);
		
	    Point3D[] points = new Point3D[numPoints];
	    double radius = 0.5;
	    double theta = 0.0;
	    double increment = 2.0*Math.PI/numPoints;
	    for (int i=0; i<numPoints; i++) {		
	      points[i] = new Point3D(	radius*Math.sin(theta)+radius,
	    		  					radius*Math.cos(theta)+radius,
	    		  					0.0);
	      dt.drawPoint(points[i].getX(), points[i].getY(), points[i].getZ());
	      theta = theta + increment;
	    }
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
