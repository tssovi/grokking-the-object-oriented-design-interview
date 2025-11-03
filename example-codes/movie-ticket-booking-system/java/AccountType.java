package movie.ticket.booking.system;

import java.util.List;

/**
 * AccountType.java
 * 
 * This file contains classes related to user accounts and different types of users
 * in the movie ticket booking system. It includes Account, Person (abstract base class),
 * and specific user types: Customer, Admin, FrontDeskOfficer, and Guest.
 */

/**
 * Class representing a user account in the system.
 * Contains authentication credentials and account status.
 */
class Account {
    private String id;
    private String password;
    private AccountStatus status;

    /**
     * Constructor to create a new Account.
     * 
     * @param id Unique identifier for the account
     * @param password Account password (should be hashed in production)
     * @param status Current status of the account
     */
    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    /**
     * Resets the account password.
     * In a real implementation, this would involve security checks and email verification.
     * 
     * @return true if password reset was successful, false otherwise
     */
    public boolean resetPassword() {
        // Implementation would include password reset logic
        return false;
    }

    // Getters
    public String getId() {
        return id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    // Setters
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * Validates the provided password against the stored password.
     * 
     * @param password Password to validate
     * @return true if password matches, false otherwise
     */
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}

/**
 * Abstract base class representing a person in the system.
 * All user types (Customer, Admin, FrontDeskOfficer) inherit from this class.
 */
abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Account account;

    /**
     * Constructor to create a Person instance.
     * 
     * @param name Full name of the person
     * @param address Physical address of the person
     * @param email Email address for communication
     * @param phone Phone number for contact
     * @param account Associated account for authentication
     */
    public Person(String name, Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Account getAccount() {
        return account;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

/**
 * Class representing a customer who can book movie tickets.
 * Customers can make bookings and view their booking history.
 */
class Customer extends Person {

    /**
     * Constructor to create a Customer instance.
     * 
     * @param name Customer's full name
     * @param address Customer's address
     * @param email Customer's email
     * @param phone Customer's phone number
     * @param account Customer's account
     */
    public Customer(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Creates a new booking for the customer.
     * 
     * @param booking The booking object to be created
     * @return true if booking was successful, false otherwise
     */
    public boolean makeBooking(Booking booking) {
        // Implementation would include booking creation logic
        return false;
    }

    /**
     * Retrieves all bookings made by this customer.
     * 
     * @return List of all bookings associated with this customer
     */
    public List<Booking> getBookings() {
        // Implementation would retrieve bookings from database
        return null;
    }
}

/**
 * Class representing an administrator with elevated privileges.
 * Admins can add movies, create shows, and manage users.
 */
class Admin extends Person {

    /**
     * Constructor to create an Admin instance.
     * 
     * @param name Admin's full name
     * @param address Admin's address
     * @param email Admin's email
     * @param phone Admin's phone number
     * @param account Admin's account
     */
    public Admin(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Adds a new movie to the system catalog.
     * 
     * @param movie The movie to be added
     * @return true if movie was successfully added, false otherwise
     */
    public boolean addMovie(Movie movie) {
        // Implementation would add movie to catalog
        return false;
    }

    /**
     * Creates a new show for a movie in a cinema hall.
     * 
     * @param show The show to be added
     * @return true if show was successfully created, false otherwise
     */
    public boolean addShow(Show show) {
        // Implementation would create a new show
        return false;
    }

    /**
     * Blocks a customer account, preventing them from making bookings.
     * 
     * @param customer The customer to be blocked
     * @return true if customer was successfully blocked, false otherwise
     */
    public boolean blockUser(Customer customer) {
        // Implementation would update customer account status
        if (customer.getAccount() != null) {
            customer.getAccount().setStatus(AccountStatus.BLOCKED);
            return true;
        }
        return false;
    }
}

/**
 * Class representing a front desk officer who can assist with bookings.
 * Front desk officers can create bookings on behalf of customers.
 */
class FrontDeskOfficer extends Person {

    /**
     * Constructor to create a FrontDeskOfficer instance.
     * 
     * @param name Officer's full name
     * @param address Officer's address
     * @param email Officer's email
     * @param phone Officer's phone number
     * @param account Officer's account
     */
    public FrontDeskOfficer(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Creates a booking on behalf of a customer at the front desk.
     * 
     * @param booking The booking to be created
     * @return true if booking was successful, false otherwise
     */
    public boolean createBooking(Booking booking) {
        // Implementation would create booking for walk-in customers
        return false;
    }
}

/**
 * Class representing a guest user who hasn't registered yet.
 * Guests can browse movies but need to register to make bookings.
 */
class Guest {

    /**
     * Registers a new account for the guest user.
     * 
     * @param name Guest's full name
     * @param email Guest's email
     * @param password Desired password
     * @return The newly created Account, or null if registration failed
     */
    public Account registerAccount(String name, String email, String password) {
        // Implementation would create a new account and customer record
        return null;
    }
}
