package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestSetDefaults extends Application implements Draw3DDefaults{
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestSetDefaults");
		primaryStage.show();
	}
	
	private void buildData() {
		
		dt.setSequencingOn();
		dt.setCumulate(false);
		double[] v1 = {2,  2, 2};
		double[] v2 = {2, -2, 2};
		dt.setDrawColor(Color.BLUE);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.drawLabel(0, 1.6, 0.5, 0, 180, 18, "Base Vectors");
	    
		dt.incrSequence();
//		dt.setYaxisUp(true);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setShowAxes(false);
		dt.setShowBoundaryCube(false);
		dt.setCamera(-45.0,  -180.0,  -800.0);
		dt.setOriginView(-100, -100, -100);		
		Point3D[] p = { new Point3D( 3.0, 1.4, 0),	// p0
						new Point3D( 5.1, 2.3, 0),	// p1
						new Point3D( 5.8, 3.8, 0),	// p2
						new Point3D( 5.4, 5.2, 0),	// p3
						new Point3D( 4.8, 6.1, 0),	// p4
						new Point3D( 3.6, 7.1, 0),	// p5
						new Point3D( 2.1, 5.7, 0),	// p6
						new Point3D( 1.4, 4.2, 0),	// p7
						new Point3D( 1.0, 3.0, 0),	// p8
						new Point3D( 1.0, 1.0, 0)	// p9
						};	
		dt.setDrawColor(Color.ANTIQUEWHITE);
		dt.drawPolygon(p, true, true, true);
		
		dt.incrSequence();
		dt.setDefaults();
		dt.setZaxisUp(true);
		dt.setDrawColor(Color.CORAL);
		dt.drawCone(4,2,0,1.5,3);

	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
