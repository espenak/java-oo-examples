class Person implements Comparable<Person> {
    String name;
    Integer score;

    Person(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int compareTo(Person other) {
        if(this.score < other.score) {
            return -1;
        } else if(this.score == other.score) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString() {
        return name + ":" + score;
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

    protected void add(V value, Node cur) {
        if (value.compareTo(cur.value) < 0) {
            if(cur.left == null) {
                cur.left = new Node(value);
            } else {
                add(value, cur.left);
            }
        }
        else if (value.compareTo(cur.value) > 0) {
            if(cur.right == null) {
                cur.right = new Node(value);
            } else {
                add(value, cur.right);
            }
        }
        else {
            throw new RuntimeException("Duplicates not supported!");
        }
    }

    public void add(V value) {
        if (value == null) {
            throw new NullPointerException(
                    "This tree does not support null elements.");
        }
        if(root == null) {
            root = new Node(value);
        } else {
            add(value, root);
        }
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


        GenericTree<Person> p = new GenericTree<Person>();
        p.add(new Person("Superman", 10));
        p.add(new Person("Batman", 11));
        p.add(new Person("Michael Jackson", 100000));
        p.print();
    }
}
