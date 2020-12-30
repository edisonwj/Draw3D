package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Draw3D;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.12 June 2017
*
*/
public class DrawParabolaHyperbola extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("DrawParabolaHyperbola");
		primaryStage.show();
	}

	private void buildData() {
		dt.setXYaxesOnly(true);
		dt.setAmbientLight(true);
		dt.setPointLight(false);

// Draw parabola
		dt.setDrawColor(Color.CRIMSON);
		int numPoints = 1000;
	    Point3D[] points = new Point3D[numPoints+1];
	    double incr = Math.sqrt(dt.getYMinMax())/numPoints;
	    double x = 0.0;
	    double y = x*x;
	    points[0] = new Point3D(x, y, 0.0);
	    for (int i=1; i<numPoints; i=i+2) {
	    	x -= 2*incr;
	    	y = parabolaFunction(x);
	    	points[i]	= new Point3D( x, y, 0.0);
	    	points[i+1]	= new Point3D(-x, y, 0.0);
	    }
	    dt.drawPoint3DSet(points);

	 // Draw hyperbola
		dt.setDrawColor(Color.BLUE);
		numPoints = 500;
	    points = new Point3D[4*numPoints+2];
	    x = dt.getXMinMax();
	    double xEnd = inverseHyperbolaFuncton(0.0);
	    points[4*numPoints]   = new Point3D( xEnd, 0.0, 0.0);
	    points[4*numPoints+1] = new Point3D(-xEnd, 0.0, 0.0);
	    incr = (x - xEnd) / numPoints;
	    for (int i = 0; i < 4*numPoints; i = i+4) {
	    	y = hyperbolaFunction(x);
	    	points[i]	= new Point3D( x,  y, 0.0);
	    	points[i+1]	= new Point3D(-x,  y, 0.0);
	    	points[i+2]	= new Point3D( x, -y, 0.0);
	    	points[i+3]	= new Point3D(-x, -y, 0.0);
	    	x -= incr;
	    }
	    dt.drawPoint3DSet(points);
	    
	}

	public double parabolaFunction(double x) {
	// y = a*x*x					Parabola centered at origin
		double a = 1.0;
		return a*x*x;
	}

	public double inverseParabolaFunction(double y) {
		double a = 1.0;
		return Math.sqrt(y/a);
	}

	public double hyperbolaFunction(double x) {
	// y = +-(b/a)*sqrt(x*x - a*a)	Hyperbola centered at origin
		double a = 1.0;				// cannot be zero
		double b = 1.0;
		return b*Math.sqrt(x*x - a*a)/a;
	}

	public double inverseHyperbolaFuncton(double y) {
		double a = 1.0;				// cannot be zero
		double b = 1.0;
		return Math.sqrt((1.0 + y*y/b*b)/(a*a));
	}

	public static void main(String[] args) {
		launch(args);
	}
}