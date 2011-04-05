package sorting;

import java.util.Collection;

public class TreeSort {

    public static void main(String[] args) {
        Integer[] in = {3, 2, 1, 5, 4};
        //treeSort(in);
        Integer[] result = treeSort(in);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static Integer[] treeSort(Integer[] in) {
        GenericTree<Integer> t = new GenericTree<Integer>();
        for (int i : in) {
            t.add(i);
        }
        return t.toArray(new Integer[in.length]);
    }
}

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
        for (V value : values) {
            add(value);
        }
    }

    protected Node add(V value, Node cur) {
        if (cur == null) {
            size++;
            return new Node(value);
        }

        if (value.compareTo(cur.value) == 0) ;
        else if (value.compareTo(cur.value) < 0) {
            cur.left = add(value, cur.left);
        } else {
            cur.right = add(value, cur.right);
        }
        return cur;
    }

    public void add(V value) {
        if (value == null) {
            throw new NullPointerException(
                    "This tree does not support null elements.");
        }
        root = add(value, root);
    }

    public int size() {
        return size;
    }

    private int toArray(V[] out, Node current, int i) {
        if (current == null) {
            return i;
        }
        i = toArray(out, current.left, i);
        out[i] = current.value;
        i = toArray(out, current.right, i + 1);
        return i;
    }

    public V[] toArray(V[] out) {
        toArray(out, root, 0);
        return out;
    }
}