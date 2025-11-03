import java.time.LocalDate;

/**
 * Account.java
 * 
 * This file contains classes related to user accounts and personnel in the
 * restaurant management system, including employees, receptionists, managers, and chefs.
 */

/**
 * Represents a user account in the restaurant management system.
 * 
 * This class stores authentication credentials and account status information.
 * Each account is associated with a physical address and has a unique identifier.
 */
class Account {
    // Private fields for account information
    private String id;
    private String password;
    private Address address;
    private AccountStatus status;

    /**
     * Constructor to create a new Account.
     * 
     * @param id Unique identifier for the account
     * @param password Account password for authentication
     * @param address Physical address associated with the account
     * @param status Current status of the account (defaults to ACTIVE if not specified)
     */
    public Account(String id, String password, Address address, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.address = address;
        this.status = status;
    }

    /**
     * Overloaded constructor with default ACTIVE status.
     * 
     * @param id Unique identifier for the account
     * @param password Account password for authentication
     * @param address Physical address associated with the account
     */
    public Account(String id, String password, Address address) {
        this(id, password, address, AccountStatus.ACTIVE);
    }

    /**
     * Allows the user to reset their password.
     * Implementation would include password validation and security measures.
     */
    public void resetPassword() {
        // TODO: Implement password reset logic
        // This would typically involve:
        // 1. Verify user identity
        // 2. Generate temporary password or reset link
        // 3. Update password in database
    }

    // Getter methods
    
    public String getId() {
        return id;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }
}

/**
 * Abstract base class representing a person in the system.
 * 
 * This class contains common attributes shared by all people,
 * such as name, email, and phone number.
 */
abstract class Person {
    // Private fields for personal information
    private String name;
    private String email;
    private String phone;

    /**
     * Constructor to create a Person object.
     * 
     * @param name Full name of the person
     * @param email Email address for communication
     * @param phone Phone number for contact
     */
    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getter methods
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}

/**
 * Abstract class representing an employee in the restaurant.
 * 
 * Extends Person and adds employee-specific attributes like employee ID,
 * date joined, and associated account. This serves as a base class for
 * specific employee types (Receptionist, Manager, Chef).
 */
abstract class Employee extends Person {
    // Private fields for employee information
    private String employeeId;
    private LocalDate dateJoined;
    private Account account;

    /**
     * Constructor to create an Employee object.
     * 
     * @param employeeId Unique identifier for the employee
     * @param account Account associated with this employee
     * @param name Full name of the employee
     * @param email Email address of the employee
     * @param phone Phone number of the employee
     */
    public Employee(String employeeId, Account account, String name, String email, String phone) {
        super(name, email, phone);
        this.employeeId = employeeId;
        this.dateJoined = LocalDate.now(); // Set to current date when employee is created
        this.account = account;
    }

    // Getter methods
    
    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public Account getAccount() {
        return account;
    }
}

/**
 * Represents a Receptionist employee.
 * 
 * Receptionists are responsible for managing reservations and customer inquiries.
 * They handle front-desk operations including creating reservations and searching
 * for customer information.
 */
class Receptionist extends Employee {
    /**
     * Constructor to create a Receptionist object.
     * 
     * @param employeeId Unique identifier for the receptionist
     * @param account Account associated with this receptionist
     * @param name Full name of the receptionist
     * @param email Email address of the receptionist
     * @param phone Phone number of the receptionist
     */
    public Receptionist(String employeeId, Account account, String name, String email, String phone) {
        super(employeeId, account, name, email, phone);
    }

    /**
     * Creates a new reservation for a customer.
     * 
     * @return Reservation object representing the newly created reservation
     */
    public Reservation createReservation() {
        // TODO: Implement reservation creation logic
        // This would typically involve:
        // 1. Gather customer information
        // 2. Check table availability
        // 3. Create and store reservation
        // 4. Send confirmation to customer
        return null;
    }

    /**
     * Searches for a customer by name in the system.
     * 
     * @param name The name of the customer to search for
     * @return Customer object if found, null otherwise
     */
    public Object searchCustomer(String name) {
        // TODO: Implement customer search logic
        // This would typically involve:
        // 1. Query database for customer records
        // 2. Return matching customer information
        return null;
    }
}

/**
 * Represents a Manager employee.
 * 
 * Managers have administrative privileges including the ability to add new employees,
 * manage restaurant operations, and oversee other staff members.
 */
class Manager extends Employee {
    /**
     * Constructor to create a Manager object.
     * 
     * @param employeeId Unique identifier for the manager
     * @param account Account associated with this manager
     * @param name Full name of the manager
     * @param email Email address of the manager
     * @param phone Phone number of the manager
     */
    public Manager(String employeeId, Account account, String name, String email, String phone) {
        super(employeeId, account, name, email, phone);
    }

    /**
     * Adds a new employee to the restaurant system.
     * 
     * @return boolean indicating success or failure of the operation
     */
    public boolean addEmployee() {
        // TODO: Implement employee addition logic
        // This would typically involve:
        // 1. Collect employee information
        // 2. Create employee account
        // 3. Assign role and permissions
        // 4. Add to employee database
        return false;
    }
}

/**
 * Represents a Chef employee.
 * 
 * Chefs are responsible for preparing food orders in the kitchen.
 * They receive orders from waiters and update order status as meals are prepared.
 */
class Chef extends Employee {
    /**
     * Constructor to create a Chef object.
     * 
     * @param employeeId Unique identifier for the chef
     * @param account Account associated with this chef
     * @param name Full name of the chef
     * @param email Email address of the chef
     * @param phone Phone number of the chef
     */
    public Chef(String employeeId, Account account, String name, String email, String phone) {
        super(employeeId, account, name, email, phone);
    }

    /**
     * Receives and processes a new order from a waiter.
     * 
     * @return boolean indicating whether the order was successfully received
     */
    public boolean takeOrder() {
        // TODO: Implement order taking logic
        // This would typically involve:
        // 1. Receive order details
        // 2. Update order status to PREPARING
        // 3. Begin meal preparation
        return false;
    }
}
