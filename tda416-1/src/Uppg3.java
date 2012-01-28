public class Uppg3 {

	/*
	 * a)
	 * 
	 * Algoritmerna tar emot en lista och undersöker alla sammanhängande
	 * delmängder efter den delmängd som ger störst summa. Algoritm 1 gör detta
	 * så grundligt att koden nästan blir svårläst eftersom den först hittar
	 * alla start- och slutpunkter innan en inre loop itererar mellan punkterna.
	 * Algoritm 2 känns mest naturlig eftersom man kan återanvända den
	 * föregående delmängdens summa så länge inte startpunkten förändras.
	 * Algoritm 3 är effektiv, men att avgöra om den gör rätt sak kräver lite
	 * eftertanke. Nyckeln i tredje algoritmen är att den aktuella delmängden
	 * [a, b] endast är bidragande till nästa delmängd [a, b+1] om summan av
	 * elementen [a, b] är större än noll, annars är det lika bra att börja om
	 * att räkna från [b+1, x]. Det som till en början verkar som en begränsning
	 * i tredje algoritmen visar sig vara en smart lösning. Listor som endast
	 * innehåller negativa tal kommer inte påverka slutresultatet, men i ett
	 * sådant fall är det den tomma delmängden (vars summa är noll) som är den
	 * största, alltså inga problem.
	 */

	/*
	 * b)
	 * 
	 * Handviftning: Det som samtliga metoder har gemensamt är att de gör
	 * operationer med konstant tidsåtgång inuti loopar med arrayens storlek i
	 * värsta fall. Därmed kan vi snabbt avgöra att variant 1 har O(n^3)
	 * eftersom tre loopar i värsta fall går igenom hela fältet. Enligt samma
	 * mönster gäller O(n^2) för variant 2 och O(n) för variant 3.
	 * 
	 * Se PDF för matematiskt korrekt komplexitet.
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
