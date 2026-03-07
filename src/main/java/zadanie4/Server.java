//package zadanie4;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Server {
//
//    /**
//     * ustalamy port do komunikacji pomiędzy stronami połączenia */
//    private final int PORT = 6000;
//
//    /**
//     * metoda uruchamiająca serwer */
//    public void start() throws IOException {
//
//        ServerSocket server = new ServerSocket(PORT); //uruchamiamy lokalny serwer
//
//        /**
//         * nieskończona pętla, aby serwer nie przestawał oczekiwać nowych połączeń */
//        while (true) {
//
//            Socket socket = server.accept(); //metoda accept() blokuje program w oczekiwaniu na nowe połączenie z zewnątrz
//            new Thread(ServerClient(socket)).start();
//        }
//
//    }
//}
