package FactoryMethodPattern;

public class After {
    // 1. One interface
    interface Notification {
        void send(String message);
    }

    // 2. Concrete implementations
    static class EmailNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    static class SMSNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    static class PushNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Sending Push Notification: " + message);
        }
    }

    // 3. Factory class
    static class NotificationFactory {
        public static Notification createNotification(String type) {
            return switch (type.toUpperCase()) {
                case "EMAIL" -> new EmailNotification();
                case "SMS" -> new SMSNotification();
                case "PUSH" -> new PushNotification();
                default -> throw new IllegalArgumentException("Unknown notification type: " + type);
            };
        }
    }

    // 4. Clean client code
    public static class NotificationService {
        public void sendNotification(String type, String message) {
            Notification notification = NotificationFactory.createNotification(type);
            notification.send(message);
            // if any new type is added
            // we will not change the current implementation of the function
            // since we delegated the creation to the factory
            // and this is how we apply the factor pattern.
        }
    }

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.sendNotification("EMAIL", "hello");
        notificationService.sendNotification("SMS", "hello");
        notificationService.sendNotification("PUSH", "hello");
    }
}
