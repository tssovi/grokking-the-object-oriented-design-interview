/**
 * Constants.java
 * 
 * This file contains enumerations and value objects used throughout the Facebook system.
 * It defines status types for connections and accounts, as well as the Address class
 * for storing location information.
 */

/**
 * Enum representing the status of a connection invitation between members.
 * 
 * PENDING - Invitation has been sent but not yet responded to
 * ACCEPTED - Invitation has been accepted by the recipient
 * REJECTED - Invitation has been declined by the recipient
 * CANCELED - Invitation has been canceled by the sender
 */
public enum ConnectionInvitationStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    CANCELED
}

/**
 * Enum representing the current status of a user account.
 * 
 * ACTIVE - Account is active and can be used normally
 * CLOSED - Account has been closed by the user
 * CANCELED - Account has been canceled
 * BLACKLISTED - Account has been blacklisted due to violations
 * DISABLED - Account has been temporarily disabled
 */
enum AccountStatus {
    ACTIVE,
    CLOSED,
    CANCELED,
    BLACKLISTED,
    DISABLED
}

/**
 * Address class represents a physical address with standard components.
 * This is a value object used to store location information for users.
 */
class Address {
    // Private fields to encapsulate address components
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create an Address object with all required fields.
     * 
     * @param streetAddress The street address (e.g., "123 Main St")
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

    // Getter methods to access private fields
    
    /**
     * @return The street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @return The city name
     */
    public String getCity() {
        return city;
    }

    /**
     * @return The state or province
     */
    public String getState() {
        return state;
    }

    /**
     * @return The postal/zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @return The country name
     */
    public String getCountry() {
        return country;
    }
}
