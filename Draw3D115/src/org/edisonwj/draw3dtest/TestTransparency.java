package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Box3D;
import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.14 February 2018
* 
*/
public class TestTransparency extends Application {
	private Draw3D dt;
	
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestTransparency");
		primaryStage.show();
	}
	
	private void buildData() {

		dt.setXYaxesOnly(true);
		dt.setShowAxes(false);

		Image map = new Image("/Resources/mercator.jpg");

		Box3D r1 = new Box3D( new Point3D( 0, 0, 0), 20, 10, 0);
		dt.setTexture(map);
		dt.drawBox3D(r1);

		dt.setDrawColor(Color.BLACK);
		dt.setLabelFontSize(16);

		Box3D r2 = new Box3D( new Point3D(-8, 0, 0), 3, 12, 2);
		r2.setMaterial(new PhongMaterial(Color.rgb(255, 0, 0, .0)));
		dt.drawBox3D(r2);
		dt.drawLabel(-8, -7, 0, 0, 180, 180, ".0");

		Box3D r3 = new Box3D( new Point3D(-3, 0, 0), 3, 12, 2);
		r3.setMaterial(new PhongMaterial(Color.rgb(255, 0, 0, .1)));
		dt.drawBox3D(r3);
		dt.drawLabel(-3, -7, 0, 0, 180, 180, ".1");

		Box3D r4 = new Box3D( new Point3D( 3, 0, 0), 3, 12, 2);
		r4.setMaterial(new PhongMaterial(Color.rgb(255, 0, 0, .2)));
		dt.drawBox3D(r4);
		dt.drawLabel( 3, -7, 0, 0, 180, 180, ".2");

		Box3D r5 = new Box3D( new Point3D( 8, 0, 0), 3, 12, 2);
		r5.setMaterial(new PhongMaterial(Color.rgb(255, 0, 0, .4)));
		dt.drawBox3D(r5);
		dt.drawLabel( 8, -7, 0, 0, 180, 180, ".4");

		dt.drawLabel(-6, -8,  0,  0,180, 180, "Example Transparency Levels");

		dt.incrSequence();
		dt.setXYaxesOnly(false);
		dt.setDrawColor(Color.ORCHID);
		dt.drawSphere(0,0,0,6);

		dt.setDrawColor(Color.rgb(100, 100, 240, .5));
		dt.drawCuboid(0, 0, 0, 6, 16, 8, -10, 20, -20);
		
		dt.incrSequence();
		dt.setXYaxesOnly(false);
		dt.setShowAxes(true);
//		dt.setBackgroundColor(Color.LIGHTGOLDENRODYELLOW);
		Box3D es = new Box3D( new Point3D( 0, 0, 0), 20, 10, 0);
		PhongMaterial m = new PhongMaterial();
		m.setDiffuseMap(map);
		m.setDiffuseColor(new Color(1,1,1,0.5));
		es.setMaterial(m);
		dt.drawBox3D(es);
		
		dt.setDrawColor(Color.BLACK);
		dt.drawCuboid(-.75, .5, 0, 1, 14, 1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}