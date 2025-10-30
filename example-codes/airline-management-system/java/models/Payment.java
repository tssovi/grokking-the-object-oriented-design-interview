package models;

import enums.PaymentStatus;
import java.time.LocalDateTime;

public abstract class Payment {
    private double amount;
    private LocalDateTime timestamp;
    private PaymentStatus status;
    private String transactionId;

    public Payment(double amount, String transactionId) {
        this.amount = amount;
        this.transactionId = transactionId;
        this.timestamp = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public abstract boolean processPayment();

    // Getters
    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    // Setters
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
