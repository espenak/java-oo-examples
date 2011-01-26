class SimplePerson {
    String name;
	int score;

    SimplePerson(String name, int score) {
        this.name = name;
		this.score = score;
    }
}


class SimpleArrayList {
    SimplePerson[] people = new SimplePerson[1000];
    int where = 0;

	void add(SimplePerson p) {
		people[where] = p;
		where ++;
	}

    void print() {
        // Try alternative loops at home. Can you make it even more simple?
        for(SimplePerson p : people) {
            if (p == null)
                break;
            System.out.println(p.name + " " + p.score);
        }
    }
}


public class SimpleArrayListTest {
    SimpleArrayList a = new SimpleArrayList();

    public static void main(String[] args) {
        SimpleArrayListTest t = new SimpleArrayListTest();
        t.run();
    }

    void run() {
        // Add some people
        a.add(new SimplePerson("Tor Ivar", 100));
        a.add(new SimplePerson("Espen", 100));
        a.print(); // Print them
        
        // See ArrayListTest.java for a more complete example.
    }
}
