package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.*;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
*
* @author William Edison
* @version 1.14 October 2017
*
* Draws a series of boxes around the x-axis
* as the angle of rotation is varied from 0 to 2 PI.
* Uses x-axis transformation matrix.
*
*/
public class Draw3DExample2Algorithm implements org.edisonwj.draw3d.Algorithm {

    private int iterations = 32;
    private long delay = 50L;
    private PhongMaterial vMaterial;
    private Object parms;
    private boolean clear = false;
    private final boolean drone = false;
    private int id;
    private double[] info;

    public Draw3DExample2Algorithm ()
    {
		System.out.println("Loaded: " + this.getClass().getName());
		vMaterial = new PhongMaterial();
		vMaterial.setDiffuseColor(Color.SALMON);
		vMaterial.setSpecularColor(Color.LIGHTSALMON);
    }

    public Object processAlgorithm(int n)
    {
		double depth = 2.0;
		double height = 3.0;
		double width = 2.0;

        double a = n * 2.0 * Math.PI / iterations;
    	double[] v = {5.0, 5.0, 5.0};

    	double[][] mx = {{    1.0,        0.0,       0.0      },
				 		 {    0.0,    Math.cos(a), Math.sin(a)},
				 		 {    0.0,   -Math.sin(a), Math.cos(a)}};

    	double[] t = matrixMultiply(v, mx);

    	Box3D vt = new Box3D(new Point3D(t[0], t[1], t[2]), depth, height, width, vMaterial);

    	return vt;

    }

    private double[] matrixMultiply(double[] v, double[][] m)
    {
    	double[] t = new double[3];
    	t[0] = v[0]*m[0][0] + v[1]*m[1][0] + v[2]*m[2][0];
    	t[1] = v[0]*m[0][1] + v[1]*m[1][1] + v[2]*m[2][1];
    	t[2] = v[0]*m[0][2] + v[1]*m[1][2] + v[2]*m[2][2];
    	return t;
    }

    public int getIterations()
    {
    	return iterations;
    }

    public void setIterations(int iterations)
    {
    	this.iterations=iterations;
    }

    public long getDelay()
    {
    	return delay;
    }

    public void setDelay(long delay)
    {
    	this.delay=delay;
    }

    public PhongMaterial getMaterial(int iteration)
    {
    	return vMaterial;
    }

    public void setMaterial(PhongMaterial vMaterial)
    {
    	this.vMaterial = vMaterial;
    }

    public Object getParms()
    {
    	return parms;
    }

    public void setParms(Object parms)
    {
    	this.parms = parms;
    }

    public boolean getClear()
    {
    	return clear;
    }

    public void setClear(boolean clear)
    {
    	this.clear=clear;
    }

    public boolean isDrone()
    {
    	return drone;
    }

    public int getId()
    {
    	return id;
    }

    public void setId(int id)
    {
    	this.id = id;
    }

    public double[] getInfo()
    {
    	return info;
    }

    public void setInfo(double[] info)
    {
    	this.info = info.clone();
    }
}