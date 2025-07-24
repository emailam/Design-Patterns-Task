package ThreadSafeSingleton;

public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
        System.out.println("Creating instance...");
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public void perform() {
        System.out.println("Perform anything...");
    }

    public static void main(String[] args) {
        // Trying double singleton
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
                doubleCheckSingleton.perform();
                System.out.println("Instance: " + doubleCheckSingleton);
            }).start();
        }
    }
}