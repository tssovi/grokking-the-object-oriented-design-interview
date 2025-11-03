package models;

import java.util.Date;

public class FrontDeskOfficer extends Person {
    
    public FrontDeskOfficer(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    public FlightReservation createReservation(FlightInstance flightInstance, Customer customer) {
        // Implementation to create a reservation
        return null;
    }

    public boolean cancelReservation(String reservationNumber) {
        // Implementation to cancel a reservation
        return true;
    }

    public FlightReservation searchReservation(String reservationNumber) {
        // Implementation to search for a reservation
        return null;
    }
}
