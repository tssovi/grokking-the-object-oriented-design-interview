/**
 * Constants.java
 * 
 * This file contains all the enumerations and constant classes used throughout
 * the Hotel Management System. It defines room styles, booking statuses, 
 * account types, payment statuses, and the Address class.
 */

/**
 * Enum representing different styles/types of rooms available in the hotel.
 */
enum RoomStyle {
    STANDARD,        // Basic room with standard amenities
    DELUXE,          // Enhanced room with premium amenities
    FAMILY_SUITE,    // Large room suitable for families
    BUSINESS_SUITE   // Room with business facilities
}

/**
 * Enum representing the current status of a room.
 */
enum RoomStatus {
    AVAILABLE,       // Room is ready and available for booking
    RESERVED,        // Room is reserved but guest hasn't checked in
    OCCUPIED,        // Room is currently occupied by a guest
    NOT_AVAILABLE,   // Room is not available for booking
    BEING_SERVICED,  // Room is being cleaned or maintained
    OTHER            // Any other status
}

/**
 * Enum representing the status of a room booking.
 */
enum BookingStatus {
    REQUESTED,       // Booking has been requested but not confirmed
    PENDING,         // Booking is pending approval
    CONFIRMED,       // Booking has been confirmed
    CHECKED_IN,      // Guest has checked in
    CHECKED_OUT,     // Guest has checked out
    CANCELLED,       // Booking was cancelled
    ABANDONED        // Booking was abandoned/not completed
}

/**
 * Enum representing the status of an account.
 */
enum AccountStatus {
    ACTIVE,          // Account is active and in good standing
    CLOSED,          // Account has been closed
    CANCELED,        // Account has been canceled
    BLACKLISTED,     // Account has been blacklisted
    BLOCKED          // Account has been temporarily blocked
}

/**
 * Enum representing different types of accounts in the system.
 */
enum AccountType {
    MEMBER,          // Regular member account
    GUEST,           // Guest account (non-member)
    MANAGER,         // Hotel manager account
    RECEPTIONIST     // Receptionist account
}

/**
 * Enum representing the status of a payment transaction.
 */
enum PaymentStatus {
    UNPAID,          // Payment has not been made
    PENDING,         // Payment is being processed
    COMPLETED,       // Payment has been completed successfully
    FILLED,          // Payment details have been filled
    DECLINED,        // Payment was declined
    CANCELLED,       // Payment was cancelled
    ABANDONED,       // Payment process was abandoned
    SETTLING,        // Payment is being settled
    SETTLED,         // Payment has been settled
    REFUNDED         // Payment has been refunded
}

/**
 * Class representing a physical address.
 * This class stores complete address information for persons and hotel locations.
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create an Address object.
     * 
     * @param streetAddress The street address
     * @param city The city name
     * @param state The state or province
     * @param zipCode The postal/zip code
     * @param country The country name
     */
    public Address(String streetAddress, String city, String state, String zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters
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
