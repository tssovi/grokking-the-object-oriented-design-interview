package com.stockbrokerage;

/**
 * Entry point to demonstrate the Stock Brokerage System functionality.
 * 
 * Simulates:
 *  1. Member account creation
 *  2. Placing a buy order
 *  3. Attempting a sell order (which fails without holdings)
 */
public class Main {
    public static void main(String[] args) {
        // Create sample address
        Constants.Location addr = new Constants.Location("100 Wall St", "New York", "NY", "10005", "USA");

        // Create a member with some funds
        Member member = new Member("MEM001", "pass123", "Alice", addr, "alice@stocks.com", "555-2222");

        System.out.println("\n===== BUY ORDER =====");
        Constants.ReturnStatus buyResult = member.placeBuyLimitOrder("AAPL", 10, 175.50);
        System.out.println("Buy order result: " + buyResult);

        System.out.println("\n===== SELL ORDER =====");
        Constants.ReturnStatus sellResult = member.placeSellLimitOrder("AAPL", 5, 180.00);
        System.out.println("Sell order result: " + sellResult);
    }
}
