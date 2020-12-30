package org.edisonwj.draw3dtest;

import java.util.ArrayList;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Sphere3D;
import org.edisonwj.draw3d.Text3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
/**
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestAntipodal extends Application implements Draw3DDefaults{
	private Draw3D dt;
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAntipodal");
		primaryStage.show();
	}
	
	private void buildData() {
	    
		dt.setCumulate(true);
		dt.setYaxisUp(true);
		dt.setShowBoundaryCube(false);
		dt.setShowAxes(false);
		dt.setCamera(0.0,  -180.0,  -800.0);
		dt.setOriginView(-100, -100, -100);
		
		Point3D[] p = { new Point3D( 3.0, 1.0, 0),	// p0
						new Point3D( 5.1, 2.3, 0),	// p1
						new Point3D( 5.8, 3.8, 0),	// p2
						new Point3D( 5.4, 5.2, 0),	// p3
						new Point3D( 4.8, 7.1, 0),	// p4
						new Point3D( 3.6, 7.1, 0),	// p5
						new Point3D( 2.1, 5.7, 0),	// p6
						new Point3D( 1.4, 4.2, 0),	// p7
						new Point3D( 1.0, 3.0, 0),	// p8
						new Point3D( 1.0, 1.0, 0)	// p9
						};
/*	
		Point3D[] p = { new Point3D( 3.0, 1.4, 0),	// p0
						new Point3D( 5.1, 2.3, 0),	// p1
						new Point3D( 5.8, 3.8, 0),	// p2
						new Point3D( 5.4, 5.2, 0),	// p3
						new Point3D( 4.8, 6.1, 0),	// p4
						new Point3D( 3.6, 7.1, 0),	// p5
						new Point3D( 2.1, 5.7, 0),	// p6
						new Point3D( 1.4, 4.2, 0),	// p7
						new Point3D( 1.0, 3.0, 0),	// p8
						new Point3D( 1.0, 1.0, 0)	// p9
						};
*/
		
		// Compute all distances
		double maxdist = 0;
		double dist = 0;
		int maxi = 0;
		int maxj = 0;
		System.out.println("\nHull Distances");
		for (int i=0; i<p.length-1; i++) {
			for (int j=i+1; j<p.length; j++) {
				dist = p[i].distance(p[j]);
				if (dist > maxdist) {
					maxdist = dist;
					maxi = i;
					maxj= j;
				}
				System.out.println( "Distance: p[" + i + "]= " + p[i].toString() +
											", p[" + j + "]= " + p[j].toString() +
											", d= " + dist);	
			}
		}
		
	    System.out.println( "Hull Largest i= " + maxi + ", j= " + maxj + 
				"\n  (Xi= " + p[maxi].getX() + ", Yi= " + p[maxi].getY() +
				"),\n  (Xj= " + p[maxj].getX() + ", Yj= " + p[maxj].getY() +
				"),\n  Hull largestDistance= " + maxdist);
	    dt.setDrawColor(Color.GREEN);
	    dt.drawLine3D(new Line3D(p[maxi], p[maxj]));
	    System.out.println("");
		
		dt.setDrawColor(Color.ANTIQUEWHITE);
		dt.drawPolygon(p);
		
		dt.setDrawColor(Color.BLACK);
		dt.setPointSize(1);
		dt.setLineRadius(.5);
		dt.setLabelFontSize(10);
		
		for (int i = 0; i < p.length; i++) {
			dt.drawPoint(p[i].getX(), p[i].getY(), p[i].getZ());
			dt.drawLabel(p[i].getX(), p[i].getY(), p[i].getZ(), ("P" + i));
			dt.drawLine(p[i].getX(), p[i].getY(), p[i].getZ(),
					p[next(p.length, i)].getX(), p[next(p.length, i)].getY(), p[next(p.length, i)].getZ());
		}
		
		
		
		// Find antipodal pairs
		ArrayList<Pair> ap = antipodalPairs(p);
	}
	
	/**
	 * Method for finding antipodal pairs of the hull.
	 * Adopted from algorithm in Preparata and Shamos, p. 180.
	 * See also Pirzadeh, p. 12.
	 */
	private ArrayList<Pair> antipodalPairs(Point3D[] h)
	{
		Line3D edge;
		
		// Assumes input hull points are in
		// array h ordered counterclockwise.

		// Returns ArrayList of antipodal pairs w/ distances

		ArrayList<Pair> al = new ArrayList<>();
		Pair pr;
		int p, p0, pn, q, q0;
		int n = h.length-1;
		int count = 0;
		
		double maxDist = 0.0;
		int maxp = 0;
		int maxq = 0;

		p0 = 0;
		pn = n;
		p = pn;
		q = next(h.length, p);
		dt.incrSequence();
		dt.drawSphere3D(new Sphere3D(h[p],.1,new PhongMaterial(Color.CRIMSON)));
		dt.drawSphere3D(new Sphere3D(h[q],.1,new PhongMaterial(Color.SALMON)));
		dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Start")));
		System.out.println("p= " + p + ", q= " + q + ", count=" + count++);

		// Traverse hull counterclockwise to find point, q0,
		// farthest from pn-p0.

		while ( area2(h, p, next(h.length, p), next(h.length, q)) -
				area2(h, p, next(h.length, p), q) > EPSILON ) {
			q = next(h.length, q);
			dt.incrSequence();
			edge = new Line3D(h[p],h[next(h.length,p)],new PhongMaterial(Color.GREEN));
			edge.setLineRadius(1);
			dt.drawLine3D(edge);
			dt.drawSphere3D(new Sphere3D(h[q],.1,new PhongMaterial(Color.SALMON)));
			dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Move q")));
			System.out.println("p= " + p + ", q= " + q + ", count=" + count++);
		}  		
		q0 = q;

		// Move p counterclockwise from pn to q0
		// and q from q0 to pn, each change creating
		// an anitpodal pair.

		scan: while (q != p0)
		{
			p = next(h.length, p);
			dt.incrSequence();
			edge = new Line3D(h[p],h[next(h.length,p)],new PhongMaterial(Color.GREEN));
			edge.setLineRadius(1);
			dt.drawLine3D(edge);
			dt.drawSphere3D(new Sphere3D(h[p],.1,new PhongMaterial(Color.CRIMSON)));
			pr = new Pair(h[p], h[q]);
			al.add(pr);
			if (pr.getDist() > maxDist) {
				maxp = p;
				maxq = q;
				maxDist = pr.getDist();
			}
			dt.setDrawColor(Color.BLACK);
			dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
						pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
			dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Outer Pair")));
			System.out.println("NEW PAIR outer while: p= " + p + ", q= " + q + ", d= " + pr.getDist() +
					",count = " + count++);
			
			System.out.println("Check for inner while");
			while ( area2(h, p, next(h.length, p), next(h.length, q)) -
					area2(h, p, next(h.length, p), q) > EPSILON )
			{
				q = next(h.length, q);
				dt.incrSequence();
				edge = new Line3D(h[p],h[next(h.length,p)],new PhongMaterial(Color.GREEN));
				edge.setLineRadius(1);
				dt.drawLine3D(edge);
				dt.drawSphere3D(new Sphere3D(h[q],.1,new PhongMaterial(Color.SALMON)));
				dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Inner Move q")));
				System.out.println("Inner while move q count= " + count++);
				if ( !(p == q0 && q == p0) ) {
					pr = new Pair(h[p], h[q]);
					al.add(pr);
					if (pr.getDist() > maxDist) {
						maxp = p;
						maxq = q;
						maxDist = pr.getDist();
					}
					dt.incrSequence();
					edge = new Line3D(h[p],h[next(h.length,p)],new PhongMaterial(Color.GREEN));
					dt.setDrawColor(Color.BLUE);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
					dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Inner Pair")));
					System.out.println("NEW PAIR inner while: p= " + p + ", q= " + q + ", d= " + pr.getDist() + ", count= " + count++);
				}
				else {
					System.out.println("Break from inner while");
					break scan;
				}
			}

			// Handle parallel lines
			System.out.println("Check for parallel lines");
			if ( Math.abs( area2(h, p, next(h.length, p), next(h.length, q)) -
			   	   		   area2(h, p, next(h.length, p), q) ) < EPSILON ) {
				System.out.println("Parallel lines");

				if ( !(p == q0 && q == pn) ) {
					pr = new Pair(h[p], h[next(h.length, q)]);
					al.add(pr);
					if (pr.getDist() > maxDist) {
						maxp = p;
						maxq = q;
						maxDist = pr.getDist();
					}
					dt.incrSequence();
					edge = new Line3D(h[p],h[next(h.length,p)],new PhongMaterial(Color.GREEN));
					edge.setLineRadius(1);
					dt.drawLine3D(edge);
					dt.setDrawColor(Color.RED);
					dt.drawLine(pr.getp1().getX(), pr.getp1().getY(), pr.getp1().getZ(),
								pr.getp2().getX(), pr.getp2().getY(), pr.getp2().getZ());
					dt.drawLabel3D(new Text3D(8, .7*(18-count), 0, 180, 0, 0, ("C"+count+" p"+p+" q"+q+" Parallel Pair")));
					System.out.println("NEW PAIR parallel lines: p= " + p + ", next(q)= " + next(h.length, q) + ", d= " + pr.getDist() + ", count= " + count++);
				}
				else {
					System.out.println("Break from parallel inner while");
					break scan;
				}	
			}
		}
		System.out.println("End of search: maxDist= " + maxDist + ", p= " + maxp + ", q= " + maxq);
		return al;
	}
	
	private double area2(Point3D[] h, int ai, int bi, int ci) {
		Point3D a = h[ai];
		Point3D b = h[bi];
		Point3D c = h[ci];
		double a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
		System.out.println("area2: ai= " + ai + ", bi= " + bi + ", ci= " + ci + ", area2= " + a2);
		return a2;
	}
	
	/**
	 * Return next index cycling back to zero after n-1.
	 */
	private int next(int length, int i)
	{
		return i == (length-1) ? 0 : i + 1;
	}
	
	/**
	 * Inner class defining antipodal pairs.
	 */
	private class Pair
	{
		Point3D p1;
		Point3D p2;
		double d;

		Pair(Point3D a, Point3D b)
		{
			p1 = a;
			p2 = b;
			d = p1.distance(p2);
		}

		public Point3D getp1()
		{
			return p1;
		}

		public Point3D getp2()
		{
			return p2;
		}

		public double getDist()
		{
			return d;
		}

		public String toString()
		{
			return ("" + p1 + " : " + p2 + " Distance= " + d);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}