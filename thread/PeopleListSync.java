package threads;

import java.util.LinkedList;

public class PeopleListSync {

    private LinkedList<String> people;

    public PeopleListSync() {
        people = new LinkedList<String>();
    }

    public synchronized void add(String name) {
        people.add(name);
    }

    public synchronized void remove(String name) {
        people.remove(name);
    }

    public synchronized String toString() {
        String r = "";
        for (String name : people) {
            r += ", " + name;
        }
        return r.substring(2); // remove first ", "
    }
}