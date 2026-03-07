package zadanie4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * klasa przechowująca kolejkę wiadomości*/
public class MessageQueue {

    /**
     * kolejka w bezpiecznej wielowątkowo implementacji (LinkedBlockingQueue - operacje mogą czekać) przechowująca
     * kolejno dodawane elementy/wiadomości*/
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    /**
     * metoda dodająca element/wiadomość do kolejki*/
    public void add(String message) {
        queue.add(message);
    }

    /**
     * metoda pobierająca najstarszy element/wiadomość z kolejki
     * rzuca wyjątek przerwania, gdy oczekiwanie na zasób zostanie przerwane*/
    public String take() throws InterruptedException {
        return queue.take();
    }




}
