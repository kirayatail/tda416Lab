package edu.chl.sebljunwmax.exercise;

/**
 * Exercises from PDFs in Datastructures. Fishy class names due to lack of
 * imagination.
 * 
 * @author wmax
 * 
 */
public class Ex1 {

	/**
	 * Skriv en metod som givet ett argument av typen int[], ett heltal samt ett
	 * index placerar in heltalet på den position som anges av indexet samt
	 * flyttar alla element i fältet med samma eller högre index ett steg
	 * "uppåt". Det sista elementet i fältet försvinner ur fältet om antalet tal
	 * i fältet skulle bli för stort.
	 * 
	 * @param array
	 * @param data
	 * @param index
	 */
	public static void one(int[] array, int data, int index) {

		// Simpel for-loop som börjar bakifrån. Kanske inte så effektivt, men
		// detta är bara vecka ett.

		if (index < 0 || index >= array.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		for (int i = array.length - 1; i > index; i--) {
			array[i] = array[i - 1];
		}
		array[index] = data;
	}

	/**
	 * Fläta samman två strängar så varannan bokstav kommer från de två olika
	 * källorna. De bokstäver som blir över från den längsta källan läggs till
	 * på slutet utan att flätas med något. Exempel: anna + patrik = apnantarik
	 * 
	 * Detta är den rekursiva varianten 3 b
	 * 
	 * @param comp
	 *            Sammanflätade strängen så långt.
	 * @param a
	 *            Ännu icke sammanflätade delen ur källa a
	 * @param b
	 *            Ännu icke sammanflätade delen ur källa b
	 * @return comp om det inte finns någonting kvar att bearbeta i a och b.
	 *         Annars rekursivt anrop efter att en eller två bokstäver har lagts
	 *         till i comp
	 */
	public static String three(String comp, String a, String b) {
		if (a.equals("") && b.equals("")) {
			return comp;
		}

		if (a.length() > 0) {
			comp += a.substring(0, 1);
			a = a.substring(1);
		}

		if (b.length() > 0) {
			comp += b.substring(0, 1);
			b = b.substring(1);
		}

		return three(comp, a, b);
	}
	
	/**
	 * Binomialkoefficient. Skriv en rekursiv funktion...
	 * @param n
	 * @param k
	 * @return
	 */
	public static int six(int n, int k){
		if(k<0 || n < k){
			return 0;
		} else if(k == 0 || n == k){
			return 1;
		} else {
			return (six(n-1, k-1) + six(n-1, k));
		}
	}
}


