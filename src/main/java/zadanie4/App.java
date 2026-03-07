package zadanie4;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

//        if (args.length == 0) {
//            System.out.println("server | publisher | consumer");
//            return;
//        }

        switch (args[0]) {
//            case "server" -> new Server().start();
            case "publisher" -> new Publisher().start();
//            case "consumer" -> new Consumer().start();
        }

    }
}
