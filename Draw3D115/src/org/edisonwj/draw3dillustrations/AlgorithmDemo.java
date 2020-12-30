package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Algorithm;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
* Demonstrates running various Draw3D Algorithm class files with setting
* of algorithm parameters such as delay time, etc.
*
* @author William Edison
* @version 1.12 June 2017
*
*/
public class AlgorithmDemo extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("AlgorithmDemo");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(false);
		dt.setPointLight(true);

		dt.setSequencingOn();														// View 0
		dt.buildTest();

		dt.incrSequence();															// View 1 (includes 0)
		dt.setCumulate(true);
		Algorithm algorithmC1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmC");
		algorithmC1.setDelay(100);
		algorithmC1.setMaterial(new PhongMaterial(Color.ORCHID));
		Algorithm algorithmC2 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmC");
		algorithmC2.setDelay(25);
		algorithmC2.setMaterial(new PhongMaterial(Color.TAN));

		dt.incrSequence();															// View 2
		dt.setCumulate(false);
		Algorithm algorithmX1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmX");
		Algorithm algorithmY1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmY");
		Algorithm algorithmZ1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmZ");
		algorithmX1.setDelay(128);
		algorithmY1.setDelay(32);
		algorithmZ1.setDelay(8);

		dt.incrSequence();															// View 3
		dt.buildTest();

		dt.incrSequence();															// View 4
		dt.setCumulate(true);
		dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmDrone1");
		dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmDrone2");
//		dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmDrone2");

		dt.incrSequence();															// View 5
		dt.setCumulate(false);
		dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmDrone3");
		dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmDrone4");

		dt.incrSequence();
		dt.buildTest();
	}

	public static void main(String[] args) {
		launch(args);
	}
}