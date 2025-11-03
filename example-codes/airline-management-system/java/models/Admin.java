package models;

public class Admin extends Person {
    
    public Admin(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    public boolean addAircraft(Aircraft aircraft) {
        // Implementation to add aircraft to the system
        return true;
    }

    public boolean addFlight(Flight flight) {
        // Implementation to add flight to the system
        return true;
    }

    public boolean cancelFlight(FlightInstance flightInstance) {
        // Implementation to cancel a flight
        return flightInstance.cancel();
    }

    public boolean assignPilotToFlight(Crew pilot, FlightInstance flightInstance) {
        // Implementation to assign pilot
        return true;
    }

    public boolean assignCrewToFlight(Crew crew, FlightInstance flightInstance) {
        // Implementation to assign crew
        return true;
    }
}
