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
	}

	void remove(String name) {
		// How?
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
        for(Person p : people) {
            // Hva mangler her?
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
        a.add(new Person("Tor Ivar", 100));
        a.add(new Person("Espen", 100));
        a.print();

        // Make sure it worked!
        testContains("Tor Ivar");
        testContains("Espen");

        // Read input from user until the user enters a blank line
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String name = s.nextLine();
            if(name.equals(""))
                break;
            a.add(new Person(name, 10));
            System.out.println("New person added: " + name);
        }

        // Print current state of the arraylist
        System.out.println("Current state:");
        a.print();
    }

    void testContains(String name) {
        if (!a.contains(name)) {
            System.out.println("ERROR: " + name + " not found");
            System.exit(1); // Avslutt hele programmet
        }
    }
}
