package com.airline;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an aircraft model with seat details.
 * Each aircraft can serve multiple flights.
 */
public class Aircraft {
    private String name;
    private String model;
    private int manufacturingYear;
    private List<Seat> seats;

    public Aircraft(String name, String model, int manufacturingYear) {
        this.name = name;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.seats = new ArrayList<>();
    }

    public List<FlightSchedule.Flight> getFlights() {
        // Placeholder: Fetch flights assigned to this aircraft.
        return new ArrayList<>();
    }
}
