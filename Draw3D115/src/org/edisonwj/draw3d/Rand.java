package org.edisonwj.draw3d;

import java.util.*;
/**
 * Rand provides a variety of random number generators.
 * 
 * @author Rahul Simha
 * 
 */
public class Rand {
	public static final int DEBUG = 1;
	public static final double DEFAULTEPSILON = 0.0000000000001;
	public static final long   DEFAULTSEED = Math.abs(System.currentTimeMillis());
	public static final double BOUNDED_EXP_PARAM = 5.0;
	
	private static final long m = 2147483647L;
	private static final long a = 48271L;
	private static final long q = 44488L;
	private static final long r = 3399L;
	private static final double epsilon = DEFAULTEPSILON;

	private static final long L = 1812433253L;
	private static final long P = 4294967296L;
	private static final long b = 7;

	private long r_seed;

	public Rand(long s)
	{
		r_seed = s;
	}

	public Rand()
	{
		this(DEFAULTSEED);
	}

	public long getSeed()
	{
		return r_seed;
	}

/* Basic Lehmer generator - See Knuth, Vol. II                      */
/* Returns a uniform random number between 0 and 1                  */

	public double uniform ()
//	{
//		r_seed = ( L * r_seed + b ) % P;
//
//		return ( (double) r_seed / (double) P );
//	}

	{
		double result;
		do
		{
			long hi = r_seed / q;
 			long lo = r_seed - q * hi;
 			long t = a * lo - r * hi;
 			if (t > 0)
 				r_seed = t;
 			else
 				r_seed = t + m;
 			result = ( (double) r_seed / (double) m );
		} while (result < 0.0 || result > 1.0);
		return result;
	}

/* Uniform random number generator - returns a number between a and b */

	public double uniform (double a, double b)
	{
		if (b > a)
			return ( a + (b-a) * uniform() );
		else {
			System.out.println("ERR: in uniform(double, double):a= " + a + " b= " + b);
			return 0;
		}
	}

/* Discrete uniform random generator - returns an integer between a and b */

	public int uniform (int a, int b)
	{
		if (b > a) {
			double x = uniform();
			int c = ( a + (int) Math.floor((b-a+1)*x) );
			return c;
		}
		else if (a == b)
			return a;
		else {
			System.out.println("ERR: in uniform(int, int):a= " + a  + " b= " + b);
			return 0;
		}
	}

	public long uniform (long a, long b)
	{
		if (b > a) {
			double x = uniform();
			long c = ( a + (long) Math.floor((b-a+1)*x) );
			return c;
		}
		else if (a == b)
			return a;
		else {
			System.out.println("ERR: in uniform(long, long):a= " + a  + " b= " + b);
			return 0;
		}
	}

/* Exponential random number generator */

	public double exponential(double mu)
	{
		double u, x;

		u = uniform();
		while ( (u <= 0) || ( u >= 1))
			u = uniform();
		x = (-1) * mu * Math.log( u );
//		x = (-1) * mu * Math.log( 1 - u );
		return(x);
	}

/* Exponential random number between a and b */

	public double exponential(double a, double b)
	{
		double x;

		if (b > a) {
			x = exponential(2);
//			x = exponential(.5);
			while ((x < a) || (x > b))
				x = exponential(2);
//				x = exponential(.5);
			return ( x );
		}
		else {
		    System.out.println("ERR: in exponential(double, double):a= " + a + " b= " + b);
			 return 0;
		}
	}

	public double bounded_exponential(double rate)
	{
		double mean, x;

		if(Math.abs(rate) < epsilon)
			System.out.println("ERR: bounded exp: rate=0");

		mean = 1.0/rate;
		do {
			x = exponential(rate);
		} while(x > mean*BOUNDED_EXP_PARAM);
		return x;
	}

	/* Generate binomial(n,p) random variable */
	public int binomial(int n, double p)
	{
		double U = uniform();

		double c = p/(1-p);
		double pr = Math.pow(1-p,n);
		double F = pr;
		int i = 0;

		while ( !(U<F) )
		{
			pr = (c*(n-i)/(i+1))*pr;
			F += pr;
			i++;
		}
		return i;
	}

