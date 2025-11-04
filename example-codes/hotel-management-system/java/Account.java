/**
 * Account.java
 * 
 * This file contains classes related to user accounts and persons in the
 * Hotel Management System. It includes the Account class and Person hierarchy
 * (Guest, Receptionist, Server).
 */

/**
 * Class representing a user account in the system.
 * Each account has an ID, password, and status.
 */
class Account {
    private String id;
    private String password;
    private AccountStatus status;

    /**
     * Constructor to create an Account with default ACTIVE status.
     * 
     * @param id Unique identifier for the account
     * @param password Password for the account
     */
    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Constructor to create an Account with specified status.
     * 
     * @param id Unique identifier for the account
     * @param password Password for the account
     * @param status Initial status of the account
     */
    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    /**
     * Resets the password for this account.
     * Implementation should include password validation and security measures.
     * 
     * @param newPassword The new password to set
     * @return true if password was reset successfully, false otherwise
     */
    public boolean resetPassword(String newPassword) {
        // TODO: Implement password reset logic with validation
        // Should include: password strength check, old password verification, etc.
        return false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

/**
 * Abstract base class representing a person in the system.
 * All person types (Guest, Receptionist, Server, etc.) inherit from this class.
 */
abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Account account;

    /**
     * Constructor to create a Person object.
     * 
     * @param name Full name of the person
     * @param address Physical address of the person
     * @param email Email address
     * @param phone Phone number
     * @param account Associated account object
     */
    public Person(String name, Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    // Getters and Setters
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
}

/**
 * Class representing a guest in the hotel.
 * Guests can make bookings and check into rooms.
 */
class Guest extends Person {
    private int totalRoomsCheckedIn;

    /**
     * Constructor to create a Guest object.
     * 
     * @param name Full name of the guest
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param account Associated account
     */
    public Guest(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
        this.totalRoomsCheckedIn = 0;
    }

    /**
     * Retrieves all bookings made by this guest.
     * 
     * @return List of RoomBooking objects associated with this guest
     */
    public java.util.List<RoomBooking> getBookings() {
        // TODO: Implement logic to fetch bookings from database
        // Should query the booking system for all bookings linked to this guest's ID
        return new java.util.ArrayList<>();
    }

    /**
     * Gets the total number of rooms this guest has checked into.
     * 
     * @return Total count of check-ins
     */
    public int getTotalRoomsCheckedIn() {
        return totalRoomsCheckedIn;
    }

    /**
     * Increments the check-in counter when guest checks into a room.
     */
    public void incrementCheckInCount() {
        this.totalRoomsCheckedIn++;
    }
}

/**
 * Class representing a hotel receptionist.
 * Receptionists can search for members and create bookings.
 */
class Receptionist extends Person {
    
    /**
     * Constructor to create a Receptionist object.
     * 
     * @param name Full name of the receptionist
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param account Associated account
     */
    public Receptionist(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Searches for a member by name in the hotel system.
     * 
     * @param name Name of the member to search for
     * @return List of matching Person objects
     */
    public java.util.List<Person> searchMember(String name) {
        // TODO: Implement member search logic
        // Should query the database for members matching the given name
        return new java.util.ArrayList<>();
    }

    /**
     * Creates a new room booking for a guest.
     * 
     * @param guest The guest making the booking
     * @param room The room to be booked
     * @param startDate Start date of the booking
     * @param durationInDays Duration of stay in days
     * @return The created RoomBooking object, or null if booking failed
     */
    public RoomBooking createBooking(Guest guest, Room room, java.util.Date startDate, int durationInDays) {
        // TODO: Implement booking creation logic
        // Should validate room availability, create booking record, and update room status
        return null;
    }
}

/**
 * Class representing a hotel server/staff member.
 * Servers can add charges to rooms for services provided.
 */
class Server extends Person {
    
    /**
     * Constructor to create a Server object.
     * 
     * @param name Full name of the server
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param account Associated account
     */
    public Server(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Adds a charge to a room for services provided.
     * 
     * @param room The room to charge
     * @param roomCharge The charge to be added
     * @return true if charge was added successfully, false otherwise
     */
    public boolean addRoomCharge(Room room, RoomCharge roomCharge) {
        // TODO: Implement room charge logic
        // Should add the charge to the room's invoice and update billing
        return false;
    }
}
