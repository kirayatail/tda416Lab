/**
 * 
 * @author Sebastian Ljunggren, Max Witt, grupp 10
 * 
 */
public class Uppg2 {

	public static void main(String[] args) {
		System.out.println(binarySqrt(81, 0.000001));
		System.out.println(binarySqrt(9, 0.000001));
		System.out.println(binarySqrt(102400, 0.000001));
		
		try{
			System.out.println("FAIL! faulty root of 1: "+binarySqrt(1,0.001));
		} catch(IllegalArgumentException e){
			System.out.println("OK! Exception for sqr == 1 caught");
		}
		try{
			System.out.println("FAIL! Computed inacceptable eps 0: "+binarySqrt(9,0));
		} catch(IllegalArgumentException e){
			System.out.println("OK! Exception for eps == 0 caught");
		}
	}

	/**
	 * Finds square root by binary search. This is the access method, and
	 * therefore public.
	 * The precision limit determines that the result must be within ± <code>eps</code> of the true root
	 * 
	 * @param sqr
	 *           - number to find the root of, must be >1
	 * @param eps
	 *           - precision limit, must be >0
	 * @return
	 * @throws IllegalArgumentException thrown when parameter rules are violated
	 */
	public static double binarySqrt(double sqr, double eps)
			throws IllegalArgumentException {

		if (sqr <= 1) {
			throw new IllegalArgumentException("Argument sqr must be >1");

		} else if (eps <= 0) {
			throw new IllegalArgumentException("Argument eps must be >0");
		} else {
			return help(sqr, eps, 1, sqr);
		}
	}

	/**
	 * This is the recursive help method that does all work. Does no input validation
	 * (which is done in the access method).
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
