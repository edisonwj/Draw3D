package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import org.edisonwj.draw3d.Polygon3D;

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
public class TestDrawPolygon extends Application implements Draw3DDefaults{
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDrawPolygon");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setCamera(0.0,  -180.0,  -800.0);
		dt.setOriginView(-100, -100, -100);
		dt.setDrawColor(Color.ANTIQUEWHITE);
		
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

		dt.setSequencingOn();
		dt.drawPolygon(p, false, false, false);
		
		dt.incrSequence();
		dt.drawPolygon(p, false, false, true);
		
		dt.incrSequence();
		dt.drawPolygon(p, false, true, false);
		
		dt.incrSequence();
		dt.drawPolygon(p, false, true, true);
		
		dt.incrSequence();
		dt.drawPolygon(p, true, false, false);
		
		dt.incrSequence();
		dt.drawPolygon(p, true, false, true);
		
		dt.incrSequence();
		dt.drawPolygon(p, true, true, false);
				
		dt.incrSequence();
		dt.drawPolygon(p, true, true, true);
		
		Polygon3D polygon = new Polygon3D(p, true, true, true);
		
		System.out.println(polygon.toString());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}