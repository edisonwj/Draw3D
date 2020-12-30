package org.edisonwj.draw3dlin;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.14 February 2018
* 
*/
public class CheckIndp extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("CheckIndp");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		
		dt.setDrawColor(Color.RED);
		dt.drawVector(2,-1,1);
		dt.setDrawColor(Color.BLUE);
		dt.drawVector(0,1,1);
		dt.setDrawColor(Color.GREEN);
		dt.drawVector(0,1,7);
		
		dt.incrSequence();
		dt.setDrawColor(Color.RED);
		dt.drawVector(2,-1,1);
		dt.setDrawColor(Color.BLUE);
		dt.drawVector(0,1,1);
		dt.setDrawColor(Color.GREEN);
		dt.drawVector(0,1,8);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}