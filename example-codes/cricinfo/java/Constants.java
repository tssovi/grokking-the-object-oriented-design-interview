/**
 * Constants and basic data structures for the Cricinfo system.
 * This file contains enums for match formats, results, umpire types, wicket types,
 * ball types, and run types, as well as basic classes for Address and Person.
 */

/**
 * Represents a physical address with street, city, state, zip code, and country.
 */
class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to initialize an Address object.
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
}

/**
 * Represents a person with basic contact information.
 * This is used as a base for players, umpires, referees, and other personnel.
 */
class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;

    /**
     * Constructor to initialize a Person object.
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
 * Enum representing different cricket match formats.
 */
enum MatchFormat {
    ODI,    // One Day International (50 overs per side)
    T20,    // Twenty20 (20 overs per side)
    TEST    // Test Match (multi-day format)
}

/**
 * Enum representing possible match results/statuses.
 */
enum MatchResult {
    LIVE,       // Match is currently in progress
    FINISHED,   // Match has completed with a result
    DRAWN,      // Match ended in a draw
    CANCELLED   // Match was cancelled
}

/**
 * Enum representing different types of umpires in cricket.
 */
enum UmpireType {
    FIELD,      // On-field umpire (2 per match)
    RESERVED,   // Reserve umpire (backup)
    TV          // Third umpire (TV/video replay)
}

/**
 * Enum representing different ways a batsman can be dismissed.
 */
enum WicketType {
    BOLD,           // Bowled (ball hits stumps)
    CAUGHT,         // Caught by fielder
    STUMPED,        // Stumped by wicket-keeper
    RUN_OUT,        // Run out while running between wickets
    LBW,            // Leg Before Wicket
    RETIRED_HURT,   // Batsman retired due to injury
    HIT_WICKET,     // Batsman hits own wicket
    OBSTRUCTING     // Obstructing the field
}

/**
 * Enum representing different types of balls bowled.
 */
enum BallType {
    NORMAL,     // Regular legal delivery
    WIDE,       // Wide ball (illegal, extra run awarded)
    WICKET,     // Ball that resulted in a wicket
    NO_BALL     // No ball (illegal delivery, extra run awarded)
}

/**
 * Enum representing different types of runs scored.
 */
enum RunType {
    NORMAL,     // Regular runs scored by hitting the ball
    FOUR,       // Boundary (ball reaches boundary after bouncing)
    SIX,        // Six (ball crosses boundary without bouncing)
    LEG_BYE,    // Runs scored off the batsman's body
    BYE,        // Runs scored when ball misses everyone
    NO_BALL,    // Extra run for no ball
    OVERTHROW   // Extra runs from fielding error
}
