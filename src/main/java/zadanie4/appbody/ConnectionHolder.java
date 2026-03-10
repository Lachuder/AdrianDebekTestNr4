package zadanie4.appbody;

import zadanie4.models.Server;

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

            if (type.equals("CONSUMER")) {
                handleConsumer();
            } else if (type.equals("PUBLISHER")) {
                handlePublisher();
            }

        } catch (Exception e) {
            System.out.println("Klient się rozłączył");
        }
    }

    private void handleConsumer() throws Exception {
        System.out.println("Consumer podłączony");

        Server.consumers.add(this);

        String message = Server.messageQueue.take();
        System.out.println("Odebrano: " + message);
        out.println(message);

    }

    private void handlePublisher() throws Exception {
        String message;
        while ((message = in.readLine()) != null) {

            System.out.println("Wysłano: " + message);

            for(ConnectionHolder consumer : Server.consumers){
                consumer.send(message);
            }
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}


