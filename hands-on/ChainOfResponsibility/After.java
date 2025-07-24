package ChainOfResponsibility;

public class After {
    static abstract class ApprovalHandler {
        protected ApprovalHandler nextHandler;

        public void setNext(ApprovalHandler handler) {
            this.nextHandler = handler;
        }

        public abstract void approve(int amount);
    }

    static class Manager extends ApprovalHandler {
        public void approve(int amount) {
            if (amount <= 1000) {
                System.out.println("Manager approved: $" + amount);
            } else if (nextHandler != null) {
                nextHandler.approve(amount);
            }
        }
    }

    static class Director extends ApprovalHandler {
        public void approve(int amount) {
            if (amount <= 5000) {
                System.out.println("Director approved: $" + amount);
            } else if (nextHandler != null) {
                nextHandler.approve(amount);
            }
        }
    }

    static class CEO extends ApprovalHandler {
        public void approve(int amount) {
            if (amount <= 10000) {
                System.out.println("CEO approved: $" + amount);
            } else if (nextHandler != null) {
                nextHandler.approve(amount);
            }
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        Director director = new Director();
        CEO ceo = new CEO();
        manager.setNext(director);
        director.setNext(ceo);
        ceo.setNext(null);
        manager.approve(10000);
        manager.approve(7000);
        manager.approve(3000);
        manager.approve(600);
    }
}
