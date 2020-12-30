package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestCameraSetting extends Application implements Draw3DDefaults{
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestCameraSetting");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCamera(0.0, -180.0,  -600.0);

		dt.setDrawColor(Color.BLUE);
		dt.drawVector(6,6,6);
		dt.drawVector(-6,6,6);


	}
	
	public static void main(String[] args) {
		launch(args);
	}
}