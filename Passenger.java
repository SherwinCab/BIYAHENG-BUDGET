// Parent class of all passenger types (Abstraction)
public abstract class Passenger {
    private String type;

    public Passenger(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Each passenger type has its own discount (Polymorphism)
    public abstract double getDiscountRate();

    public double applyDiscount(double fare) {
        return fare - (fare * getDiscountRate());
    }
}
