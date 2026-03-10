package zadanie4;

import zadanie4.models.Consumer;
import zadanie4.models.Publisher;
import zadanie4.models.Server;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz jedną z ról: SERVER | PUBLISHER | CONSUMER");

        String result = scanner.nextLine();

        switch (result) {
            case "SERVER" -> {
                new Server().start();
                scanner.close();
            }
            case "PUBLISHER" -> {
                new Publisher().start();
                scanner.close();
            }
            case "CONSUMER" -> {
                new Consumer().start();
                scanner.close();
            }
        }

    }
}
