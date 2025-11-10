package com.bank;

/**
 * Represents a bank customer.
 */
public class Customer {
    private String name;
    private Constants.Address address;
    private String email;
    private String phone;
    private Constants.CustomerStatus status;
    private Card card;
    private Account account;

    public Customer(String name, Constants.Address address, String email, String phone,
                    Constants.CustomerStatus status, Card card, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.card = card;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void makeTransaction(Transaction transaction) {
        transaction.makeTransaction();
    }
}

/**
 * Represents a customer's card.
 */
class Card {
    private String number;
    private String customerName;
    private String expiry;
    private String pin;

    public Card(String number, String customerName, String expiry, String pin) {
        this.number = number;
        this.customerName = customerName;
        this.expiry = expiry;
        this.pin = pin;
    }
}

/**
 * Represents an account base class.
 */
class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }
}

/**
 * Subclass for Savings account.
 */
class SavingAccount extends Account {
    private double withdrawLimit;

    public SavingAccount(String accountNumber, double withdrawLimit) {
        super(accountNumber);
        this.withdrawLimit = withdrawLimit;
    }
}

/**
 * Subclass for Checking account.
 */
class CheckingAccount extends Account {
    private String debitCardNumber;

    public CheckingAccount(String accountNumber, String debitCardNumber) {
        super(accountNumber);
        this.debitCardNumber = debitCardNumber;
    }
}
