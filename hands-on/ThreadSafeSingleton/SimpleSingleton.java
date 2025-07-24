package ThreadSafeSingleton;

public class SimpleSingleton {
    private static SimpleSingleton instance;

    private SimpleSingleton() {
        // Private constructor prevents instantiation
        System.out.println("Creating instance...");
    }

    public static synchronized SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public void perform() {
        System.out.println("Perform anything...");
    }

    public static void main(String[] args) {
        // Trying simple singleton
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                SimpleSingleton simpleSingleton = SimpleSingleton.getInstance();
                simpleSingleton.perform();
                System.out.println("Instance: " + simpleSingleton);
            }).start();
        }
    }
}
