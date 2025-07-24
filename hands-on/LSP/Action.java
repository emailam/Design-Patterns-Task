package LSP;

public class Action {
    public abstract static class Bird {
        public abstract void move();
    }

    static class FlyingBird extends Bird {
        public void fly() {
            System.out.println("Flying high!");
        }

        public void move() {
            fly();
        }
    }

    static class Eagle extends FlyingBird {
        public void fly() {
            System.out.println("Eagle Flying!");
        }
    }

    public static class Penguin extends Bird {
        public void swim() {
            System.out.println("Swimming fast!");
        }

        public void move() {
            swim();
        }
    }

    public static void main(String[] args) {
        // Trying after adding LSP
        Action.Bird penguin1 = new Action.Penguin();
        penguin1.move();
    }
}
