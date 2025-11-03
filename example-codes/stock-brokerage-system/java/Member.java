package stockbrokerage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class representing an account in the stock brokerage system.
 * This class contains common properties for all account types.
 * It serves as the foundation for more specific account types like Member accounts.
 */
public abstract class Account {
    // Unique identifier for the account
    private String id;
    
    // Encrypted password for account authentication
    private String password;
    
    // Account holder's full name
    private String name;
    
    // Physical address of the account holder
    private Location address;
    
    // Email address for communication
    private String email;
    
    // Phone number for contact
    private String phone;
    
    // Current status of the account (ACTIVE, CLOSED, etc.)
    private AccountStatus status;

    /**
     * Constructor to initialize an Account with all required information.
     * 
     * @param id Unique account identifier
     * @param password Account password (should be encrypted)
     * @param name Account holder's name
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param status Initial account status
     */
    public Account(String id, String password, String name, Location address, 
                   String email, String phone, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    /**
     * Resets the account password.
     * This method should implement password reset logic with proper security measures.
     * 
     * @param newPassword The new password to set
     * @return true if password reset successful, false otherwise
     */
    public boolean resetPassword(String newPassword) {
        // TODO: Implement password reset logic
        // Should include:
        // 1. Validate new password strength
        // 2. Encrypt the password
        // 3. Update in database
        // 4. Send confirmation email
        return false;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

/**
 * Represents a stock position held by a member.
 * Tracks the quantity of a specific stock owned by the member.
 */
class StockPosition {
    // The stock symbol or ID
    private String stockId;
    
    // Number of shares owned
    private int quantity;
    
    // Average purchase price
    private double averagePrice;

    /**
     * Constructor for creating a stock position.
     * 
     * @param stockId The stock identifier
     * @param quantity Number of shares
     * @param averagePrice Average purchase price per share
     */
    public StockPosition(String stockId, int quantity, double averagePrice) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    /**
     * Gets the quantity of shares in this position.
     * 
     * @return Number of shares
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of shares.
     * 
     * @param quantity New quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the stock ID for this position.
     * 
     * @return The stock ID
     */
    public String getStockId() {
        return stockId;
    }
}

/**
 * Represents a member of the stock brokerage system.
 * Members can place buy/sell orders, hold stock positions, and manage their portfolio.
 * This class extends Account and adds trading-specific functionality.
 */
public class Member extends Account {
    // Available cash balance for trading
    private double availableFundsForTrading;
    
    // Date when the member joined the system
    private Date dateOfMembership;
    
    // Map of stock positions: Key = stock ID, Value = StockPosition object
    private Map<String, StockPosition> stockPositions;
    
    // Map of active orders: Key = order ID, Value = Order object
    private Map<String, Order> activeOrders;

    /**
     * Constructor for creating a Member account.
     * Initializes trading funds, membership date, and empty collections for positions and orders.
     * 
     * @param id Unique member identifier
     * @param password Member password
     * @param name Member's full name
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param status Account status
     */
    public Member(String id, String password, String name, Location address, 
                  String email, String phone, AccountStatus status) {
        super(id, password, name, address, email, phone, status);
        this.availableFundsForTrading = 0.0;
        this.dateOfMembership = new Date();
        this.stockPositions = new HashMap<>();
        this.activeOrders = new HashMap<>();
    }

    /**
     * Places a sell limit order for a stock.
     * This method validates that the member owns the stock and has sufficient quantity.
     * 
     * @param stockId The ID of the stock to sell
     * @param quantity Number of shares to sell
     * @param limitPrice Minimum price willing to accept
     * @param enforcementType How long the order should remain active
     * @return ReturnStatus indicating success or failure reason
     */
    public ReturnStatus placeSellLimitOrder(String stockId, int quantity, 
                                           double limitPrice, TimeEnforcementType enforcementType) {
        // Check if member has this stock position
        if (!stockPositions.containsKey(stockId)) {
            return ReturnStatus.NO_STOCK_POSITION;
        }

        StockPosition stockPosition = stockPositions.get(stockId);
        
        // Check if the member has enough quantity available to sell
        if (stockPosition.getQuantity() < quantity) {
            return ReturnStatus.INSUFFICIENT_QUANTITY;
        }

        // Create a new limit order for selling
        LimitOrder order = new LimitOrder(stockId, quantity, limitPrice, enforcementType);
        order.setIsBuyOrder(false);  // This is a sell order
        order.saveInDB();

        // Submit the order to the stock exchange
        boolean success = StockExchange.getInstance().placeOrder(order);
        
        if (!success) {
            // Order placement failed, update status
            order.setStatus(OrderStatus.CANCELLED);
            order.saveInDB();
        } else {
            // Order placed successfully, add to active orders
            activeOrders.put(order.getOrderId(), order);
        }
        
        return success ? ReturnStatus.SUCCESS : ReturnStatus.FAIL;
    }

    /**
     * Places a buy limit order for a stock.
     * This method validates that the member has sufficient funds to purchase the stock.
     * 
     * @param stockId The ID of the stock to buy
     * @param quantity Number of shares to buy
     * @param limitPrice Maximum price willing to pay
     * @param enforcementType How long the order should remain active
     * @return ReturnStatus indicating success or failure reason
     */
    public ReturnStatus placeBuyLimitOrder(String stockId, int quantity, 
                                          double limitPrice, TimeEnforcementType enforcementType) {
        // Check if the member has enough funds to buy this stock
        double requiredFunds = quantity * limitPrice;
        if (availableFundsForTrading < requiredFunds) {
            return ReturnStatus.INSUFFICIENT_FUNDS;
        }

        // Create a new limit order for buying
        LimitOrder order = new LimitOrder(stockId, quantity, limitPrice, enforcementType);
        order.setIsBuyOrder(true);  // This is a buy order
        order.saveInDB();

        // Submit the order to the stock exchange
        boolean success = StockExchange.getInstance().placeOrder(order);
        
        if (!success) {
            // Order placement failed, update status
            order.setStatus(OrderStatus.CANCELLED);
            order.saveInDB();
        } else {
            // Order placed successfully, add to active orders
            activeOrders.put(order.getOrderId(), order);
        }
        
        return success ? ReturnStatus.SUCCESS : ReturnStatus.FAIL;
    }

    /**
     * Callback method invoked by the stock exchange when there's an update to an order.
     * This is called when an order is filled (completely or partially) or cancelled.
     * 
     * @param orderId The ID of the order that was updated
     * @param orderParts List of order parts representing fills
     * @param status The new status of the order
     */
    public void callbackStockExchange(String orderId, List<OrderPart> orderParts, OrderStatus status) {
        // Get the order from active orders
        Order order = activeOrders.get(orderId);
        
        if (order != null) {
            // Add the new order parts (fills) to the order
            order.addOrderParts(orderParts);
            
            // Update the order status
            order.setStatus(status);
            
            // Persist the changes to database
            order.updateInDB();

            // If order is completely filled or cancelled, remove from active orders
            if (status == OrderStatus.FILLED || status == OrderStatus.CANCELLED) {
                activeOrders.remove(orderId);
            }
        }
    }

    /**
     * Gets the available funds for trading.
     * 
     * @return Available cash balance
     */
    public double getAvailableFundsForTrading() {
        return availableFundsForTrading;
    }

    /**
     * Sets the available funds for trading.
     * 
     * @param funds New available funds amount
     */
    public void setAvailableFundsForTrading(double funds) {
        this.availableFundsForTrading = funds;
    }

    /**
     * Gets all stock positions held by this member.
     * 
     * @return Map of stock positions
     */
    public Map<String, StockPosition> getStockPositions() {
        return stockPositions;
    }

    /**
     * Gets all active orders for this member.
     * 
     * @return Map of active orders
     */
    public Map<String, Order> getActiveOrders() {
        return activeOrders;
    }
}
