package org.edisonwj.draw3d;

import java.util.StringTokenizer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
/**
*
* @author William Edison
* @version 1.00 July 2015
* 
*/
public class Drone3D implements Draw3DDefaults {
	private int id;
	private Point3D p;
	private double radius;
	private double height;
	private double xr;		// x rotation
	private double yr;		// y rotation
	private double zr;		// z rotation
	private PhongMaterial m1;
	private PhongMaterial m2;
	
	public Drone3D(int id) {
		this(0, 0, 0, 0, 0, 0, 0, 0);
		this.id = id;
	}
	
	public Drone3D(Point3D p, double radius, double height) {
		this.p = p;
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m1 = null;
		this.m2 = null;
	}
	
	public Drone3D(Point3D p, double radius, double height,
					   PhongMaterial m1, PhongMaterial m2) {
		this(p, radius, height);
		this.m1 = m1;
		this.m2 = m2;
	}
	
	public Drone3D(Point3D p, double radius, double height,
					   double xr, double yr, double zr) {
		this(p, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m1 = null;
		this.m2 = null;
	}
	
	public Drone3D(Point3D p, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m1, PhongMaterial m2) {
		this(p, radius, height, xr, yr, zr);
		this.m1 = m1;
		this.m2 = m2;
	}
	
	public Drone3D(double x, double y, double z, double radius, double height) {
		this.p = new Point3D(x, y, z);
		this.radius = radius;
		this.height = height;
		this.xr = 0.0;
		this.yr = 0.0;
		this.zr = 0.0;
		this.m1 = null;
		this.m2 = null;
	}
	
	public Drone3D(double x, double y, double z, double radius, double height,
					   PhongMaterial m1, PhongMaterial m2) {
		this(x, y, z, radius, height);
		this.m1 = m1;
		this.m2 = m2;
	}
	
	public Drone3D(double x, double y, double z, double radius, double height,
					   double xr, double yr, double zr) {
		this(x, y, z, radius, height);
		this.xr = xr;
		this.yr = yr;
		this.zr = zr;
		this.m1 = null;
		this.m2 = null;
	}
	
	public Drone3D(double x, double y, double z, double radius, double height,
			   double xr, double yr, double zr,
			   PhongMaterial m1, PhongMaterial m2) {
		this(x, y, z, radius, height, xr, yr, zr);
		this.m1 = m1;
		this.m2 = m2;
	}
	
	public Drone3D(String lineString) {
		StringTokenizer st = new StringTokenizer(lineString, ",;: ");
		String dataType = st.nextToken().trim();
		if (dataType.equals("Drone")) {
			double x = Double.valueOf(st.nextToken()).doubleValue();
			double y = Double.valueOf(st.nextToken()).doubleValue();
			double z = Double.valueOf(st.nextToken()).doubleValue();
			this.p = new Point3D(x, y, z);
			this.radius = Double.valueOf(st.nextToken()).doubleValue();
			this.height = Double.valueOf(st.nextToken()).doubleValue();
			this.xr = 0.0;
			this.yr = 0.0; 
			this.zr = 0.0;
			if (st.hasMoreTokens()) {
				this.xr = Double.valueOf(st.nextToken()).doubleValue();
				this.yr = Double.valueOf(st.nextToken()).doubleValue();
				this.zr = Double.valueOf(st.nextToken()).doubleValue();
			}
			if (st.hasMoreTokens()) {
				this.m1 = new PhongMaterial();
				this.m1.setDiffuseColor(Color.web(st.nextToken()));
				if (st.hasMoreTokens()) {
					String spcolor = st.nextToken();
					if (!spcolor.equals("null")) {
						this.m1.setSpecularColor(Color.web(st.nextToken()));
					}
				}
			}
			else {
				this.m1 = null;
			}
			if (st.hasMoreTokens()) {
				this.m2 = new PhongMaterial();
				this.m2.setDiffuseColor(Color.web(st.nextToken()));
				if (st.hasMoreTokens()) {
					String spcolor = st.nextToken();
					if (!spcolor.equals("null")) {
						this.m2.setSpecularColor(Color.web(st.nextToken()));
					}
				}
			}
			else {
				this.m2 = null;
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setP(Point3D p) {
		this.p = p;
	}
	
	public Point3D getP() {
		return p;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getHeight() {
		return height;
	}
		
	public double getXr() {
		return xr;
	}
	
	public void setXr(double xr) {
		this.xr = xr;
	}
	
	public double getYr() {
		return yr;
	}
	
	public void setYr(double yr) {
		this.yr = yr;
	}
	
	public double getZr() {
		return zr;
	}
	
	public void setZr(double zr) {
		this.zr = zr;
	}
	
	public PhongMaterial getMaterial1() {
		return m1;
	}
	
	public void setMaterial1(PhongMaterial m1) {
		this.m1 = m1;
	}
	
	public PhongMaterial getMaterial2() {
		return m2;
	}
	
	public void setMaterial2(PhongMaterial m2) {
		this.m2 = m2;
	}
	
	public String outString() {
		String ms = "";
		if (m1 != null) {
			if (m1.getDiffuseColor() != null) {
				ms = ms.concat(m1.getDiffuseColor().toString());
			}
			if (m1.getSpecularColor() != null) {
				ms = ms.concat(", " + m1.getSpecularColor().toString());
			}
			else {
				ms = ms.concat(", null");
			}
		}
		else {
			ms = ms.concat("null");
		}
		if (m2 != null) {
			if (m2.getDiffuseColor() != null) {
				ms = ms.concat(", " + m2.getDiffuseColor().toString());
			}
			if (m2.getSpecularColor() != null) {
				ms = ms.concat(", " + m2.getSpecularColor().toString());
			}
			else {
				ms = ms.concat(", null");
			}
		}
		else {
			ms = ms.concat(", null");
		}
		return "Drone: " + p.getX() + ", " + p.getY() + ", " + p.getZ() +
			   ", " + radius + ", " + height +
			   ", " + xr + ", " + yr + ", " + zr +
			   ", " + ms;
	}
	
	public String toString() {
		String ms = "m= ";
		if (m1 != null) {
			if (m1.getDiffuseColor() != null) {
				ms = ms.concat(m1.getDiffuseColor().toString());
			}
			if (m1.getSpecularColor() != null) {
				ms = ms.concat(", " + m1.getSpecularColor().toString());
			}
			else {
				ms = ms.concat(", null");
			}
		}
		else {
			ms = ms.concat("null");
		}
		if (m2 != null) {
			if (m2.getDiffuseColor() != null) {
				ms = ms.concat(", " + m2.getDiffuseColor().toString());
			}
			if (m2.getSpecularColor() != null) {
				ms = ms.concat(", " + m2.getSpecularColor().toString());
			}
			else {
				ms = ms.concat(", null");
			}
		}
		else {
			ms = ms.concat(", null");
		}
		return "Drone3D: p= " + p.toString() +
			   ", radius = " + radius + ", height= " + height +
			   ", xr= " + xr + ", yr= " + yr + ", zr= " + zr +
			   ", " + ms;
	}
}