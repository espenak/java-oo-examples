/**
 * A stack (LIFO) which only supports add and contains() operations.
 * Note that this is not <em>really</em> a stack because the add and remove
 * methods make a list into a stack, and we do not implement remove.
 *
 * @see -http://en.wikipedia.org/wiki/Stack_(data_structure)
 */
class Stack<E extends Comparable<E>> {

    /**
     * Points to the node at the top of the stack.
     */
    protected StackNode<E> top;

    /**
     * Add an element to the stack.
     *
     * @param e An element which is to be present in the stack after this
     *          operation.
     */
    public void add(E e) {
        StackNode<E> n = new StackNode<E>();
        n.value = e;
        n.below = top;
        top = n;
    }

    /**
     * Check if an element is present in the stack.
     *
     * @param e The element to check for.
     * @return <code>true</code> if the element is present in the stack.
     */
    public boolean contains(E e) {
        for (StackNode<E> n = top; n != null; n = n.below) {
            if (n.value.compareTo(e) == 0)
                return true;
        }
        return false;
    }

    /**
     * Stores information about a single item in the Stack.
     */
    public class StackNode<E extends Comparable<E>> {
        public StackNode<E> below;
        public E value;
    }
}


public class StackExample {

    public static void main (String[] args) {
	Stack<String> s = new Stack<String>();
	
	s.add("Katherine");
	s.add("Joseph");
	
	System.out.println(s.contains("Katherine"));
	
	System.out.println(s.contains("Jermaine"));
    }
}
