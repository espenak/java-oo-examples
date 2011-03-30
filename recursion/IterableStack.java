import java.util.Iterator;
import java.util.NoSuchElementException;


public class IterableStack<E extends Comparable<E>> extends Stack<E>
        implements Iterable<E> {

    public static void main(String [] args) {
        IterableStack<String> s = new IterableStack<String>();
        s.add("Catwoman");
        s.add("Superman");
        s.add("Spiderman");
        for(String name: s) {
            System.out.println(name);
        }
    }

    public Iterator<E> iterator() {
        return new StackIterator(top);
    }

    /**
     * A iterator over the stack.
     */
    class StackIterator implements Iterator<E> {
        protected StackNode<E> current;

        public StackIterator(StackNode<E> top) {
            current = top;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            StackNode<E> n = current;
            current = n.below;
            return n.value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }
}
