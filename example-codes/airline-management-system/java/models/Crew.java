package models;

import java.util.ArrayList;
import java.util.List;

public class Crew extends Person {
    private List<FlightInstance> assignedFlights;

    public Crew(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
        this.assignedFlights = new ArrayList<>();
    }

    public List<FlightInstance> getAssignedFlights() {
        return assignedFlights;
    }

    public void assignFlight(FlightInstance flightInstance) {
        this.assignedFlights.add(flightInstance);
    }
}
