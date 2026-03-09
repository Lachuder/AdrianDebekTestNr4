package zadanie1;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringContainer implements Iterable<String> {

    private final String providedPattern;
    private Node first;
    private Node last;
    private int size = 0;
    private boolean duplicatesNotAllowed;

    public StringContainer(String providedPattern) {
        this.providedPattern = providedPattern;
    }

    public StringContainer(String providedPattern, boolean duplicatesNotAllowed) {
        this.providedPattern = providedPattern;
        this.duplicatesNotAllowed = duplicatesNotAllowed;
    }

    public void add(String stringToBeAdded) {
        if (!checkPattern(stringToBeAdded)) {
            throw new InvalidStringContainerValueException("Bad Value");
        }
        Node newNode = new Node(stringToBeAdded); //tworzymy nowy obiekt węzła z wartością podaną w argumencie
        if (first == null) { //jeżeli jeszcze nic nie ma i pierwszy element jest pusty
            first = newNode; //to pierwszym ustawiamy nowo stworzony węzeł
            last = newNode;
        } else { //w przypadku obecności pierwszego elementu
            if (duplicatesNotAllowed && isDuplicate(stringToBeAdded)) { //sprawdzamy, czy jest zgodny z zadanym filtrem
                throw new DuplicatedElementOnListException("Duplicate not allowed");
            }
            Node temp = first; //przypisujemy jego wartość do tymczasowej zmiennej lokalnej jako punkt startu
            while (temp.next != null) { //dopóki przypisany element ma jakiś następny
                temp = temp.next; //to nadpisujemy tym następnym ten tymczasowy
            }
            temp.next = newNode; //a jak w końcu trafimy na nulla w miejscu następcy ostatniego elementu, to wstawimy naszego Noda
            last = newNode;
        }
        newNode.index = size;
        size++;
    }

    private boolean checkPattern(String toBeChecked) {
        Pattern pattern = Pattern.compile(providedPattern);
        Matcher matcher = pattern.matcher(toBeChecked);
        if (!matcher.matches()) {
            throw new InvalidStringContainerPatternException("Bad Pattern");
        } else {
            return matcher.matches();
        }
    }

    private boolean isDuplicate(String toBeChecked) {
        Node temp = first;

        while (temp != null) {
            if (temp.value.equals(toBeChecked)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public String get(int index) {
        if (index > size) {
            throw new RuntimeException("Index above max");
        }

        int counter = 0;
        Node temp = first;
        while (counter < index) {
            temp = temp.next;
            counter++;
        }
        return temp.value;
    }

    public StringContainer getDataBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {

        StringContainer result = new StringContainer(this.providedPattern);

        Node temp = first;

        while (temp != null) {

            boolean fromOrNull = dateFrom == null || !temp.dateOfCreation.isBefore(dateFrom);
            boolean beforeOrNull = dateTo == null || !temp.dateOfCreation.isAfter(dateTo);

            if (fromOrNull && beforeOrNull) {
                result.add(temp.value);
            }
            temp = temp.next;
        }

        return result;
    }

    public void remove(int index) {
        if (index > size) {
            throw new RuntimeException("Index above max");
        }

        if (index == 0) {
            first = first.next;
        } else {
            Node current = first;
            int counter = 0;
            while (counter < index - 1) {
                current = current.next;
                counter++;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public void remove(String object) {
        Iterator<String> iter = iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            if (next.equals(object)) {
                iter.remove();
            }
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new StringIterator();
    }

    private class StringIterator implements Iterator<String> {

        Node current = first;
        Node previous = null;
        Node lastReturned = null;
        Node lastReturnedPrevious = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {

            if (!hasNext()) {
                throw new RuntimeException("empty");
            }

            lastReturned = current;
            lastReturnedPrevious = previous;

            String result = current.value;

            previous = current;
            current = current.next;

            return result;
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
            size--;
        }
    }

    static class Node {

        String value;
        Node next;
        int index;
        LocalDateTime dateOfCreation;

        public Node(String value) {
            this.value = value;
            this.dateOfCreation = LocalDateTime.now();
        }
    }

}

