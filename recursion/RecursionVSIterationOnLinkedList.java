
public class RecursionVSIterationOnLinkedList extends IterableStack<Long> {

    public RecursionVSIterationOnLinkedList() {
        super();
    }

    public void recursion() {
        recursion(this.top);
    }

    private void recursion(StackNode n) {
        if (n == null) return;
        //System.out.println(n.value);
        recursion(n.below);
    }


    public void iteration() {
        for (Long i : this)
            ;//System.out.println(i);
        //for (Node n = top; n != null; n = n.below) ;
        }

    /**
     * You migth need to increase the stack size using
     * java -Xss50M RecursionVSIterationOnLinkedList
     */
    public static void main(String[] args) {
        RecursionVSIterationOnLinkedList rec = new RecursionVSIterationOnLinkedList();
        final long N = 1000000;

        //System.out.println(Integer.MAX_VALUE + " " + Long.MAX_VALUE);

        for (long i = 0; i < N; i++)
            rec.add(i);

        //for (int i = 0; i < N; i++) new Integer(i);

        Long t1 = System.currentTimeMillis();
        rec.iteration();
        Long t2 = System.currentTimeMillis();
        rec.recursion();
        Long t3 = System.currentTimeMillis();

        // report
        System.out.println("Time of iteration: " + (t2 - t1) + " ms");
        System.out.println("Time of recursion: " + (t3 - t2) + " ms");
	}
}
