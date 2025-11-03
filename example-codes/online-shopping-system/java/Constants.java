/**
 * Constants and Enumerations for Online Shopping System
 * 
 * This file contains all the constant values, enumerations, and basic data structures
 * used throughout the online shopping system.
 */

/**
 * Address class represents a physical address
 * Used for shipping and billing purposes
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create a new Address
     * 
     * @param street The street address
     * @param city The city name
     * @param state The state or province
     * @param zipCode The postal/zip code
     * @param country The country name
     */
    public Address(String street, String city, String state, String zipCode, String country) {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters
    public String getStreetAddress() { return streetAddress; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }

    // Setters
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setCountry(String country) { this.country = country; }
}

/**
 * OrderStatus enum represents the various states an order can be in
 */
enum OrderStatus {
    UNSHIPPED,      // Order placed but not yet shipped
    PENDING,        // Order is being processed
    SHIPPED,        // Order has been shipped
    COMPLETED,      // Order has been delivered and completed
    CANCELED,       // Order was canceled
    REFUND_APPLIED  // Refund has been applied to the order
}

/**
 * AccountStatus enum represents the various states a user account can be in
 */
enum AccountStatus {
    ACTIVE,        // Account is active and in good standing
    BLOCKED,       // Account is temporarily blocked
    BANNED,        // Account is permanently banned
    COMPROMISED,   // Account security has been compromised
    ARCHIVED,      // Account has been archived
    UNKNOWN        // Account status is unknown
}

/**
 * ShipmentStatus enum represents the various states a shipment can be in
 */
enum ShipmentStatus {
    PENDING,    // Shipment is pending
    SHIPPED,    // Shipment is in transit
    DELIVERED,  // Shipment has been delivered
    ON_HOLD     // Shipment is on hold
}

/**
 * PaymentStatus enum represents the various states a payment can be in
 */
enum PaymentStatus {
    UNPAID,      // Payment has not been made
    PENDING,     // Payment is being processed
    COMPLETED,   // Payment has been completed
    FILLED,      // Payment has been filled
    DECLINED,    // Payment was declined
    CANCELLED,   // Payment was cancelled
    ABANDONED,   // Payment was abandoned
    SETTLING,    // Payment is settling
    SETTLED,     // Payment has settled
    REFUNDED     // Payment has been refunded
}
