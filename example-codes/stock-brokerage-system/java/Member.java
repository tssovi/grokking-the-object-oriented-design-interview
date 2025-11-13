package com.stockbrokerage;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a brokerage account member (trader/investor).
 * 
 * Each member can place buy/sell orders through the StockExchange.
 * The class maintains available funds, owned stock positions,
 * and currently active orders.
 */
public class Member extends Account {
    private double availableFunds;
    private Map<String, Integer> stockPositions;  // Stock ID → quantity owned
    private Map<String, Order> activeOrders;      // Order ID → order object

    public Member(String id, String password, String name, Constants.Location address, String email, String phone) {
        super(id, password, name, address, email, phone, Constants.AccountStatus.ACTIVE);
        this.availableFunds = 50000.0;  // Starting balance for simplicity
        this.stockPositions = new HashMap<>();
        this.activeOrders = new HashMap<>();
    }

    /**
     * Places a BUY order with a price limit.
     * Checks whether the member has enough balance to execute.
     */
    public Constants.ReturnStatus placeBuyLimitOrder(String stockId, int quantity, double priceLimit) {
        double totalCost = quantity * priceLimit;

        if (availableFunds < totalCost) {
            System.out.println("Not enough funds to buy " + stockId);
            return Constants.ReturnStatus.INSUFFICIENT_FUNDS;
        }

        Order order = new LimitOrder(stockId + "_BUY", quantity, priceLimit, true);
        order.saveInDB();
        Constants.ReturnStatus result = StockExchange.getInstance().placeOrder(order);

        if (result == Constants.ReturnStatus.SUCCESS) {
            availableFunds -= totalCost; // Deduct funds after placing
            activeOrders.put(order.orderId, order);
        }
        return result;
    }

    /**
     * Places a SELL order for a stock the user owns.
     * Validates if they have enough quantity to sell.
     */
    public Constants.ReturnStatus placeSellLimitOrder(String stockId, int quantity, double priceLimit) {
        if (!stockPositions.containsKey(stockId)) {
            System.out.println("Cannot sell " + stockId + " — no holdings found.");
            return Constants.ReturnStatus.NO_STOCK_POSITION;
        }

        int ownedQty = stockPositions.get(stockId);
        if (ownedQty < quantity) {
            System.out.println("Not enough quantity to sell " + stockId);
            return Constants.ReturnStatus.INSUFFICIENT_QUANTITY;
        }

        Order order = new LimitOrder(stockId + "_SELL", quantity, priceLimit, false);
        order.saveInDB();
        Constants.ReturnStatus result = StockExchange.getInstance().placeOrder(order);

        if (result == Constants.ReturnStatus.SUCCESS) {
            stockPositions.put(stockId, ownedQty - quantity);
            activeOrders.put(order.orderId, order);
        }
        return result;
    }
}


/**
 * Abstract base class for all types of user accounts.
 * 
 * Handles common attributes such as credentials, name, address, and contact info.
 */
abstract class Account {
    protected String id, password, name, email, phone;
    protected Constants.Location address;
    protected Constants.AccountStatus status;

    public Account(String id, String password, String name, Constants.Location address,
                   String email, String phone, Constants.AccountStatus status) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    /**
     * Simulates a password reset.
     */
    public void resetPassword() {
        System.out.println("Password reset link sent to: " + email);
    }
}
