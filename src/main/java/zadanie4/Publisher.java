package zadanie4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Publisher extends ServerClient {

    /**
     * metoda uruchamiająca połączenie */
    public void start() throws IOException {

        Socket socket = new Socket("localhost", 6000);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("PUT MESSAGE");

        Scanner scanner = new Scanner(System.in);

        /**
         * nieskończona pętla, aby serwer nie przestawał oczekiwać nowych wiadomości */
        while (true) {
            String message = scanner.nextLine();
            out.println(message);

        }

    }
}
