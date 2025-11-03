/**
 * Abstraction Example in Java
 * 
 * Abstraction is one of the four fundamental principles of Object-Oriented Programming (OOP).
 * It focuses on hiding the implementation details and showing only the essential features
 * of an object to the user.
 * 
 * Key Concepts:
 * - Abstract classes cannot be instantiated directly
 * - Abstract methods must be implemented by concrete subclasses
 * - Provides a template for derived classes
 * - Helps achieve loose coupling and code reusability
 * 
 * This example demonstrates:
 * - An abstract parent class with both concrete and abstract methods
 * - Two child classes implementing the abstract method differently
 * - How abstraction allows different implementations of the same interface
 */

/**
 * Abstract parent class demonstrating abstraction.
 * This class defines a common interface for all child classes.
 */
abstract class Parent {
    
    /**
     * Concrete method that is common to all child classes.
     * This method has a default implementation that can be used by all subclasses.
     */
    public void common() {
        System.out.println("In common method of Parent");
    }
    
    /**
     * Abstract method that must be implemented by all child classes.
     * Each child class will provide its own specific implementation.
     * This enforces a contract that all children must follow.
     */
    public abstract void vary();
}

/**
 * First concrete implementation of the Parent abstract class.
 * This class provides its own specific implementation of the vary() method.
 */
class Child1 extends Parent {
    
    /**
     * Implementation of the abstract vary() method for Child1.
     * This demonstrates how different child classes can have different behaviors
     * while maintaining the same method signature.
     */
    @Override
    public void vary() {
        System.out.println("In vary method of Child1");
    }
}

/**
 * Second concrete implementation of the Parent abstract class.
 * This class provides a different implementation of the vary() method.
 */
class Child2 extends Parent {
    
    /**
     * Implementation of the abstract vary() method for Child2.
     * This shows polymorphic behavior - same method name, different implementation.
     */
    @Override
    public void vary() {
        System.out.println("In vary method of Child2");
    }
}

/**
 * Main class to demonstrate abstraction in action.
 */
public class Abstraction {
    
    /**
     * Main method - entry point of the program.
     * Demonstrates how abstraction allows us to work with objects through
     * their abstract parent type while getting specific behavior from concrete implementations.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an object of Child1 class
        // Note: We can reference it as Parent type due to abstraction
        Child1 child1 = new Child1();
        child1.common();  // Calls the inherited common method
        child1.vary();    // Calls Child1's specific implementation
        
        System.out.println(); // Empty line for better readability
        
        // Create an object of Child2 class
        Child2 child2 = new Child2();
        child2.common();  // Calls the same inherited common method
        child2.vary();    // Calls Child2's specific implementation
        
        System.out.println("\n--- Demonstrating Polymorphism through Abstraction ---");
        
        // We can also use Parent reference type (polymorphism)
        // This shows how abstraction enables polymorphic behavior
        Parent[] children = {new Child1(), new Child2()};
        
        for (int i = 0; i < children.length; i++) {
            System.out.println("\nChild " + (i + 1) + ":");
            children[i].common();
            children[i].vary();
        }
    }
}
