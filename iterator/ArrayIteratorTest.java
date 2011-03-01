import java.util.Iterator;

class ArrayExample<E extends Comparable<E>> {
    
    E[] elements;
    int index;

    @SuppressWarnings({"unchecked"})
    public ArrayExample() {
	elements = (E[]) new Comparable[16];
	index = 0;
    }
    
    public void add(E elem) {
	elements[index++] = elem;
    }

    public Iterator<E> iterator() {
	return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E>  {
	private int pos;
	private boolean nextIsRun = false;

	public ArrayIterator() {
	    pos = 0;
	}

	public boolean hasNext() {
	    return elements[pos] != null;
	}

	public E next() {
	    nextIsRun = true;
	    return elements[pos++];
	}

	public void remove() {
	    if (nextIsRun) {
		elements[pos-1] = null;
	    }
	    nextIsRun = false;
	}
    }
}



public class ArrayIteratorTest {

    public static void main(String[] args) {
	
	ArrayExample<String> ae = new ArrayExample<String>();

	ae.add("Tor Ivar");
	ae.add("Espen");
	ae.add("Magnus Christensen");
	ae.add("Deep Purple");

	Iterator it = ae.iterator();

	while(it.hasNext()) {
	    System.out.println(it.next());
	    it.remove();
	}


	it = ae.iterator();

	while(it.hasNext()) {
	    System.out.println(it.next());
	}


    }
}