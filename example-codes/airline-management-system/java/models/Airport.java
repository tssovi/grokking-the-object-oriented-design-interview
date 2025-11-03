package models;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String name;
    private Address address;
    private String code;
    private List<Flight> flights;

    public Airport(String name, Address address, String code) {
        this.name = name;
        this.address = address;
        this.code = code;
        this.flights = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    // Getters
    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
