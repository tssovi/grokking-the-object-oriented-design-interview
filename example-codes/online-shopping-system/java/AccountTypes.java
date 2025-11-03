import java.util.ArrayList;
import java.util.List;

/**
 * Account and Customer-related classes for Online Shopping System
 * 
 * This file contains the account hierarchy including base Account class,
 * Customer abstract class, and concrete implementations for Guest and Member.
 * 
 * Note: For simplicity, we are not defining all getter and setter functions.
 * The reader can assume that all class attributes are private and accessed
 * through their respective public getter methods and modified only through
 * their public setter methods.
 */

/**
 * Account represents a user account in the online shopping system
 * Contains user credentials, contact information, and payment methods
 */
class Account {
    private String userName;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Address shippingAddress;
    private AccountStatus status;
    private List<Object> creditCards;      // List of credit card information
    private List<Object> bankAccounts;     // List of bank account information

    /**
     * Constructor to create a new account
     * 
     * @param userName Unique username for the account
     * @param password Password for the account
     * @param name Full name of the account holder
     * @param email Email address
     * @param phone Phone number
     * @param shippingAddress Default shipping address
     * @param status Initial account status (defaults to ACTIVE)
     */
    public Account(String userName, String password, String name, String email, 
                   String phone, Address shippingAddress, AccountStatus status) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.shippingAddress = shippingAddress;
        this.status = (status != null) ? status : AccountStatus.ACTIVE;
        this.creditCards = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    /**
     * Add a product to the catalog (for seller accounts)
     * 
     * @param product The product to add
     */
    public void addProduct(Product product) {
        // Implementation would add product to seller's catalog
    }

    /**
     * Add a product review
     * 
     * @param review The review to add
     */
    public void addProductReview(ProductReview review) {
        // Implementation would add review to the system
    }

    /**
     * Reset the account password
     * Typically would involve verification and security checks
     */
    public void resetPassword() {
        // Implementation would handle password reset logic
    }

    // Getters
    public String getUserName() { return userName; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Address getShippingAddress() { return shippingAddress; }
    public AccountStatus getStatus() { return status; }
    public List<Object> getCreditCards() { return creditCards; }
    public List<Object> getBankAccounts() { return bankAccounts; }

    // Setters
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setShippingAddress(Address address) { this.shippingAddress = address; }
    public void setStatus(AccountStatus status) { this.status = status; }
}

/**
 * Customer is an abstract base class for all customer types
 * Provides common functionality for shopping cart and order management
 */
abstract class Customer {
    private ShoppingCart cart;
    private Order order;

    /**
     * Constructor to create a customer with a shopping cart and order
     * 
     * @param cart The shopping cart for this customer
     * @param order The current order for this customer
     */
    public Customer(ShoppingCart cart, Order order) {
        this.cart = cart;
        this.order = order;
    }

    /**
     * Get the customer's shopping cart
     * 
     * @return The shopping cart
     */
    public ShoppingCart getShoppingCart() {
        return cart;
    }

    /**
     * Add an item to the shopping cart
     * 
     * @param item The item to add
     */
    public void addItemToCart(Item item) {
        // Implementation would add item to cart
    }

    /**
     * Remove an item from the shopping cart
     * 
     * @param item The item to remove
     */
    public void removeItemFromCart(Item item) {
        // Implementation would remove item from cart
    }

    // Getters
    public Order getOrder() { return order; }

    // Setters
    public void setCart(ShoppingCart cart) { this.cart = cart; }
    public void setOrder(Order order) { this.order = order; }
}

/**
 * Guest represents a customer who is not logged in
 * Guests can browse and shop but must register to complete purchases
 */
class Guest extends Customer {
    
    /**
     * Constructor for Guest customer
     * 
     * @param cart The shopping cart for the guest
     * @param order The current order for the guest
     */
    public Guest(ShoppingCart cart, Order order) {
        super(cart, order);
    }

    /**
     * Register a new account
     * Converts a guest to a registered member
     * 
     * @return The newly created account
     */
    public Account registerAccount() {
        // Implementation would create and return a new account
        return null;
    }
}

/**
 * Member represents a registered customer with an account
 * Members have full access to all shopping features
 */
class Member extends Customer {
    private Account account;

    /**
     * Constructor for Member customer
     * 
     * @param cart The shopping cart for the member
     * @param order The current order for the member
     * @param account The account associated with this member
     */
    public Member(ShoppingCart cart, Order order, Account account) {
        super(cart, order);
        this.account = account;
    }

    /**
     * Place an order
     * Processes the current shopping cart into an order
     * 
     * @param order The order to place
     * @return true if order was placed successfully, false otherwise
     */
    public boolean placeOrder(Order order) {
        // Implementation would process and place the order
        return false;
    }

    // Getters
    public Account getAccount() { return account; }

    // Setters
    public void setAccount(Account account) { this.account = account; }
}
