package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Cone3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Cylinder3D;
import org.edisonwj.draw3d.Oval3D;
import org.edisonwj.draw3d.Rectangle3D;
import org.edisonwj.draw3d.Text3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestRotations extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestRotations");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(false);
		dt.setShowBoundaryCube(true);
		PhongMaterial blue = new PhongMaterial(Color.LIGHTBLUE);
		Color black = Color.BLACK;
		dt.setLabelFontSize(14);
		dt.setDrawColor(Color.LIGHTBLUE);
		
		dt.setSequencingOn();
		dt.drawBox3D(new Box3D(3, 2, 0, 4, .1, 2, blue));
		dt.drawRectangle3D(new Rectangle3D(3, 6, 0, 4, 2, blue));
		dt.drawCylinder3D(new Cylinder3D(-2, 2, 0, 1.5, .1, blue));
		dt.drawCircle(-2, 6, 0, 1.5);
		dt.drawOval3D(new Oval3D(3, -2, 0, 1.5, 1, blue));
		dt.drawCone3D(new Cone3D(-2, -2,  0, 1, 2, 0, 0, 0, blue));
		dt.drawLabel3D(new Text3D(-6, 6, -8, 180, 0, 0, "No Rotation", black));

		
		dt.incrSequence();
		dt.drawBox3D(new Box3D(3, 2, 0, 4, .1, 2, 45, 0, 0, blue));
		dt.drawRectangle3D(new Rectangle3D(3, 6, 0, 4, 2, 45, 0, 0, blue));
		dt.drawCylinder3D(new Cylinder3D(-2, 2, 0, 1.5, .1, 45, 0, 0, blue));
		dt.drawCircle(-2, 6, 0, 1.5, 45, 0, 0);
		dt.drawOval3D(new Oval3D(3, -2, 0, 1.5, 1, 45, 0, 0, blue));
		dt.drawCone3D(new Cone3D(-2, -2,  0, 1, 2, 45, 0, 0, blue));
		dt.drawLabel3D(new Text3D(-6, 6, -8, 180, 0, 0, "X Rotation", black));
		
		dt.incrSequence();
		dt.drawBox3D(new Box3D(3, 2, 0, 4, .1, 2, 0, 45, 0, blue));
		dt.drawRectangle3D(new Rectangle3D(3, 6, 0, 4, 2, 0, 45, 0, blue));
		dt.drawCylinder3D(new Cylinder3D(-2, 2, 0, 1.5, .1, 0, 45, 0, blue));
		dt.drawCircle(-2, 6, 0, 1.5, 0, 45, 0);
		dt.drawOval3D(new Oval3D(3, -2, 0, 1.5, 1, 0, 45, 0, blue));
		dt.drawCone3D(new Cone3D(-2, -2,  0, 1, 2, 0, 45, 0, blue));
		dt.drawLabel3D(new Text3D(-6, 6, -8, 180, 0, 0, "Y Rotation", black));
		
		dt.incrSequence();
		dt.drawBox3D(new Box3D(3, 2, 0, 4, .1, 2, 0, 0, 45, blue));
		dt.drawRectangle3D(new Rectangle3D(3, 6, 0, 4, 2, 0, 0, 45, blue));
		dt.drawCylinder3D(new Cylinder3D(-2, 2, 0, 1.5, .1, 0, 0, 45, blue));
		dt.drawCircle(-2, 6, 0, 1.5, 0, 0, 45);
		dt.drawOval3D(new Oval3D(3, -2, 0, 1.5, 1, 0, 0, 45, blue));
		dt.drawCone3D(new Cone3D(-2, -2,  0, 1, 2, 0, 0, 45, blue));
		dt.drawLabel3D(new Text3D(-6, 6, -8, 180, 0, 0, "Z Rotation", black));
		
		dt.incrSequence();
		dt.drawBox3D(new Box3D(3, 2, 0, 4, .1, 2, 45, 45, 45, blue));
		dt.drawRectangle3D(new Rectangle3D(3, 6, 0, 4, 2, 45, 45, 45, blue));
		dt.drawCylinder3D(new Cylinder3D(-2, 2, 0, 1.5, .1, 45, 45, 45, blue));
		dt.drawCircle(-2, 6, 0, 1.5, 45, 45, 45);
		dt.drawOval3D(new Oval3D(3, -2, 0, 1.5, 1, 45, 45, 45, blue));
		dt.drawCone3D(new Cone3D(-2, -2,  0, 1, 2, 45, 45, 45, blue));
		dt.drawLabel3D(new Text3D(-6, 6, -8, 180, 0, 0, "XYZ Rotation", black));

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}