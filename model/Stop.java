package model;
import passenger.Passenger;


import java.util.ArrayList;

// One drop-off point. One or more passengers can get off here.
public class Stop {
    private String place;
    private double distanceKm;
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public Stop(String place, double distanceKm) {
        this.place = place;
        this.distanceKm = distanceKm;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public String getPlace() {
        return place;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
}
