package com.stockbrokerage;

import java.util.Date;

/**
 * Abstract base class representing a generic stock order.
 * 
 * This class provides the shared structure for buy and sell orders,
 * including order ID, status, enforcement type, and creation timestamp.
 * Specific types of orders (like limit orders or market orders)
 * extend this base class to add additional attributes or logic.
 */
public abstract class Order {
    protected String orderId;
    protected boolean isBuyOrder;
    protected Constants.OrderStatus status;
    protected Constants.TimeEnforcementType enforcementType;
    protected Date createdAt;

    public Order(String orderId) {
        this.orderId = orderId;
        this.isBuyOrder = false;
        this.status = Constants.OrderStatus.OPEN;
        this.enforcementType = Constants.TimeEnforcementType.ON_THE_OPEN;
        this.createdAt = new Date();
    }

    /**
     * Changes the status of an order (e.g., from OPEN to FILLED).
     */
    public void setStatus(Constants.OrderStatus status) {
        this.status = status;
    }

    /**
     * Pretends to save this order to a database.
     * In a real-world system, this would persist to a DB or event queue.
     */
    public void saveInDB() {
        System.out.println("Saving order " + orderId + " to database...");
    }

    /**
     * Every subclass (limit, market, etc.) defines its own execution behavior.
     */
    public abstract void execute();
}


/**
 * Represents a Limit Order — an order with a maximum or minimum price
 * at which an investor is willing to buy or sell a stock.
 */
class LimitOrder extends Order {
    private double priceLimit;
    private int quantity;

    public LimitOrder(String orderId, int quantity, double priceLimit, boolean isBuyOrder) {
        super(orderId);
        this.priceLimit = priceLimit;
        this.quantity = quantity;
        this.isBuyOrder = isBuyOrder;
    }

    /**
     * Executes the limit order. In a real system, this would communicate
     * with a matching engine to compare bid/ask prices and execute trades.
     */
    @Override
    public void execute() {
        String type = isBuyOrder ? "BUY" : "SELL";
        System.out.println("Executing " + type + " LIMIT ORDER for " + quantity + " shares @ $" + priceLimit);
    }
}
