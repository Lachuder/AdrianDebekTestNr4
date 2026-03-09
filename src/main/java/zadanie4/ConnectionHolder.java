package zadanie4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHolder implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ConnectionHolder(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String type = in.readLine();

            if (type.equals("SUB")) {
                handleSubscriber();
            } else if (type.equals("PUB")) {
                handlePublisher();
            }

        } catch (Exception e) {
            System.out.println("Klient się rozłączył");
        }
    }

    private void handleSubscriber() throws Exception {
        System.out.println("Consumer podłączony");

        // wysyłanie backlogu + przyszłych wiadomości
        while (true) {
            String msg = Server.messageQueue.take();
            out.println(msg);
        }
    }

    private void handlePublisher() throws Exception {
        String message;
        while ((message = in.readLine()) != null) {

            System.out.println("Odebrano od PUB: " + message);

            // dodaj do kolejki
            Server.messageQueue.add(message);
        }
    }
}


