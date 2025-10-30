package models;

import enums.FlightStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightInstance {
    private Flight flight;
    private LocalDateTime departureTime;
    private String gate;
    private FlightStatus status;
    private Aircraft aircraft;
    private List<FlightSeat> seats;
    private List<Pilot> pilots;
    private List<Crew> crewMembers;

    public FlightInstance(Flight flight, LocalDateTime departureTime, String gate, 
                         FlightStatus status, Aircraft aircraft) {
        this.flight = flight;
        this.departureTime = departureTime;
        this.gate = gate;
        this.status = status;
        this.aircraft = aircraft;
        this.seats = new ArrayList<>();
        this.pilots = new ArrayList<>();
        this.crewMembers = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        // Initialize flight seats from aircraft seats
        for (Seat seat : aircraft.getSeats()) {
            FlightSeat flightSeat = new FlightSeat(
                seat.getSeatNumber(), 
                seat.getType(), 
                seat.getSeatClass(), 
                calculateFare(seat.getSeatClass())
            );
            this.seats.add(flightSeat);
        }
    }

    private double calculateFare(enums.SeatClass seatClass) {
        // Simple fare calculation based on seat class
        switch (seatClass) {
            case FIRST_CLASS:
                return 1000.0;
            case BUSINESS:
                return 600.0;
            case PREFERRED_ECONOMY:
                return 350.0;
            case ECONOMY_PLUS:
                return 250.0;
            case ECONOMY:
            default:
                return 150.0;
        }
    }

    public boolean cancel() {
        this.status = FlightStatus.CANCELLED;
        // Notify all passengers
        return true;
    }

    public boolean updateStatus(FlightStatus status) {
        this.status = status;
        return true;
    }

    public void assignPilot(Pilot pilot) {
        this.pilots.add(pilot);
        pilot.assignFlight(this);
    }

    public void assignCrew(Crew crew) {
        this.crewMembers.add(crew);
        crew.assignFlight(this);
    }

    public List<FlightSeat> getAvailableSeats() {
        List<FlightSeat> availableSeats = new ArrayList<>();
        for (FlightSeat seat : seats) {
            if (!seat.isReserved()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    // Getters
    public Flight getFlight() {
        return flight;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getGate() {
        return gate;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public List<FlightSeat> getSeats() {
        return seats;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public List<Crew> getCrewMembers() {
        return crewMembers;
    }

    // Setters
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }
}
