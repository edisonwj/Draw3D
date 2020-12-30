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
public class TestArrowVector extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestArrowVector");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setCumulate(false);

		dt.setSequencingOn();
		dt.setVectorRadius(1);
		dt.setDrawColor(Color.RED);
		dt.drawVector(0, 5, 5);
		dt.drawVector(0, -5, 5);
        dt.setDrawColor(Color.BLUE);
        dt.setArrowRadius(1);
      	dt.drawArrow(2, 0, 0, 7, 0, 5);
      	dt.drawArrow(-2, 0, 0, -7, 0, 5);
        
      	dt.setDrawColor(Color.GREEN);
        dt.drawVector(5, 5, 5);
        dt.drawArrow(0, 0, 0, -5, -5, -5);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}