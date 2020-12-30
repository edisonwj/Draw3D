package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.05 February 2016
*  
*/
public class TestPlanes extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPlanes");
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
		
		dt.incrSequence();
		dt.setDrawColor(Color.BLACK);
		dt.drawSphere( 1,-2, 0, .25 );
		dt.drawSphere( 3, 1, 4, .25 );
		dt.drawSphere( 0,-1, 2, .25 );
		dt.setDrawColor(Color.LIGHTGREY);
	    dt.drawPlane(1,-2, 0,
	   		      	 3,1,4,
	   		      	 0,-1,2);
		
		dt.incrSequence();
		dt.drawPlane (4,5,6,7);
		
		dt.incrSequence();
		dt.drawPlane(-4, 5, 6, 7);
		
		dt.incrSequence();
		dt.drawPlane(4, -5, 6, 7);
		
		dt.incrSequence();
		dt.drawPlane(4, 5, -6, 7);
		
		dt.incrSequence();
		dt.drawPlane(4, 5, 6, -7);
		
		dt.incrSequence();
		dt.drawPlane(1, 2,  3, 4);

		dt.incrSequence();
		dt.drawPlane(0, 5, -2, 6);

		dt.incrSequence();
		dt.drawPlane(8,  -1,  -1, 6);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}