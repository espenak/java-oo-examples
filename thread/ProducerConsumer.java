package threads;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock; 

/**
 * @autor Jon Petter Åsen
 * http://en.wikipedia.org/wiki/Producer-consumer_problem (18.05.2010)
 */
public class ProducerConsumer {

    private Stack<Thread> threads;

    public ProducerConsumer(
            Buffer<Integer> storage,
            int numberOfProducers,
            int numberOfConsumers,
            int numberOfItemsPerProducer) {

        threads = new Stack<Thread>();

        int numberOfThreads = numberOfProducers + numberOfConsumers;
        
        for (int i = 0; i < numberOfThreads; i++) {

            Runnable runner;
            String threadName;

            if (i < numberOfProducers) {
                runner = new Producer(numberOfItemsPerProducer, storage);
                threadName = "Producer: " + (i + 1);
            } else {
                runner = new Consumer(storage);
                threadName = "Consumer: " + (i + 1 - numberOfProducers);
            }
            
            Thread thread = new Thread(runner, threadName);
            threads.push(thread);
            thread.start();
        }

        new Scanner(System.in).nextLine();

        for (int i = 0; i < numberOfThreads; i++) {
            threads.pop().interrupt();
        }
    }

    public static void main(String[] args) {
        //Buffer<Integer> storage = new Buffer<Integer>(2);
        Buffer<Integer> storage = new ImprovedBuffer<Integer>(2);
        //Buffer<Integer> storage = new DoubleConditionBuffer<Integer>(storageLimit);

        //new ProducerConsumer(1, 8, 4, 2);
        new ProducerConsumer(storage, 8, 1, 4);
        //new ProducerConsumer(4, 4, 4, 2);
    }
}

class Producer implements Runnable {

    protected int numberOfItems;
    protected Buffer<Integer> storage;

    public Producer(int numberOfItems, Buffer<Integer> storage) {
        this.numberOfItems = numberOfItems;
        this.storage = storage;
    }

    public void run() {
        
        for (int i = 0; i < numberOfItems; i++) {
            try {
                storage.add(new Integer(1));

                System.out.println(Thread.currentThread().getName()
                        + " produced nr " + (i+1));

            } catch (InterruptedException e) {

                System.out.println(Thread.currentThread().getName()
                        + " was interrupted. Exit thread.");
                return;
            }
        }
    }
}

class Consumer implements Runnable {

    protected Buffer<Integer> storage;

    public Consumer(Buffer<Integer> storage) {
        this.storage = storage;
    }

    public void run() {

        while(true) {
            try {
                Integer item = storage.getNext();

                System.out.println(Thread.currentThread().getName()
                        + " consumed one");

            } catch (InterruptedException e) {
                
                System.out.println(Thread.currentThread().getName()
                        + " was interrupted. Exit thread.");
                return;
            }
        }
    }
}

class Buffer<I> {

    protected int limit;
    protected LinkedList<I> buffer;

    public Buffer(int limit) {

        this.limit = limit;
        buffer = new LinkedList<I>();
    }

    synchronized void add(I item) throws InterruptedException {

        while (isFull()) {
            wait();
        }

        buffer.add(item);
        customNotify();
    }

    synchronized I getNext() throws InterruptedException {

        while(isEmpty()) {
            wait();
        }

        I item = buffer.removeFirst();
        customNotify();
        return item;
    }

    synchronized boolean isEmpty() {
        return buffer.size() == 0;
    }

    synchronized boolean isFull() {
        return buffer.size() >= limit;
    }

    synchronized void customNotify() {
        notify();
    }
}

class ImprovedBuffer<I> extends Buffer<I> {

    ImprovedBuffer(int limit) {
        super(limit);
    }

    synchronized void customNotify() {
        notifyAll();
    }   
}

class DoubleConditionBuffer<I> extends Buffer<I> {

    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    DoubleConditionBuffer(int limit) {
        super(limit);

        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    void add(I item) throws InterruptedException {

        lock.lock();

        try {

            while (isFull()) {
                notFull.await();
            }

            buffer.add(item);

            notEmpty.signal();

        } finally {
            lock.unlock();
        }
        
    }

    I getNext() throws InterruptedException {

        lock.lock();

        I item = null;

        try {

            while (isEmpty()) {
                notEmpty.await();
            }

            item = buffer.removeFirst();

            notFull.signal();

        } finally {
            lock.unlock();
        }
        
        return item;
    }
}