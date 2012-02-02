package datastructures;

/**
 * The requirments to satisfy the requirements
 * of the abstract data type for stacks
 *
 * @author (Bror Bjerner) 
 * @version (2007)
 */
public interface Stacks<E>
{
    /**
     * The current number of elements in the stack
     * 
     * @return   the number of elements stored in the stack
     */
    public int size();

    /**
     * Test whether the stack is empty
     * 
     * @return	true if the stack is empty, false otehrwise
     */
    public boolean isEmpty();

    /**
     * Returns the elements in FILO ( LIFO )order
     * 
     * @return  the top element of the stack
     * @throws NoSuchElementException if stack is empty
     */
    public E top();

    /**
     * To add an object into the top of the stack
     * 
     * @param  element	the object to add to the stack
     */
    public void push(E element);

    /**
     * Remove the top element from the stack
     * 
     * @return  the removed object
     * @throws NoSuchElementException if stack is empty
     */
    public E pop();

}  // interface Stacks
