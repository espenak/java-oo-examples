package threads;

public class SemaphoreOppg {
    public static void main(String[] args) {
        final Semaphore lock = new Semaphore(-1);

        Thread t1 = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000); // sleep for 2 seconds
                }
                catch (InterruptedException e) {
                }
                System.out.println("finished " + Thread.currentThread());
                lock.release();
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            public void run() {
                System.out.println("finished " + Thread.currentThread());
                lock.release();
            }
        };
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //lock.acquire(); // wait for both threads to release the lock.
        // if the semaphore works correctly, this will be printed after both
        // threads are finished.
        System.out.println("Lock acquired.");
    }
}


class Semaphore {
    private int value;

    Semaphore(int value) {
        this.value = value;
    }

    /**
     * Increment value and notify acquire if value becomes positive.
     */
    synchronized void release() {
        value++;
        if (value > 0)
            notify();
    }


    /**
     * Wait for <tt>value</tt> to become positive.
     */
    synchronized void acquire() {
        while (value < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("[error] acquire was interrupted.");
            }
		}
	}
}
