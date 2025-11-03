package com.airline;

import java.util.Date;

/**
 * Demo class to showcase the Airline Management System.
 * Simulates creating airports, flights, aircraft, and reservations.
 */
public class Main {

    public static void main(String[] args) {
        // ----- CREATE ADDRESSES -----
        Constants.Address jfkAddr = new Constants.Address("JFK Rd", "New York", "NY", "10001", "USA");
        Constants.Address laxAddr = new Constants.Address("1 World Way", "Los Angeles", "CA", "90045", "USA");

        // ----- CREATE AIRPORTS -----
        Airport jfk = new Airport("John F. Kennedy International", jfkAddr, "JFK");
        Airport lax = new Airport("Los Angeles International", laxAddr, "LAX");

        // ----- CREATE AIRCRAFT -----
        Aircraft boeing737 = new Aircraft("Boeing 737", "MAX-8", 2018);

        // ----- CREATE A FLIGHT -----
        FlightSchedule.Flight flight = new FlightSchedule.Flight("AA101", jfk, lax, 360);

        // ----- ADD A FLIGHT INSTANCE -----
        FlightSchedule.FlightInstance instance = new FlightSchedule.FlightInstance(
                "2025-11-05T08:00",
                "Gate 12",
                Constants.FlightStatus.SCHEDULED,
                boeing737
        );

        // ----- CREATE ACCOUNT AND CUSTOMER -----
        Account account = new Account("CUST123", "password123", Constants.AccountStatus.ACTIVE);
        Customer customer = new Customer(
                "Jane Doe",
                jfkAddr,
                "jane@example.com",
                "555-1234",
                account,
                "FF12345"
        );

        // ----- CREATE RESERVATION -----
        FlightSchedule.FlightReservation reservation = new FlightSchedule.FlightReservation(
                "RSV001",
                flight,
                boeing737,
                new Date(),
                Constants.ReservationStatus.CONFIRMED
        );

        // ----- CREATE ITINERARY -----
        FlightSchedule.Itinerary itinerary = new FlightSchedule.Itinerary(
                "CUST123",
                jfk,
                lax,
                new Date()
        );

        // (In a full system, you'd add reservations to the itinerary list.)
        System.out.println("Customer " + customer.name + " booked flight " + flight + " from " + jfk + " to " + lax);
        System.out.println("Flight instance departs at " + instance + " with status " + Constants.FlightStatus.SCHEDULED);
        System.out.println("Reservation created successfully with ID: " + "RSV001");
    }
}
