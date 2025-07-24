public class Main {
    public static abstract class MyAbstractClass {
        public MyAbstractClass() {
            System.out.println("hello, I am the abstract class!");
        }
    }

    public static class MyChildClass extends MyAbstractClass {
        public MyChildClass() {
            System.out.println("hello, I am the child class!");
        }
    }

    public static void main(String[] args) {
        // the constructors will be called in order starting from the parent to the lowest child
        // the destructor will be called in reverse order
        MyChildClass x = new MyChildClass();
    }
}
