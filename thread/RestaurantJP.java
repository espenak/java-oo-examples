package threads;

public class RestaurantJP {
    int antBestilt;
    int antLaget = 0, antServert = 0; // tallerkenretter

    RestaurantJP(int ant) {
        antBestilt = ant;
    }

    public static void main(String[] args) {
        /**
         * Sett antall tallerkner, kokker og servitører
         **/
        int n = 9;
        int antallKokker = 6;
        int antallServitorer = 1;
        /***/
        Thread[] t = new Thread[antallKokker + antallServitorer];
        RestaurantJP rest = new RestaurantJP(n);

        for (int i = 1; i <= antallKokker; i++) {
            t[i - 1] = new KokkJP(rest, "Kokk " + i);
            t[i - 1].start();
        }
        /*try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }*/
        for (int i = 1; i <= antallServitorer; i++) {
            t[antallKokker + i - 1] = new ServitorJP(rest, "Servtør " + i);
            t[antallKokker + i - 1].start();
        }

        for (Thread tt : t) {
            try {
                tt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Alle tråder er avsluttet");
    }

    synchronized boolean kokkFerdig() {
        return antLaget == antBestilt;
    }

    synchronized boolean servitorFerdig() {
        return antServert == antBestilt;
    }

    synchronized void putTallerken() {
        // Kokketråden blir eier av låsen.

        //System.out.println(antLaget);

        while (antLaget - antServert >= 2) {
            /* så lenge det er minst 2 tallerkner
                * som ikke er servert, skal kokken vente. */
            try {
                System.out.println(Thread.currentThread().getName() + " la seg til å sove");
                wait(); /* Kokketråden gir fra seg */
                System.out.println(Thread.currentThread().getName() + " våkner opp");
                /*låsen og sover til den
                 * blir vekket */
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            // Kokketråden blir igjen eier av låsen.
        }

        //if (kokkFerdig()) return; // fiks

        if (!kokkFerdig()) {
            antLaget++;
            System.out.println(Thread.currentThread().getName()
                    + " laget nr " + antLaget);
        }
        notify(); /* Si ifra til servit{ø}ren. */
    }

    synchronized void getTallerken() {
        // Servitørtråden blir eier av låsen.


        while (antLaget == antServert) {
            /* så lenge kokken ikke har plassert
                * en ny tallerken. Dermed skal
                * servitøren vente. */
            try {
                wait(); /* Servitørtråden gir fra seg
			 * låsen og sover til den
			 * blir vekket */
            } catch (InterruptedException e) {
            }
            // Servitørtråden blir igjen eier av låsen.
        }

        //if (servitorFerdig()) return; // fiks

        if (!servitorFerdig()) {
            antServert++;
            System.out.println(Thread.currentThread().getName()
                    + " serverer nr " + antServert);
        }
        notify(); /* si ifra til kokken. */
    }
} // end class Restaurant0


class KokkJP extends Thread {
    RestaurantJP rest;

    KokkJP(RestaurantJP rest, String name) {
        super(name);
        this.rest = rest;
    }

    public void run() {
        while (!rest.kokkFerdig()) {
            rest.putTallerken();  // lever tallerken
            /*try {
                sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
            } */
        }
        // Kokken er ferdig
    }
}

class ServitorJP extends Thread {
    RestaurantJP rest;

    ServitorJP(RestaurantJP rest, String name) {
        super(name);
        this.rest = rest;
    }

    public void run() {
        while (!rest.servitorFerdig()) {
            rest.getTallerken(); /* hent tallerken og
			 * server */
            /*try {
                sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
            } */
        }
        // Servitøren er ferdig
    }
}

