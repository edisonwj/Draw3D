package org.edisonwj.draw3d;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.11 June 2017
*
* Draws a drone moving on a vertical path.
*
*/
public class AlgorithmDrone3 implements Algorithm {

	private int iterations = 200;
	private long delay = 50L;
    private PhongMaterial vMaterial;
	private Object parms;
	private boolean clear = false;
	private final boolean drone = true;
	private int id;
	private double[] info;

	private long time;
	private long timeOld;
	private Point3D location;
	private Point3D locationOld;

	public AlgorithmDrone3() {
		System.out.println("Loaded:  " + this.getClass().getName());
	}

	public Object processAlgorithm(int n) {

		double x = 5.0;
		double y = -5.0;
		double z = -10.0 + n*20.0/iterations;
		Drone3D d = new Drone3D(x, y, z, 48, 8, 0, 0, 0,
				new PhongMaterial(Color.BLUE),
				new PhongMaterial(Color.BLACK));
		d.setId(this.id);
    	return d;

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