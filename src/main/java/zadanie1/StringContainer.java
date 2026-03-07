package zadanie1;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringContainer {

    private String text;
    private Pattern pattern;
    private Node first;
    private Node last;
    private int size = 0;

    public StringContainer(String text) {
        this.text = text;
    }

    public void add(String stringToBeAdded) {
        if(!check(stringToBeAdded)) {
            throw new InvalidStringContainerValueException("Bad Value");
        } else if (duplicateNotAllowed) {
            throw new DuplicatedElementOnListException("Duplicate not allowed");
        } else {
            Node newNode = new Node(stringToBeAdded);
            if (first == null) {
                first = newNode;
            } else {
                Node current = first;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }

    private void addNode() {

    }

    private boolean check(String toBeChecked) {
        Pattern pattern = Pattern.compile(text);
        Matcher matcher = pattern.matcher(toBeChecked);
        if (!matcher.matches()) {
            throw new InvalidStringContainerPatternException("Bad Pattern");
        } else {
            return matcher.matches();
        }
    }

    public int size() {

    }

    public String get(int index) {

    }

    static class Node {

        String value;
        Node next;

        public Node(String value) {
            this.value = value;
        }
    }

}
