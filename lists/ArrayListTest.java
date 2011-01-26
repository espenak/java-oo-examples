class Person {
    String name;
	int score;

    Person(String name, int score) {
        this.name = name;
		this.score = score;
    }
}



class ArrayList {
}


public class ArrayListTest {
    ArrayList a = new ArrayList();

    public static void main(String[] args) {
        ArrayListTest t = new ArrayListTest();
        t.run();
    }

    void run() {
        a.add(new Person("Tor Ivar", 100));
    }
}
