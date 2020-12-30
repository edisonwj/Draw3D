package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestDrawModeCullFace extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDrawModeCullFace");
		primaryStage.show();
	}
	
	private void buildData() {
		
//		dt.setDrawColor(Color.SALMON);
//		dt.setShapeFill(false);
//		dt.setCullFaceNone(false);
//		dt.drawCuboid(-5, -5, 3, 2, 4, 2, 0, 0, 0);
		
		
		dt.setDrawColor(Color.FUCHSIA);
		dt.setCylinderDivisions(8);
		dt.setDrawMode(DrawMode.FILL);
		dt.setCullFace(CullFace.NONE);
		dt.drawCylinder(-5, -5, 5, 1, 3, 0, 0, 0);		
		
		dt.setDrawColor(Color.FUCHSIA);
		dt.setCylinderDivisions(8);
		dt.setDrawMode(DrawMode.LINE);
		dt.setCullFace(CullFace.NONE);
		dt.drawCylinder(2, -5, 5, 1, 3, 0, 0, 0);
		
		dt.setDrawColor(Color.FUCHSIA);
		dt.setCylinderDivisions(8);
		dt.setDrawMode(DrawMode.LINE);
		dt.setCullFace(CullFace.BACK);
		dt.drawCylinder(6, -5, 5, 1, 3, 0, 0, 0);
		
				
//		dt.setDrawColor(Color.AQUA);
//		dt.setConeDivisions(8);
//		dt.setShapeFill(true);
//		dt.setCullFaceNone(true);
//		dt.drawCone(-5, 5, -3, 1, 3, 0, 0, 0);
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}