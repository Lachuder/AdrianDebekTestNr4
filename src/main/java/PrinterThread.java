import java.util.concurrent.Callable;

public class PrinterThread implements Callable<Void> {

    private String letter;
    private long amount;
    private long interval;

    public PrinterThread(String letter, long amount, long interval) {
        this.letter = letter;
        this.amount = amount;
        this.interval = interval;
    }

    @Override
    public Void call() throws Exception {
        for (int i = 0; i < amount; i++) {
            System.out.println(letter);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return null;
    }

}
