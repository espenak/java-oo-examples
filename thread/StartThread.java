public class StartThread extends Thread implements Runnable {

    public StartThread(int forNothing) {
        // Her er det 2 og 3 som er hovedmåtene.
        // 4 og 5 er helt like. De er bare skrevet på en annen måte.

        // 1. Kjører arbeidet i main.
        work();

        // 2. Starter ny StartThread.
        new StartThread().start();

        // 3. Starter ny tråd med en ny StartThread som input.
        new Thread(new StartThread(), "Test").start();
        // Hvorfor fører denne linjen til at tråd nr 2 ikke forekommer i utskriften?

        // 4. Lager en anonym tråd ved å omdefinere Thread sin run-metoden
        new Thread() {
            public void run() {
                work();
            }
        }.start();

        // 5. Lager en anonym tråd med en anonym indre klasse av typen Runnable.
        new Thread(new Runnable() {
            public void run() {
                work();
            }
        }).start();

        // 6. Starter en tråd som er en inder klasse som sub-klasser Thread.
        new InternalThread().start();

        // 7. Starter en tråd som er en inder klasse som implementerer Runnable.
        new Thread(new InternalRunnable()).start();

        // 8. ?
    }

    public StartThread() {
        
    }

    @Override
    public void run() {
        work();
    }

    private class InternalThread extends Thread {
        @Override
        public void run() {
            work();
        }
    }

    private class InternalRunnable implements Runnable {
        public void run() {
            work();
        }
    }

    public void work() {
        Thread t = Thread.currentThread();
        int res = 0;
        System.out.println(t.getName() + ". Work starting now.");
        for (int i = 0; i < 10000; i++) {
            res += i * i;

            if (i == 5000) try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
		    System.out.println("Ble interrupted");
            }
        }
        System.out.println(t.getName() + ". End of work.");
    }

    public static void main(String[] args) {
        System.out.println("Start of main");
        new StartThread(-1);
        System.out.println("End of main");
    }
}

class ThreadEgenKlasse extends Thread implements Runnable {
    StartThread st;

    ThreadEgenKlasse(StartThread st) {
        this.st = st;
    }

    @Override
    public void run() {
        st.work();
    }
}
