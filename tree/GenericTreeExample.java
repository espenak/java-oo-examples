package stuff;
import java.util.Collection;
import java.util.ArrayList;

class GenericTree<V extends Comparable<V>> {
    protected Node root;
    protected int size;

    public class Node {
        public V value;
        public Node left, right;

        public Node(V value) {
            this.value = value;
        }
    }

    public GenericTree() {
    }

    public GenericTree(Collection<V> values) {
        for (V value : values)
            add(value);
    }

    protected Node add(V value, Node cur) {
        if (cur == null) {
            size++;
            return new Node(value);
        }

        if (value.compareTo(cur.value) == 0)
            ;
        else if (value.compareTo(cur.value) < 0)
            cur.left = add(value, cur.left);
        else
            cur.right = add(value, cur.right);
        return cur;
    }

    public int size() {
        return size;
    }

    public void add(V value) {
        if (value == null) {
            throw new NullPointerException(
                    "This tree does not support null elements.");
        }
        root = add(value, root);
    }

    private void print(Node n) {
        if (n == null)
            return;
        print(n.left);
        System.out.println(n.value);
        print(n.right);
    }

    public void print() {
        print(root);
    }
}


public class GenericTreeExample {
    public static void main(String[] args) {
        GenericTree<Integer> t = new GenericTree<Integer>();
        t.add(5);
        t.add(2);
        t.add(3);
        t.add(9);
        t.add(1);
        t.print();
    }
}
