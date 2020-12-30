package org.edisonwj.draw3dtest;

import org.edisonwj.draw3d.Draw3D;
import org.edisonwj.draw3d.Draw3DDefaults;
import org.edisonwj.draw3d.Line3D;
import org.edisonwj.draw3d.Rand;
import org.edisonwj.draw3d.Text3D;
import org.edisonwj.draw3d.Triangle3D;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
/**
* Test Simha antipodal algorithm with Preparata/Shamos test case.
*
* @author William Edison
* @version 1.01 August 2015
* 
*/
public class TestAntipodal9 extends Application implements Draw3DDefaults{
	private Draw3D dt;
	private boolean turnComplete = false;
	private Rand mr = new Rand();
	
	@Override
	public void start(Stage primaryStage) {
		dt = new Draw3D();
		Scene scene = dt.buildScene();
		buildData();
		dt.setStart();
		primaryStage.setScene(scene);
		primaryStage.setTitle("TestAntipodal9");
		primaryStage.show();
	}
	
	private void buildData() {
	
		// Hull from 500 point sample
		
		dt.setCumulate(true);
		dt.setYaxisUp(true);
		dt.setXYZRange(10, 10, 10);
		dt.setCamera(0.0,  -180.0,  -800.0);
		dt.setOriginView(-100, -100, -100);
		
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
		
   		System.out.println("\nHull Points");
		dt.setPointSize(2);
		for (int i = 0; i < p.length; i++) {
			dt.setDrawColor(Color.RED);
			dt.drawPoint3D(p[i]);
			dt.setDrawColor(Color.BLACK);
			dt.drawLabel3D(new Text3D(p[i],180,0,0,(""+i)));
			System.out.println("i= " + i + ", " + p[i].toString());
		}
		
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
	    dt.setDrawColor(Color.RED);
	    dt.drawLine3D(new Line3D(p[maxi], p[maxj]));
		
		// Find antipodal pairs
	    System.out.println("\nFind antipodal pairs\n");
		System.out.println("\nMax AntiPodal Distance= " + findLargestDistance(p));
	}
	
	  // Find the distance between two points.
	  private double distance (Point3D p, Point3D q)
	  {
	    return Math.sqrt ( (p.getX()-q.getX())*(p.getX()-q.getX()) + (p.getY()-q.getY())*(p.getY()-q.getY()) );
	  }


	  // Find the max distance for a set of 3 points.
	  private double findMaxDistance3 (Point3D[] points, int a, int b, int c)
	  {
	    // Compute the distance between each of ab, ac and bc,
	    // and return the largest.
	    double dist_ab = distance (points[a], points[b]);
	    double dist_bc = distance (points[c], points[b]);
	    double dist_ac = distance (points[a], points[c]);
	    System.out.println("findMaxDistance3: dist_ab = " + dist_ab +
	    					", dist_bc= " + dist_bc +
	    					", dist_ac= " + dist_ac);
	    double maxDist = dist_ab;
	    if (dist_bc > maxDist)
	      maxDist = dist_bc;
	    if (dist_ac > maxDist)
	      maxDist = dist_ac;
	    System.out.println("findMaxDistance3: maxDist = " + maxDist);
	    return maxDist;
	  }

	  // Find the next point in the list going counterclockwise,
	  // that is, in increasing order. Go back to zero if needed.
	  // Return the array index.
	  int nextCounterClockwise (Point3D[] points, int i)
	  {
	    if (i >= points.length-1) {
	      turnComplete = true;
	      return 0;
	    }
	    else
	      return (i+1);
	  }

	  // Find the next point in counterclockwise order.
	  int prevCounterClockwise (Point3D[] points, int i)
	  {
	    if (i <= 0)
	      return points.length-1;
	    else
	      return (i - 1);
	  }

	  // Given a convex polygon, an index into the vertex array (a particular vertex),
	  // find it's antipodal vertex, the one farthest away. Start the search from
	  // a specified vertex, the "startAntiPodalIndex"
	  int findAntiPodalIndex (Point3D[] hullPoints, int currentIndex, int startAntiPodalIndex)
	  {
	    // Short forms:
	    int b = currentIndex;
	    int c = startAntiPodalIndex;

	    // 1. Find the point preceding b:
	    int a = prevCounterClockwise (hullPoints, b);

	    // 2. We start just behind the starting index:
	    int current = prevCounterClockwise (hullPoints, c);

	    // 3. Start with area computations, to use in lieu of distance comparisons.
    	System.out.println("findAntiPodal Initial");
    	System.out.println("a= " + a + ", b= " + b + ", c= " + c+ ", current= " + current); 
	    
	    double area_abc = Math.abs (area2 (hullPoints[a], hullPoints[b], hullPoints[c]));
	    double area_abcurrent = Math.abs (area2 (hullPoints[a], hullPoints[b], hullPoints[current]));
	    
    	System.out.println("area_abc= " + area_abc + ", area_abcurrent= " + area_abcurrent);

	    // 3. While the current point (current) is closer than c:
	    while (area_abc >= area_abcurrent) {
	    	System.out.println("findAntiPodal Loop");
	    	System.out.println("a= " + a + ", b= " + b + ", c= " + c+ ", current= " + current);

	      // 3.1 Move current up:
	      current = c;

	      // 3.2 Move c up:
	      c = nextCounterClockwise (hullPoints, c);

	      // 3.3 Compare distances again:
	      System.out.println("a= " + a + ", b= " + b + ", c= " + c+ ", current= " + current); 
	      
	      area_abc = Math.abs (area2 (hullPoints[a], hullPoints[b], hullPoints[c]));
	      area_abcurrent = Math.abs (area2 (hullPoints[a], hullPoints[b], hullPoints[current]));
	      
	      System.out.println("area_abc= " + area_abc + ", area_abcurrent= " + area_abcurrent);
	      
	    }

	    // 4. When distances start decreasing, return the currently largest one:
	    return current;

	  }

