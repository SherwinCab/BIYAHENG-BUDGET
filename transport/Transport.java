package transport;

// Parent class of all transport types (Abstraction + Encapsulation)
public abstract class Transport {
    private String name;
    private double baseFare;

    public Transport(String name, double baseFare) {
        this.name = name;
        this.baseFare = baseFare;
    }

    public String getName() {
        return name;
    }

    public double getBaseFare() {
        return baseFare;
    }

    // Each transport type computes its fare in its own way (Polymorphism)
    public abstract double computeFare(double distanceKm);
}
