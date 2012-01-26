package edu.chl.wmaxsebljun.tda416.lab1;

public class RecursiveMath {

	public static void main(String[] args) {
		System.out.println(binarySqrt(81, 0.000001));
		System.out.println(binarySqrt(9, 0.000001));
		System.out.println(binarySqrt(102400,0.000001));
	}

	/**
	 * Finds square root by binary search. This is the access method, and
	 * therefore public.
	 * 
	 * @param sqr
	 * @param eps
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static double binarySqrt(double sqr, double eps)
			throws IllegalArgumentException {
		
		if (sqr < 1) {
			throw new IllegalArgumentException("Argument sqr must be >1");

		} else if (eps < 0) {
			throw new IllegalArgumentException("Argument eps must be >0");
		} else {
			return help(sqr, eps, 1, sqr);
		}
	}

	/**
	 * This is the recursive help method that does all work. Does no validation,
	 * (which is done in the access method) and is private for protection.
	 * 
	 * @param sqr
	 * @param eps
	 * @param low
	 * @param high
	 * @return
	 */
	private static double help(double sqr, double eps, double low, double high) {
		
		double candidate = (high + low) / 2;
		if (Math.abs(candidate * candidate - sqr) < eps) {
			return candidate;
		} else {
			if (candidate * candidate < sqr) {
				return help(sqr, eps, candidate, high);
			} else {
				return help(sqr, eps, low, candidate);
			}
		}
	}
}
