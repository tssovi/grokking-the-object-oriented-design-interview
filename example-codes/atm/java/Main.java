package com.bank;

import java.util.Date;

/**
 * Demo class for the Bank Management System.
 * Creates a simple bank environment and performs a sample withdrawal.
 */
public class Main {
    public static void main(String[] args) {
        // Create base objects
        Constants.Address addr = new Constants.Address("123 Wall St", "New York", "NY", "10001", "USA");
        Account acc = new SavingAccount("ACC001", 5000);
        Card card = new Card("CARD001", "John Doe", "12/30", "1234");

        Customer cust = new Customer("John Doe", addr, "john@example.com", "555-9999",
                Constants.CustomerStatus.ACTIVE, card, acc);

        Bank bank = new Bank("Grokking Bank", "GB001");
        ATM atm = new ATM("ATM01", "Downtown");

        // Create and execute a withdrawal
        Transaction withdraw = new Withdraw("TXN001", new Date(), Constants.TransactionStatus.SUCCESS, 100);
        cust.makeTransaction(withdraw);
        atm.makeTransaction(cust, withdraw);
    }
}