	private static final double p0 = 0.322232431088;
	private static final double p1 = 1.0;
	private static final double p2 = 0.342242088547;
	private static final double p3 = 0.204231210245e-1;
	private static final double p4 = 0.453642210148e-4;

	private static final double q0 = 0.099348462606;
	private static final double q1 = 0.588581570495;
	private static final double q2 = 0.531103462366;
	private static final double q3 = 0.103537752850;
	private static final double q4 = 0.385607006340e-2;

	public double Normal(double m, double s)

/* ========================================================================
 * Returns a normal (Gaussian) distributed real number.
 * Mean m, standard deviation s.
 * NOTE: use s > 0.0
 *
 * Uses a very accurate approximation of the normal idf due to Odeh & Evans,
 * J. Applied Statistics, 1974, vol 23, pp 96-97.
 * ========================================================================
 */
	{
		double u, t, p, q, z;

		u   = this.uniform();
		if (u < 0.5)
			t = Math.sqrt(-2.0 * Math.log(u));
		else
			t = Math.sqrt(-2.0 * Math.log(1.0 - u));
		p   = p0 + t * (p1 + t * (p2 + t * (p3 + t * p4)));
		q   = q0 + t * (q1 + t * (q2 + t * (q3 + t * q4)));
		if (u < 0.5)
			z = (p / q) - t;
		else
			z = t - (p / q);
		return (m + s * z);
	}


	/*
	 * Generate Poisson random variables
	 * Stoyan and Stoyan, Fractals, Random Shapes, and Point Fields, 1994, p. 218.
	 */
	public int[] poisson(int n, double m)
	{
		int[] kz = new int[n];
		int k;
		double p, t, z;

		t = Math.exp(-m);
		for ( int i = 0; i < n; i++)
		{
			p = 1;
			k = 0;	/* set so as not to return 0 -- effectively poisson # +1 */
			k = -1;
			do
			{
				k++;
				z = this.uniform();
				p = p*z;
			} while (p >= t);
			kz[i] = k;
		}
		return kz;
	}

	/*
	 * Generate Poisson random variables
	 * Ross, A Course in Simulation, 1990, p. 49.
	 */
	public int[] poisson2(int n, double m)
	{
		int[] rva = new int[n];		/* Poisson random variable array */
		int rvc = 0;				/* Poisson random varialbe count */

		int i, j, k;
		double a, b, cum, f, p, pp, s, u;
		a = 0.0;
		b = 0.0;
		cum = 0.0;
		f = 0.0;
		p = 0.0;
		pp = 0.0;
		s = 0.0;
		u = 0.0;

		i = (int)Math.floor(m);
		b = 1/m;

		for (k = 1; k <= i; k++)
			s = s + Math.log(k);

		s = -s -m +i*Math.log(m);
		pp = Math.exp(s);
		f = 1;

		for (k = 1; k <= i; k++)
		{
			f = f*(i+1-k)*b;
			cum = cum + f;
		}

		cum = (cum+1)*pp;

		for (j=1; j <= n; j++)
		{
			i = (int)Math.floor(m);
			a = cum;
			p = pp;
			u = this.uniform();
			if (u >= a)
			{
				do
				{
					i = i + 1;
					p = m*p/i;
					a = a + p;
				} while (u >= a);
				rva[rvc++] = i;
				continue;
			}
			else
			{
				while (u <= a)
				{
					a = a - p;
					p = i*p*b;
					i = i - 1;
				}
			}
			rva[rvc++] = i;
		}
		return rva;
	}

