    package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.04 February 2016
* 
*/
public class TestSequenceLabels extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestSequenceLabels");
		primaryStage.show();
	}
	
	private void buildData() {
		
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setCumulate(true);

		double[] v1 = {2,  2, 2};
		double[] v2 = {-2, 2, 2};
		double[] v3 = add(v1, v2);
		double[] v4 = crossProduct(v1, v2);
		
		dt.setSequencingOn();
		dt.setDrawColor(Color.CORAL);
		dt.drawVector(v1);
		dt.drawVector(v2);
		dt.drawLabel(1.6, 2, 0.5, 0, 180, 16, "Base Vectors");
		
		dt.incrSequence();
		dt.setDrawColor(Color.RED);
		dt.drawVector(v3);
		dt.drawLabel(v3[0], v3[1], v3[2], 0, 180, 16, "Vector Addition");
		
		dt.incrSequence();
		dt.setDrawColor(Color.BLUE);
		dt.drawArrow(v1[0], v1[1], v1[2], v3[0], v3[1], v3[2]);
		dt.drawArrow(v2[0], v2[1], v2[2], v3[0], v3[1], v3[2]);
		dt.drawLabel((v2[0]+.4), v2[1]+.6, v2[2], 0, 180,  16,  "Complete Parallelogram");
		
		dt.incrSequence();
		dt.setDrawColor(Color.GOLDENROD);
		dt.drawVector(v4);
		dt.drawLabel(v4[0], v4[1], v4[2], 0,  180,  16,  "Vector Cross Product");
	}
	
	public double[] add(double[] v1, double[] v2) {
		double[] v3 = new double[3];
		v3[0] = v1[0] + v2[0];
		v3[1] = v1[1] + v2[1];
		v3[2] = v1[2] + v2[2];
		return v3;
	}
	
	public double[] crossProduct(double[] v1, double[] v2) {
		double[] v3 = new double[3];
		v3[0] = v1[1] * v2[2] - v1[2] * v2[1];
		v3[1] = v1[2] * v2[0] - v1[0] * v2[2];
		v3[2] = v1[0] * v2[1] - v1[1] * v2[0];      
		return v3;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
