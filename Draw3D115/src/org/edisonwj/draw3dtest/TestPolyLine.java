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
* @version 1.00 July 2015
* 
*/
public class TestPolyLine extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPolyLine");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
//		dt.setAmbientLight(true);
//		dt.setPointLight(false);
		
		dt.setShowBoundaryCube(true);
		
		Point3D[] p = { new Point3D( -5.0,  -2.6, 0),
						new Point3D(  -0.75,  -5.0, 0),
						new Point3D(  6.0,  -4.0, 1),
						new Point3D(  5.0,  5.4, 5) };
		dt.setDrawColor(Color.CORAL);
		dt.drawPolyLine3D(p);


		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
