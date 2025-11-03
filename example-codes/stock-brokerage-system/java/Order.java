package stockbrokerage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class representing an order in the stock brokerage system.
 * This class contains common properties and methods shared by all order types.
 * Subclasses should implement specific order types (e.g., LimitOrder, MarketOrder).
 */
public abstract class Order {
    // Unique identifier for this order
    private String orderId;
    
    // Flag indicating if this is a buy order (true) or sell order (false)
    private boolean isBuyOrder;
    
    // Current status of the order (OPEN, FILLED, etc.)
    private OrderStatus status;
    
    // Time enforcement type for this order (how long it stays active)
    private TimeEnforcementType timeEnforcement;
    
    // Timestamp when the order was created
    private Date creationTime;
    
    // Map of order parts - orders can be filled in multiple parts/transactions
    // Key: part ID, Value: OrderPart object
    private Map<String, OrderPart> parts;

    /**
     * Constructor to initialize an Order with a unique ID.
     * Sets default values for order properties.
     * 
     * @param id Unique identifier for this order
     */
    public Order(String id) {
        this.orderId = id;
        this.isBuyOrder = false;
        this.status = OrderStatus.OPEN;
        this.timeEnforcement = TimeEnforcementType.ON_THE_OPEN;
        this.creationTime = new Date();
        this.parts = new HashMap<>();
    }

    /**
     * Sets the status of this order.
     * 
     * @param status The new status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Gets the current status of this order.
     * 
     * @return The current order status
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
     * Gets the unique order ID.
     * 
     * @return The order ID
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * Checks if this is a buy order.
     * 
     * @return true if buy order, false if sell order
     */
    public boolean isBuyOrder() {
        return this.isBuyOrder;
    }

    /**
     * Sets whether this is a buy order or sell order.
     * 
     * @param isBuyOrder true for buy order, false for sell order
     */
    public void setIsBuyOrder(boolean isBuyOrder) {
        this.isBuyOrder = isBuyOrder;
    }

    /**
     * Saves this order to the database.
     * This is a placeholder method that should be implemented with actual database logic.
     */
    public void saveInDB() {
        // TODO: Implement database save logic
        // This would typically use JDBC or an ORM like Hibernate
    }

    /**
     * Updates this order in the database.
     * This is a placeholder method that should be implemented with actual database logic.
     */
    public void updateInDB() {
        // TODO: Implement database update logic
    }

    /**
     * Adds order parts to this order.
     * Orders can be filled in multiple parts/transactions, this method tracks them.
     * 
     * @param parts List of OrderPart objects representing partial fills
     */
    public void addOrderParts(List<OrderPart> parts) {
        for (OrderPart part : parts) {
            this.parts.put(part.getId(), part);
        }
    }

    /**
     * Gets all order parts associated with this order.
     * 
     * @return Map of order parts
     */
    public Map<String, OrderPart> getParts() {
        return this.parts;
    }
}

/**
 * Represents a limit order - an order to buy or sell at a specific price or better.
 * A buy limit order will only execute at the limit price or lower.
 * A sell limit order will only execute at the limit price or higher.
 */
class LimitOrder extends Order {
    // The price limit for this order
    private double priceLimit;

    /**
     * Constructor for creating a limit order.
     * 
     * @param stockId The ID of the stock to trade
     * @param quantity The number of shares to trade
     * @param limitPrice The limit price for the order
     * @param enforcementType How long the order should remain active
     */
    public LimitOrder(String stockId, int quantity, double limitPrice, TimeEnforcementType enforcementType) {
        // Call parent constructor with a generated order ID
        super(generateOrderId());
        this.priceLimit = limitPrice;
    }

    /**
     * Gets the price limit for this order.
     * 
     * @return The limit price
     */
    public double getPriceLimit() {
        return this.priceLimit;
    }

    /**
     * Sets the price limit for this order.
     * 
     * @param priceLimit The new limit price
     */
    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    /**
     * Generates a unique order ID.
     * This is a placeholder - in production, this would use a more robust ID generation strategy.
     * 
     * @return A unique order ID string
     */
    private static String generateOrderId() {
        // TODO: Implement proper ID generation (UUID, database sequence, etc.)
        return "ORD-" + System.currentTimeMillis();
    }
}

/**
 * Represents a part of an order that has been filled.
 * Orders can be filled in multiple parts at different prices and times.
 */
class OrderPart {
    // Unique identifier for this order part
    private String id;
    
    // The price at which this part was filled
    private double price;
    
    // The quantity filled in this part
    private int quantity;
    
    // Timestamp when this part was executed
    private Date executionTime;

    /**
     * Constructor for creating an order part.
     * 
     * @param id Unique identifier for this part
     * @param price Execution price
     * @param quantity Number of shares filled
     */
    public OrderPart(String id, double price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.executionTime = new Date();
    }

    /**
     * Gets the ID of this order part.
     * 
     * @return The order part ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the execution price for this part.
     * 
     * @return The price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets the quantity filled in this part.
     * 
     * @return The quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Gets the execution time for this part.
     * 
     * @return The execution timestamp
     */
    public Date getExecutionTime() {
        return this.executionTime;
    }
}
