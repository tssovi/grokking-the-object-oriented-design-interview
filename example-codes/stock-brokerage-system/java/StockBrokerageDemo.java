package stockbrokerage;

/**
 * Demonstration class showing how to use the Stock Brokerage System.
 * This class contains example usage scenarios for the main functionality.
 */
public class StockBrokerageDemo {
    
    /**
     * Main method demonstrating the stock brokerage system functionality.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Stock Brokerage System Demo ===\n");
        
        // Step 1: Create a member account
        System.out.println("1. Creating a new member account...");
        Location memberAddress = new Location(
            "123 Wall Street",
            "New York",
            "NY",
            "10005",
            "USA"
        );
        
        Member member = new Member(
            "MEM001",                    // Member ID
            "securePassword123",         // Password
            "John Doe",                  // Name
            memberAddress,               // Address
            "john.doe@example.com",      // Email
            "+1-555-0123",              // Phone
            AccountStatus.ACTIVE         // Status
        );
        
        System.out.println("   Member created: " + member.getName());
        System.out.println("   Account Status: " + member.getStatus());
        System.out.println();
        
        // Step 2: Add funds to the trading account
        System.out.println("2. Adding funds to trading account...");
        double initialFunds = 50000.0;
        member.setAvailableFundsForTrading(initialFunds);
        System.out.println("   Available funds: $" + member.getAvailableFundsForTrading());
        System.out.println();
        
        // Step 3: Place a buy limit order
        System.out.println("3. Placing a buy limit order for AAPL stock...");
        String stockId = "AAPL";
        int buyQuantity = 100;
        double buyLimitPrice = 150.0;
        
        ReturnStatus buyStatus = member.placeBuyLimitOrder(
            stockId,
            buyQuantity,
            buyLimitPrice,
            TimeEnforcementType.GOOD_TILL_CANCELLED
        );
        
        System.out.println("   Stock: " + stockId);
        System.out.println("   Quantity: " + buyQuantity + " shares");
        System.out.println("   Limit Price: $" + buyLimitPrice);
        System.out.println("   Order Status: " + buyStatus);
        System.out.println("   Active Orders: " + member.getActiveOrders().size());
        System.out.println();
        
        // Step 4: Simulate adding a stock position (as if buy order was filled)
        System.out.println("4. Simulating order fill - adding stock position...");
        StockPosition position = new StockPosition(stockId, buyQuantity, buyLimitPrice);
        member.getStockPositions().put(stockId, position);
        System.out.println("   Stock Position Added: " + stockId);
        System.out.println("   Quantity: " + position.getQuantity() + " shares");
        System.out.println();
        
        // Step 5: Place a sell limit order
        System.out.println("5. Placing a sell limit order for AAPL stock...");
        int sellQuantity = 50;
        double sellLimitPrice = 160.0;
        
        ReturnStatus sellStatus = member.placeSellLimitOrder(
            stockId,
            sellQuantity,
            sellLimitPrice,
            TimeEnforcementType.GOOD_TILL_CANCELLED
        );
        
        System.out.println("   Stock: " + stockId);
        System.out.println("   Quantity: " + sellQuantity + " shares");
        System.out.println("   Limit Price: $" + sellLimitPrice);
        System.out.println("   Order Status: " + sellStatus);
        System.out.println("   Active Orders: " + member.getActiveOrders().size());
        System.out.println();
        
        // Step 6: Test error scenarios
        System.out.println("6. Testing error scenarios...");
        
        // Test insufficient funds
        System.out.println("   a) Attempting to buy with insufficient funds...");
        ReturnStatus insufficientFundsStatus = member.placeBuyLimitOrder(
            "GOOGL",
            1000,
            2500.0,  // Total: $2,500,000 - more than available
            TimeEnforcementType.IMMEDIATE_OR_CANCEL
        );
        System.out.println("      Result: " + insufficientFundsStatus);
        
        // Test selling stock not owned
        System.out.println("   b) Attempting to sell stock not owned...");
        ReturnStatus noStockStatus = member.placeSellLimitOrder(
            "MSFT",  // Stock not owned
            10,
            300.0,
            TimeEnforcementType.FILL_OR_KILL
        );
        System.out.println("      Result: " + noStockStatus);
        
        // Test selling more than owned
        System.out.println("   c) Attempting to sell more shares than owned...");
        ReturnStatus insufficientQtyStatus = member.placeSellLimitOrder(
            stockId,
            200,  // Only own 100 shares
            160.0,
            TimeEnforcementType.GOOD_TILL_CANCELLED
        );
        System.out.println("      Result: " + insufficientQtyStatus);
        System.out.println();
        
        // Step 7: Display final account summary
        System.out.println("7. Account Summary:");
        System.out.println("   Member: " + member.getName());
        System.out.println("   Available Funds: $" + member.getAvailableFundsForTrading());
        System.out.println("   Stock Positions: " + member.getStockPositions().size());
        System.out.println("   Active Orders: " + member.getActiveOrders().size());
        
        // Display stock positions
        System.out.println("\n   Stock Holdings:");
        member.getStockPositions().forEach((stock, pos) -> {
            System.out.println("      " + stock + ": " + pos.getQuantity() + " shares");
        });
        
        System.out.println("\n=== Demo Complete ===");
    }
    
    /**
     * Demonstrates the callback mechanism when an order is filled.
     * This would typically be called by the StockExchange.
     */
    public static void demonstrateOrderCallback() {
        System.out.println("\n=== Order Callback Demo ===\n");
        
        // Create a member
        Location address = new Location("456 Market St", "San Francisco", "CA", "94102", "USA");
        Member member = new Member(
            "MEM002",
            "password456",
            "Jane Smith",
            address,
            "jane.smith@example.com",
            "+1-555-0456",
            AccountStatus.ACTIVE
        );
        
        member.setAvailableFundsForTrading(100000.0);
        
        // Place an order
        member.placeBuyLimitOrder("TSLA", 50, 200.0, TimeEnforcementType.GOOD_TILL_CANCELLED);
        
        System.out.println("Order placed. Active orders: " + member.getActiveOrders().size());
        
        // Simulate the exchange calling back with a filled order
        // In a real system, this would be called by the StockExchange
        String orderId = member.getActiveOrders().keySet().iterator().next();
        
        // Create order parts (simulating partial fills)
        java.util.List<OrderPart> parts = new java.util.ArrayList<>();
        parts.add(new OrderPart("PART1", 199.5, 30));  // 30 shares at $199.50
        parts.add(new OrderPart("PART2", 200.0, 20));  // 20 shares at $200.00
        
        // Callback with filled status
        member.callbackStockExchange(orderId, parts, OrderStatus.FILLED);
        
        System.out.println("Order filled! Active orders: " + member.getActiveOrders().size());
        System.out.println("\n=== Callback Demo Complete ===");
    }
}
