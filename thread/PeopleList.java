package threads;

import java.util.LinkedList;

public class PeopleList {

    private LinkedList<String> people;

    public PeopleList() {
        people = new LinkedList<String>();
    }

    public void add(String name) {
        people.add(name);
    }

    public void remove(String name) {
        people.remove(name);
    }

    public String toString() {
        String r = "";
        for (String name : people) {
            r += ", " + name;
        }
        return r.substring(2); // remove first ", "
    }
}
