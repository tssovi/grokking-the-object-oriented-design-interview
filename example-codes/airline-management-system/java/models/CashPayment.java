package models;

import enums.PaymentStatus;

public class CashPayment extends Payment {
    private double cashTendered;

    public CashPayment(double amount, String transactionId, double cashTendered) {
        super(amount, transactionId);
        this.cashTendered = cashTendered;
    }

    @Override
    public boolean processPayment() {
        // Implementation for cash payment processing
        if (cashTendered >= getAmount()) {
            setStatus(PaymentStatus.COMPLETED);
            return true;
        }
        setStatus(PaymentStatus.DECLINED);
        return false;
    }

    public double getChange() {
        return cashTendered - getAmount();
    }

    // Getters
    public double getCashTendered() {
        return cashTendered;
    }
}
