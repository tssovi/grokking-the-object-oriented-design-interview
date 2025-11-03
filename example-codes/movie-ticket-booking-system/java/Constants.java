package movie.ticket.booking.system;

/**
 * Constants.java
 * 
 * This file contains all the enumerations and constant classes used throughout
 * the movie ticket booking system. It defines booking statuses, seat types,
 * account statuses, payment statuses, and the Address class.
 */

/**
 * Enum representing the various states a booking can be in throughout its lifecycle.
 * 
 * REQUESTED - Initial state when a booking is first requested
 * PENDING - Booking is awaiting confirmation or payment
 * CONFIRMED - Booking has been confirmed and paid
 * CHECKED_IN - Customer has checked in for the show
 * CANCELED - Booking has been canceled by customer or system
 * ABANDONED - Booking was started but not completed within time limit
 */
public enum BookingStatus {
    REQUESTED,
    PENDING,
    CONFIRMED,
    CHECKED_IN,
    CANCELED,
    ABANDONED
}

/**
 * Enum representing different types of cinema hall seats.
 * 
 * REGULAR - Standard seating with basic comfort
 * PREMIUM - Enhanced seating with extra comfort and amenities
 * ACCESSIBLE - Seats designed for wheelchair accessibility
 * SHIPPED - Special seats (possibly recliner or luxury)
 * EMERGENCY_EXIT - Seats located near emergency exits (may have extra legroom)
 * OTHER - Any other special seat category
 */
enum SeatType {
    REGULAR,
    PREMIUM,
    ACCESSIBLE,
    SHIPPED,
    EMERGENCY_EXIT,
    OTHER
}

/**
 * Enum representing the status of a user account.
 * 
 * ACTIVE - Account is active and in good standing
 * BLOCKED - Account temporarily blocked (can be unblocked)
 * BANNED - Account permanently banned
 * COMPROMISED - Account security has been compromised
 * ARCHIVED - Account has been archived (inactive)
 * UNKNOWN - Account status cannot be determined
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
 * Enum representing the various states of a payment transaction.
 * 
 * UNPAID - Payment has not been initiated
 * PENDING - Payment is being processed
 * COMPLETED - Payment successfully completed
 * FILLED - Payment details filled but not processed
 * DECLINED - Payment was declined by payment processor
 * CANCELLED - Payment was cancelled by user
 * ABANDONED - Payment process was abandoned
 * SETTLING - Payment is in settlement process
 * SETTLED - Payment has been settled
 * REFUNDED - Payment has been refunded to customer
 */
enum PaymentStatus {
    UNPAID,
    PENDING,
    COMPLETED,
    FILLED,
    DECLINED,
    CANCELLED,
    ABANDONED,
    SETTLING,
    SETTLED,
    REFUNDED
}

/**
 * Class representing a physical address.
 * Contains all necessary components of a mailing address.
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create a new Address instance.
     * 
     * @param streetAddress The street address including house/building number
     * @param city The city name
     * @param state The state or province
     * @param zipCode The postal/ZIP code
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
