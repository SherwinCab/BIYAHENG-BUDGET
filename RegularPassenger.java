// Inheritance: RegularPassenger extends Passenger
public class RegularPassenger extends Passenger {
    public RegularPassenger() {
        super("Regular");
    }

    @Override
    public double getDiscountRate() {
        return 0.00;
    }
}
