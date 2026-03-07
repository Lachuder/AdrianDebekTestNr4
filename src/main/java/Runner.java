import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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

//        List<PrinterThread> threadList = List.of(t1, t2, t3, t4);
//
//        for (int i = 0; i < threadList.size(); i++) {
//            Thread.sleep(3000);
//            executor.submit(threadList.get(i));
//        }

    }

}
