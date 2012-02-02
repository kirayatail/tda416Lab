package testSortCol;

import java.util.*;
import static java.lang.Character.isLetter;

/**
 * En klass som endast innehåller en metod, </tt findRefs>,
 * som givet en text konstruerar en ordreferenslista.
 * 
 * @author (Bror Bjerner) 
 * @version (2007)
 */
public class Referenslista {


  /**
   *  En metod som givet en text konstruerar en ordreferenslista.
   *  För varje ord konstrueras en lista med radnummer för förekomster
   *  av ordet i den givna texten.
   *
   *  @param text Texten skall ges i form av en  </tt Scanner>.
   *  @param map En  </tt Map> som fylls i av metoden.
   */
  public static void findRefs( Scanner text, Map<String, List<Integer>> map) {
    int  radNo = 0;
    while ( text.hasNextLine()) {
      String rad  = text.nextLine();
      radNo++;
      int    pos  = 0;
      while ( pos < rad.length() ) {
	char c = rad.charAt(pos);
	if (isLetter(c)) {
	  int start = pos;
	  while (pos < rad.length() && isLetter(rad.charAt(pos)))
	    pos++;
          String ord = rad.substring(start,pos);
	  List<Integer> li = map.get(ord);
	  if ( li == null ) {
	      li = new ArrayList<Integer>();
              li.add(radNo);
              map.put(ord,li);
	  }
	  else
	      li.add(radNo);
	}
	pos++;
      }
    }
  }
} 

