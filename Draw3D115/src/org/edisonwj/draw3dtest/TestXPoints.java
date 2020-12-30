package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class TestXPoints extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestXPoints");
		primaryStage.show();
	}
	
	private void buildData() {
		double n = 10.0;
		double m = n * 1.0;
		double minx = -6.0;
		double maxx= 6.0;
		double miny = -6.0;
		double maxy = 6.0;
		double intv = .2;
		int sizex = (int)(((maxx - minx) / intv) + 1.0);
		int sizey = (int)(((maxy - miny) / intv) + 1.0);
		
		Point3D[] f = new Point3D[sizex * sizey];
		
		double x = minx-intv;
		for (int i = 0; i < sizex; i++) {
			x += intv;
			double y = miny-intv;
    		for (int j = 0; j < sizey; j++) {
    			y += intv;
    			double z = m * Math.cos(Math.pow(x*x + y*y, 0.5))/(2.0 + Math.pow(x*x + y*y, 0.5));
    			int k = i*sizey + j;
    			f[k] = new Point3D(x, y, z);
    		}
    	}
		dt.setDrawColor(Color.SALMON);
		dt.drawPoint3DSet(f);
	}


	
	public static void main(String[] args) {
		launch(args);
	}
}
