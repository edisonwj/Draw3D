package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Sphere3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.geometry.Point3D;
/**
*
* @author William Edison
* @version 1.14 February 2018
*  
*/
public class TestPlane extends Application {
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestPlanes");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		dt.setShowBoundaryCube(true);		
		dt.setSequencingOn();
		
//		double[] u1 = { 2, -1,  1};		//OK
//		double[] u2 = { 0,  1,  1};
//		double[] u3 = { 6,  1,  8};
		
		double[] u1 = { 1, 1, 2}; 	// Collinear points
		double[] u2 = { 2, 1, 3};
		double[] u3 = { 3, 1, 4};
		
//		double[] u1 = { 1,  1, 0};		// OK
//		double[] u2 = {-1,  1, 0};
//		double[] u3 = { 0, -1, 0};
		
		dt.setVectorRadius(1);
		dt.setDrawColor(Color.BLUE);
//		dt.drawPoint(u1[0], u1[1], u1[2]);
//		dt.drawPoint(u2[0], u2[1], u2[2]);
//		dt.drawPoint(u3[0], u3[1], u3[2]);
		dt.drawVector(u1[0], u1[1], u1[2]);
		dt.drawVector(u2[0], u2[1], u2[2]);
		dt.drawVector(u3[0], u3[1], u3[2]);
		
		dt.setLineRadius(1);
		dt.setDrawColor(Color.GREEN);
		dt.drawLine(u1[0], u1[1], u1[2],
					u2[0], u2[1], u2[2]);
		dt.drawLine(u2[0], u2[1], u2[2],
					u3[0], u3[1], u3[2]);
		dt.drawLine(u3[0], u3[1], u3[2],
					u1[0], u1[1], u1[2]);

		dt.setDrawColor(Color.ANTIQUEWHITE);
		dt.drawPlane(u1[0], u1[1], u1[2],
					 u2[0], u2[1], u2[2],
					 u3[0], u3[1], u3[2]);
		
/*		
		dt.setVectorRadius(.25);
		dt.setDrawColor (Color.LIGHTGRAY);
		double alphaLow=-1, alphaHigh=1, alphaStep=0.1;
		double betaLow=-1, betaHigh=1, betaStep=0.1;
		double gammaLow=-1, gammaHigh=1, gammaStep=0.1;
		
		for (double alpha=alphaLow; alpha<=alphaHigh; alpha+=alphaStep) {
			  for (double beta=betaLow; beta<=betaHigh; beta+=betaStep) {
				for (double gamma=gammaLow; gamma<=gammaHigh; gamma+=gammaStep) {
				    double[] temp1 = scalarMult (alpha, u1);
				    double[] temp2 = scalarMult (beta,  u2);
				    double[] temp3 = scalarMult (gamma, u3);
				    double[] temp4 = add (temp1, temp2);
				    double[] z = add (temp4, temp3);
				    dt.drawVector (z);
				}
			  }
		}
	*/
	}
	

    public double[] add (double[] u, double[] v)
    {
		double[] w = new double [u.length];
		for (int i=0; i<w.length; i++) {
		    w[i] = u[i] + v[i];
		}
		return w;
    }

	public double[] scalarMult (double a, double[] u)
	{
		double[] w = new double [u.length];
		for (int i=0; i<w.length; i++) {
			w[i] = a * u[i];
		}
		return w;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}