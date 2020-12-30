package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Draw3D;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
* Example use of setXYaxes only.
*
* @author William Edison
* @version 1.12 June 2017
*
*/
public class DrawParabolaHyperbolaLines extends Application {
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
		dt.setXYZRange(10, 10, 10);
		dt.setXYaxesOnly(true);
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setDrawColor(Color.BLUE);

		int numPoints = 100;
		double a = 0.5;			// width parameter of parabola
		double h = 0.0; 		// x coor of vertex
		double k = 2.0;			// y coor of vertex
		drawParabola(numPoints, a, h, k);

	    numPoints = 100;
	    double ah = 1.0;
	    double bh = 0.8;
	    drawHyperbola(numPoints, ah, bh);
	}

	private void drawParabola(int numPoints, double a, double h, double k) {
		// Draw parabola
		// y = a*(x - h)*(x - h) + k
	    Point3D[] points = new Point3D[2*numPoints+1];
	    double xMax = inverseParabolaFunction(a, h, k, dt.getYMinMax())-h;
	    double incr = xMax/numPoints;
	    double xp = h;
	    double xm = h;
	    double yp = parabolaFunction(a, h, k, xm);
	    double ym = k;
	    points[numPoints] = new Point3D(xp, yp, 0.0);
	    for (int i=1; i<=numPoints; i++) {
	    	xp = h+i*incr;
	    	xm = h-i*incr;
	    	yp = parabolaFunction(a, h, k, xp);
	    	ym = parabolaFunction(a, h, k, xm);
	    	points[numPoints-i]	= new Point3D(xp, yp, 0.0);
	    	points[numPoints+i] = new Point3D(xm, ym, 0.0);
	    }
	    dt.drawPolyLine3D(points);
	}

	private void drawHyperbola(int numPoints, double a, double b) {
	 // Draw hyperbola
	    Point3D[] pointsM = new Point3D[2*numPoints+1];
	    Point3D[] pointsP = new Point3D[2*numPoints+1];
	    double xMax = inverseHyperbolaFuncton(a, b, dt.getYMinMax());
	    double incr = xMax/numPoints;
	    double x = xMax;
	    double y = 0.0;
	    pointsM[numPoints] = new Point3D(-inverseHyperbolaFuncton(a, b, 0.0), 0.0, 0.0);
	    pointsP[numPoints] = new Point3D( inverseHyperbolaFuncton(a, b, 0.0), 0.0, 0.0);
	    for (int i = 0; i < numPoints; i++) {
	    	y = hyperbolaFunction(a, b, x);
	    	pointsM[i]				= new Point3D(-x,  y, 0.0);
	    	pointsM[2*numPoints-i]	= new Point3D(-x, -y, 0.0);
	    	pointsP[i]				= new Point3D( x,  y, 0.0);
	    	pointsP[2*numPoints-i]	= new Point3D( x, -y, 0.0);
	    	x -= incr;
	    }
	    dt.drawPolyLine3D(pointsM);
	    dt.drawPolyLine3D(pointsP);
	}

	private double parabolaFunction(double a, double h, double k, double x) {
		// y = a*(x - h)*(x - h) + k		Axis of symmetry parallel to the y axis
		return a*(x - h)*(x - h) + k;
	}

	private double inverseParabolaFunction(double a, double h, double k, double y) {
		// x =  sqrt((y - k) / a) + h;
		return Math.sqrt((y - k) / a) + h;
	}

	private double hyperbolaFunction(double a, double b, double x) {
	// y = +-(b/a)*sqrt(x*x - a*a)	Hyperbola centered at origin
		return b*Math.sqrt(x*x - a*a)/a;
	}

	private double inverseHyperbolaFuncton(double a, double b, double y) {
		return Math.sqrt((1.0 + y*y/b*b)/(a*a));
	}

	public static void main(String[] args) {
		launch(args);
	}
}