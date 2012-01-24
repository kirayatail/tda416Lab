package edu.chl.wmaxsebljun.tda416.lab1;
public class RecursiveMath {

	public static void main(String[] args) {
		System.out.println(binarySqrt(81, 0.000001));
		System.out.println(binarySqrt(3, 0.000001));
	}

	public static double binarySqrt(double sqr, double eps)
			throws IllegalArgumentException {
		/*
		 * Fasadmetod som anropar help, där allt jobb görs. Ska kontrollera
		 * indata. Bounds för high och low är 1 och sqr. sqr ska vara större än
		 * 1
		 */
		if (sqr < 1) {
			throw new IllegalArgumentException("Argument sqr must be >1");

		} else if (eps < 0) {
			throw new IllegalArgumentException("Argument eps must be >0");
		} else {
			return help(sqr, eps, 1, sqr);
		}
	}

	private static double help(double sqr, double eps, double low, double high) {
		/*
		 * Rekursiv metod med följande karaktär: parametrar sqr och eps följer
		 * med i varje anrop low och high förändras för varje anrop yttre bounds
		 * enligt fasadmetoden
		 * 
		 * eps innebär nogrannhet, check enligt följande: kandidat^2 - sqr < eps
		 * OK! annars en ny runda.
		 */

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
