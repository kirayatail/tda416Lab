public class Uppg3 {

	/*
	 * a)
	 */

	/*
	 * b)
	 */

	/*
	 * c)
	 * 
	 * Pedantisk analys av den tredje algoritmen.
	 * 
	 * Vi räknar som innan aritmetiska operatorer som en tidsenhet.
	 */
	static private int seqStart = 0;
	static private int seqEnd = -1;

	public static int maxSubSum3(int[] a) {
		int maxSum = 0; // 1
		int thisSum = 0; // + 1
		for (int i = 0, j = 0; // + 2
		// n (= a.length()) * (
		j < a.length; // 1
		j++) // + 1
		{
			thisSum += a[j]; // + 2
			if (thisSum > maxSum) // + 1
			{
				maxSum = thisSum; // + 1
				seqStart = i; // + 1
				seqEnd = j; // + 1
			} else if (thisSum < 0) // + 1
			{
				i = j + 1; // + 2
				thisSum = 0; // + 1
			} // I detta if/else block är worst case 5
		}
		// ) slut på n *
		return maxSum;
	}

	/*
	 * Detta ger oss:
	 * 
	 * 9n + 4 = O(n)
	 */
}
