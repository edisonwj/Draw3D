package org.edisonwj.draw3dillustrations;

import org.edisonwj.draw3d.Algorithm;
import org.edisonwj.draw3d.Polygon3D;
import org.edisonwj.draw3d.Text3D;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.text.Font;

/*
 * @author William Edison
 * @version 1.12 June 2017
 *
 * Illustrates Archimedes' method for estimating Pi
 * using inscribed and circumscribed polygons with
 * increasing numbers of sides converging to the
 * circle.
*/
public class AlgorithmArchimedesPi implements Algorithm {

	private int iterations = 10;
	private long delay = 1500L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = true;
	private final boolean drone = false;
	private int id;
	private double[] info;

	private int nCirclePoints = 1024;		// Number of points for circle
	private double ri = 1.0;
    private double sni = ri;
	private double pii = 0;
	private double sno = 2.0*Math.sqrt(3.0)/3.0;
	private double pio = 0;
	private double ro = sno;
	private int n = 6;						// Starting number of polygon sides
	private Point3D[] circle;

	public AlgorithmArchimedesPi() {
		System.out.println("Loaded: " + this.getClass().getName());
		vMaterial = new PhongMaterial(Color.CRIMSON);
		circle = makeCircle(nCirclePoints);
	}

	public Object processAlgorithm(int i) {
		PhongMaterial innerColor = new PhongMaterial(Color.GREEN);
		PhongMaterial outerColor = new PhongMaterial(Color.BLUE);

		if (i == 0) {
			ri = 1.0;
		    sni = ri;
			sno = 2.0*Math.sqrt(3.0)/3.0;
			ro = sno;
			n = 6;
		}
		else if (i > 0) {
			sni = Math.sqrt(2.0-Math.sqrt(4.0-sni*sni));
			sno = (2.0*Math.sqrt(4+sno*sno)-4.0)/sno;
			ro = Math.sqrt(1 + sno*sno/4.0);
		}

		pii = (sni * n)/2.0;
		pio = (sno * n)/2.0;

		Object[] oa = new Object[8];

		oa[0] = circle;

		Polygon3D p1 = makeInnerPolygon(n);
		p1.setDrawEdges(true);
		p1.setEdgeMaterial(innerColor);
		oa[1] = p1;

		Polygon3D p2 = makeOuterPolygon(n);
		p2.setDrawEdges(true);
		p2.setEdgeMaterial(outerColor);
		oa[2] = p2;

		Text3D lab1 = new Text3D(new Point3D(0.6,1.30,0.0), 180, 0, 0, ("nSides= " + n), Color.BLACK);
		lab1.setLabelFont(Font.font("Regular", 10));
		oa[3] = lab1;
		Text3D lab2 = new Text3D(new Point3D(0.6,1.25,0.0), 180, 0, 0, ("inner pi= " + pii), Color.BLACK);
		lab2.setLabelFont(Font.font("Regular", 10));
		oa[4] = lab2;
		Text3D lab3 = new Text3D(new Point3D(0.6,1.20,0.0), 180, 0, 0, ("outer pi= " + pio), Color.BLACK);
		lab3.setLabelFont(Font.font("Regular", 10));
		oa[5] = lab3;
		Text3D lab4 = new Text3D(new Point3D(0.6,1.15,0.0), 180, 0, 0, ("pi= " + Math.PI), Color.BLACK);
		lab4.setLabelFont(Font.font("Regular", 10));
		oa[6] = lab4;
		Text3D lab5 = new Text3D(new Point3D(-1.0,1.20,0.0), 180, 0, 0, "Archimedes' Estimation of PI", Color.BLACK);
		lab5.setLabelFont(Font.font("Regular", 16));
		oa[7] = lab5;

		n = 2*n;

		return oa;
	}

	public Point3D[] makeCircle(int n) {
		Point3D[] points = new Point3D[n];
	    double radius = 1.0;
	    double theta = 0.0;
	    double increment = 2.0*Math.PI/n;
	    for (int i=0; i<n; i++) {
	      points[i] = new Point3D(	radius*Math.sin(theta),
	    		  					radius*Math.cos(theta),
	    		  					0.0);
	      theta = theta + increment;
	    }
	    return points;
	}

    public Polygon3D makeInnerPolygon(int n) {
//    	System.out.println("Make inner polygon with nSides = " + n);
   		Point3D[] v = new Point3D[n];

    	double t = 0.0;
    	double x = 1.0;
    	double y = 0.0;
    	v[0] = new Point3D(x, y, 0.0);
    	for (int i=1; i<n; i++) {
    		t += 2.0*Math.PI/n;
    		x = Math.cos(t);
    		y = Math.sin(t);
    		v[i] = new Point3D(x, y, 0.0);
    	}
    	return new Polygon3D(v, new PhongMaterial(Color.TRANSPARENT));
    }

    public Polygon3D makeOuterPolygon(int n) {
//    	System.out.println("Make outer polygon with nSides = " + n);
    	Point3D[] v = new Point3D[n];

    	double t = 0.0;
		double x = ro;
		double y = 0.0;
		v[0] = new Point3D(x, y, 0.0);
    	for (int i=1; i<n; i++) {
    		t += 2.0*Math.PI/n;
    		x = ro*Math.cos(t);
    		y = ro*Math.sin(t);
    		v[i] = new Point3D(x, y, 0.0);
    	}
    	return new Polygon3D(v, new PhongMaterial(Color.TRANSPARENT));
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