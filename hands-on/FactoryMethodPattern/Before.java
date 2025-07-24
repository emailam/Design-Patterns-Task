package FactoryMethodPattern;

public class Before {
    static class EmailNotification {
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    static class SMSNotification {
        public void send(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    static class PushNotification {
        public void send(String message) {
            System.out.println("Sending Push Notification: " + message);
        }
    }

    public static class NotificationService {
        public void sendNotification(String type, String message) {
            if (type.equals("EMAIL")) {
                EmailNotification email = new EmailNotification();
                email.send(message);
            } else if (type.equals("SMS")) {
                SMSNotification sms = new SMSNotification();
                sms.send(message);
            } else if (type.equals("PUSH")) {
                PushNotification push = new PushNotification();
                push.send(message);
            }
            // Adding new notification types requires changing this method!
            // Which violates the OCP!
        }
    }

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        notificationService.sendNotification("EMAIL", "hello");
        notificationService.sendNotification("SMS", "hello");
        notificationService.sendNotification("PUSH", "hello");

    }
}
