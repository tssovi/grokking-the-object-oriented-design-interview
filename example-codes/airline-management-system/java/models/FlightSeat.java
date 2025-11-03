package models;

import enums.SeatClass;
import enums.SeatType;

public class FlightSeat extends Seat {
    private double fare;
    private boolean isReserved;

    public FlightSeat(String seatNumber, SeatType type, SeatClass seatClass, double fare) {
        super(seatNumber, type, seatClass);
        this.fare = fare;
        this.isReserved = false;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
