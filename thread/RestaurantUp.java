package threads;

/* Løsningsforslag oppgave 2 og 3, kapittel 17.
 * Lagres på fil Restaurant.java
 * 
 * Feilen oppstår i den ene kokken har sjekket at fler tallerkner skal
 * leveres og går inn i while løkken. Før kallet på rest.putTallerken
 * er det ingen lås, og den andre kokken rekker å komme inn i sin
 * while-løkke. Dermed vil begge kokkene befinne seg i while-løkka og
 * det blir produsert minst en tallerken for mye. Det hjelper med
 * andre ord ikke at kokkFerdig-metoden er synkronisert, så lenge
 * kokken slipper låsen før kallet på putTallerken-metoden.
 *
 * En mulig løsning er å legge kallet på kokkFerdig-metoden i
 * putTallerken-metoden. Hvis vi i tillegg lar putTallerken-metoden
 * returnere svaret fra kokkFerdig-metoden kan vi bruke denne som
 * betingelsen i while-løkka.
 */


public class RestaurantUp {
    int antBestilt;
    int antLaget = 0, antServert = 0; // tallerkenrertter
    int antKokker = 6, antServitorer = 1;

    RestaurantUp(int ant) {
        antBestilt = ant;
        for (int i = 0; i < antKokker; i++) {
            KokkUp k = new KokkUp(this, "Kokk nr. " + (i + 1));
            k.start();
        }

        for (int i = 0; i < antServitorer; i++) {
            ServitorUp s = new ServitorUp(this, "Servitør nr. " + (i + 1));
            s.start();
        }

    }

    public static void main(String[] args) {
        if (args.length > 0)
            new RestaurantUp(Integer.parseInt(args[0]));
        else
            new RestaurantUp(9);
    }

    synchronized boolean kokkFerdig() {
        return antLaget == antBestilt;
    }

    synchronized boolean servitorFerdig() {
        return antServert == antBestilt;
    }

    synchronized boolean putTallerken(KokkUp k) {
        // Kokketråden blir eier av låsen.

        while (antLaget - antServert > 2) {
            /* så lenge det er minst 2 tallerkner
                * som ikke er servert, skal kokken vente. */
            try {
                System.out.println(Thread.currentThread().getName() + " legger seg til å sove.");
                wait(); /* Kokketråden gir fra seg
				 * låsen og sover til den 
				 * blir vekket */
                System.out.println(Thread.currentThread().getName() + " våkner opp.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Kokketråden blir igjen eier av låsen

        }

        boolean ferdig = kokkFerdig();
        if (!ferdig) {
            antLaget++;
            System.out.println(k.getName() + " laget nr: " + antLaget);
        }

        notify(); /* Si ifra til servitøren. */

        return !ferdig;
    }

    synchronized boolean getTallerken(ServitorUp s) {
        // Servitørtråden blir eier av låsen.

        while (antLaget == antServert && !servitorFerdig()) {
            /* så lenge kokken ikke har plassert
                * en ny tallerken. Dermed skal
                * servitøren vente. */
            try {
                System.out.println(Thread.currentThread().getName() + " legger seg til å sove.");
                wait(); /* Servitørtråden gir fra seg
				 * låsen og sover til den
				 * blir vekket */
                System.out.println(Thread.currentThread().getName() + " våkner opp.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Servitørtråden blir igjen eier av låsen.
        }

        boolean ferdig = servitorFerdig();
        if (!ferdig) {
            antServert++;
            System.out.println(s.getName() + " serverer nr: " + antServert);
        }


        notify(); /* si ifra til kokken */
        return !ferdig;
    }
}

class KokkUp extends Thread {
    RestaurantUp rest;

    KokkUp(RestaurantUp rest, String navn) {
        super(navn); // Denne tråden heter nå
        this.rest = rest;
    }

    public void run() {
        while (rest.putTallerken(this)) {
            // levert tallerken.

            /*try {
                   sleep((long) (1000 * Math.random()));
               } catch (InterruptedException e) {}*/
        }
        // Kokken er ferdig
    }
}

class ServitorUp extends Thread {
    RestaurantUp rest;

    ServitorUp(RestaurantUp rest, String navn) {
        super(navn); // Denne tråden heter nå
        this.rest = rest;
    }

    public void run() {
        while (rest.getTallerken(this)) {
            /*try {
                   sleep((long) (1000 * Math.random()));
               } catch (InterruptedException e) {}*/
        }
        // Servitøren er ferdig

    }
}



