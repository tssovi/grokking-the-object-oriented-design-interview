import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Shopping-related classes for Online Shopping System
 * 
 * This file contains classes for managing shopping carts, items, orders, and order logs.
 */

/**
 * Item represents a product in a shopping cart or order
 * Contains product reference, quantity, and price information
 */
class Item {
    private String productId;
    private int quantity;
    private double price;

    /**
     * Constructor to create a new item
     * 
     * @param id The product ID
     * @param quantity The quantity of this product
     * @param price The price per unit
     */
    public Item(String id, int quantity, double price) {
        this.productId = id;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Update the quantity of this item
     * 
     * @param quantity The new quantity
     */
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculate the total price for this item (quantity * price)
     * 
     * @return The total price
     */
    public double getTotalPrice() {
        return quantity * price;
    }

    // Getters
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setters
    public void setProductId(String productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
}

/**
 * ShoppingCart manages items that a customer intends to purchase
 * Provides functionality to add, remove, and update items
 */
class ShoppingCart {
    private List<Item> items;

    /**
     * Constructor to create a new empty shopping cart
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Add an item to the shopping cart
     * 
     * @param item The item to add
     * @return true if item was added successfully
     */
    public boolean addItem(Item item) {
        // Check if item already exists in cart
        for (Item existingItem : items) {
            if (existingItem.getProductId().equals(item.getProductId())) {
                // Update quantity instead of adding duplicate
                existingItem.updateQuantity(existingItem.getQuantity() + item.getQuantity());
                return true;
            }
        }
        // Add new item
        return items.add(item);
    }

    /**
     * Remove an item from the shopping cart
     * 
     * @param item The item to remove
     * @return true if item was removed successfully
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Update the quantity of a specific item in the cart
     * 
     * @param item The item to update
     * @param quantity The new quantity
     * @return true if update was successful
     */
    public boolean updateItemQuantity(Item item, int quantity) {
        for (Item cartItem : items) {
            if (cartItem.getProductId().equals(item.getProductId())) {
                cartItem.updateQuantity(quantity);
                return true;
            }
        }
        return false;
    }

    /**
     * Get all items in the shopping cart
     * 
     * @return List of items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Calculate the total cost of all items in the cart
     * 
     * @return The total cost
     */
    public double getTotalCost() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    /**
     * Checkout the shopping cart
     * Converts the cart into an order
     * 
     * @return The created order, or null if checkout fails
     */
    public Order checkout() {
        // Implementation would create an order from cart items
        return null;
    }

    /**
     * Clear all items from the shopping cart
     */
    public void clear() {
        items.clear();
    }
}

/**
 * OrderLog represents a log entry for tracking order status changes
 * Maintains history of order state transitions
 */
class OrderLog {
    private String orderNumber;
    private Date creationDate;
    private OrderStatus status;

    /**
     * Constructor to create a new order log entry
     * 
     * @param orderNumber The order number this log belongs to
     * @param status The status at the time of this log entry
     */
    public OrderLog(String orderNumber, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.creationDate = new Date();
        this.status = (status != null) ? status : OrderStatus.PENDING;
    }

    // Getters
    public String getOrderNumber() { return orderNumber; }
    public Date getCreationDate() { return creationDate; }
    public OrderStatus getStatus() { return status; }

    // Setters
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setStatus(OrderStatus status) { this.status = status; }
}

/**
 * Order represents a customer's purchase order
 * Contains order details, status, and order history
 */
class Order {
    private String orderNumber;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderLog> orderLogs;
    private List<Item> items;

    /**
     * Constructor to create a new order
     * 
     * @param orderNumber Unique identifier for the order
     * @param status Initial status of the order
     */
    public Order(String orderNumber, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.status = (status != null) ? status : OrderStatus.PENDING;
        this.orderDate = new Date();
        this.orderLogs = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    /**
     * Send the order for shipment
     * Changes order status and initiates shipping process
     * 
     * @return true if shipment was initiated successfully
     */
    public boolean sendForShipment() {
        // Implementation would initiate shipment process
        this.status = OrderStatus.SHIPPED;
        addOrderLog(new OrderLog(this.orderNumber, OrderStatus.SHIPPED));
        return true;
    }

    /**
     * Process payment for the order
     * 
     * @param payment The payment information
     * @return true if payment was processed successfully
     */
    public boolean makePayment(Object payment) {
        // Implementation would process payment
        return false;
    }

    /**
     * Add a log entry to track order status changes
     * 
     * @param orderLog The log entry to add
     */
    public void addOrderLog(OrderLog orderLog) {
        this.orderLogs.add(orderLog);
    }

    /**
     * Add an item to the order
     * 
     * @param item The item to add
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Calculate the total cost of the order
     * 
     * @return The total cost
     */
    public double getTotalCost() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Getters
    public String getOrderNumber() { return orderNumber; }
    public OrderStatus getStatus() { return status; }
    public Date getOrderDate() { return orderDate; }
    public List<OrderLog> getOrderLogs() { return orderLogs; }
    public List<Item> getItems() { return items; }

    // Setters
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setStatus(OrderStatus status) { 
        this.status = status;
        addOrderLog(new OrderLog(this.orderNumber, status));
    }
}
