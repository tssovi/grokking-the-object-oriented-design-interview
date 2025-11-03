/**
 * Encapsulation Example in Java
 * 
 * Encapsulation is one of the four fundamental principles of Object-Oriented Programming (OOP).
 * It is the mechanism of wrapping data (variables) and code (methods) together as a single unit,
 * and restricting direct access to some of an object's components.
 * 
 * Key Concepts:
 * - Data hiding: Private fields cannot be accessed directly from outside the class
 * - Access control: Use private, protected, and public modifiers to control access
 * - Getters and Setters: Provide controlled access to private fields
 * - Validation: Setters can include validation logic before modifying data
 * - Security: Prevents unauthorized or unintended modification of data
 * 
 * This example demonstrates:
 * - Private field that cannot be accessed directly
 * - Public getter method to read the private field
 * - Public setter method to modify the private field with control
 * - How direct field access attempts are ignored (in Java, they cause compilation errors)
 */

/**
 * Product class demonstrating encapsulation.
 * The maxPrice field is private and can only be accessed through public methods.
 */
class Product {
    
    /**
     * Private field - encapsulated data.
     * The double underscore naming in Python indicated private fields.
     * In Java, we use the 'private' keyword to achieve true encapsulation.
     * This field cannot be accessed directly from outside this class.
     */
    private int maxPrice;
    
    /**
     * Constructor to initialize the Product object.
     * Sets the initial maximum price to 900.
     */
    public Product() {
        this.maxPrice = 900;
    }
    
    /**
     * Public method to display the selling price.
     * This demonstrates controlled access to private data.
     * External code can view the price but cannot modify it directly.
     */
    public void sell() {
        System.out.println("Selling Price: " + this.maxPrice);
    }
    
    /**
     * Setter method to modify the maximum price.
     * This is the controlled way to change the private maxPrice field.
     * 
     * Benefits of using a setter:
     * - Can add validation logic (e.g., price must be positive)
     * - Can add logging or auditing
     * - Can trigger other actions when price changes
     * - Maintains encapsulation while allowing controlled modification
     * 
     * @param price The new maximum price to set
     */
    public void setMaxPrice(int price) {
        // In a real application, you might add validation here:
        // if (price > 0) {
        //     this.maxPrice = price;
        // } else {
        //     throw new IllegalArgumentException("Price must be positive");
        // }
        this.maxPrice = price;
    }
    
    /**
     * Getter method to retrieve the maximum price.
     * This provides read-only access to the private field.
     * 
     * @return The current maximum price
     */
    public int getMaxPrice() {
        return this.maxPrice;
    }
}

/**
 * Main class to demonstrate encapsulation in action.
 */
public class Encapsulation {
    
    /**
     * Main method - entry point of the program.
     * Demonstrates how encapsulation protects data and provides controlled access.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a Product object
        Product product = new Product();
        
        // Display initial price using the public sell() method
        System.out.println("Initial price:");
        product.sell();
        
        System.out.println("\n--- Attempting Direct Access (This would fail in Java) ---");
        // In Python, this line attempted direct access: product.__maxprice = 1000
        // In Java, direct access to private fields is prevented at compile time
        // Uncommenting the line below would cause a compilation error:
        // product.maxPrice = 1000;  // ERROR: maxPrice has private access in Product
        
        System.out.println("After attempting direct modification:");
        product.sell();  // Price remains unchanged because we cannot access it directly
        
        System.out.println("\n--- Using Setter Method (Proper Encapsulation) ---");
        // The correct way to modify the price is through the setter method
        product.setMaxPrice(1000);
        System.out.println("After using setMaxPrice(1000):");
        product.sell();  // Price is now updated to 1000
        
        System.out.println("\n--- Additional Demonstration ---");
        // We can also use the getter method to retrieve the value
        System.out.println("Getting price using getter: " + product.getMaxPrice());
        
        // Demonstrate multiple price changes
        product.setMaxPrice(1500);
        System.out.println("After setting price to 1500:");
        product.sell();
        
        System.out.println("\n--- Benefits of Encapsulation ---");
        System.out.println("1. Data is protected from unauthorized access");
        System.out.println("2. Internal implementation can change without affecting external code");
        System.out.println("3. Validation logic can be added in setters");
        System.out.println("4. Better control over how data is accessed and modified");
    }
}
