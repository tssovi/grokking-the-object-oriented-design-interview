package com.bank;

/**
 * Represents the main bank and ATM components.
 */
public class Bank {
    private String name;
    private String bankCode;

    public Bank(String name, String bankCode) {
        this.name = name;
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void addATM(ATM atm) {
        // TODO: Register ATM into the bank system
    }
}

/**
 * Represents an ATM machine.
 */
class ATM {
    private String id, location;
    private CashDispenser dispenser;
    private Screen screen;
    private Keypad keypad;

    public ATM(String id, String location) {
        this.id = id;
        this.location = location;
        this.dispenser = new CashDispenser();
        this.screen = new Screen();
        this.keypad = new Keypad();
    }

    public void makeTransaction(Customer customer, Transaction txn) {
        // Placeholder for transaction handling
        System.out.println("Transaction processed for customer: " + customer.getName());
    }
}

/**
 * Represents ATM hardware components.
 */
class CashDispenser {
    public void dispenseCash(double amount) {
        System.out.println("Dispensing $" + amount);
    }
}

class Screen {
    public void showMessage(String message) {
        System.out.println("[SCREEN]: " + message);
    }
}

class Keypad {
    public String getInput() {
        return "User Input Simulated";
    }
}
