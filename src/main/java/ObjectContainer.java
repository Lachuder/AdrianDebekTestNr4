import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ObjectContainer<T> implements Iterable<T> {

    private Predicate<T> predicate;

    public ObjectContainer(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    private Node first;
    private Node last;

    public void add(T i) {
        if (i == null) {
            throw new NullPointerException("Element can not be null");
        }
        if (!predicate.test(i)) {
            throw new RuntimeException("nietakiemiasto");
        }

            Node temp = last;
            Node newNode = new Node(i, null);
            last = newNode;
            if (temp == null) {
                first = newNode;
            } else {
                temp.next = newNode;
            }
    }

    public void removeIf(Predicate<T> p) {
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            T next = iter.next();
            if (p.test(next)) {
                iter.remove();
            }
        }
    }

    public List<T> getWithFilter(Predicate<T> p) {
        List<T> res = new ArrayList<>();
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            T next = iter.next();
            if (p.test(next)) {
                res.add(next);
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new ObjectIterator();
    }

    private class ObjectIterator implements Iterator<T> {

        Node current = first;
        Node previous = null;
        Node lastReturned = null;
        Node lastReturnedPrevious = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("empty");
            }

            lastReturnedPrevious = previous;
            lastReturned = current;

            T object = current.object;

            previous = current;
            current = current.next;

            return object;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            if (lastReturnedPrevious == null) {
                first = first.next;
                if (first == null) {
                    last = null;
                }
            } else {
                lastReturnedPrevious.next = lastReturned.next;
                if (lastReturned == last) {
                    last = lastReturnedPrevious;
                }
            }

            previous = lastReturnedPrevious;
            lastReturned = null;
        }
    }

    private class Node {

        T object;
        Node next;

        public Node(T object, Node next) {
            this.object = object;
            this.next = next;
        }
    }

}
