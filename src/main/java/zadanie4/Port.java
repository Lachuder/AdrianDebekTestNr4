package zadanie4;

public class Port {

    /**
     * singleton z adresem portu zapewni nam stałą wartość dla wszystkich połączeń w ramach jednej aplikacji*/
    private static volatile Port INSTANCE;

    private final int port;

    private Port(int value) {
        this.port = value;
    }

    public int getPort() {
        return port;
    }

    public static Port create() {
        Port result = INSTANCE;
        if (result == null) {
            synchronized (Port.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new Port(6000);
                }
            }
        }
        return result;
    }
}
