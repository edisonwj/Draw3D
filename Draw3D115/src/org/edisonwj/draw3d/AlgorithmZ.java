package org.edisonwj.draw3d;

import javafx.scene.paint.Color;

import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
* Provides an example implementation of the Algorithm Interface.
* This instance animates progressive applications of a Z axis
* transform matrix that rotates an X-axis vector around the Z axis.
* At each iteration the angle of rotation determining sine/cosine
* in the matrix is varied from 0 to 2 PI.
*
* The animation uses:
* -iterations = 32
* -delay between iterations = 50 milliseconds
* -constant color vMaterial = PhongMaterial(Color.GOLDENROD)
*
*/
public class AlgorithmZ implements Algorithm {

	private int iterations = 32;
	private long delay = 50L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = false;
	private final boolean drone = false;
	private int id;
	private double[] info;

	public AlgorithmZ() {
		System.out.println("Loaded:  " + this.getClass().getName());
		vMaterial = new PhongMaterial();
		vMaterial.setDiffuseColor(Color.DARKGOLDENROD);
	    vMaterial.setSpecularColor(Color.GOLDENROD);
	}

	public Object processAlgorithm(int n) {

        double a = n * 2.0 * Math.PI/iterations;

    	double[]    v = {5.0, 0.0, 0.0};

    	double[][] mz = {{ Math.cos(a), Math.sin(a), 0.0},
    					 {-Math.sin(a), Math.cos(a), 0.0},
    					 {    0.0,        0.0,       1.0}};

    	double[]    t = matrixMultiply(v, mz);

    	Vector3D   vt = new Vector3D(t[0], t[1], t[2], vMaterial);

    	return vt;
	}

    private double[] matrixMultiply(double[] v, double[][] m) {
    	double[] t = new double[3];
    	t[0] = v[0]*m[0][0] + v[1]*m[1][0] + v[2]*m[2][0];
    	t[1] = v[0]*m[0][1] + v[1]*m[1][1] + v[2]*m[2][1];
    	t[2] = v[0]*m[0][2] + v[1]*m[1][2] + v[2]*m[2][2];
    	return t;
    }

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public PhongMaterial getMaterial(int n) {
		return vMaterial;
	}

	public void setMaterial(PhongMaterial m) {
		this.vMaterial = m;
	}

	public Object getParms() {
		return parms;
	}

	public void setParms(Object parms) {
		this.parms = parms;
	}

	public boolean getClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public boolean isDrone() {
		return drone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double[] getInfo() {
		return info;
	}

	public void setInfo(double[] info) {
		this.info = info.clone();
	}
}