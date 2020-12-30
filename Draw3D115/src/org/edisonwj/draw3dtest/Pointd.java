package org.edisonwj.draw3dtest;

import java.text.*;
import java.util.*;

import org.edisonwj.draw3d.Draw3DDefaults;

/**
 * Pointd class implements a class corresponding
 * to 2-dimensional points in the plane specified
 * by type double x and y coordinates.
 */
public class Pointd implements Draw3DDefaults {
	/**
	 * Class variables providing for inexact point comparison.
	 */
	private static final double epsilon = EPSILON;
	private static final boolean exact_comp = true;

	/**
	 * Variables specifying a point instance.
	 */
	public double x;
	public double y;

	/**
	 * Pointd constructor.
	 */
	public Pointd(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Default Pointd constructor.
	 */
	public Pointd()
	{
		this(0.0, 0.0);
	}

	/**
	 * Constructor taking a string containing
	 * two floating point numbers as argument.
	 */
	public Pointd(String s)
	{
		StringTokenizer t = new StringTokenizer(s, "|");
		x = Double.parseDouble(t.nextToken());
		y = Double.parseDouble(t.nextToken());
	}

	/**
	 * Translate point location by xdelta and ydelta.
	 */
	public Pointd translate(double xdelta, double ydelta)
	{
		return new Pointd(x + xdelta, y + ydelta);
	}

	/**
	 * Scale point coordinate values by a scalar factor.
	 */
	public Pointd scale(double factor)
	{
		return new Pointd(x*factor, y*factor);
	}

	/**
	 * Return string representation of Pointd instance.
	 */
	public String toString()
	{
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(16);
		return ("(" + nf.format(x) + ", " + nf.format(y) + ")");
	}

	/**
	 * Accessor for x-coordinate.
	 */
	public final double getx()
	{
		return x;
	}

	/**
	 * Accessor for y-coordinate.
	 */
	public final double gety()
	{
		return y;
	}

	/**
	 * Settor for x-coordinate.
	 */
	public final void setx(double x)
	{
		this.x = x;
	}

	/**
	 * Settor for y-coordinate.
	 */
	public final void sety(double y)
	{
		this.y = y;
	}

	/**
	 * Return true if the point instance occurs before point p
	 * during a horizontal line sweep.
	 */
	public final boolean before(Pointd p)		/* used for sweep line */
	{
		if ( this.y >  p.gety() ||
			 this.y == p.gety() && this.x < p.getx() )
			return true;
		else
			return false;
	}

	/**
	 * Return true if the point instance occurs before point p
	 * during a vertical line sweep.
	 */
	public final boolean vbefore(Pointd p)		/* used for sweep line */
	{
		if (exact_comp)
		{
			if ( this.x < p.getx() ||
				 this.x == p.getx() && this.y < p.gety() )
				return true;
			else
				return false;
		}
		else
		{
			if ( this.x < p.getx() ||
				(Math.abs(this.x - p.getx()) < epsilon) && this.y < p.gety() )
				return true;
			else
				return false;
		}
	}

	/**
	 * Return true if the point instance is less than point p
	 * during a counter clockwise polygon scan test.
	 */
	public final boolean less(Pointd p)			/* used for counter clockwise polygon */
	{
		if ( this.y <  p.gety() ||
			 this.y == p.gety() && this.x < p.getx() )
			return true;
		else
			return false;
	}

	/**
	 * Return true if the point instance is less than point p
	 * where less means smaller x coordinate or if equal x, smaller y.
	 */
	public final boolean xless(Pointd p)
	{
		if ( this.x <  p.getx() ||
			 this.x == p.getx() && this.y < p.gety() )
			return true;
		else
			return false;
	}

	/**
	 * Return true if the point instance equals point p.
	 * Exact or inexact comparison is supported.
	 */
	public final boolean equals(Pointd p)
	{
		if (exact_comp)
		{
			if ( this.x == p.getx() && this.y == p.gety() )
				return true;
			else
				return false;
		}
		else
		{
			if ( (Math.abs(this.x - p.getx()) < epsilon) &&
				 (Math.abs(this.y - p.gety()) < epsilon) )
				return true;
			else
				return false;
		}
	}

}