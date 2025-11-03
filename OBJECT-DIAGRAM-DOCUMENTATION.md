# Object Diagram Documentation

## Overview

This document provides comprehensive documentation for the object-oriented design patterns and relationships across all case studies in the "Grokking the Object Oriented Design Interview" project.

The object diagram (`object-diagram.puml`) visualizes the class structures, relationships, and design patterns used across 8 major system designs:

1. **Library Management System**
2. **Parking Lot System**
3. **Online Shopping System**
4. **Chess Game System**
5. **ATM System**
6. **Hotel Management System**
7. **Airline Management System**
8. **Movie Ticket Booking System**

## How to View the Diagram

### Option 1: Using PlantUML Online
1. Visit [PlantUML Online Editor](http://www.plantuml.com/plantuml/uml/)
2. Copy the contents of `object-diagram.puml`
3. Paste into the editor to view the rendered diagram

### Option 2: Using VS Code
1. Install the "PlantUML" extension by jebbs
2. Open `object-diagram.puml`
3. Press `Alt+D` to preview the diagram

### Option 3: Using Command Line
```bash
# Install PlantUML
# Then run:
java -jar plantuml.jar object-diagram.puml
```

## System Designs Breakdown

### 1. Library Management System

**Core Classes:**
- `Account` (Abstract) → `Librarian`, `Member`
- `Book` (Abstract) → `BookItem`
- `BookReservation`, `BookLending`, `Fine`
- `Rack`

**Key Relationships:**
- Inheritance: Account is extended by Librarian and Member
- Composition: BookItem is placed at a Rack
- Association: Member interacts with BookReservation, BookLending, and Fine

**Design Patterns:**
- **Template Method Pattern**: Account class provides template for different account types
- **Factory Pattern**: Book creation can be handled through factory methods

**Key Features:**
- Members can checkout, return, and renew books
- Librarians can add books and manage members
- Fine calculation for overdue books
- Book reservation system

---

### 2. Parking Lot System

**Core Classes:**
- `ParkingLot` (Singleton)
- `ParkingFloor`, `ParkingSpot` (Abstract)
- `Vehicle` (Abstract) → `Car`, `Van`, `Truck`, `Motorbike`, `ElectricCar`
- `ParkingTicket`, `EntrancePanel`, `ExitPanel`

**Key Relationships:**
- Singleton: ParkingLot ensures single instance
- Inheritance: Different vehicle types and parking spot types
- Composition: ParkingLot contains multiple ParkingFloors, each with multiple ParkingSpots

**Design Patterns:**
- **Singleton Pattern**: ParkingLot class (only one instance in the system)
- **Strategy Pattern**: Different parking spot allocation strategies
- **Factory Pattern**: Vehicle and ParkingSpot creation

**Key Features:**
- Multi-floor parking management
- Different spot types (Compact, Large, Motorbike, Electric)
- Ticket generation and payment processing
- Real-time spot availability tracking

---

### 3. Online Shopping System

**Core Classes:**
- `ShoppingAccount`
- `Customer` (Abstract) → `Guest`, `ShoppingMember`
- `ShoppingCart`, `Item`, `Product`
- `Order`, `OrderLog`, `Shipment`

**Key Relationships:**
- Inheritance: Customer hierarchy (Guest vs Member)
- Composition: ShoppingCart contains Items, Order contains OrderLogs
- Association: Order linked to Shipment

**Design Patterns:**
- **State Pattern**: Order status management
- **Observer Pattern**: Order status notifications
- **Composite Pattern**: Shopping cart with multiple items

**Key Features:**
- Guest and member shopping capabilities
- Shopping cart management
- Order processing and tracking
- Product catalog and search
- Shipment tracking

---

### 4. Chess Game System

**Core Classes:**
- `ChessGame`, `ChessBoard`
- `Piece` (Abstract) → `King`, `Queen`, `Rook`, `Bishop`, `Knight`, `Pawn`
- `Position`, `MoveCommand`, `Player`

**Key Relationships:**
- Inheritance: All chess pieces inherit from Piece
- Composition: ChessBoard contains multiple Pieces
- Association: MoveCommand operates on Positions

**Design Patterns:**
- **Strategy Pattern**: Each piece has different movement strategy
- **Command Pattern**: MoveCommand encapsulates move operations
- **Template Method Pattern**: Piece class provides template for move validation

**Key Features:**
- Complete chess game logic
- Move validation for each piece type
- Check and checkmate detection
- Turn-based gameplay
- Position tracking

---

### 5. ATM System

**Core Classes:**
- `ATM`, `Bank`
- `BankAccount` (Abstract) → `SavingsAccount`, `CheckingAccount`
- `Transaction` (Abstract) → `BalanceInquiry`, `Deposit`, `Withdraw`, `Transfer`
- `Customer`, `Card`

**Key Relationships:**
- Inheritance: Different account types and transaction types
- Association: Customer has Card and BankAccount
- Composition: Bank contains multiple BankAccounts

**Design Patterns:**
- **Command Pattern**: Transaction hierarchy
- **State Pattern**: Transaction status management
- **Proxy Pattern**: ATM acts as proxy to Bank

**Key Features:**
- Multiple transaction types
- Account management (Savings/Checking)
- Card-based authentication
- Balance inquiry and fund transfers
- Cash withdrawal and deposit

---

### 6. Hotel Management System

**Core Classes:**
- `Hotel`, `HotelLocation`
- `Room` (Abstract) → `StandardRoom`, `DeluxeRoom`, `FamilySuite`, `BusinessSuite`
- `RoomBooking`, `RoomCharge`
- `HotelGuest`, `Receptionist`
- `Amenity`, `KitchenService`

**Key Relationships:**
- Inheritance: Different room types
- Composition: Hotel contains HotelLocations, which contain Rooms
- Association: RoomBooking links Guest to Room

**Design Patterns:**
- **Factory Pattern**: Room creation based on type
- **Observer Pattern**: Booking status notifications
- **Decorator Pattern**: Room amenities and services

**Key Features:**
- Multi-location hotel management
- Different room types and pricing
- Booking and reservation system
- Guest management
- Room services and amenities
- Invoice generation

---

### 7. Airline Management System

**Core Classes:**
- `Airline`, `Aircraft`, `Seat`
- `Flight`, `FlightInstance`
- `FlightReservation`, `Itinerary`
- `Airport`, `Passenger`

**Key Relationships:**
- Composition: Airline contains Aircraft, Aircraft contains Seats
- Association: FlightInstance links to Flight and Aircraft
- Aggregation: Itinerary contains multiple FlightReservations

**Design Patterns:**
- **Composite Pattern**: Itinerary with multiple reservations
- **Factory Pattern**: Flight and reservation creation
- **State Pattern**: Flight status management

**Key Features:**
- Flight scheduling and management
- Seat reservation system
- Multi-leg itinerary support
- Passenger management
- Airport and aircraft tracking
- Flight status updates

---

### 8. Movie Ticket Booking System

**Core Classes:**
- `Cinema`, `CinemaHall`, `CinemaHallSeat`
- `Show`, `Movie`
- `MovieBooking`, `ShowSeat`
- `Payment`

**Key Relationships:**
- Composition: Cinema contains CinemaHalls, CinemaHall contains Seats
- Association: Show links Movie to CinemaHall
- Aggregation: MovieBooking contains ShowSeats

**Design Patterns:**
- **Factory Pattern**: Show and booking creation
- **State Pattern**: Seat and booking status management
- **Observer Pattern**: Booking notifications

**Key Features:**
- Multi-hall cinema management
- Show scheduling
- Seat selection and booking
- Real-time seat availability
- Payment processing
- Booking confirmation

---

## Common Design Patterns Used

### 1. Inheritance (IS-A Relationship)
Used extensively across all systems to create specialized classes from general ones:
- Account → Librarian, Member
- Vehicle → Car, Van, Truck
- Customer → Guest, Member
- Piece → King, Queen, Rook, etc.

### 2. Composition (HAS-A Relationship)
Strong ownership relationships:
- ParkingLot has ParkingFloors
- ShoppingCart has Items
- ChessBoard has Pieces
- Cinema has CinemaHalls

### 3. Association
Weaker relationships between objects:
- Member uses BookReservation
- Customer places Order
- FlightReservation links to Passenger

### 4. Singleton Pattern
- `ParkingLot`: Ensures only one parking lot instance exists

### 5. Strategy Pattern
- Chess pieces: Each piece has different movement strategy
- Parking spot allocation: Different strategies for different vehicle types

### 6. Command Pattern
- ATM transactions: Each transaction type encapsulates an action
- Chess moves: MoveCommand encapsulates move operations

### 7. Factory Pattern
- Object creation across all systems (Books, Vehicles, Rooms, etc.)

### 8. State Pattern
- Order status management
- Booking status management
- Flight status management

### 9. Observer Pattern
- Notification systems for bookings and reservations

## Common Elements Package

The diagram includes a "Common Elements" package with shared classes:

- **Person**: Basic person information (name, address, email, phone)
- **Address**: Standard address structure
- **AccountStatus**: Enum for account states
- **PaymentStatus**: Enum for payment states

These are used across multiple systems to maintain consistency.

## Class Relationship Symbols

- `<|--` : Inheritance (extends)
- `*--` : Composition (strong ownership)
- `-->` : Association (uses)
- `o--` : Aggregation (weak ownership)

## Key Principles Demonstrated

### 1. SOLID Principles

**Single Responsibility Principle (SRP)**
- Each class has a single, well-defined purpose
- Example: `BookLending` handles only lending operations

**Open/Closed Principle (OCP)**
- Classes are open for extension but closed for modification
- Example: Adding new vehicle types doesn't require modifying existing code

**Liskov Substitution Principle (LSP)**
- Derived classes can substitute base classes
- Example: Any `Vehicle` subclass can be used where `Vehicle` is expected

**Interface Segregation Principle (ISP)**
- Clients aren't forced to depend on interfaces they don't use
- Example: Different account types have specific methods

**Dependency Inversion Principle (DIP)**
- Depend on abstractions, not concretions
- Example: Abstract `Account`, `Vehicle`, `Piece` classes

### 2. Encapsulation
- All classes use private attributes (indicated by `__` prefix in Python)
- Public methods provide controlled access

### 3. Abstraction
- Abstract base classes define common interfaces
- Concrete classes implement specific behaviors

### 4. Polymorphism
- Different implementations of common interfaces
- Example: Different chess pieces implement `canMove()` differently

## Usage in Interviews

When discussing these designs in interviews:

1. **Start with high-level architecture**: Explain the main components and their relationships
2. **Identify key patterns**: Point out which design patterns are used and why
3. **Discuss trade-offs**: Explain why certain design decisions were made
4. **Consider scalability**: How would the design handle growth?
5. **Address edge cases**: What happens in error scenarios?

## Extending the Diagram

To add new systems or modify existing ones:

1. Follow the existing package structure
2. Use consistent naming conventions
3. Apply appropriate design patterns
4. Maintain clear relationships between classes
5. Add notes for complex patterns or behaviors

## Additional Resources

- **PlantUML Documentation**: https://plantuml.com/
- **Design Patterns**: "Design Patterns: Elements of Reusable Object-Oriented Software" by Gang of Four
- **UML Basics**: https://www.uml-diagrams.org/

## Notes

- This diagram represents the **class structure** (static view) of the systems
- For **dynamic behavior** (sequence of operations), refer to sequence diagrams in the project
- The diagram is simplified for clarity; actual implementations may have additional helper classes
- All code examples in the project are in Python but concepts apply to any OOP language

---

**Last Updated**: 2025-11-03
**Version**: 1.0
**Author**: Generated for Grokking the Object Oriented Design Interview Project
