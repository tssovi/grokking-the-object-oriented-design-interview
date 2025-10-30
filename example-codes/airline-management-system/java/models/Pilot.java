package models;

import java.util.ArrayList;
import java.util.List;

public class Pilot extends Person {
    private String licenseNumber;
    private List<FlightInstance> assignedFlights;

    public Pilot(String name, Address address, String email, String phone, 
                Account account, String licenseNumber) {
        super(name, address, email, phone, account);
        this.licenseNumber = licenseNumber;
        this.assignedFlights = new ArrayList<>();
    }

    public List<FlightInstance> getAssignedFlights() {
        return assignedFlights;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void assignFlight(FlightInstance flightInstance) {
        this.assignedFlights.add(flightInstance);
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
