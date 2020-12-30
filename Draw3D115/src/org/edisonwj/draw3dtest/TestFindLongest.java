package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Plane3D;
import org.edisonwj.draw3d.Vector3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestFindLongest extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestFindLongest");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		
		dt.setShowBoundaryCube(true);

		dt.setSequencingOn();
		dt.setDrawColor(Color.BLUE);	
		dt.drawSphere( 0, 6, 0, .25 );
		dt.drawSphere( 0,-6, 0, .25 );
		dt.drawSphere( 0, 0, 6, .25 );
		dt.drawSphere( 0, 0,-6, .25 );
		dt.setDrawColor(Color.LIGHTBLUE);
		dt.drawPlane( 0, 6, 5,
			    	  0,-6, 0,
			    	  0, 0, 6);
	
		dt.incrSequence();
		dt.setDrawColor(Color.RED);
		dt.drawSphere( 0, 5, 0, .25 );
		dt.drawSphere( 0,-5, 0, .25 );
		dt.drawSphere( 5, 0, 0, .25 );
		dt.drawSphere(-5, 0, 0, .25 );
		dt.setDrawColor(Color.LIGHTPINK);
	    dt.drawPlane( 0, 5, 0,
		 	    	  0,-5, 0,
		 	    	  5, 0, 0);
		
		dt.incrSequence();
		dt.setDrawColor(Color.GREENYELLOW);
		dt.drawSphere( 4, 0, 0, .25 );
		dt.drawSphere(-4, 0, 0, .25 );
		dt.drawSphere( 0, 0, 4, .25 );
		dt.drawSphere( 0, 0,-4, .25 );
		dt.setDrawColor(Color.LIGHTGREEN);
		dt.drawPlane( 4, 0, 3,
			    	 -4, 0, 0,
			    	  0, 0, 4);

	    
		dt.incrSequence();;
		dt.setDrawColor(Color.LIGHTGREY);
	    Plane3D pl3 = new Plane3D(1, -2, 0, 3, 1, 4, 0, -1, 2);
	    dt.drawPlane3D(pl3);
		dt.setDrawColor(Color.MAGENTA);
		Vector3D un3 = Vector3D.multiply(10.0, pl3.getUnitNormal());
		dt.drawVector3D(un3);
		
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGREY);
		Plane3D pl1 = new Plane3D(4, 5, 6, 7);
		dt.drawPlane3D (pl1);
		dt.setDrawColor(Color.MAGENTA);
		Vector3D un1 = Vector3D.multiply(10.0,pl1.getUnitNormal());
		dt.drawVector3D(un1);
	
		dt.incrSequence();
		dt.setDrawColor(Color.LIGHTGREY);
		Plane3D pl2 = new Plane3D(4, 5, -6, 7);
		dt.drawPlane3D (pl2);
		dt.setDrawColor(Color.MAGENTA);
		Vector3D un2 = Vector3D.multiply(10.0,pl2.getUnitNormal());
		dt.drawVector3D(un2);
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}