package org.edisonwj.draw3dtest;

import java.util.*;

public class Hull {

  // Maintain hull points internally:
  private Pointd[] hull;

  // The data set is given to the constructor.

  public Hull (Pointd[] points)
  {
    Pointd[] vertices;
    int i, n;
    
    //// Make hash map of input points
    HashMap hm = new HashMap();
    for (int iv = 0; iv < points.length; iv++) {
    	hm.put(points[iv], iv);
    }
    
    // Record the number of vertices:
    n = points.length;

    // 1. Make a copy of the vertices because we'll need to sort:
    vertices = new Pointd[n];
    System.arraycopy (points, 0, vertices, 0, n);

    // 2. Find the rightmost, lowest point (it's on the hull).
    int low = findLowest (vertices);

    // 3. Put that in the first position and sort the rest by
    //    angle made from the horizontal line through "low".
    swap (vertices, 0, low);	
    HullSortComparator comp = new HullSortComparator (vertices[0]);
    Arrays.sort (vertices, 1, vertices.length, comp);
    
    //// Print out sorted vertices with input point number
    System.out.println("\nSorted vertices");
    for (int is = 0; is < vertices.length; is++) {
    	Pointd v = vertices[is];
    	System.out.println("v= " + hm.get(vertices[is]) + ", " + vertices[is]);
    }

    // 4. Remove collinear points:
    n = removeCollinearPoints (vertices);		

    // 5. Now compute the hull.
    hull = grahamScan (vertices, n);
  }

  
  // Return size of hull found.

  public final int size()
  {
    return hull.length;
  }

  public String toString()
  {
    String hullString = "";
    for (int i = 0; i < hull.length; i++)
      hullString += ("h[" + i + "]= " + hull[i] +"\n");
    return hullString;
  }
  

  // Return the convex hull.

  public final Pointd[] getPoints()
  {
    return hull;
  }
  

  void printPoints (Pointd[] points)
  {
    System.out.print ("Hull.printPoints: ");
    for (int i=0; i<points.length; i++)
      System.out.print (points[i]);
  }
  

  Pointd[] grahamScan (Pointd[] p, int numPoints)
  {
    // 1. Create a stack and initialize with first two points:
    HullStack hstack = new HullStack (numPoints);
    hstack.push (p[0]);
    hstack.push (p[1]);

    // 2. Start scanning points.
    int i = 2;

    // 3. While scan not complete:
    while (i < numPoints)
    {
      // 3.1 If the current point is on the hull, push next one.
      //     We know a point is potentially on the hull if the
      //     the angle is convex (a left turn).
      if ( hstack.isHull (p[i]) )
        hstack.push (p[i++]);
      //     Else remove it.
      else
        hstack.pop ();
    }

    // 4. Return all points still on the stack.
    return hstack.hullArray();
  }

  private int findLowest (Pointd[] v)
  {
    // 1. Scan through points:
    int low = 0;
    for (int i = 1; i < v.length; i++)
    {
      // 1.1 If y-value is lower, the point is lower. If the y-values
      //     are the same, check that the x value is further to the right:
      if ( (v[i].gety() < v[low].gety()) ||
           ((v[i].gety() == v[low].gety()) && (v[i].getx() > v[low].getx())) )
        low = i;
    }
    // 2. Return lowest point found:
    return low;
  }
  
  private int removeCollinearPoints (Pointd[] p)
  {
    // We'll need the values after the loop, so declare them here:
    int i, j;
    
    // 1. Scan through points:
    for (i = 1, j=1; i <= p.length - 2; i++)
    {
      // 1.1 If the area is zero, we have collinear points.
      if ( Geometry.area_sign(p[0], p[i], p[i+1]) != 0 )
        p[j++] = p[i];
    }

    // Last point is always on the hull.
    p[j++] = p[i++];		
    
    // 2. Remove the rest:
    for (i = j; i <= p.length - 1; i++)
      p[i] = null;
    
    // 3. Return the number of non-collinear points:
    return j;
  }
  
  
  void swap (Pointd[] data, int i, int j)
  {
    Pointd temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }



  static void printPointArray (Pointd[] points)
  {
    System.out.print ("PointArray: ");
    for (int i=0; i<points.length; i++)
      System.out.print (" " + points[i]);
    System.out.println ("");
  }


  /**
   * Main method for testing class implementation.
   * Test case from O'Rourke, pp. 85-86.
   */

  public static void test1 ()
  {
    Pointd[] tv = new Pointd[19];
    tv[0] = new Pointd(3,3);
    tv[1] = new Pointd(3,5);
    tv[2] = new Pointd(0,1);
    tv[3] = new Pointd(2,5);
    tv[4] = new Pointd(-2,2);
    tv[5] = new Pointd(-3,2);
    tv[6] = new Pointd(6,5);
    tv[7] = new Pointd(-3,4);
    tv[8] = new Pointd(-5,2);
    tv[9] = new Pointd(-5,-1);
    tv[10] = new Pointd(1,-2);
    tv[11] = new Pointd(-3,-2);
    tv[12] = new Pointd(4,2);
    tv[13] = new Pointd(5,1);
    tv[14] = new Pointd(-5,1);
    tv[15] = new Pointd(3,-2);
    tv[16] = new Pointd(0,5);
    tv[17] = new Pointd(0,0);
    tv[18] = new Pointd(7,4);
    
    for (int i=0; i<tv.length; i++)
      System.out.println("tv[" + i + "]= " + tv[i]);
    
    Hull htest = new Hull(tv);
    System.out.println("\nHull:\n" + htest.toString());
  }

  public static void test2 ()
  {
    Pointd[] tv = new Pointd[4];
    tv[0] = new Pointd(0,0);
    tv[1] = new Pointd(0,1);
    tv[2] = new Pointd(1,0);
    tv[3] = new Pointd(1,1);
    printPointArray (tv);
    Hull htest = new Hull(tv);
    System.out.println("\nHull:\n" + htest.toString());
  }
  

  public static void main(String [] args)
  {
    test1();
    test2();
  }
  
}