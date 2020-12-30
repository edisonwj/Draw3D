package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
* Animates the computation of an order-N Bezier curve in 3D space.
*/
public class AlgorithmBezierOrderN3D implements Algorithm {

	private int iterations = 200;
	private long delay = 50L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = true;
	private final boolean drone = false;
	private int id;
	private double[] info;
    private PhongMaterial red = new PhongMaterial(Color.RED);
    private PhongMaterial grey = new PhongMaterial(Color.GREY);
    private int di;
    private Rand mr;
    private Object[] drawSet;
	private Point3D[] bezierCurve;
    private Point3D[] p = {
    		new Point3D(0, 2, 0),
    		new Point3D(0, 0, 5),
    		new Point3D(2, 4, 2),
    		new Point3D(-4, 6, 4),
    		new Point3D(0, 9, 5),
    		new Point3D(2, 6, 0)};

//		new Point3D(2, 0, 0),
//		new Point3D(0, 0, 5),
//		new Point3D(4, 2, 7),
//		new Point3D(8,-2, 4),
//		new Point3D(5, 0, 0) };

	public AlgorithmBezierOrderN3D() {
		System.out.println("Loaded:  " + this.getClass().getName());
		mr = new Rand();
		bezierCurve = new Point3D[iterations+1];
		bezierCurve[0] = p[0];
	}

	public Object[] processAlgorithm(int n) {
		int nlines = p.length * (p.length-1) / 2;
		drawSet = new Object[3*nlines + n + 1];
		di = 0;

		for (int i = 0; i < p.length-1; i++) {
			drawSet[di++] = new Line3D(p[i], p[i+1], grey);
			drawSet[di++] = new Sphere3D(p[i], .05, grey);
			drawSet[di++] = new Sphere3D(p[i+1], .05, grey);
		}

		setBezierPoints(n);

	    return drawSet;

	}

	private void setBezierPoints(int n) {
		double t = n * 1.0/iterations;
		Point3D[] tmp = p.clone();
	    int i = tmp.length - 1;
	    while (i > 0) {
	    	PhongMaterial m = new PhongMaterial(setColor());
	        for (int k = 0; k < i; k++) {
//				tmp[k] = tmp[k] + t * (tmp[k+1] - tmp[k])
	            tmp[k] = tmp[k].add(tmp[k+1].subtract(tmp[k]).multiply(t));
	            if (k > 0) {
	            	drawSet[di++] = new Line3D(tmp[k-1], tmp[k], m);
	    			drawSet[di++] = new Sphere3D(tmp[k-1], .05, m);
	    			drawSet[di++] = new Sphere3D(tmp[k], .05, m);
		            }
	        }
	        i--;
	    }
	    bezierCurve[n + 1] = tmp[0];
		for (i = 0; i < n + 1; i++) {
			drawSet[di++] = new Line3D(bezierCurve[i], bezierCurve[i+1], red);
		}
	}

	private Color setColor() {
		int rRGB = mr.uniform(0,  255);
		int gRGB = mr.uniform(0,  255);
		int bRGB = mr.uniform(0,  255);
		return Color.rgb(rRGB, gRGB, bRGB);
	}

    public int factorial(int n) {
    	int result = 1;
        for (int i = 1; i <= n; i++) {
                result = result * i;
        }
        return result;
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