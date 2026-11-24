package passenger;

// Inheritance: StudentPassenger extends Passenger
public class StudentPassenger extends Passenger {
    public StudentPassenger() {
        super("Student");
    }

    @Override
    public double getDiscountRate() {
        return 0.20;
    }
}
