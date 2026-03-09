package zadanie3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static void main(String[] args) throws InterruptedException {

        PrinterThread t1 = new PrinterThread("A", 10, 100);
        PrinterThread t2 = new PrinterThread("B", 15, 150);
        PrinterThread t3 = new PrinterThread("C", 20, 200);
        PrinterThread t4 = new PrinterThread("D", 10, 100);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.submit(t4);

        executor.shutdown();

    }



}
