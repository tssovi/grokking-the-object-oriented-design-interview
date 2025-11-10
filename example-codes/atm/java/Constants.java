package com.bank;

/**
 * Holds all enumerations and common classes used in the Bank System.
 */
public class Constants {

    public enum TransactionType {
        BALANCE_INQUIRY, DEPOSIT_CASH, DEPOSIT_CHECK, WITHDRAW, TRANSFER
    }

    public enum TransactionStatus {
        SUCCESS, FAILURE, BLOCKED, FULL, PARTIAL, NONE
    }

    public enum CustomerStatus {
        ACTIVE, BLOCKED, CLOSED, BANNED, UNKNOWN
    }

    // Represents a standard postal address.
    public static class Address {
        private String street, city, state, zipCode, country;

        public Address(String street, String city, String state, String zipCode, String country) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.country = country;
        }
    }
}
