import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order.java
 * 
 * This file contains classes related to order management in the restaurant,
 * including meal items, meals, checks, and orders.
 */

/**
 * Represents a single item within a meal.
 * 
 * A meal item links a menu item with a quantity, allowing customers
 * to order multiple servings of the same dish.
 */
class MealItem {
    // Private fields for meal item information
    private String mealItemId;
    private int quantity;
    private MenuItem menuItem;

    /**
     * Constructor to create a new MealItem.
     * 
     * @param mealItemId Unique identifier for this meal item
     * @param quantity Number of servings of this menu item
     * @param menuItem The MenuItem being ordered
     */
    public MealItem(String mealItemId, int quantity, MenuItem menuItem) {
        this.mealItemId = mealItemId;
        this.quantity = quantity;
        this.menuItem = menuItem;
    }

    /**
     * Updates the quantity of this meal item.
     * 
     * This allows customers to modify their order before it's sent to the kitchen.
     * 
     * @param newQuantity The new quantity to set
     * @return boolean indicating whether the update was successful
     */
    public boolean updateQuantity(int newQuantity) {
        // TODO: Implement quantity update logic
        // This would typically involve:
        // 1. Validate new quantity (must be positive)
        // 2. Check if order can still be modified
        // 3. Update quantity
        // 4. Recalculate meal total
        if (newQuantity > 0) {
            this.quantity = newQuantity;
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getMealItemId() {
        return mealItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Calculates the total price for this meal item.
     * 
     * @return Total price (quantity * item price)
     */
    public double getTotalPrice() {
        return quantity * menuItem.getPrice();
    }
}

/**
 * Represents a meal for a specific seat at a table.
 * 
 * A meal is a collection of meal items ordered by a single person.
 * Each seat at a table can have its own meal, allowing for individual orders.
 */
class Meal {
    // Private fields for meal information
    private String mealId;
    private TableSeat seat;
    private List<MealItem> mealItems;

    /**
     * Constructor to create a new Meal.
     * 
     * @param mealId Unique identifier for this meal
     * @param seat The table seat this meal is associated with
     */
    public Meal(String mealId, TableSeat seat) {
        this.mealId = mealId;
        this.seat = seat;
        this.mealItems = new ArrayList<>();
    }

    /**
     * Adds a meal item to this meal.
     * 
     * @param mealItem The MealItem to add to this meal
     * @return boolean indicating whether the item was successfully added
     */
    public boolean addMealItem(MealItem mealItem) {
        // TODO: Implement meal item addition logic
        // This would typically involve:
        // 1. Validate meal item
        // 2. Add to meal's item list
        // 3. Update meal total
        if (mealItem != null) {
            mealItems.add(mealItem);
            return true;
        }
        return false;
    }

    /**
     * Calculates the total price for this meal.
     * 
     * @return Total price of all meal items
     */
    public double getMealTotal() {
        double total = 0.0;
        for (MealItem item : mealItems) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Getter methods
    
    public String getMealId() {
        return mealId;
    }

    public TableSeat getSeat() {
        return seat;
    }

    public List<MealItem> getMealItems() {
        return new ArrayList<>(mealItems); // Return a copy to prevent external modification
    }
}

/**
 * Represents a check (bill) for an order.
 * 
 * The check contains payment information and the final amount due.
 * It's generated when customers are ready to pay for their meal.
 */
class Check {
    // Private fields for check information
    private String checkId;
    private double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime generatedTime;

    /**
     * Default constructor to create a new Check.
     * Initializes with default values.
     */
    public Check() {
        this.checkId = "";
        this.amount = 0.0;
        this.paymentStatus = PaymentStatus.UNPAID;
        this.generatedTime = LocalDateTime.now();
    }

    /**
     * Constructor to create a new Check with specific details.
     * 
     * @param checkId Unique identifier for this check
     * @param amount Total amount due
     */
    public Check(String checkId, double amount) {
        this.checkId = checkId;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.UNPAID;
        this.generatedTime = LocalDateTime.now();
    }

    /**
     * Processes payment for this check.
     * 
     * @param paymentStatus The new payment status
     * @return boolean indicating whether payment was processed successfully
     */
    public boolean processPayment(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return true;
    }

    // Getter and setter methods
    
    public String getCheckId() {
        return checkId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}

/**
 * Represents a complete order for a table.
 * 
 * An order contains multiple meals (one per seat), tracks the order status,
 * and associates the order with the responsible waiter and chef.
 * This is the main class for managing the entire ordering process.
 */
class Order {
    // Private fields for order information
    private String orderId;
    private OrderStatus orderStatus;
    private LocalDateTime creationTime;
    private List<Meal> meals;
    private Table table;
    private Employee waiter;
    private Chef chef;
    private Check check;

    /**
     * Constructor to create a new Order.
     * 
     * @param orderId Unique identifier for this order
     * @param orderStatus Initial status of the order
     * @param table The table this order is for
     * @param waiter The waiter responsible for this order
     * @param chef The chef assigned to prepare this order
     */
    public Order(String orderId, OrderStatus orderStatus, Table table, Employee waiter, Chef chef) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.creationTime = LocalDateTime.now();
        this.meals = new ArrayList<>();
        this.table = table;
        this.waiter = waiter;
        this.chef = chef;
        this.check = new Check();
    }

    /**
     * Adds a meal to this order.
     * 
     * @param meal The Meal object to add to this order
     * @return boolean indicating whether the meal was successfully added
     */
    public boolean addMeal(Meal meal) {
        // TODO: Implement meal addition logic
        // This would typically involve:
        // 1. Validate meal
        // 2. Add to order's meal list
        // 3. Update order total
        // 4. Notify kitchen if order is already being prepared
        if (meal != null) {
            meals.add(meal);
            updateCheckAmount();
            return true;
        }
        return false;
    }

    /**
     * Removes a meal from this order.
     * 
     * @param meal The Meal object to remove from this order
     * @return boolean indicating whether the meal was successfully removed
     */
    public boolean removeMeal(Meal meal) {
        // TODO: Implement meal removal logic
        // This would typically involve:
        // 1. Check if order can still be modified
        // 2. Remove meal from list
        // 3. Update order total
        // 4. Notify kitchen if necessary
        if (meals.remove(meal)) {
            updateCheckAmount();
            return true;
        }
        return false;
    }

    /**
     * Updates the check amount based on all meals in the order.
     */
    private void updateCheckAmount() {
        double total = 0.0;
        for (Meal meal : meals) {
            total += meal.getMealTotal();
        }
        check.setAmount(total);
    }

    /**
     * Gets the current status of this order.
     * 
     * @return The current OrderStatus
     */
    public OrderStatus getStatus() {
        return orderStatus;
    }

    /**
     * Sets the status of this order.
     * 
     * @param status The new OrderStatus to set
     * @return boolean indicating whether the status was successfully updated
     */
    public boolean setStatus(OrderStatus status) {
        // TODO: Implement status update logic
        // This would typically involve:
        // 1. Validate status transition
        // 2. Update order status
        // 3. Notify relevant parties (waiter, chef, customer)
        // 4. Log status change
        this.orderStatus = status;
        return true;
    }

    // Getter methods
    
    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public List<Meal> getMeals() {
        return new ArrayList<>(meals); // Return a copy to prevent external modification
    }

    public Table getTable() {
        return table;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public Chef getChef() {
        return chef;
    }

    public Check getCheck() {
        return check;
    }
}
