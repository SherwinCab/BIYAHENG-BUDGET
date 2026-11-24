package model;
import transport.Transport;
import passenger.Passenger;
import util.Ui;


import java.util.ArrayList;

// One ride of a group. Passengers ride together but get off at different stops.
public class Trip {
    private String origin;
    private Transport transport;
    private String rideType;   // Individual, Duo, or Group
    private ArrayList<Stop> stops = new ArrayList<>();

    public Trip(String origin, Transport transport, String rideType) {
        this.origin = origin;
        this.transport = transport;
        this.rideType = rideType;
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    // Fare of ONE passenger: compute by distance, then apply the discount
    private double computeFare(Stop stop, Passenger passenger) {
        double regularFare = transport.computeFare(stop.getDistanceKm());
        return passenger.applyDiscount(regularFare);
    }

    public double getTotalFare() {
        double total = 0;
        for (Stop s : stops) {
            for (Passenger p : s.getPassengers()) {
                total += computeFare(s, p);
            }
        }
        return total;
    }

    public int getPassengerCount() {
        int count = 0;
        for (Stop s : stops) {
            count += s.getPassengers().size();
        }
        return count;
    }

    public String getOrigin() {
        return origin;
    }

    public String getRideType() {
        return rideType;
    }

    public String getTransportName() {
        return transport.getName();
    }

    // Receipt as plain text (used by the window version)
    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();
        sb.append(rideType).append(" trip: ").append(transport.getName())
          .append(" from ").append(origin).append("\n");
        sb.append("------------------------------------------------\n");
        int stopNo = 1;
        for (Stop s : stops) {
            sb.append(String.format("Stop %d: %-18s (%.1f km)%n", stopNo, s.getPlace(), s.getDistanceKm()));
            for (Passenger p : s.getPassengers()) {
                sb.append(String.format("    %-12s %20s%n", p.getType(), Ui.money(computeFare(s, p))));
            }
            stopNo++;
        }
        sb.append("------------------------------------------------\n");
        sb.append(String.format("TOTAL for %d passenger(s): %s%n", getPassengerCount(), Ui.money(getTotalFare())));
        return sb.toString();
    }

    public void displayReceipt() {
        System.out.println();
        Ui.title("FARE RECEIPT");
        Ui.row(rideType + " trip: " + transport.getName() + " from " + origin);
        Ui.line();
        int stopNo = 1;
        for (Stop s : stops) {
            Ui.row("Stop " + stopNo + ": " + s.getPlace(), String.format("(%.1f km)", s.getDistanceKm()));
            for (Passenger p : s.getPassengers()) {
                Ui.row("   " + p.getType(), Ui.money(computeFare(s, p)));
            }
            stopNo++;
        }
        Ui.line();
        Ui.row("TOTAL for " + getPassengerCount() + " passenger(s)", Ui.money(getTotalFare()));
        Ui.line();
    }
}
