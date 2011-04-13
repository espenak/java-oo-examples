public class SimpleThread extends Thread {

    SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
            System.out.println(getName() + ": " + i);
    }

    public static void main(String[] args) {
        int n = 3; // test med ulikt antall tråder
        for (int i = 1; i <= n; i++)
            new SimpleThread("Thread " + i).start();
    }
}
