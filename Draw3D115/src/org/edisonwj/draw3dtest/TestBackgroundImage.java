package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Box3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestBackgroundImage extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestBackgroundImage");
		primaryStage.show();
	}
	
	private void buildData() {

		Image i01 = new Image("/Resources/desert.jpg");

		dt.setSequencingOn();
		dt.setCumulate(false);
		dt.setXYaxesOnly(true);

		// Rectangle
		
		dt.setTexture(i01);
		dt.drawBox3D(new Box3D(0, 0, 0, 20, 20, 0, 0, 0, 180));
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}