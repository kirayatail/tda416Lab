package testSortCol;
import java.util.*;
import datastructures.*;

/**
 *  This is a <tt>Map</tt> that uses the <tt>CollectionWithGet</tt>
 *  given to the only constructor as a container for the 
 *  elements of type <tt>TestMapEntry</tt>.
 *   
 *  The method <tt>compareTo<tt> for  <tt>TestMapEntry</tt>
 *  compare the keys in the elements and increment a counter
 *  for the number of comparisions.
 *  Two element for which method compareTo
 *  gives 0 are not allowed.
 * 
 * @author (Bror Bjerner) 
 * @version (2008)
 */
public class TestMapWithCounter<K extends Comparable<? super K>,V>
       extends AbstractMap<K,V>  {

    // The set of the elements of type TestMapEntry
    private CollectionWithGet<TestMapEntry<K,V>> container;

    /**
     * The constructor of  the map. 
     * 
     * @param container The constructur must be provided an empty  
     *                  <tt>CollectionWithGet<tt>,
     *                  which the map vill use to store its key-value pairs.
     */
    public TestMapWithCounter( CollectionWithGet<TestMapEntry<K,V>> container) {
   	super();
	this.container = container;
    } // constructor TestMapWithCounter


    /**
     *  The class the map will use for its key-value entries.
     *  As a side-effect the  <tt>counter</tt> increases with
     *  one each time the <tt>compareTo</tt>-method is used.
     */
    public static class TestMapEntry<EK extends Comparable<? super EK>,EV> 
           implements Map.Entry<EK,EV>, Comparable<TestMapEntry<EK,EV>>  {

       // Counter for number of comparisions of TestMapEntry
       public  static int  counter;

        EK key; 
        EV value;

        TestMapEntry( EK key, EV value ) {
	        this.key = key;
	        this.value = value;
	    } //  constructor TestMapEntry 

        public boolean equals( TestMapEntry<EK,EV> bme ) {
		return ( key==null ? bme.key == null
                                   : key.equals(bme.key))  &&
		       ( value == null ? bme.value == null 
			               : value.equals(bme.value ));
        }  //  equals

        public EK getKey() {
            return key;
        } // getKey 

	    public EV getValue() {
            return value;
        } // getValue 

        public int hashCode() {
	    return key.hashCode();
	}

        public EV setValue( EV value ) {
	        EV temp = this.value;
            this.value  = value;
            return temp;
	    }

        public int compareTo( TestMapEntry<EK,EV> e ) {
            counter = counter + 1;
	    return key.compareTo( e.key );
        }
    } //  class TestMapEntry 

   /**
    * An iterator that travers throw the elements
    * in the same order as the given containers iterator.
    */
   public Iterator< ? extends Map.Entry<K,V>> iterator() {
        return container.iterator();
    }






    /** 
     * Returns a set view of the mappings contained in this map.
     *
     * @ returns a a set view of the mappings contained in this map.
     */
    public Set<Map.Entry<K,V>> entrySet() {
	return new TreeSet<Map.Entry<K,V>>(container);
    }

    /**
     *  Returns the value to which is mapped by the specified key.
     *
     * @param key the key to some value 
     * @return the value that corresponds to the key.
     */
    public V get(Object  key) {
        TestMapEntry<K,V> bme = container.get( new TestMapEntry<K,V>( (K) key, null ));
	return  bme == null ? null  : bme.value;
    } 

    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param key  key whose presence in this map is to be tested.
     * @return true if this map contains a mapping for the specified key.
     */
    public boolean containsKey( K key) {
	   return container.contains( 
			      new TestMapEntry<K,V>( key, null ));
    } 

    /**
     * Associates the specified value with the specified key in this map.
     * Two separate values are nor allowed. If the key allready has a value
     * the old value is returned.
     * @param key  key with which the specified value is to be associated.
     * @param value  value to be associated with the specified key.
     */
    public V put( K key, V value ) {
        TestMapEntry<K,V> bme = new TestMapEntry<K,V>(key, value );
        TestMapEntry<K,V> old = container.get(bme);

        if ( old == null ) {
            container.add( bme );
	    return null;
	}
        else
	    return old.setValue( value );
    } 

    /**
     *  Removes all mappings from this map
     */
    public void clear() {
	container.clear();
    } 


    /**
     * Removes the mapping for this key from this map if present.
     *
     * @param key - key whose mapping is to be removed from the map.
     *
     */
    public V remove(K key) {
	TestMapEntry<K,V> bme = container.get( new TestMapEntry<K,V>( key, null ));
	if ( bme != null ) {
	    container.remove(bme);
	    return bme.value;
	}
	else
	    return null;
    } 

    /**
     * Set this maps counter to 0.
     */ 
    public void resetCounter() {
        TestMapEntry.counter = 0;
    }  // counter

    /**
     * Get the value of the counter.
     */ 
    public int getCounter() {
	    return TestMapEntry.counter;
    } // getCounter
     
    
}  //  class TestMapWithCounter
