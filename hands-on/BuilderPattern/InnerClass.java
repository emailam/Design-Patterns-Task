package BuilderPattern;

public class InnerClass {
    public static class Car {
        private static String temp = "a temp variable";
        private String brand;
        private String color;
        private int doors;

        // Private constructor - only Builder can make Car
        private Car(Builder builder) {
            this.brand = builder.brand;
            this.color = builder.color;
            this.doors = builder.doors;
        }

        // Inner Class Builder (inside Car class), this is the most used approach
        public static class Builder {
            private String brand = "bmw";
            private String color = "white";
            private int doors = 4;

            public Builder() {
                // it can access only the private constructor, private static fields,
                // private static methods of the outer class
                System.out.println(temp);
            }

            public Builder setBrand(String brand){
                this.brand = brand;
                return this;
            }
            public Builder setColor(String color) {
                this.color = color;
                return this;
            }

            public Builder setDoors(int doors) {
                this.doors = doors;
                return this;
            }

            public Car build() {
                return new Car(this);
            }
        }

        @Override
        public String toString() {
            return brand + " car, " + color + " color, " + doors + " doors";
        }
    }

    public static void main(String[] args) {
        Car car = new Car.Builder()
                .setBrand("bmw")
                .setColor("red")
                .setDoors(2)
                .build();

        System.out.println(car);
    }
}
