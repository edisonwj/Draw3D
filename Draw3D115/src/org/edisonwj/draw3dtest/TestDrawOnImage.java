package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Box3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.14 January 2018
* 
*/
public class TestDrawOnImage extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestDrawOnImage");
		primaryStage.show();
	}
	
	private void buildData() {

		Image desert = new Image("/Resources/desert.jpg");
				
		dt.setSequencingOn();
		dt.setCumulate(false);
		dt.setXYaxesOnly(true);
		dt.setShowAxes(false);
		dt.setTexture(desert);
		Box3D b3d = new Box3D(0, 0, 0, 20, 15, 0, 0, 0, 180);
		dt.drawBox3D(b3d);
		
		Point3D[] p3d = {	new Point3D(-5,-2, 0),
							new Point3D(-3,-4, 0),
							new Point3D( 3,-5, 0),
							new Point3D( 8,-3, 0)
						 };
		dt.setDrawColor(Color.YELLOW);
		dt.setLineRadius(6);
		dt.drawPolyLine3D(p3d);
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}