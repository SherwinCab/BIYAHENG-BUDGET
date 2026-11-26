import model.Trip;
import util.Ui;

import java.util.ArrayList;

// Manages all trips and the budget
public class TripManager {
    private ArrayList<Trip> tripList = new ArrayList<>();
    private double budget = 0;

    public void addTrip(Trip trip) {
        tripList.add(trip);
    }

    public void setBudget(double budget) {
        if (budget <= 0) {
            throw new IllegalArgumentException("Budget must be more than 0.");
        }
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public double computeTotal() {
        double total = 0;
        for (Trip t : tripList) {
            total += t.getTotalFare();
        }
        return total;
    }

    public ArrayList<Trip> getTrips() {
        return tripList;
    }

    // Budget message (used by the window version)
    public String getBudgetStatus() {
        if (budget == 0) {
            return "You have not set a budget yet.";
        }
        double left = budget - computeTotal();
        if (left < 0) {
            return "WARNING: Over budget by " + Ui.money(-left) + "!";
        } else if (left <= budget * 0.20) {
            return "WARNING: Budget is almost used up.";
        }
        return "You are within your budget.";
    }

    public void viewTrips() {
        System.out.println();
        Ui.title("TRIP HISTORY");
        if (tripList.isEmpty()) {
            Ui.row("No trips saved yet.");
            Ui.line();
            return;
        }
        int no = 1;
        for (Trip t : tripList) {
            Ui.row(no + ". " + t.getRideType() + " - " + t.getTransportName()
                    + " (" + t.getPassengerCount() + " pax)", Ui.money(t.getTotalFare()));
            no++;
        }
        Ui.line();
        Ui.row("Total spent", Ui.money(computeTotal()));
        Ui.line();
    }

    public void checkBudget() {
        double total = computeTotal();
        System.out.println();
        Ui.title("BUDGET");
        Ui.row("Total spent", Ui.money(total));
        if (budget == 0) {
            Ui.row("You have not set a budget yet.");
            Ui.line();
            return;
        }
        double left = budget - total;
        Ui.row("Budget", Ui.money(budget));
        Ui.row("Left", Ui.money(Math.max(left, 0)));
        Ui.row(Ui.bar(total, budget, 30));
        Ui.line();
        if (left < 0) {
            Ui.row("WARNING: Over budget by " + Ui.money(-left) + "!");
        } else if (left <= budget * 0.20) {
            Ui.row("WARNING: Budget is almost used up.");
        } else {
            Ui.row("You are within your budget.");
        }
        Ui.line();
    }
}
