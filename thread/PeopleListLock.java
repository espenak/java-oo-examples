package threads;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class PeopleListLock {

    private LinkedList<String> people;
    private ReentrantLock lock;

    public PeopleListLock() {
        people = new LinkedList<String>();
        lock = new ReentrantLock();
    }

    public void add(String name) {
        lock.lock();
        people.add(name);
        lock.unlock();
    }

    public void remove(String name) {
        lock.lock();
        people.remove(name);
        lock.unlock();
    }

    public String toString() {
        String r = "";
        lock.lock();
        for (String name : people) {
            r += ", " + name;
        }
        lock.unlock();
        return r.substring(2); // remove first ", "
    }
}