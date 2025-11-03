/**
 * Constants.java
 * 
 * This file contains enumerations and common classes used throughout the LinkedIn system.
 * It defines connection invitation statuses, account statuses, and the Address class.
 */

/**
 * Enum representing the status of a connection invitation.
 * 
 * PENDING - Invitation has been sent but not yet responded to
 * ACCEPTED - Invitation has been accepted by the recipient
 * CONFIRMED - Connection has been confirmed by both parties
 * REJECTED - Invitation was rejected by the recipient
 * CANCELED - Invitation was canceled by the sender
 */
public enum ConnectionInvitationStatus {
    PENDING,
    ACCEPTED,
    CONFIRMED,
    REJECTED,
    CANCELED
}

/**
 * Enum representing the status of a user account.
 * 
 * ACTIVE - Account is active and in good standing
 * BLOCKED - Account has been temporarily blocked
 * BANNED - Account has been permanently banned
 * COMPROMISED - Account security has been compromised
 * ARCHIVED - Account has been archived by the user
 * UNKNOWN - Account status is unknown
 */
enum AccountStatus {
    ACTIVE,
    BLOCKED,
    BANNED,
    COMPROMISED,
    ARCHIVED,
    UNKNOWN
}

/**
 * Class representing a physical address.
 * 
 * This class encapsulates all components of a mailing address including
 * street, city, state, zip code, and country information.
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create a new Address object.
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

    // Setters
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
