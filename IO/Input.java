package IO;

/*
1) Skriv om koden nedenfor slik at den leser fra tastatur istedenfor fra fil.
2) Modifiser koden videre slik at navnene samles i en string separert med ':',
   og skrives til skjerm når brukeren signaliserer at input er ferdig (ctrl-D).
3) Modifiser den originale koden slik at den skriver til filen "names2.txt"
   istedenfor til terminalen.
*/

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Input {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(new File("names.txt"));
            while (s.hasNextLine()) {
                String name = s.nextLine();
                System.out.println("Name=" + name);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
