/**
 * Polymorphism Example in Java
 * 
 * Polymorphism is one of the four fundamental principles of Object-Oriented Programming (OOP).
 * The word "polymorphism" means "many forms" - it allows objects of different classes to be
 * treated as objects of a common parent class, while each maintains its own specific behavior.
 * 
 * Key Concepts:
 * - Method Overriding: Subclasses provide specific implementations of methods
 * - Runtime Polymorphism: The actual method called is determined at runtime
 * - Common Interface: Different classes can be used interchangeably through a common interface
 * - Flexibility: Code can work with objects without knowing their specific types
 * 
 * Types of Polymorphism in Java:
 * 1. Compile-time Polymorphism (Method Overloading) - not shown in this example
 * 2. Runtime Polymorphism (Method Overriding) - demonstrated here
 * 
 * This example demonstrates:
 * - Multiple classes with the same method name but different implementations
 * - A common interface (ChessPiece) that defines the contract
 * - How polymorphism allows treating different objects uniformly
 * - Runtime method resolution based on actual object type
 */

/**
 * Interface defining the common contract for all chess pieces.
 * In Java, interfaces provide a way to achieve polymorphism and abstraction.
 * All chess pieces must implement the move() method.
 */
interface ChessPiece {
    /**
     * Method to define how a chess piece moves.
     * Each implementing class will provide its own specific movement rules.
     */
    void move();
}

/**
 * Bishop class implementing the ChessPiece interface.
 * Represents a Bishop chess piece with its specific movement pattern.
 */
class Bishop implements ChessPiece {
    
    /**
     * Implementation of the move() method for Bishop.
     * Bishops move diagonally on the chess board.
     * 
     * This is an example of polymorphism - the same method name (move)
     * has different behavior depending on the object type.
     */
    @Override
    public void move() {
        System.out.println("Bishops can move diagonally");
    }
    
    /**
     * Additional method specific to Bishop.
     * Demonstrates that classes can have their own unique methods
     * beyond the interface requirements.
     */
    public void displayPiece() {
        System.out.println("This is a Bishop piece");
    }
}

/**
 * Knight class implementing the ChessPiece interface.
 * Represents a Knight chess piece with its specific movement pattern.
 */
class Knight implements ChessPiece {
    
    /**
     * Implementation of the move() method for Knight.
     * Knights have a unique L-shaped movement pattern.
     * 
     * This demonstrates polymorphism - same method signature as Bishop's move(),
     * but completely different implementation and behavior.
     */
    @Override
    public void move() {
        System.out.println("Knights can move two squares vertically and one square horizontally, " +
                         "or two squares horizontally and one square vertically");
    }
    
    /**
     * Additional method specific to Knight.
     */
    public void displayPiece() {
        System.out.println("This is a Knight piece");
    }
}

/**
 * Rook class implementing the ChessPiece interface.
 * Added to demonstrate polymorphism with more than two classes.
 */
class Rook implements ChessPiece {
    
    /**
     * Implementation of the move() method for Rook.
     * Rooks move horizontally or vertically.
     */
    @Override
    public void move() {
        System.out.println("Rooks can move horizontally or vertically any number of squares");
    }
}

/**
 * Main class to demonstrate polymorphism in action.
 */
public class Polymorphism {
    
    /**
     * Common interface method to test chess piece movement.
     * This method demonstrates polymorphism - it can accept any object
     * that implements the ChessPiece interface, and will call the appropriate
     * move() method based on the actual object type at runtime.
     * 
     * This is the key benefit of polymorphism: we can write generic code
     * that works with any chess piece without knowing its specific type.
     * 
     * @param chessPiece Any object implementing the ChessPiece interface
     */
    public static void moveTest(ChessPiece chessPiece) {
        // The actual move() method called depends on the runtime type of chessPiece
        // This is runtime polymorphism (also called dynamic method dispatch)
        chessPiece.move();
    }
    
    /**
     * Main method - entry point of the program.
     * Demonstrates various aspects of polymorphism.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Polymorphism Demonstration ===\n");
        
        // Instantiate objects of different chess piece types
        Bishop bishop = new Bishop();
        Knight knight = new Knight();
        Rook rook = new Rook();
        
        System.out.println("--- Direct Method Calls ---");
        // Direct method calls on specific object types
        bishop.move();
        knight.move();
        rook.move();
        
        System.out.println("\n--- Polymorphic Method Calls ---");
        // Passing objects to a common interface method
        // The moveTest method doesn't need to know the specific type
        System.out.print("Bishop: ");
        moveTest(bishop);
        
        System.out.print("Knight: ");
        moveTest(knight);
        
        System.out.print("Rook: ");
        moveTest(rook);
        
        System.out.println("\n--- Array of Polymorphic Objects ---");
        // Store different types in an array of the common interface type
        // This is a powerful feature of polymorphism
        ChessPiece[] pieces = {new Bishop(), new Knight(), new Rook()};
        
        // Iterate through the array and call move() on each piece
        // Each piece responds according to its own implementation
        for (int i = 0; i < pieces.length; i++) {
            System.out.print("Piece " + (i + 1) + ": ");
            pieces[i].move();
        }
        
        System.out.println("\n--- Runtime Type Determination ---");
        // Demonstrate that the actual method called is determined at runtime
        ChessPiece piece;
        
        piece = new Bishop();
        System.out.print("piece = new Bishop(): ");
        piece.move();  // Calls Bishop's move()
        
        piece = new Knight();
        System.out.print("piece = new Knight(): ");
        piece.move();  // Calls Knight's move()
        
        piece = new Rook();
        System.out.print("piece = new Rook(): ");
        piece.move();  // Calls Rook's move()
        
        System.out.println("\n--- Benefits of Polymorphism ---");
        System.out.println("1. Code Flexibility: Same method works with different object types");
        System.out.println("2. Extensibility: New chess pieces can be added without changing existing code");
        System.out.println("3. Maintainability: Changes to specific implementations don't affect common interface");
        System.out.println("4. Loose Coupling: Code depends on interfaces, not concrete implementations");
    }
}
