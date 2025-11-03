/**
 * Constants and Enumerations for Library Management System
 * 
 * This file contains all the enums and constant values used throughout
 * the library management system, including book formats, statuses,
 * and system-wide configuration constants.
 */

/**
 * Enum representing different formats a book can have
 */
enum BookFormat {
    HARDCOVER,      // Physical hardcover book
    PAPERBACK,      // Physical paperback book
    AUDIO_BOOK,     // Audio format book
    EBOOK,          // Electronic book
    NEWSPAPER,      // Newspaper publication
    MAGAZINE,       // Magazine publication
    JOURNAL         // Journal publication
}

/**
 * Enum representing the current status of a book item
 */
enum BookStatus {
    AVAILABLE,      // Book is available for checkout
    RESERVED,       // Book is reserved by a member
    LOANED,         // Book is currently loaned out
    LOST            // Book is marked as lost
}

/**
 * Enum representing the status of a book reservation
 */
enum ReservationStatus {
    WAITING,        // Reservation is waiting for book availability
    PENDING,        // Reservation is pending confirmation
    CANCELED,       // Reservation has been canceled
    COMPLETED,      // Reservation has been completed
    NONE            // No reservation status
}

/**
 * Enum representing the status of a user account
 */
enum AccountStatus {
    ACTIVE,         // Account is active and in good standing
    CLOSED,         // Account has been closed
    CANCELED,       // Account has been canceled
    BLACKLISTED,    // Account has been blacklisted
    NONE            // No specific status
}

/**
 * Address class representing a physical address
 * Contains street address, city, state, zip code, and country information
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create an Address object
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
    public String getStreetAddress() { return streetAddress; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
}

/**
 * Abstract Person class representing a person in the system
 * This is the base class for all person types (members, librarians, etc.)
 */
abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;

    /**
     * Constructor to create a Person object
     * 
     * @param name The person's full name
     * @param address The person's address
     * @param email The person's email address
     * @param phone The person's phone number
     */
    public Person(String name, Address address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    // Getters
    public String getName() { return name; }
    public Address getAddress() { return address; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

/**
 * Constants class containing system-wide configuration values
 * These values define the business rules for the library system
 */
class Constants {
    /**
     * Maximum number of books a single user can have checked out at once
     */
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    
    /**
     * Maximum number of days a book can be borrowed before it's due
     */
    public static final int MAX_LENDING_DAYS = 10;
}
