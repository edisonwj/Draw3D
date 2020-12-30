 package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
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
public class TestRangeChange extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestRangeChange");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setCumulate(false);

		dt.setSequencingOn();
		dt.setDrawColor(Color.RED);
		dt.drawArrow(1,1,1, 5,5,5);
        
        dt.incrSequence();
        dt.setXYZRange(1, 1, 1);
		dt.setDrawColor(Color.BLUE);
		dt.drawArrow(-.1,-.1,.1, -.6,-.6,-.6);
		
        dt.incrSequence();
        dt.setXYZRange(100, 100, 100);
		dt.setDrawColor(Color.GREEN);
		dt.drawArrow(-20,-20,-20, -40,60,60);  
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}