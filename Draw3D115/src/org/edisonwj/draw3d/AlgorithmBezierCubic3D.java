package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
* * Animates the computation of a Bezier curve in 3D space.
**/
public class AlgorithmBezierCubic3D implements Algorithm {

	private int iterations = 200;
	private long delay = 50L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = true;
	private final boolean drone = false;
	private int id;
	private double[] info;
	private Line3D[] ln;
	private Point3D[] bezierCurve;
    private Point3D[] p = {
		new Point3D(0, 2, 0),
		new Point3D(0, 0, 3),
		new Point3D(0, 4, 3),
		new Point3D(0, 7, 0) };
    private PhongMaterial x = new PhongMaterial(Color.GREY);
    private PhongMaterial r = new PhongMaterial(Color.RED);
    private PhongMaterial g = new PhongMaterial(Color.GREEN);
    private PhongMaterial b = new PhongMaterial(Color.BLUE);

	public AlgorithmBezierCubic3D() {
		System.out.println("Loaded:  " + this.getClass().getName());
		bezierCurve = new Point3D[iterations + 1];
		bezierCurve[0] = p[0];
	}

	public Object processAlgorithm(int n) {
		ln = new Line3D[6 + n + 1];
		ln[0] = new Line3D(p[0], p[1], x);
		ln[1] = new Line3D(p[1], p[2], x);
		ln[2] = new Line3D(p[2], p[3], x);
		setBezierPoints(n);
//	    makeBezierCurve(n);

	    return ln;
	}

	private void setBezierPoints(int n) {
		double t = n * 1.0/iterations;
		Point3D[] tmp = p.clone();
	    int i = tmp.length - 1;
	    while (i > 0) {
	        for (int k = 0; k < i; k++) {
//				tmp[k] = tmp[k] + t * (tmp[k+1] - tmp[k])
	            tmp[k] = tmp[k].add(tmp[k+1].subtract(tmp[k]).multiply(t));
	            if ((i == tmp.length - 1) && (k == i - 1)) {
	            	ln[3] = new Line3D(tmp[0], tmp[1], g);
	            	ln[4] = new Line3D(tmp[1], tmp[2], g);
		        }
	            else if ((i == tmp.length - 2) && (k == i - 1)) {
	            	ln[5] = new Line3D(tmp[0], tmp[1], b);
	            }
	        }
	        i--;
	    }
	    bezierCurve[n + 1] = tmp[0];
		for (int j = 0; j < n + 1; j++) {
			ln[6 + j] = new Line3D(bezierCurve[j], bezierCurve[j+1], r);
		}
	}

	private void makeBezierCurve(int n) {
		System.out.println("makeBezierCurve n= " + n);
		for (int i = 0; i < (n + 1); i++) {
			ln[6 + i] = new Line3D(bezierCurve[i], bezierCurve[i+1], r);
			System.out.println("makeBezierCurve i= " + i + "ln[" + (6+i) + "]= " + ln[6+i]);
		}
		System.out.println("return from makeBezierCurve");
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