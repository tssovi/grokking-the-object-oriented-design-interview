package stockbrokerage;

/**
 * This file contains all the enums and constant classes used throughout the stock brokerage system.
 * It defines various statuses, types, and configuration values needed for the system to operate.
 */

/**
 * Enum representing the possible return statuses for various operations in the system.
 * Used to communicate the result of operations like placing orders or transferring funds.
 */
public enum ReturnStatus {
    SUCCESS,                // Operation completed successfully
    FAIL,                   // Operation failed for general reasons
    INSUFFICIENT_FUNDS,     // User doesn't have enough money for the transaction
    INSUFFICIENT_QUANTITY,  // User doesn't have enough stock quantity to sell
    NO_STOCK_POSITION       // User doesn't own the stock they're trying to sell
}

/**
 * Enum representing the current status of an order in the system.
 * Orders transition through these states during their lifecycle.
 */
enum OrderStatus {
    OPEN,               // Order has been placed but not yet executed
    FILLED,             // Order has been completely executed
    PARTIALLY_FILLED,   // Order has been partially executed
    CANCELLED           // Order has been cancelled by user or system
}

/**
 * Enum representing time enforcement types for orders.
 * These define how long an order remains active in the market.
 */
enum TimeEnforcementType {
    GOOD_TILL_CANCELLED,    // Order remains active until explicitly cancelled
    FILL_OR_KILL,           // Order must be filled immediately and completely or cancelled
    IMMEDIATE_OR_CANCEL,    // Fill whatever possible immediately, cancel the rest
    ON_THE_OPEN,            // Execute at market open
    ON_THE_CLOSE            // Execute at market close
}

/**
 * Enum representing the status of a user account.
 * Determines what actions the account holder can perform.
 */
enum AccountStatus {
    ACTIVE,         // Account is active and can perform all operations
    CLOSED,         // Account has been closed
    CANCELED,       // Account has been cancelled
    BLACKLISTED,    // Account has been blacklisted due to violations
    NONE            // Account status not set (initial state)
}

/**
 * Class representing a physical location/address.
 * Used to store address information for accounts and members.
 */
class Location {
    // Private fields to store address components
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to initialize a Location object with all address components.
     * 
     * @param street The street address
     * @param city The city name
     * @param state The state or province
     * @param zipCode The postal/zip code
     * @param country The country name
     */
    public Location(String street, String city, String state, String zipCode, String country) {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters for all fields
    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}

/**
 * Class containing system-wide constants.
 * These values define limits and constraints for the entire system.
 */
class Constants {
    // Maximum amount that can be transferred in a single transaction
    private final double MONEY_TRANSFER_LIMIT = 100000.0;

    /**
     * Gets the money transfer limit for the system.
     * 
     * @return The maximum amount that can be transferred in one transaction
     */
    public double getMoneyTransferLimit() {
        return MONEY_TRANSFER_LIMIT;
    }
}
