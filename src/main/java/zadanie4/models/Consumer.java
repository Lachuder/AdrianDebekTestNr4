package zadanie4.models;

import zadanie4.appbody.Port;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Consumer {

    /**
     * gwarancja tego samego portu do połączenia stron*/
    private final Port port = Port.create();


    /**
     * metoda uruchamiająca połączenie */
    public void start() throws IOException {

        Socket socket = new Socket("localhost", port.getPort());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //wrapper do strumienia danych przesyłanych przez socket
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("CONSUMER"); //informacja dla Connection Holdera, jaka rola została podłączona

        String message;
        while ((message = bufferedReader.readLine()) != null) {
            System.out.println("New message: " + message);
        }

    }

}
