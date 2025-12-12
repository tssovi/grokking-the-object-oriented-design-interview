package com.stockbrokerage;

/**
 * Contains all enumerations and system-wide constants for the Stock Brokerage System.
 * 
 * These enums define common states used by multiple entities such as Orders, Accounts,
 * and ReturnStatus for handling transactions and responses from the system.
 */
public class Constants {

    // ---- ENUMS ----

    /**
     * Represents the result of an operation (buy, sell, etc.)
     */
    public enum ReturnStatus {
        SUCCESS,          // Operation completed successfully
        FAIL,             // Operation failed
        INSUFFICIENT_FUNDS, 
        INSUFFICIENT_QUANTITY, 
        NO_STOCK_POSITION // Member tried to sell stock they don’t own
    }

    /**
     * Status of an order within the stock exchange.
     */
    public enum OrderStatus {
        OPEN,             // Order is active and waiting to be filled
        FILLED,           // Order completely executed
        PARTIALLY_FILLED, // Only part of the order filled
        CANCELLED         // Order was canceled before execution
    }

    /**
     * Defines how long an order stays active.
     */
    public enum TimeEnforcementType {
        GOOD_TILL_CANCELLED, // Active until explicitly canceled
        FILL_OR_KILL,        // Must fill immediately or be canceled
        IMMEDIATE_OR_CANCEL, // Fill part instantly, cancel rest
        ON_THE_OPEN,         // Executes when market opens
        ON_THE_CLOSE         // Executes when market closes
    }

    /**
     * Represents the current status of a user account.
     */
    public enum AccountStatus {
        ACTIVE, CLOSED, CANCELED, BLACKLISTED, NONE
    }

    /**
     * Represents a user’s physical or mailing address.
     */
    public static class Location {
        private String street, city, state, zipCode, country;

        public Location(String street, String city, String state, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }
    }

    /**
     * Stores global system configuration and operational limits.
     */
    public static class Config {
        // Maximum money allowed per trade, simulating a compliance constraint
        public static final double MONEY_TRANSFER_LIMIT = 100000.0;
    }
}
