package models;

import enums.ReservationStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class FlightReservation {
    private String reservationNumber;
    private FlightInstance flightInstance;
    private Map<Passenger, FlightSeat> seatMap;
    private LocalDateTime creationDate;
    private ReservationStatus status;
    private Customer customer;

    public FlightReservation(String reservationNumber, FlightInstance flightInstance, 
                            LocalDateTime creationDate, Customer customer) {
        this.reservationNumber = reservationNumber;
        this.flightInstance = flightInstance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.status = ReservationStatus.REQUESTED;
        this.seatMap = new HashMap<>();
    }

    public static FlightReservation fetchReservationDetails(String reservationNumber) {
        // Implementation to fetch reservation from database
        return null;
    }

    public List<Passenger> getPassengers() {
        return new ArrayList<>(seatMap.keySet());
    }

    public boolean assignSeat(Passenger passenger, FlightSeat seat) {
        if (!seat.isReserved()) {
            seatMap.put(passenger, seat);
            seat.setReserved(true);
            return true;
        }
        return false;
    }

    public boolean cancel() {
        // Release all reserved seats
        for (FlightSeat seat : seatMap.values()) {
            seat.setReserved(false);
        }
        this.status = ReservationStatus.CANCELLED;
        return true;
    }

    public double calculateTotalFare() {
        double total = 0.0;
        for (FlightSeat seat : seatMap.values()) {
            total += seat.getFare();
        }
        return total;
    }

    // Getters
    public String getReservationNumber() {
        return reservationNumber;
    }

    public FlightInstance getFlightInstance() {
        return flightInstance;
    }

    public Map<Passenger, FlightSeat> getSeatMap() {
        return seatMap;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Setters
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
