public class SimpleThreadImplements implements Runnable {
    String name;

    SimpleThread(String name) {
	this.name = name;

    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + ": " + i);
	}
    }

    public static void main(String[] args) {
        int n = 3; // test med ulikt antall tråder

        for (int i = 1; i <= n; i++) {
            new Thread(new SimpleThread("Thread" +i)).start();
	}

    }
}
