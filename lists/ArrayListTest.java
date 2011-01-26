import java.util.Scanner;

class Person {
    String name;
	int score;

    Person(String name, int score) {
        this.name = name;
		this.score = score;
    }
}


class ArrayList {
    Person[] people = new Person[1000];
    int where = 0;

	void add(Person p) {
		people[where] = p;
		where ++;
        // Try to extend it to handle more than 1000 users!
	}

	void remove(String name) {
		// How do we do this? Try at home:)
	}

    boolean contains(String name) {
        for(Person p: people) {
            if (p == null)
                break;
            if(p.name.equals(name))
                return true;
        }
        return false;
    }

    void print() {
        // Try alternative loops at home. Can you make it even more simple?
        for(Person p : people) {
            if (p == null)
                break;
            System.out.println(p.name + " " + p.score);
        }
    }
}


public class ArrayListTest {
    ArrayList a = new ArrayList();

    public static void main(String[] args) {
        ArrayListTest t = new ArrayListTest();
        t.run();
    }

    void run() {
        // Add some people
        a.add(new Person("Tor Ivar", 100));
        a.add(new Person("Espen", 100));

        // Make sure it worked! (like we did in the "plenumstime")
        testContains("Tor Ivar");
        testContains("Espen");

        // Make sure it worked! (a better way that we did not explain)
        assertTrue(a.contains("Tor Ivar"), "Tor Ivar was not found.");
        assertTrue(a.contains("Espen"), "Espen was not found.");
        assertTrue(!a.contains("Michael Jackson"),
                "The list should not contain Michel Jackson!");

        // It works! Lets use it! Note that a "real" program would only run the
        // tests during development, not in the start of the program.
        exampleUsageWithScanner();
    }

    /** Read input from user until the user enters a blank line. */
    void exampleUsageWithScanner() {
        System.out.println("Current state:");
        a.print(); // Print current state of the arraylist

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String name = s.nextLine();
            if(name.equals("")) // Quit when a user enters a empty line
                break;
            a.add(new Person(name, 10));
            System.out.println("New person added: " + name);
        }

        System.out.println("Current state:");
        a.print(); // Print current state of the arraylist
    }

    /** Test that the datastructure contains a given name, and exit if it does
     * not. */
    void testContains(String name) {
        if (!a.contains(name)) {
            System.out.println("ERROR: " + name + " not found");
            System.exit(1); // Quit the entire program
        }
    }

    /** A more general approach to testing. We take a boolean expression as
     * input, and fails with the given message if it is false. */
    void assertTrue(boolean resultOk, String message) {
        if (!resultOk) {
            System.out.println("ERROR: " + message);
            System.exit(1); // Quit the entire program
        }
    }
}
