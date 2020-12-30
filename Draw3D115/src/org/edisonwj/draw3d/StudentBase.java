package org.edisonwj.draw3d;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
* StudentBase class provides a template for students/teachers to use in creating
* programs to display three dimensional data. The user creates code in the buildData()
* method using the methods available in Draw3D for drawing lines, vectors, etc.
* 
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class StudentBase extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("StudentBase");
		primaryStage.show();
	}
	
	private void buildData() {
		
    // Student places code here
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}