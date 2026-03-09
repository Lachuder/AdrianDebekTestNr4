package zadanie4;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz jedną z ról: server | publisher | consumer");

        String result = scanner.nextLine();

        switch (result) {
            case "server" -> new Server().start();
            case "publisher" -> new Publisher().start();
            case "consumer" -> new Consumer().start();
        }

    }
}
