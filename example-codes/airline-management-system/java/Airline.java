package com.airline;

import com.airline.Constants.AccountStatus;

/**
 * Represents user account credentials and status.
 */
public class Account {
    private String id;
    private String password;
    private AccountStatus status;

    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    public void resetPassword() {
        // TODO: implement password reset logic
    }
}

/**
 * Abstract class representing a generic person (customer, admin, etc.).
 */
abstract class Person {
    protected String name;
    protected Constants.Address address;
    protected String email;
    protected String phone;
    protected Account account;

    public Person(String name, Constants.Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }
}

/**
 * Represents a registered customer with a frequent flyer number.
 */
class Customer extends Person {
    private String frequentFlyerNumber;

    public Customer(String name, Constants.Address address, String email, String phone,
                    Account account, String frequentFlyerNumber) {
        super(name, address, email, phone, account);
        this.frequentFlyerNumber = frequentFlyerNumber;
    }
}

/**
 * Represents an individual passenger on a flight.
 */
class Passenger {
    private String name;
    private String passportNumber;
    private String dateOfBirth;

    public Passenger(String name, String passportNumber, String dateOfBirth) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
}
