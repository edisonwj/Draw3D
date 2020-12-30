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
public class Nullity extends Application {
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
		
		double [][] A = {
				{2, 1, 3, 0, 3},
				{1, 0, 1, 1, 2},
				{3, 2, 5,-1, 4}
				};
		print("A", A);
		
		double[][] X = {
				{ 1, -2, 1, 0, 0},
				{ 1, -1, 0, 1, 0},
				{-1, -2, 0, 0, 1},
				{-1, -1, 1, 0, 0},
				{-1,  2, 0, 1, 0},
				{-2,  1, 0, 0, 1}
				};
		for (int i = 0; i < X.length; i++) {
			String label = "X[" + i + "]"; 
			print(label, X[i]);
			double[] b = matrixVectorMult(A, X[i]);
			print("b", b);
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
//		System.out.println("nr=" + nr + ", nc=" + nc);
		double[] b = new double[nr];
		for (int i = 0; i < nr; i++) {
			b[i] = 0;
			for (int j = 0; j < nc; j++) {
				b[i] = b[i] + A[i][j]*x[j];
//				System.out.println("b[" + i + "]=" + b[i] + ", A[" + i + "][" + j + "]=" + A[i][j] + ", x[" + j + "]=" + x[j]);
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