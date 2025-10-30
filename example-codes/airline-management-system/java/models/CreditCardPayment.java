package models;

import enums.PaymentStatus;

public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String nameOnCard;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(double amount, String transactionId, String cardNumber, 
                            String nameOnCard, String expiryDate, String cvv) {
        super(amount, transactionId);
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment() {
        // Implementation for credit card payment processing
        // Validate card details, process payment through payment gateway
        setStatus(PaymentStatus.COMPLETED);
        return true;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