	public double[] mmpp        /* Return an array of arrival times, sorted. */
	  ( int num_items,          /* Number of points or arrivals */
	    int ndiv,               /* Number of A (or B) intervals to use */
	    double alpha_A,         /* Exponential length parameter for A-intervals */
	    double alpha_B,         /* Exponential length parameter for B-intervals */
	    double lambda_A,        /* Exponential rate parameter for A-intervals */
	    double lambda_B,        /* Exponential rate parameter for B-intervals */
	    double upper_bound      /* Overall length of interval */
	  )
	{
		int debug=DEBUG;			 /* Debug level */

	   double [] Y;				 /* Array of values returned; */
	   double [] X;             /* Working array to generate values returned */
	   double [] A_int;         /* Generate A-interval values in this cdf */
	   double [] B_int;         /* Generate A-interval values in this cdf */
	   double [] A_arrivals;    /* Generate A-arrivals in here */
	   double [] B_arrivals;    /* Generate B-arrivals in here */
	   double total_int;        /* Overall length of intervals, A and B */
	   double frac_A, frac_B;   /* Fraction of A, B arrivals */
	   double ex_A, ex_B;       /* Expectations of A, B arrivals */
	   double A_lower, A_upper;    /* Used in scanning */
	   double B_lower, B_upper;

		int i, j, k, m;          /* Indices */
		int num_A, num_B;        /* Number of A, B arrivals (or points) */
	   int A_start, B_start;    /* Used in scanning */
	   int [] which_A_int;      /* which_A_int[i] tells you which A-interval */
	   int [] which_B_int;      /*  the i-th A arrival occured. Same for B */

		X = new double [num_items+1];

		if (debug >= 3)
		{
		    System.out.println("D3: MMPP: n= " + num_items
								+ ", ndiv= " + ndiv
								+ ", a_A= " + alpha_A
								+ ", a_B= " + alpha_B);
		    System.out.println(" l_A= " + lambda_A
								+ ", l_B= " + lambda_B);
		}

		A_int = new double [ndiv+1];
		B_int = new double [ndiv+1];

	 /* Create the A and B intervals */
		A_int[0] = B_int[0] = 0;
		for (i=1; i<=ndiv; i++) {
			A_int[i] = A_int[i-1] + exponential(alpha_A);
			B_int[i] = B_int[i-1] + exponential(alpha_B);
		}

	  /* Compute how many A and how many B arrivals (points) */
		total_int = A_int[ndiv] + B_int[ndiv];
		ex_A = lambda_A * A_int[ndiv];
		ex_B = lambda_B * B_int[ndiv];
		frac_A = (ex_A) / (ex_A + ex_B);
		frac_B = (ex_B) / (ex_A + ex_B);
		num_A = (int)Math.ceil(frac_A * num_items);
		num_B = (int)(num_items - num_A);

	  /* Now distribute the A arrivals uniformly in the A intervals */
		A_arrivals = new double [num_A+1];
		B_arrivals = new double [num_B+1];
		which_A_int = new int [num_A+1];
		which_B_int = new int [num_B+1];

		A_arrivals[0] = B_arrivals[0] = 0;
		for (i=1; i<=num_A; i++)
	    	A_arrivals[i] = uniform (0, A_int[ndiv]);
	  	for (i=1; i<=num_B; i++)
	  		B_arrivals[i] = uniform (0, B_int[ndiv]);

	   if (debug >= 3)
		{
	   	System.out.println("D3:mmpp:A= " + A_int[ndiv]
							+ ", B= " + B_int[ndiv]
							+ ", ex_A = " + ex_A
							+ ", ex_B = " + ex_B
							+ ", n_A= " + num_A
							+ ", n_B= " + num_B);
	   	System.out.println("A_int:");
	   	for (i=0; i<=ndiv; i++) System.out.println (A_int[i]);
	   		System.out.println();
	   	System.out.println("B_int:");
	   	for (i=0; i<=ndiv; i++) System.out.println(B_int[i]);
	   		System.out.println();
	   	System.out.println("A_arr:");
	   	for (i=0; i<=num_A; i++) System.out.println(A_arrivals[i]);
	   		System.out.println();
	   	System.out.println("B_arr:");
	   	for (i=0; i<=num_B; i++) System.out.println(B_arrivals[i]);
	   		System.out.println();
	  }

	  /* Next, sort the arrivals: see qsort.c for documentation on quicksort. */
		Arrays.sort(A_arrivals);
		Arrays.sort(B_arrivals);

	   if (debug >= 3)
		{
	   	System.out.println("D3:mmpp:after sort:");
	   	System.out.println ("A_arr:");
	   	for (i=0; i<=num_A; i++)
				System.out.println (A_arrivals[i]);
	   	System.out.println ();
	   	System.out.println ("B_arr:");
	   	for (i=0; i<=num_B; i++)
				System.out.println (B_arrivals[i]);
	   	System.out.println ();
	   }

	  /* Now find out which interval each arrival belongs to */

		for (i=1; i<=num_A; i++)
			which_A_int[i] = Math.abs(Arrays.binarySearch(A_int, A_arrivals[i]))-1;
		for (i=1; i<=num_B; i++)
			which_B_int[i] = Math.abs(Arrays.binarySearch(B_int, B_arrivals[i]))-1;

	   if (debug >= 3)
		{
	   	System.out.println("D3:mmpp:after interval ident:");
	   	System.out.println("A_whi:");
	   	for (i=0; i<=num_A; i++)
				System.out.println(which_A_int[i]);
	   	System.out.println();
	   	System.out.println("B_whi:");
	   	for (i=0; i<=num_B; i++)
				System.out.println(which_B_int[i]);
	   	System.out.println ();
	  }

	  /* Now, do each pair of intervals. Basically, we scan past the
	     A arrivals in order and determine their interval. While we
	     are on the i-th interval, we only pick off those A-arrivals
	     that occurred in the i-th interval. For this interval, we
	     need the lower end and upper end. After the i-th A interval
	     is processed, we process the i-th B-interval similarly.
	     In this way, arrivals from the A and B intervals are interleaved. */

		A_start = 1;  B_start = 1;
		A_lower = A_upper = 0;    B_lower = B_upper = 0;
		X[0] = 0;   m = 1;
		for (i=1; i<=ndiv; i++) {
	      /* First, the i-th A-interval */
			k = A_start;
		   A_upper = A_lower + (A_int[i] - A_int[i-1]);

	      if (debug >= 3)
			{
	      	System.out.println("D3:mmpp:1:i= " + i
		  					+ ", A_st= " + A_start
							+ ", A_l= " + A_lower
							+ ", A_u= " + A_upper
							+ ", m= " + m
							+ ", k= " + k);
	      	System.out.println("X:");
				for (j=1; j<m; j++)
		  			System.out.println(X[j]);
	      	System.out.println();
	    	}

			while ( (k <= num_A) && (which_A_int[k] == i) )
			{
				X[m] = X[m-1] + (A_arrivals[k] - A_arrivals[k-1]);
				k++;
				m++;
	   	 }
	   	 A_lower = A_upper;  A_start = k;

	   	 /* Next, the i-th B-interval */
	   	 k = B_start;
	   	 B_upper = B_lower + (B_int[i] - B_int[i-1]);

	   	if (debug >= 3)
			{
	   		System.out.println("D3:mmpp:1:i= " + i
		  					+ ", B_st= " + B_start
							+ ", B_l= " + B_lower
							+ ", B_u= " + B_upper
							+ ", m= " + m
							+ ", k= " + k);
				System.out.println("X:");
	   		for (j=1; j<m; j++)
			  		System.out.println(X[j]);
	   	   System.out.println();
			}

			while ( (k <= num_B) && (which_B_int[k] == i) ){
		      X[m] = X[m-1] + (B_arrivals[k] - B_arrivals[k-1]);
		      k++;
		      m++;
			}
			B_lower = B_upper;  B_start = k;
		}
		m--;

	   if (debug >= 3)
		{
	   	System.out.println("D3: mmpp: after assign: m= " + m);
			System.out.println("X:");
	   	for (i=0; i<X.length; i++)
				System.out.println(X[i]);
	   	System.out.println ();
	   }

	  /* Finally, scale up/down to specified interval */
		for (i=1; i<=num_items; i++)
			X[i] = (X[i] / total_int) * upper_bound;

		if (debug >= 4)
		{
			System.out.println("X:");
			for (i=0; i<X.length; i++)
				System.out.println("X[" + i + "]=" + X[i]);
			System.out.println();
		}

//		Y = new double [num_items];
//		System.arraycopy(X,1,Y,0,num_items);
//
//		if (debug >= 3)
//		{
//			System.out.println("Y:");
//			for (i=0; i<num_items; i++)
//				System.out.println("Y[" + i + "]=" + Y[i]);
//			System.out.println();
//		System.out.println("End of mmpp:");
//		}
//
//	   return Y;

		return X;
	}
}