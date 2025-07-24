package BuilderPattern;

public class SubClass {
    static class Vehicle {
        protected String brand;
        protected String color;

        public Vehicle(String brand, String color) {
            this.brand = brand;
            this.color = color;
        }
    }

    static class Motorcycle extends Vehicle {
        private boolean hasSidecar;

        public Motorcycle(String brand, String color, boolean hasSidecar) {
            super(brand, color);
            this.hasSidecar = hasSidecar;
        }

        @Override
        public String toString() {
            return brand + " motorcycle, " + color + " color, sidecar: " + hasSidecar;
        }
    }

    // Usage
    public static void main(String[] args) {
        Motorcycle bike = new Motorcycle("Harley", "black", true);
        System.out.println(bike); // Harley motorcycle, black color, sidecar: true
    }

}
