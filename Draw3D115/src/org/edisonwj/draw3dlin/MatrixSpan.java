package org.edisonwj.draw3dlin;

import org.edisonwj.draw3d.Draw3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.14 February 2018
*
*/
public class MatrixSpan extends Application {
	private Draw3D dt;

	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("CheckIndp");
		primaryStage.show();
	}

	private void buildData() {
		dt.setAmbientLight(true);
		dt.setPointLight(false);
		
		// Three vectors:
		double[] u  = {1, 4, 6};
		double[] v  = {3, 2, 5};
		double[] w  = {2, 7, 4};
		
		dt.setDrawColor(Color.BLUE);
		dt.drawVector(u);
		dt.drawVector(v);
		dt.drawVector(w);
		dt.setDrawColor(Color.LIGHTGREEN);
				
		// Range of alpha to explore:
		double alphaLow=-1, alphaHigh=1, alphaStep=0.5;
		// Range of beta:
		double betaLow=-1, betaHigh=1, betaStep=0.5;
		// Range of gamma:
		double gammaLow=-1, gammaHigh=1, gammaStep=0.5;
		
		for (double alpha=alphaLow; alpha<=alphaHigh; alpha+=alphaStep) {
		    for (double beta=betaLow; beta<=betaHigh; beta+=betaStep) {
			    for (double gamma=gammaLow; gamma<=gammaHigh; gamma+=gammaStep) {
			    	double[] t = linComb (alpha, u, beta, v, gamma, w);
			    	dt.drawLine (0,0,0, t[0], t[1], t[2]);
			    }
		    }
		}
		
		dt.incrSequence();
		
		double[] ut = {1, 3, 2};
		double[] vt = {4, 2, 7};
		double[] wt = {6, 5, 4};
		
		dt.setDrawColor(Color.BLUE);
		dt.drawVector(ut);
		dt.drawVector(vt);
		dt.drawVector(wt);
		dt.setDrawColor(Color.LIGHTGREEN);
					
		for (double alpha=alphaLow; alpha<=alphaHigh; alpha+=alphaStep) {
		    for (double beta=betaLow; beta<=betaHigh; beta+=betaStep) {
			    for (double gamma=gammaLow; gamma<=gammaHigh; gamma+=gammaStep) {
			    	double[] t = linComb (alpha, ut, beta, vt, gamma, wt);
			    	dt.drawLine (0,0,0, t[0], t[1], t[2]);
			    }
		    }
		}
	}
	
	    static double[] linComb (double alpha, double[] u,
    						 	 double beta,  double[] v,
    						 	 double gamma, double[] w)
    {
		double[][] A = {u,v,w};
		print("A", A);
		double[] x = {alpha, beta, gamma};
		print("x", x);
		double[] t = matrixVectorMult (A, x);
		return t;
    }

    static double[] linComb (double alpha, double[] u, double beta, double[] v)
    {
		double[][] A = {u,v};
		print("A", A);
		double[] x = {alpha, beta};
		print("x", x);
		double[] w = matrixVectorMult (A, x);
		return w;
    }

    static double[] matrixVectorMult (double[][] A, double[] x)
    {
		int nr = A.length;	  // # of rows
		int nc = A[0].length; // # of columns, i.e row size
		System.out.println("nr=" + nr + ", nc=" + nc);
		double[] b = new double[nr];
		for (int i = 0; i < nr; i++) {
			b[i] = 0;
			for (int j = 0; j < nc; j++) {
				b[i] = b[i] + A[i][j]*x[j];
				System.out.println("b[" + i + "]=" + b[i] + ", A[" + i + "][" + j + "]=" + A[i][j] + ", x[" + j + "]=" + x[j]);
			}
		}
		return b;
    }
    
	public static double[][] transpose( double[][] A) {
		int nrA = A.length;		// # of rows
		int ncA = A[0].length;	// # of columns, i.e row size
		double[][] AT = new double[ncA][nrA];
		for (int i = 0; i < nrA; i++) {
			for (int j = 0; j < ncA; j++) {
				AT[j][i] = A[i][j];
			}
		}
		return AT;
	}
    
    static double[][] matrixMult (double[][] A, double[][] B)
    {
		int nrA = A.length;			// # of rows
		int ncA = A[0].length;		// # of columns, i.e row size
		int nrB = B.length;			// # of rows
		int ncB = B[0].length;		// # of columns, i.e row size
		double[][] C = new double[nrA][ncB];
		for (int i = 0; i < nrA; i++) {
			for (int j = 0; j < nrA; j++) {
				C[i][j] = 0;
				for (int k = 0; k < ncA; k++) {
					C[i][j] = C[i][j] + A[i][k]*B[k][j];
				}
			}
		}
	return C;
    }
    
    static void print (String label, double[] x) {
		System.out.print (label + " Vector:");
		for (int i=0; i<x.length; i++) {
		    System.out.printf (" %6.3f", x[i]);
		}
		System.out.println ();
    }
    
    static void print (String label, double[][] A) {
		System.out.println (label + " Matrix (" + A.length + "x" + A[0].length + "):");
		for (int i=0; i<A.length; i++) {
		    for (int j=0; j<A[0].length; j++) {
				System.out.printf (" %6.3f", A[i][j]);
			    }
		    System.out.println ();
		}
    }

	public static void main(String[] args) {
		launch(args);
	}
}