import java.util.Random;
/*
Note on measuring time in Java
// Time an event in a program to millisecond precision
     startTime = System.currentTimeMillis();
     for (long i = 0; i < 1000000; i++) {  // for short stuff, do it many times
        long j = i - (i / 2) + (i / 3) - (i / 4) + (i / 5);
     }
     endTime = System.currentTimeMillis();
     elapsedTime = endTime - startTime;
     seconds = elapsedTime / 1.0E03;
     System.out.println ("Elapsed Time = " + seconds + " seconds");

Time an event in a program to nanosecond precision
(Note that you are not guarantied to get nanosecond precision)
     long startTime = System.nanoTime();
     for (long i = 0; i < 1000000; i++) { 
        long j = i - (i / 2) + (i / 3) - (i / 4) + (i / 5);
     }
     long endTime = System.nanoTime();
     long elapsedTime = endTime - startTime; 
     double seconds = elapsedTime / 1.0E09;
     System.out.println ("Elapsed Time = " + seconds + " seconds");

*/

public class MaxSumTest {

	/**
	* Returns a field of size arraySize of random integers 
	* @param arraySize the size of the returned array
	* @param arrayRange the maximum random number
	* @param style the style of change made to the random numbers
	* @return an array of random numbers
	*/
	private static int[] getRandoms(int arraySize, int arrayRange, int style) {
		Random rand = new Random();
		int[] array = new int[arraySize];
		int modify = 0;
		switch (style) {
			case 0: 
				modify = 0; // no change
			break;
			case 1: 
				modify = arrayRange / 2;
			break;
			case 2:
				modify = 50;
			break;
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(arrayRange) - modify;
		}
		return array;
	}

	/**
	* Times one of the MaxSubSum algorithms and returns the time to run it 10.000 times 
	* @param arraySize  the size of the tested array
	* @param arrayRange the maximum random number
	* @param algVersion the algorithm to test
	* @return the time, in nanoseconds, to run the algorithm 10.000 times
	*/
	public static long getTimingInfo( int arraySize, int arrayRange, int algVersion ) {
		//arrayRange = 100..200 // typical values
		int nbrOfTests = 2000;
		long totalTime = 0;
		int[] test = null;

		for( int i = 0; i<5; i++ ) { // mean of 5 different arrays
			test = getRandoms(arraySize, arrayRange, 1);
			long startTime = System.nanoTime();
			// run many tests because of the short time for each
			switch( algVersion ) {
				case 1:
					for( int j = 0; j < nbrOfTests; j++ ) { 
						MaxSum.maxSubSum1( test ); // ignore return value
					}
				break;
				case 2:
					for( int j = 0; j < nbrOfTests; j++ ) {
						MaxSum.maxSubSum2( test );
					}
				break;
				case 3:
					for( int j = 0; j < nbrOfTests; j++ ) {
						MaxSum.maxSubSum3( test );
					}
				break;
				default: 
					throw new IllegalArgumentException("Wrong algVersion number");
			}
			long endTime = System.nanoTime( );
			long time = endTime - startTime;
			totalTime = totalTime + time;
		}
		return totalTime; // time per 10.000 calls to MaxSumSum in nanoseconds
	} 

	/**
	* Simple test program.
	*/
	public static void main( String [ ] args ) {
		// simple correctness test
		int a[] = { 4, -3, 5, -2, -1, 2, 6, -2 };
		int maxSum;
		maxSum = MaxSum.maxSubSum1( a );
		System.out.println( "#1 Max sum is " + maxSum + "; it goes"
			+ " from " + MaxSum.seqStart + " to " + MaxSum.seqEnd );
		maxSum = MaxSum.maxSubSum2( a );
		System.out.println( "#2 Max sum is " + maxSum + "; it goes"
			+ " from " + MaxSum.seqStart + " to " + MaxSum.seqEnd );
		maxSum = MaxSum.maxSubSum3( a );
		System.out.println( "#3 Max sum is " + maxSum + "; it goes"
			+ " from " + MaxSum.seqStart + " to " + MaxSum.seqEnd );

		// tabulate timing info
		// headline
		System.out.println(">>>Time is seconds per 10.000 calls to MaxSubSum");
		System.out.print("Size of Array ->");
		for( int arraySize = 50; arraySize <= 500000; arraySize *= 2 ) {
			System.out.printf("%11s", arraySize);
			
		}
		System.out.println();
		// end headline
		for( int alg = 1; alg <=3; alg++ ) {
			System.out.print("algo  #" + alg +"        ");
			for( int arraySize = 50; arraySize <= 500000; arraySize *= 2 ) { // double the size every time
				// eventually skip this array size, too slow
				if( (alg == 1 && arraySize > 1000) ||  (alg == 2 && arraySize > 20000)) {	// Changed by Sebastian Ljunggren
					System.out.printf("%11s" , "-");
					continue;
				}
				long time = getTimingInfo( arraySize, 100, alg );
				System.out.printf( "%10.3fs", (double)time/1.0E09); 
				//System.out.printf( "%10.2es", (double)time/1.0E09 );
			}
			System.out.println();
		}
		System.out.println();
	}
}