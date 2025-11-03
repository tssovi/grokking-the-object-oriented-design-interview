package com.airline;

/**
 * Defines all enums and shared constants used across the Airline Management System.
 * Keeps the design modular and avoids circular dependencies.
 */
public class Constants {

    // ----- ENUM DEFINITIONS -----

    public enum FlightStatus {
        ACTIVE, SCHEDULED, DELAYED, DEPARTED, LANDED, IN_AIR, ARRIVED, CANCELLED, DIVERTED, UNKNOWN
    }

    public enum PaymentStatus {
        UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
    }

    public enum ReservationStatus {
        REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELLED, ABANDONED
    }

    public enum SeatClass {
        ECONOMY, ECONOMY_PLUS, PREFERRED_ECONOMY, BUSINESS, FIRST_CLASS
    }

    public enum SeatType {
        REGULAR, ACCESSIBLE, EMERGENCY_EXIT, EXTRA_LEG_ROOM
    }

    public enum AccountStatus {
        ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED
    }

    // ----- ADDRESS CLASS -----

    /**
     * Represents a standard address structure used for both airports and people.
     */
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;

        public Address(String street, String city, String state, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }
    }
}
