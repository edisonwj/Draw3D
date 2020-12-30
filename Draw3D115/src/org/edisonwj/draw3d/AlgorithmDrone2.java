package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
* * Draws a drone moving along a Bezier curve.
*/
public class AlgorithmDrone2 implements Algorithm {

	private int iterations = 200;
	private long delay = 50L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = true;
	private final boolean drone = true;
	private int id;
	private double[] info;

	private long time;
	private long timeOld;
	private Point3D location;
	private Point3D locationOld;

	private Point3D[] bezierCurve;
	private Point3D[] p = {
			new Point3D(0, 2, 0),
			new Point3D(0, 0, 5),
			new Point3D(2, 4, 2),
			new Point3D(-4, 6, 4),
			new Point3D(0, 9, 5),
			new Point3D(4, 2, -6)};

	//	new Point3D(2, 0, 0),
	//	new Point3D(0, 0, 5),
	//	new Point3D(4, 2, 7),
	//	new Point3D(8,-2, 4),
	//	new Point3D(5, 0, 0) };

	public AlgorithmDrone2() {
		System.out.println("Loaded:  " + this.getClass().getName());
		bezierCurve = new Point3D[iterations+1];
		bezierCurve[0] = p[0];
	}

	public Object processAlgorithm(int n) {
		setBezierPoints(n);
		Drone3D d = new Drone3D(bezierCurve[n], 48, 8, 0, 0, 0,
				new PhongMaterial(Color.RED),
				new PhongMaterial(Color.BLACK));
		d.setId(this.id);
	    return d;

	}

	private void setBezierPoints(int n) {
		double t = n * 1.0/iterations;
		Point3D[] tmp = p.clone();
	    int i = tmp.length - 1;
	    while (i > 0) {
	        for (int k = 0; k < i; k++) {
	//			tmp[k] = tmp[k] + t * (tmp[k+1] - tmp[k])
	            tmp[k] = tmp[k].add(tmp[k+1].subtract(tmp[k]).multiply(t));
	        }
	        i--;
	    }
	    bezierCurve[n + 1] = tmp[0];
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
		time = System.currentTimeMillis();
		location = new Point3D(info[1], info[2], info[3]);
		if (locationOld != null) {
			double distance = location.distance(locationOld);
			double elapsed = time - timeOld;
			double speed = distance/elapsed;
			System.out.printf(
					"time: %14d, drone: %2d, for drone: %2d, " +
					"location x= %6.4f, y= %6.4f, z= %6.4f, " +
					"distance= %8.4f, elapsed= %4.2f, speed= %6.4f\n",
					time, id, (int)info[0],
					location.getX(), location.getY(), location.getZ(),
					distance, elapsed, speed );
		}
		locationOld = location;
		timeOld = time;
	}
}