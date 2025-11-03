package com.airline;

import java.util.*;

/**
 * Handles scheduling of flights (weekly, custom), reservations, and itineraries.
 * This is the core logic of the system.
 */
public class FlightSchedule {

    // Represents recurring weekly flight schedules
    public static class WeeklySchedule {
        private String dayOfWeek;
        private String departureTime;
        public WeeklySchedule(String dayOfWeek, String departureTime) {
            this.dayOfWeek = dayOfWeek;
            this.departureTime = departureTime;
        }
    }

    // Represents special one-time flight schedules
    public static class CustomSchedule {
        private Date customDate;
        private String departureTime;
        public CustomSchedule(Date customDate, String departureTime) {
            this.customDate = customDate;
            this.departureTime = departureTime;
        }
    }

    // Represents a flight definition (without specific dates)
    public static class Flight {
        private String flightNumber;
        private Airport departure;
        private Airport arrival;
        private int durationInMinutes;
        private List<WeeklySchedule> weeklySchedules = new ArrayList<>();
        private List<CustomSchedule> customSchedules = new ArrayList<>();
        private List<FlightInstance> flightInstances = new ArrayList<>();

        public Flight(String flightNumber, Airport departure, Airport arrival, int durationInMinutes) {
            this.flightNumber = flightNumber;
            this.departure = departure;
            this.arrival = arrival;
            this.durationInMinutes = durationInMinutes;
        }
    }

    // Represents a specific occurrence of a flight (e.g., on 12th Nov)
    public static class FlightInstance {
        private String departureTime;
        private String gate;
        private Constants.FlightStatus status;
        private Aircraft aircraft;

        public FlightInstance(String departureTime, String gate, Constants.FlightStatus status, Aircraft aircraft) {
            this.departureTime = departureTime;
            this.gate = gate;
            this.status = status;
            this.aircraft = aircraft;
        }

        public void cancel() {
            this.status = Constants.FlightStatus.CANCELLED;
        }

        public void updateStatus(Constants.FlightStatus status) {
            this.status = status;
        }
    }

    // Represents a reservation made by a customer for a specific flight
    public static class FlightReservation {
        private String reservationNumber;
        private Flight flight;
        private Aircraft aircraft;
        private Date creationDate;
        private Constants.ReservationStatus status;

        public FlightReservation(String reservationNumber, Flight flight, Aircraft aircraft, Date creationDate, Constants.ReservationStatus status) {
            this.reservationNumber = reservationNumber;
            this.flight = flight;
            this.aircraft = aircraft;
            this.creationDate = creationDate;
            this.status = status;
        }
    }

    // Represents a customer's complete travel itinerary (can include multiple reservations)
    public static class Itinerary {
        private String customerId;
        private Airport startingAirport;
        private Airport finalAirport;
        private Date creationDate;
        private List<FlightReservation> reservations = new ArrayList<>();

        public Itinerary(String customerId, Airport startingAirport, Airport finalAirport, Date creationDate) {
            this.customerId = customerId;
            this.startingAirport = startingAirport;
            this.finalAirport = finalAirport;
            this.creationDate = creationDate;
        }
    }
}
