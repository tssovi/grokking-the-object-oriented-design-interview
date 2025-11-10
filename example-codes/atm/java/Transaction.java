package com.bank;

import java.util.Date;

/**
 * Abstract base for all transactions.
 */
public abstract class Transaction {
    protected String id;
    protected Date createdAt;
    protected Constants.TransactionStatus status;

    public Transaction(String id, Date createdAt, Constants.TransactionStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }

    public abstract void makeTransaction();
}

/** --- Transaction Types --- **/

class Withdraw extends Transaction {
    private double amount;

    public Withdraw(String id, Date createdAt, Constants.TransactionStatus status, double amount) {
        super(id, createdAt, status);
        this.amount = amount;
    }

    @Override
    public void makeTransaction() {
        System.out.println("Withdrawing $" + amount);
    }
}

class Deposit extends Transaction {
    private double amount;

    public Deposit(String id, Date createdAt, Constants.TransactionStatus status, double amount) {
        super(id, createdAt, status);
        this.amount = amount;
    }

    @Override
    public void makeTransaction() {
        System.out.println("Depositing $" + amount);
    }
}

class BalanceInquiry extends Transaction {
    private String accountId;

    public BalanceInquiry(String id, Date createdAt, Constants.TransactionStatus status, String accountId) {
        super(id, createdAt, status);
        this.accountId = accountId;
    }

    @Override
    public void makeTransaction() {
        System.out.println("Checking balance for account: " + accountId);
    }
}

/**
 * Simple demo inside the same file for compactness.
 */
class Main {
    public static void main(String[] args) {
        // Setup bank environment
        Constants.Address addr = new Constants.Address("123 Wall St", "New York", "NY", "10001", "USA");
        Account acc = new SavingAccount("ACC001", 5000);
        Card card = new Card("CARD001", "John Doe", "12/30", "1234");
        Customer cust = new Customer("John Doe", addr, "john@example.com", "555-9999",
                Constants.CustomerStatus.ACTIVE, card, acc);

        Bank bank = new Bank("Grokking Bank", "GB001");
        ATM atm = new ATM("ATM01", "Downtown");

        // Perform a withdrawal
        Transaction withdraw = new Withdraw("TXN001", new Date(), Constants.TransactionStatus.SUCCESS, 100);
        cust.makeTransaction(withdraw);
        atm.makeTransaction(cust, withdraw);
    }
}
