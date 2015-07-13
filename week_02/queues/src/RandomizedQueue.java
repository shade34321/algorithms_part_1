/**
 * Created by shade on 7/12/2015.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    //find bugs complained that this wasn't static....
    private static final int START_SIZE = 10;
    private Item[] rq;
    private int size;

    public RandomizedQueue() {
        rq = (Item[]) new Object[START_SIZE];
        size = 0;
    }

    private void resize(int newSize){
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0;i < size;i++) {
            temp[i] = rq[i];
        }

        rq = temp;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (size == rq.length) {
            resize(2*rq.length);
        }
        rq[size++] = item;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new UnsupportedOperationException();
        }

        int rand_element = 0;

        do {
            rand_element = StdRandom.uniform(rq.length);
        } while (rq[rand_element] == null);

        Item element = rq[rand_element];
        rq[rand_element] = null;
        size--;

        return element;
    }

    public Item sample() {
        if (size == 0) {
            throw new UnsupportedOperationException();
        }

        Item temp = null;

        return temp;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] iterable;
        private int elements_left;

        public RandomizedQueueIterator() {
            iterable = (Item[]) new Object[size];
            elements_left = size;
            initArray();
        }

        private void initArray() {
            int j = 0;

            for (int i = 0; i < rq.length;i++) {
                if (rq[i] != null  && j != size) {
                    iterable[j++] = rq[i];
                }
            }
        }

        public boolean hasNext() {
            return elements_left != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int rand_element;

            do {
                rand_element = StdRandom.uniform(iterable.length);
            } while (iterable[rand_element] == null);

            Item element = iterable[rand_element];
            iterable[rand_element] = null;
            elements_left--;

            return element;
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        int x;

        try {
            x = rq.sample();
        } catch (Exception e) {
            System.out.println("sample exception thrown");
        }

        try {
            x = rq.dequeue();
        } catch (Exception e) {
            System.out.println("dequeue exception thrown");
        }

        try {
            rq.enqueue(null);
        } catch (Exception e){
            System.out.println("Can't enqueue null");
        }

        System.out.println("RQ is empty: " + rq.isEmpty());

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);

        System.out.println("RQ is not empty: " + rq.isEmpty());

        System.out.println("size should be 10: " + rq.size());

        x = rq.dequeue().intValue();
        System.out.println("Printed dequeue: " + x);
        x = rq.dequeue().intValue();
        System.out.println("Printed dequeue: " + x);
        x = rq.dequeue().intValue();
        System.out.println("Printed dequeue: " + x);

        System.out.println("size should be 7: " + rq.size());

        Iterator iter_1 = rq.iterator();
        Iterator iter_2 = rq.iterator();

        try {
            iter_1.remove();
        } catch (Exception e){
            System.out.println("Can't remove from iterator");
        }

        System.out.println("Iter_1:");
        while (iter_1.hasNext()) {
            System.out.print(iter_1.next() + " ");
        }

        System.out.println("Iter_2:");
        while (iter_2.hasNext()) {
            System.out.print(iter_2.next() + " ");
        }
    }
}
