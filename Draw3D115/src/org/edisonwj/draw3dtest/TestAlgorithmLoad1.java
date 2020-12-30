package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Algorithm;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.11 June 2017
* 
*/
public class TestAlgorithmLoad1 extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAlgorithmLoad1");
		primaryStage.show();
	}
	
	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);

		dt.setSequencingOn();
		dt.buildTest();
		
		dt.incrSequence();
		Algorithm algorithmX1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmX");
		algorithmX1.setDelay(64);
		Algorithm algorithmY1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmY");
		algorithmY1.setDelay(128);
		
		dt.incrSequence();
		Algorithm algorithmX2 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmX");
		algorithmX2.setDelay(32);
		Algorithm algorithmY2 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmY");
		algorithmY2.setDelay(32);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
