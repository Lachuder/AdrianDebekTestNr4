package zadanie4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Publisher {

    /**
     * gwarancja tego samego portu do połączenia stron*/
    private final Port port = Port.create();

    /**
     * metoda uruchamiająca połączenie */
    public void start() throws IOException {

        Socket socket = new Socket("localhost", port.getPort()); //tworzymy połączenie o tych samych parametrach co serwer

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true); //wrapper do strumienia danych przesyłanych przez socket
        printWriter.println("WRITE MESSAGE"); //intro dla użytkownika

        Scanner scanner = new Scanner(System.in);

        /**
         * nieskończona pętla, aby serwer nie przestawał oczekiwać nowych wiadomości */
        while (true) {
            String message = scanner.nextLine();
            printWriter.println(message);
        }

    }
}
