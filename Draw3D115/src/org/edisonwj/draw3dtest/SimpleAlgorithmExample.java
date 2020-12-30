package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Algorithm;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
*/
public class SimpleAlgorithmExample extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("SimpleAlgorithmExample");
		primaryStage.show();
	}

	private void buildData() {
		dt.setSequencingOn();
		dt.setLabelFontSize(16);
		dt.drawLabel(0, -3, 12, "AlgorithmExample");

		dt.incrSequence();
		Algorithm algorithmX1 = dt.loadAlgorithm("org.edisonwj.draw3d.AlgorithmX");
		algorithmX1.setMaterial(new PhongMaterial(Color.CRIMSON));
	}

	public static void main(String[] args) {
		launch(args);
	}
}