package threads;

public class Restaurant {
    int antBestilt;
    int antLaget = 0, antServert = 0; // tallerkenretter

    Restaurant(int ant) {
        antBestilt = ant;
    }

    public static void main(String[] args) {
        /***/
        int n = 9;
        /***/
        Restaurant rest = new Restaurant(n);
        Kokk kokk = new Kokk(rest);
        kokk.start();
        Kokk kokk2 = new Kokk(rest);
        kokk2.start();
        Servitor servitor = new Servitor(rest);
        servitor.start();
    }

    synchronized boolean kokkFerdig() {
        return antLaget == antBestilt;
    }

    synchronized boolean servitorFerdig() {
        return antServert == antBestilt;
    }

    synchronized void putTallerken() {
        // Kokketråden blir eier av låsen.

        while (antLaget - antServert > 2) {
            /* så lenge det er minst 2 tallerkner
                * som ikke er servert, skal kokken vente. */
            try {
                wait(); /* Kokketråden gir fra seg
			 * låsen og sover til den
			 * blir vekket */
            } catch (InterruptedException e) {
            }
            // Kokketråden blir igjen eier av låsen.
        }
        antLaget++;
        System.out.println("Kokken laget nr " + antLaget);

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
        antServert++;
        System.out.println("Servitør serverer nr:" +
                antServert);
        notify(); /* si ifra til kokken. */
    }
} // end class Restaurant0


class Kokk extends Thread {
    Restaurant rest;

    Kokk(Restaurant rest) {
        this.rest = rest;
    }

    public void run() {
        while (!rest.kokkFerdig()) {
            rest.putTallerken();  // lever tallerken
            try {
                sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
            }
        }
        // Kokken er ferdig
    }
}

class Servitor extends Thread {
    Restaurant rest;

    Servitor(Restaurant rest) {
        this.rest = rest;
    }

    public void run() {
        while (!rest.servitorFerdig()) {
            rest.getTallerken(); /* hent tallerken og
			 * server */
            try {
                sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
            }
        }
        // Servitøren er ferdig
    }
}

