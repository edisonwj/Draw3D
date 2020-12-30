package org.edisonwj.draw3dtest;

/**
 * Class <code>HullStack</code> implements a stack customized
 * to look at the top two points occasionally.
 *
 */

public class HullStack
{
  // Stack array.
  Pointd[] hstack;
  int count;


  HullStack (int maxSize)
  {
    hstack = new Pointd[maxSize];
    count = 0;
  }


  public void push (Pointd p)
  {
    hstack[count++] = p;
  }

  public Pointd pop()
  {
    return hstack[count--];
  }


  // Return true if the top two points in the stack and the
  // specified third point form a left turn proceeding
  // clockwise around the hull.

  public boolean isHull (Pointd p)
  {
    // System.out.println ("isHull: count=" + count);
    return Geometry.left (hstack[count-2], hstack[count-1], p);
  }

  public String toString()
  {
    String result = "";
    for (int i = 0; i < count; i++)
      result += "s[" + i + "] = " + hstack[i] + "\n";
    return result;
  }


  // What's left will be the hull.

  public Pointd[] hullArray()
  {
    Pointd[] h = new Pointd[count];
    System.arraycopy(hstack, 0, h, 0, count);
    return h;
  }
}
