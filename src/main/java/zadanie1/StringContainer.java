package zadanie1;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringContainer  {

    private String text;
    private Pattern pattern;
    private Node first;
//    private Node last;
    private int size = 0;
    private boolean duplicatesNotAllowed;

    public StringContainer(String text) {
        this.text = text;
    }

    public StringContainer(String text, boolean duplicatesNotAllowed) {
        this.text = text;
        this.duplicatesNotAllowed = duplicatesNotAllowed;
    }

    public void add(String stringToBeAdded) {
        if (!checkPattern(stringToBeAdded)) {
            throw new InvalidStringContainerValueException("Bad Value");
        }
        Node newNode = new Node(stringToBeAdded); //tworzymy nowy obiekt węzła z wartością podaną w argumencie
        if (first == null) { //jeżeli jeszcze nic nie ma i pierwszy element jest pusty
            first = newNode; //to pierwszym ustawiamy nowo stworzony węzeł
        } else { //w przypadku obecności pierwszego elementu
            if (duplicatesNotAllowed && isDuplicate(stringToBeAdded)) {
                throw new DuplicatedElementOnListException("Duplicate not allowed");
            }
            Node temp = first; //przypisujemy jego wartość do tymczasowej zmiennej lokalnej jako punkt startu
            while (temp.next != null) { //dopóki przypisany element ma jakiś następny
                temp = temp.next; //to nadpisujemy tym następnym ten tymczasowy
            }
            temp.next = newNode; //a jak w końcu trafimy na nulla w miejscu następcy ostatniego elementu, to wstawimy naszego Noda
        }
        size++;
    }

    private boolean checkPattern(String toBeChecked) {
        Pattern pattern = Pattern.compile(text);
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

//    @Override
//    public Iterator<String> iterator() {
//        return new StringIterator();
//    }
//
//    private class StringIterator implements Iterator<String> {
//
//        Node current = first;
//        Node previous = null;
//        Node lastReturned = null;
//        Node lastReturnedPrevious = null;
//
//        @Override
//        public boolean hasNext() {
//            return current != null;
//        }
//
//        @Override
//        public String next() {
//
//            if (!hasNext()) {
//                throw new RuntimeException("empty");
//            }
//
//            lastReturned = current;
//            lastReturnedPrevious = previous;
//
//            String result = current.value;
//
//            previous = current;
//            current = current.next;
//
//            return result;
//        }
//
//        @Override
//        public void remove() {
//            if (lastReturned == null) {
//                throw new IllegalStateException();
//            }
//
//            if (lastReturnedPrevious == null) {
//                first = first.next;
//                if (first == null) {
//                    last = null;
//                }
//            } else {
//                lastReturnedPrevious.next = lastReturned.next;
//                if (lastReturned == last) {
//                    last = lastReturnedPrevious;
//                }
//            }
//            previous = lastReturnedPrevious;
//            lastReturned = null;
//        }
//    }

    static class Node {

        String value;
        Node next;

        public Node(String value) {
            this.value = value;
        }
    }

}

