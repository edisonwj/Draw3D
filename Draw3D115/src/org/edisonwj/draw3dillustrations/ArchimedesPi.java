package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Algorithm;
import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.12 June 2017
*
* Illustrates Archimedes' method for estimating Pi
* using inscribed and circumscribed polygons with
* increasing numbers of sides converging to the
* circle.
*/
public class ArchimedesPi extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Run Algorithm Archimedes Pi");
		primaryStage.show();
	}

	private void buildData() {
		dt.setSequencingOn();
		dt.setAmbientLight(false);
		dt.setPointLight(true);
		dt.setXYZRange(1.0, 1.0, 1.0);
		dt.setXYaxesOnly(true);
		dt.setShowAxes(false);
		dt.setLabelFontSize(24);
		dt.drawLabel(-0.75, 0.50, 0.0, "Archimedes Estimation of PI");

		Algorithm algorithm = null;
		dt.incrSequence();
		dt.setShowAxes(true);
		algorithm = dt.loadAlgorithm("org.edisonwj.draw3dillustrations.AlgorithmArchimedesPi");

		dt.incrSequence();
		dt.setScale(3, 3, 3);
		dt.setOriginView(-600, 60, 0);
		algorithm = dt.loadAlgorithm("org.edisonwj.draw3dillustrations.AlgorithmArchimedesPi");
	}

	public static void main(String[] args) {
		launch(args);
	}
}