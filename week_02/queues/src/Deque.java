/**
 * Created by shade on 7/12/2015.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }

    private Node<Item> first, last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        //Set up new node
        Node<Item> temp = new Node<>();
        temp.item = item;
        temp.next = null;
        temp.prev = null;

        //Add it into the Linked List
        if (size == 0) { //Linked List is Empty
            first = temp; // Check Style didn't like me doing it on one line?
            last = temp; //Therefore our first item is our last item
        } else {
            first.prev = temp;
            temp.next = first;
            first = temp;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null){
            throw new NullPointerException();
        }

        Node<Item> temp = new Node<>();
        temp.item = item;
        temp.prev = null;
        temp.next = null;

        if(size == 0){
            first = last = temp; //Therefore our first item is our last item
        } else {
            last.next = temp;
            temp.prev = last;
            last = temp;
        }

        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<Item> temp = first;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        size--;

        return temp.item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<Item> temp = last;

        if (size == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return temp.item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);

    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item temp = current.item;
            current = current.next;
            return temp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> DQ = new Deque<>();

        System.out.println("Size should be 0: " + DQ.size());
        System.out.println("Empty should be True: " + DQ.isEmpty());

        DQ.addFirst(1);
        DQ.addFirst(2);
        DQ.addFirst(3);
        DQ.addFirst(4);
        DQ.addFirst(5);

        // Dequeue should look like
        // 5 4 3 2 1

        System.out.println("Size should be 5: " + DQ.size()); //should contain 5
        System.out.println("Empty should be False: " + DQ.isEmpty());

        int temp = DQ.removeLast(); //remove 1
        System.out.println("Should have removed 1: " + temp);

        // Dequeue should look like
        // 5 4 3 2
        temp = DQ.removeFirst(); //remove 5
        temp = DQ.removeFirst(); //remove 4
        System.out.println("should have removed 4: " + temp);
        System.out.println("Size should be 2: " + DQ.size());

        // Dequeue should look like
        // 3 2
        temp = DQ.removeLast(); // should be 2
        temp = DQ.removeLast(); // should be 3
        System.out.println("Should have removed 3: " + temp);

        // Dequeue should be empty
        System.out.println("Size should be 0: " + DQ.size());
        System.out.println("Empty should be True: " + DQ.isEmpty());

        DQ.addFirst(1);
        DQ.addLast(2);
        System.out.println("Size should be 2: " + DQ.size());
        System.out.println("Empty should be False: " + DQ.isEmpty());

        // Dequeue should look like
        // 1 2

        DQ.addFirst(3);
        DQ.addLast(4);
        System.out.println("Size should be 4: " + DQ.size());
        System.out.println("Empty should be False: " + DQ.isEmpty());

        // Dequeue should look like
        // 3 1 2 4

        Iterator Itr = DQ.iterator();

        try {
            Itr.remove();
        } catch (Exception e) {
            System.out.println("Iterator remove exception thrown");
        }

        while(Itr.hasNext()) {
            System.out.println(Itr.next());
        }

        try {
            Itr.next();
        } catch (Exception e) {
            System.out.println("Itr.next() exception Thrown");
        }

        try {
            DQ.addFirst(null);
        } catch (Exception e) {
            System.out.println("Adding null first exception thrown.");
        }

        try {
            DQ.addLast(null);
        } catch (Exception e) {
            System.out.println("Adding null last exception thrown.");
        }

        try {
            for (int i = 0;i < 5;i++) {
                DQ.removeFirst();
            }
        } catch (Exception e) {
            System.out.println("removing first from empty exception thrown.");
        }

        try {
            DQ.removeLast();
        } catch (Exception e) {
            System.out.println("removing first from empty exception thrown.");
        }
    }
}
