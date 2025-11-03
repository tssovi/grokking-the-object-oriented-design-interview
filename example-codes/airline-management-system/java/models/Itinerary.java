package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private String customerId;
    private Airport startingAirport;
    private Airport finalAirport;
    private LocalDateTime creationDate;
    private List<FlightReservation> reservations;

    public Itinerary(String customerId, Airport startingAirport, Airport finalAirport, 
                    LocalDateTime creationDate) {
        this.customerId = customerId;
        this.startingAirport = startingAirport;
        this.finalAirport = finalAirport;
        this.creationDate = creationDate;
        this.reservations = new ArrayList<>();
    }

    public List<FlightReservation> getReservations() {
        return reservations;
    }

    public boolean makeReservation(FlightReservation reservation) {
        this.reservations.add(reservation);
        return true;
    }

    public boolean cancelItinerary() {
        for (FlightReservation reservation : reservations) {
            reservation.cancel();
        }
        return true;
    }

    public double calculateTotalFare() {
        double total = 0.0;
        for (FlightReservation reservation : reservations) {
            total += reservation.calculateTotalFare();
        }
        return total;
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public Airport getStartingAirport() {
        return startingAirport;
    }

    public Airport getFinalAirport() {
        return finalAirport;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    // Setters
    public void setStartingAirport(Airport startingAirport) {
        this.startingAirport = startingAirport;
    }

    public void setFinalAirport(Airport finalAirport) {
        this.finalAirport = finalAirport;
    }
}
