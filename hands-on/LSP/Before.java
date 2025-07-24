package LSP;

public class Before {
    public static class Bird {
        public void fly() {
            System.out.println("Flying high!");
        }
    }

    static class Eagle extends Bird {
        public void fly() {
            System.out.println("Eagle Flying!");
        }
    }
    public static class Penguin extends Bird {
        public void fly() {
            throw new UnsupportedOperationException("Penguins can't fly!");
        }
    }

    public static void main(String[] args) {
        // Trying LSP before (violates lsp should result in runtime error)
        Before.Bird penguin2 = new Before.Penguin();
        penguin2.fly();
    }
}
