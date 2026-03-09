package zadanie2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ObjectContainer<T> implements Iterable<T> {

    private final Predicate<T> predicate;

    public ObjectContainer(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    private Node first;
    private Node last;

    public void add(T object) {
        if (object == null) {
            throw new NullPointerException("Element can not be null");
        }
        if (!predicate.test(object)) {
            throw new RuntimeException("City not correct");
        }

            Node temp = last;
            Node newNode = new Node(object, null);
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

            lastReturned = current; //po zakończeniu metody obecnie zwracany element stanie się już ostatnio zwróconym
            //Iterator.remove() usuwa ostatni element zwrócony przez next(), czyli musimy określić, co to za element - ten będziemy usuwać
            lastReturnedPrevious = previous; //po zakończeniu metody element aktualnie poprzedzający stanie się poprzedzającym
            //ostatnio zwróconego, czyli przesuniemy go o jeszcze jeden do tyłu

            T object = current.object; //zapisujemy do zmiennej aktualny obiekt/węzeł listy do zwrócenia, ponieważ za chwilę
            //nadpiszemy zawartość current

            previous = current; //dla kolejnego wywołania metody od razu wskazujemy, że obecny element stanie się poprzednim
            current = current.next; //a nowym aktualnym będzie kolejny/następca po obecnym, co zostanie odczytane przy kolejnym wywołaniu

            return object;
        }

        @Override
        public void remove() {
            if (lastReturned == null) { //sprawdza, czy była wcześniej wywołana metoda next(), ponieważ lastReturned wynika z jej przebiegu
                throw new IllegalStateException();
            }

            if (lastReturnedPrevious == null) { //sprawdzamy, czy element nie jest pierwszym, czyli niemiejącym poprzednika
                first = first.next; //i jeżeli jest pierwszym to usuwamy/nadpisujemy go od razu kolejnym
                if (first == null) { //jeżeli po nadpisaniu okaże się być pustym, bo w ogóle był tylko jeden element
                    last = null; // to ostatni też jest pusty, tak jak i cała lista po usunięciu jednego elementu
                }
            } else {
                lastReturnedPrevious.next = lastReturned.next; //a jeżeli elementów jest więcej, to kolejnym dla poprzednika z ostatnio
                //zwróconego elementu będzie następca aktualnie usuwanego, czyli zastąpimy drugi trzecim
                if (lastReturned == last) { //ten warunek wynika z cechy nadpisanej przy dodawaniu nowego/ostatniego Noda
                    last = lastReturnedPrevious; //to jeżeli ostatni jest do usunięcia, to nowym ostatni będzie poprzedni usuwanego
                }
            }

            previous = lastReturnedPrevious; //tutaj musimy jeszcze nadpisać pozostałe poprzednie o jeden do tyłu
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
