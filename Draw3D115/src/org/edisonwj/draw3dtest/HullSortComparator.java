package org.edisonwj.draw3dtest;


import java.util.*;

// A Comparator is used for sorting. Since Java doesn't
// know how we want to compare two points, we have to
// do the comparison ourselves (whenever Java calls us).

public class HullSortComparator implements Comparator {

  Pointd lowestPoint;

  public HullSortComparator (Pointd lowestPoint)
  {
    this.lowestPoint = lowestPoint;
  }
  
  // Return -1 if less than, 0 if equal or 1 if greater than.
  // A point is "less" than another, if it's angle with the horizontal
  // is greater than the other. So, if in going from "lowestPoint"
  // to p and then to q, we make a left turn, then p is "less".

  public int compare (Object obj1, Object obj2)
  {
    Pointd p = (Pointd) obj1;
    Pointd q = (Pointd) obj2;
    int rint = 0;
    
    // If the area has a positive sign, we're making a left turn.
    int as = Geometry.area_sign (lowestPoint, p, q);
    if ( as > 0)	// Left turn.
      rint = -1;
    else if (as < 0)	// Right turn.
      rint = 1;
    else 		// Collinear.
    {
      double x = Math.abs(p.getx() - lowestPoint.getx()) - Math.abs(q.getx() - lowestPoint.getx());
      double y = Math.abs(p.gety() - lowestPoint.gety()) - Math.abs(q.gety() - lowestPoint.gety());
      if ( (x < 0) || (y < 0) )
        rint = -1;
      else if ( (x > 0) || (y > 0) )
        rint = 1;
      else		// Points are coincident.
        rint = 0;
    }
    System.out.println("compare: " + p.toString() + ", " + q.toString() + ", r= " + rint);
    return rint;
  }
  
  // The interface requires this method, but we don't need it.

  public boolean equals (Object obj)
  {
    return false;
  }
  
}
