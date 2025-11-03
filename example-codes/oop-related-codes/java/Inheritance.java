/**
 * Inheritance Example in Java
 * 
 * Inheritance is one of the four fundamental principles of Object-Oriented Programming (OOP).
 * It is a mechanism where a new class (child/derived class) inherits properties and behaviors
 * from an existing class (parent/base class).
 * 
 * Key Concepts:
 * - Code Reusability: Child classes inherit methods and fields from parent classes
 * - IS-A Relationship: Employee IS-A Person (inheritance represents this relationship)
 * - Method Overriding: Child classes can provide specific implementations of parent methods
 * - extends keyword: Used in Java to establish inheritance relationship
 * - super keyword: Used to refer to parent class members
 * 
 * This example demonstrates:
 * - A parent class (Person) with common attributes and methods
 * - A child class (Employee) that extends Person
 * - Method overriding to provide specialized behavior
 * - How child classes inherit parent class methods
 */

/**
 * Person class - the parent/base class.
 * This class represents a general person with basic attributes and behaviors.
 * It serves as a foundation for more specialized classes.
 */
class Person {
    
    /**
     * Protected field to store the person's name.
     * Protected allows access from subclasses while maintaining encapsulation.
     */
    protected String name;
    
    /**
     * Constructor to initialize a Person object.
     * 
     * @param name The name of the person
     */
    public Person(String name) {
        this.name = name;
    }
    
    /**
     * Getter method to retrieve the person's name.
     * This method will be inherited by all subclasses.
     * 
     * @return The name of the person
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Method to check if this person is an employee.
     * Default implementation returns false.
     * Subclasses can override this method to provide specific behavior.
     * 
     * @return false by default (a general person is not an employee)
     */
    public boolean isEmployee() {
        return false;
    }
    
    /**
     * Method to display person information.
     * This demonstrates a method that can be inherited and used by child classes.
     */
    public void displayInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Is Employee: " + this.isEmployee());
    }
}

/**
 * Employee class - the child/derived class.
 * This class extends Person and inherits all its properties and methods.
 * It represents a specialized type of Person.
 */
class Employee extends Person {
    
    /**
     * Constructor to initialize an Employee object.
     * Uses super() to call the parent class constructor.
     * 
     * @param name The name of the employee
     */
    public Employee(String name) {
        // Call the parent class constructor to initialize the name field
        super(name);
    }
    
    /**
     * Overridden method to check if this person is an employee.
     * This demonstrates method overriding - providing a specific implementation
     * in the child class that differs from the parent class.
     * 
     * The @Override annotation indicates that this method overrides a parent method.
     * It helps catch errors at compile time if the method signature doesn't match.
     * 
     * @return true (an Employee is always an employee)
     */
    @Override
    public boolean isEmployee() {
        return true;
    }
    
    /**
     * Additional method specific to Employee class.
     * This demonstrates that child classes can have their own unique methods
     * in addition to inherited ones.
     * 
     * @return A string describing the employee's role
     */
    public String getRole() {
        return "Employee";
    }
}

/**
 * Main class to demonstrate inheritance in action.
 */
public class Inheritance {
    
    /**
     * Main method - entry point of the program.
     * Demonstrates how inheritance allows code reuse and polymorphic behavior.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Inheritance Demonstration ===\n");
        
        // Create a Person object
        Person person = new Person("Person 1");
        // The Person class has both getName() and isEmployee() methods
        System.out.println(person.getName() + " is employee: " + person.isEmployee());
        
        System.out.println(); // Empty line for readability
        
        // Create an Employee object
        Employee employee = new Employee("Employee 1");
        // Employee inherits getName() from Person
        // Employee overrides isEmployee() to return true
        System.out.println(employee.getName() + " is employee: " + employee.isEmployee());
        
        System.out.println("\n--- Detailed Information ---");
        
        // Display detailed information using inherited method
        System.out.println("\nPerson Details:");
        person.displayInfo();
        
        System.out.println("\nEmployee Details:");
        employee.displayInfo();
        // Employee can also use its own specific method
        System.out.println("Role: " + employee.getRole());
        
        System.out.println("\n--- Demonstrating Polymorphism through Inheritance ---");
        
        // Polymorphism: Employee object can be referenced as Person type
        Person personRef = new Employee("Employee 2");
        System.out.println(personRef.getName() + " is employee: " + personRef.isEmployee());
        // Even though referenced as Person, it calls Employee's overridden method
        
        System.out.println("\n--- Benefits of Inheritance ---");
        System.out.println("1. Code Reusability: Employee reuses Person's getName() method");
        System.out.println("2. Method Overriding: Employee provides its own isEmployee() implementation");
        System.out.println("3. Extensibility: New person types can be easily added");
        System.out.println("4. Polymorphism: Objects can be treated as their parent type");
    }
}
