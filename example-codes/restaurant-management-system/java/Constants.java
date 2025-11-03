/**
 * Constants.java
 * 
 * This file contains all the enumerations and constant classes used throughout
 * the Restaurant Management System. It defines various status types and the Address class.
 */

/**
 * Enum representing the status of a reservation in the restaurant.
 * 
 * REQUESTED - Initial state when a customer requests a reservation
 * PENDING - Reservation is being processed by staff
 * CONFIRMED - Reservation has been confirmed
 * CHECKED_IN - Customer has arrived and checked in
 * CANCELED - Reservation was canceled
 * ABANDONED - Customer did not show up for the reservation
 */
public enum ReservationStatus {
    REQUESTED,
    PENDING,
    CONFIRMED,
    CHECKED_IN,
    CANCELED,
    ABANDONED
}

/**
 * Enum representing different types of seats available in the restaurant.
 * 
 * REGULAR - Standard seating
 * KID - Child-friendly seating with appropriate accommodations
 * ACCESSIBLE - Wheelchair accessible or special needs seating
 * OTHER - Any other specialized seating type
 */
enum SeatType {
    REGULAR,
    KID,
    ACCESSIBLE,
    OTHER
}

/**
 * Enum representing the current status of an order.
 * 
 * RECEIVED - Order has been received from the customer
 * PREPARING - Kitchen is preparing the order
 * COMPLETED - Order is ready and has been served
 * CANCELED - Order was canceled
 * NONE - Default or uninitialized state
 */
enum OrderStatus {
    RECEIVED,
    PREPARING,
    COMPLETED,
    CANCELED,
    NONE
}

/**
 * Enum representing the current status of a table.
 * 
 * FREE - Table is available for seating
 * RESERVED - Table has been reserved for a future booking
 * OCCUPIED - Table is currently occupied by customers
 * OTHER - Any other special status
 */
enum TableStatus {
    FREE,
    RESERVED,
    OCCUPIED,
    OTHER
}

/**
 * Enum representing the status of an account in the system.
 * 
 * ACTIVE - Account is active and in good standing
 * CLOSED - Account has been closed
 * CANCELED - Account was canceled
 * BLACKLISTED - Account has been blacklisted due to violations
 * BLOCKED - Account is temporarily blocked
 */
enum AccountStatus {
    ACTIVE,
    CLOSED,
    CANCELED,
    BLACKLISTED,
    BLOCKED
}

/**
 * Enum representing the status of a payment transaction.
 * 
 * UNPAID - Payment has not been made
 * PENDING - Payment is being processed
 * COMPLETED - Payment successfully completed
 * FILLED - Payment information has been filled
 * DECLINED - Payment was declined
 * CANCELLED - Payment was cancelled
 * ABANDONED - Payment process was abandoned
 * SETTLING - Payment is in the settlement process
 * SETTLED - Payment has been settled
 * REFUNDED - Payment has been refunded
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
 * 
 * This class encapsulates all components of a mailing address including
 * street, city, state, zip code, and country information.
 */
class Address {
    // Private fields to store address components
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

    // Getter methods for accessing address components
    
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
