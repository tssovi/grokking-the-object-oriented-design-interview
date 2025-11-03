package com.stockbrokerage;

/**
 * The StockExchange class simulates the core stock market mechanism.
 * 
 * Implements the Singleton pattern to ensure a single global instance
 * (there’s only one stock exchange).
 */
public class StockExchange {
    private static StockExchange instance;

    // Private constructor prevents direct instantiation
    private StockExchange() { }

    /**
     * Returns the single instance of StockExchange (lazy initialization).
     */
    public static StockExchange getInstance() {
        if (instance == null) {
            instance = new StockExchange();
        }
        return instance;
    }

    /**
     * Simulates placing an order in the exchange.
     * In a real-world implementation, this would push the order to a
     * matching engine, message queue, or trade book.
     */
    public Constants.ReturnStatus placeOrder(Order order) {
        System.out.println("Submitting order to exchange: " + order.orderId);
        order.execute();  // Execute logic from specific order type
        return Constants.ReturnStatus.SUCCESS;
    }
}
