package models;

import java.util.ArrayList;
import java.util.List;

public class Airline {
    private String name;
    private String code;
    private List<Aircraft> aircrafts;
    private List<Flight> flights;

    public Airline(String name, String code) {
        this.name = name;
        this.code = code;
        this.aircrafts = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircrafts.add(aircraft);
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