	  public double findLargestDistance (Point3D[] hullPoints)
	  {
		Triangle3D tri;
//		System.out.println("All Points");
//		for (int i = 0; i < points.length; i++) {
//			System.out.println(points[i].getx() + ", " + points[i].gety());
//		}
//		System.out.println(" ");

	    // 1. Compute the convex hull:
//	    Hull hull = new Hull (points);

	    // 2. Extract the hull points:
//	    Pointd[] hullPoints = hull.getPoints();

//		System.out.println("Hull Points");
//		for (int i = 0; i < hullPoints.length; i++) {
//			System.out.println(hullPoints[i].getX() + ", " + hullPoints[i].getY());
//		}
//		System.out.println(" ");

	    // 3. If it's exactly three points, we have a method just for that:
	    if (hullPoints.length == 3)
	      return findMaxDistance3 (hullPoints, 0, 1, 2);

	    // Otherwise, we start an antipodal scan.
	    boolean over = false;

	    // 4. Start the scan at vertex 0, using the edge ending at 0:
	    int currentIndex = 0;
	    int prevIndex = prevCounterClockwise (hullPoints, currentIndex);

	    // 5. Find the antipodal vertex for edge (n-1,0):
	    System.out.println("findLargest: call findAntiPodalIndex: currentIndex= " + currentIndex +
	    				", antiPodalIndex= 1");
	    int antiPodalIndex = findAntiPodalIndex (hullPoints, currentIndex, 1);
	    System.out.println("new antiPodalIndex= " + antiPodalIndex);

	    // 6. Set the current largest distance:
	    System.out.println("findLargest: call findMaxDistance3: currentIndex= " + currentIndex +
	    		", prevIndex= " + prevIndex + ", antiPodalIndex= " + antiPodalIndex);
	    double maxDist = findMaxDistance3 (hullPoints, currentIndex, prevIndex, antiPodalIndex);
	    System.out.println("findLargest: new maxDist= " + maxDist);
	    dt.incrSequence();
	    tri = new Triangle3D(hullPoints[currentIndex], hullPoints[prevIndex], hullPoints[antiPodalIndex], newColor());
	    tri.setDrawMode(DrawMode.LINE);
	    dt.drawTriangle3D(tri);;
	    
	    // We'll stop once we've gone around and come back to vertex 0.
	    double dist = 0;
	    turnComplete = false;

	    // 7. While the turn is not complete:
	    while (! over) {
	    	
	    	System.out.println("\nHead of Loop");

	      // 7.1 Find the next edge:
	      prevIndex = currentIndex;
	      currentIndex = nextCounterClockwise (hullPoints, currentIndex);

	      // 7.2 Get its antipodal vertex:
		  System.out.println("findLargest: call findAntiPodalIndex: currentIndex= " + currentIndex +
				  		", antiPodalIndex= " + antiPodalIndex);
	      antiPodalIndex = findAntiPodalIndex (hullPoints, currentIndex, antiPodalIndex);
	      System.out.println("new antiPodalIndex= " + antiPodalIndex);
	      
	      // 7.3 Compute the distance:
	      System.out.println("findLargest: call findMaxDistance3: currentIndex= " + currentIndex +
	    		", prevIndex= " + prevIndex + ", antiPodalIndex= " + antiPodalIndex);
	      dist = findMaxDistance3 (hullPoints, currentIndex, prevIndex, antiPodalIndex);
		  System.out.println("findLargest: new dist= " + dist);
 		  dt.incrSequence();
 		    tri = new Triangle3D(hullPoints[currentIndex], hullPoints[prevIndex], hullPoints[antiPodalIndex], newColor());
 		    tri.setDrawMode(DrawMode.LINE);
 		    dt.drawTriangle3D(tri);

	      // 7.4 Record maximum:
	      if (dist > maxDist) {
	        maxDist = dist;
		    System.out.println("findLargest: new maxDist= " + maxDist);
	      }

	      // 7.5 Check whether turn is complete:
	      if (turnComplete)
	        over = true;
	    } // end-while

	    // 8. Return largest distance found.
	    return maxDist;
	  }
	
		private double area2(Point3D a, Point3D b, Point3D c) {
			double a2 = (a.getX() - c.getX()) * (b.getY() - c.getY()) - (a.getY() - c.getY()) * (b.getX() - c.getX());
//			System.out.println("area2: a= " + a + ", b= " + b + ", c= " + c + ", area2= " + a2);
			return a2;
		}
		
		private PhongMaterial newColor() {
			int r = mr.uniform(0,  255);
			int g = mr.uniform(0,  255);
			int b = mr.uniform(0,  255);
			return new PhongMaterial(Color.rgb(r, g, b));
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}