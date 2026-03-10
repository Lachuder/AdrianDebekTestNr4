package zadanie4.models;

import zadanie4.appbody.ConnectionHolder;
import zadanie4.appbody.MessageQueue;
import zadanie4.appbody.Port;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server {

    /**
     * ustalamy port do komunikacji pomiędzy stronami połączenia */
    private final Port port = Port.create();

    public static List<ConnectionHolder> consumers = new CopyOnWriteArrayList<>(); //lista odbiorców wiadomości

    public static MessageQueue messageQueue = new MessageQueue();

    /**
     * metoda uruchamiająca serwer */
    public void start() throws IOException {

        ServerSocket server = new ServerSocket(port.getPort()); //uruchamiamy lokalny serwer na wskazanym porcie
        System.out.println("Server started");
        System.out.println("server = " + server);

        /**
         * nieskończona pętla, aby serwer nie przestawał oczekiwać nowych połączeń */
        while (true) {

            Socket socket = server.accept(); //metoda accept() zwraca nowy Socket, a w trakcie ustalania połączenia blokuje się, aż do momentu ustanowienia połączenia
            new Thread(new ConnectionHolder(socket)).start(); //nowy wątek dla każdego nowego połączenia, inaczej nastąpiłaby blokada jedynego wątku wykorzystanego do połączeń
            System.out.println("Connection established");
        }

    }
}
