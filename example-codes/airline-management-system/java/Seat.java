package com.airline;

/**
 * Represents a basic seat structure.
 * Extended by FlightSeat to include fare details for specific flights.
 */
public class Seat {
    private String seatNumber;
    private Constants.SeatType type;
    private Constants.SeatClass seatClass;

    public Seat(String seatNumber, Constants.SeatType type, Constants.SeatClass seatClass) {
        this.seatNumber = seatNumber;
        this.type = type;
        this.seatClass = seatClass;
    }
}

/**
 * Represents a seat for a specific flight, including fare information.
 */
class FlightSeat extends Seat {
    private double fare;

    public FlightSeat(String seatNumber, Constants.SeatType type, Constants.SeatClass seatClass, double fare) {
        super(seatNumber, type, seatClass);
        this.fare = fare;
    }

    public double getFare() {
        return fare;
    }
}
