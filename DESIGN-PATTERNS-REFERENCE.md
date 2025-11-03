# Design Patterns Quick Reference Guide

This guide provides a quick reference for all design patterns used across the case studies in this project.

## Table of Contents

1. [Creational Patterns](#creational-patterns)
2. [Structural Patterns](#structural-patterns)
3. [Behavioral Patterns](#behavioral-patterns)
4. [Pattern Usage Matrix](#pattern-usage-matrix)

---

## Creational Patterns

### 1. Singleton Pattern

**Purpose**: Ensure a class has only one instance and provide a global point of access to it.

**Used In**: 
- **Parking Lot System**: `ParkingLot` class ensures only one parking lot instance exists

**Example**:
```python
class ParkingLot:
    instance = None
    
    class __OnlyOne:
        def __init__(self, name, address):
            self.__name = name
            self.__address = address
    
    def __init__(self, name, address):
        if not ParkingLot.instance:
            ParkingLot.instance = ParkingLot.__OnlyOne(name, address)
```

**When to Use**:
- When exactly one instance of a class is needed
- When the instance needs to be accessible from multiple points
- When the instance should be extensible by subclassing

---

### 2. Factory Pattern

**Purpose**: Define an interface for creating objects, but let subclasses decide which class to instantiate.

**Used In**:
- **Library Management System**: Creating different types of books
- **Online Shopping System**: Creating different product types
- **Hotel Management System**: Creating different room types
- **Airline Management System**: Creating flights and reservations
- **Movie Ticket Booking System**: Creating shows and bookings
- **Chess Game**: Creating different chess pieces

**Example**:
```python
class VehicleFactory:
    @staticmethod
    def create_vehicle(vehicle_type, license_number):
        if vehicle_type == VehicleType.CAR:
            return Car(license_number)
        elif vehicle_type == VehicleType.TRUCK:
            return Truck(license_number)
        elif vehicle_type == VehicleType.VAN:
            return Van(license_number)
```

**When to Use**:
- When a class can't anticipate the type of objects it needs to create
- When you want to localize the knowledge of which class gets created
- When you want to provide a library of products without exposing implementation

---

## Structural Patterns

### 3. Composite Pattern

**Purpose**: Compose objects into tree structures to represent part-whole hierarchies.

**Used In**:
- **Online Shopping System**: Shopping cart with multiple items
- **Airline Management System**: Itinerary with multiple flight reservations
- **Hotel Management System**: Hotel with multiple locations and rooms

**Example**:
```python
class ShoppingCart:
    def __init__(self):
        self.__items = []  # Composite of items
    
    def add_item(self, item):
        self.__items.append(item)
    
    def get_total_price(self):
        return sum(item.get_price() for item in self.__items)
```

**When to Use**:
- When you want to represent part-whole hierarchies
- When you want clients to treat individual objects and compositions uniformly

---

### 4. Decorator Pattern

**Purpose**: Attach additional responsibilities to an object dynamically.

**Used In**:
- **Hotel Management System**: Adding amenities and services to rooms
- **Online Shopping System**: Adding features to products

**Example**:
```python
class Room:
    def __init__(self):
        self.__amenities = []
    
    def add_amenity(self, amenity):
        self.__amenities.append(amenity)
    
    def get_total_price(self):
        base_price = self.__booking_price
        amenity_price = sum(a.get_price() for a in self.__amenities)
        return base_price + amenity_price
```

**When to Use**:
- When you want to add responsibilities to objects dynamically
- When extension by subclassing is impractical

---

## Behavioral Patterns

### 5. Strategy Pattern

**Purpose**: Define a family of algorithms, encapsulate each one, and make them interchangeable.

**Used In**:
- **Chess Game**: Different movement strategies for different pieces
- **Parking Lot System**: Different parking spot allocation strategies

**Example**:
```python
class Piece(ABC):
    @abstractmethod
    def can_move(self, board, start, end):
        pass

class King(Piece):
    def can_move(self, board, start, end):
        # King-specific movement logic
        pass

class Queen(Piece):
    def can_move(self, board, start, end):
        # Queen-specific movement logic
        pass
```

**When to Use**:
- When you have multiple algorithms for a specific task
- When you want to switch between algorithms at runtime
- When you want to isolate algorithm implementation from the code that uses it

---

### 6. Command Pattern

**Purpose**: Encapsulate a request as an object, thereby letting you parameterize clients with different requests.

**Used In**:
- **ATM System**: Different transaction types (Withdraw, Deposit, Transfer, BalanceInquiry)
- **Chess Game**: Move commands

**Example**:
```python
class Transaction(ABC):
    @abstractmethod
    def make_transaction(self):
        pass

class Withdraw(Transaction):
    def __init__(self, amount):
        self.__amount = amount
    
    def make_transaction(self):
        # Execute withdrawal logic
        pass

class Deposit(Transaction):
    def __init__(self, amount):
        self.__amount = amount
    
    def make_transaction(self):
        # Execute deposit logic
        pass
```

**When to Use**:
- When you want to parameterize objects with operations
- When you want to queue, log, or support undo operations
- When you want to structure a system around high-level operations

---

### 7. State Pattern

**Purpose**: Allow an object to alter its behavior when its internal state changes.

**Used In**:
- **Online Shopping System**: Order status management
- **Airline Management System**: Flight status management
- **Movie Ticket Booking System**: Booking and seat status management
- **ATM System**: Transaction status management

**Example**:
```python
class Order:
    def __init__(self):
        self.__status = OrderStatus.PENDING
    
    def send_for_shipment(self):
        if self.__status == OrderStatus.PENDING:
            self.__status = OrderStatus.SHIPPED
            # Trigger shipment process
        else:
            raise Exception("Order cannot be shipped in current state")
```

**When to Use**:
- When an object's behavior depends on its state
- When operations have large conditional statements that depend on object state
- When state-specific behavior should be defined independently

---

### 8. Observer Pattern

**Purpose**: Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified.

**Used In**:
- **Online Shopping System**: Order status notifications
- **Hotel Management System**: Booking notifications
- **Airline Management System**: Flight status updates
- **Movie Ticket Booking System**: Booking confirmations
- **Stack Overflow**: Question/answer notifications
- **LinkedIn**: Connection and post notifications
- **Facebook**: Friend activity notifications

**Example**:
```python
class BookReservation:
    def __init__(self):
        self.__observers = []
    
    def add_observer(self, observer):
        self.__observers.append(observer)
    
    def notify_observers(self):
        for observer in self.__observers:
            observer.update(self)
    
    def send_book_available_notification(self):
        self.notify_observers()
```

**When to Use**:
- When a change to one object requires changing others
- When an object should notify other objects without knowing who they are
- When you want loose coupling between objects

---

### 9. Template Method Pattern

**Purpose**: Define the skeleton of an algorithm in a method, deferring some steps to subclasses.

**Used In**:
- **Library Management System**: Account class with common operations
- **ATM System**: Transaction processing flow
- **Chess Game**: Piece movement validation

**Example**:
```python
class Account(ABC):
    def __init__(self, id, password, person, status):
        self.__id = id
        self.__password = password
        self.__status = status
        self.__person = person
    
    @abstractmethod
    def reset_password(self):
        pass

class Librarian(Account):
    def reset_password(self):
        # Librarian-specific password reset logic
        pass

class Member(Account):
    def reset_password(self):
        # Member-specific password reset logic
        pass
```

**When to Use**:
- When you want to let subclasses redefine certain steps of an algorithm
- When you want to avoid code duplication
- When you want to control subclass extensions

---

## Pattern Usage Matrix

| System | Singleton | Factory | Composite | Decorator | Strategy | Command | State | Observer | Template |
|--------|-----------|---------|-----------|-----------|----------|---------|-------|----------|----------|
| **Library Management** | | ✓ | | | | | | | ✓ |
| **Parking Lot** | ✓ | ✓ | | | ✓ | | | | |
| **Online Shopping** | | ✓ | ✓ | ✓ | | | ✓ | ✓ | |
| **Chess Game** | | ✓ | | | ✓ | ✓ | | | ✓ |
| **ATM System** | ✓ | | | | | ✓ | ✓ | | ✓ |
| **Hotel Management** | | ✓ | ✓ | ✓ | | | ✓ | ✓ | |
| **Airline Management** | | ✓ | ✓ | | | | ✓ | ✓ | |
| **Movie Ticket Booking** | | ✓ | | | | | ✓ | ✓ | |

---

## SOLID Principles

All designs follow SOLID principles:

### Single Responsibility Principle (SRP)
Each class has one reason to change. For example:
- `BookLending` only handles lending operations
- `Payment` only handles payment processing
- `FlightReservation` only handles flight reservations

### Open/Closed Principle (OCP)
Classes are open for extension but closed for modification:
- Adding new vehicle types doesn't require modifying existing `Vehicle` code
- Adding new chess pieces doesn't require modifying `Piece` base class
- Adding new transaction types doesn't require modifying `Transaction` base class

### Liskov Substitution Principle (LSP)
Derived classes can substitute base classes:
- Any `Vehicle` subclass can be used where `Vehicle` is expected
- Any `Piece` subclass can be used where `Piece` is expected
- Any `Transaction` subclass can be used where `Transaction` is expected

### Interface Segregation Principle (ISP)
Clients aren't forced to depend on interfaces they don't use:
- `Librarian` and `Member` have different methods despite sharing `Account` base
- Different room types have specific methods beyond base `Room` interface

### Dependency Inversion Principle (DIP)
Depend on abstractions, not concretions:
- Code depends on abstract `Account`, `Vehicle`, `Piece` classes
- Concrete implementations are injected or created through factories

---

## Common Anti-Patterns to Avoid

### 1. God Object
**Problem**: One class does too much
**Solution**: Break into smaller, focused classes with single responsibilities

### 2. Tight Coupling
**Problem**: Classes are too dependent on each other
**Solution**: Use interfaces, dependency injection, and design patterns

### 3. Premature Optimization
**Problem**: Optimizing before understanding requirements
**Solution**: Focus on clean design first, optimize later if needed

### 4. Magic Numbers
**Problem**: Hard-coded values without explanation
**Solution**: Use constants and enums (e.g., `AccountStatus`, `VehicleType`)

---

## Interview Tips

When discussing design patterns in interviews:

1. **Identify the Problem First**: Understand what problem the pattern solves
2. **Explain Trade-offs**: Every pattern has pros and cons
3. **Show Alternatives**: Discuss why you chose one pattern over another
4. **Consider Scale**: How does the pattern handle growth?
5. **Real-world Examples**: Relate to systems you've built or used

### Common Interview Questions

**Q: Why use Singleton for ParkingLot?**
A: Ensures consistent state across all entrance/exit panels. Only one parking lot should exist in the system, managing all floors and spots centrally.

**Q: Why Strategy Pattern for chess pieces?**
A: Each piece has unique movement rules. Strategy pattern allows us to encapsulate these rules and make them interchangeable without modifying the game logic.

**Q: Why Command Pattern for ATM transactions?**
A: Encapsulates each transaction type as an object, making it easy to queue, log, undo, or audit transactions. Also follows Open/Closed principle.

**Q: When would you NOT use a Singleton?**
A: When you need multiple instances, when testing requires different instances, or when the singleton creates tight coupling. Consider dependency injection instead.

---

## Further Reading

- **Design Patterns: Elements of Reusable Object-Oriented Software** (Gang of Four)
- **Head First Design Patterns** by Freeman & Freeman
- **Refactoring: Improving the Design of Existing Code** by Martin Fowler
- **Clean Code** by Robert C. Martin

---

**Last Updated**: 2025-11-03
**Version**: 1.0
