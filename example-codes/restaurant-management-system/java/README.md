# Restaurant Management System - Java Implementation

## Overview

This is a comprehensive object-oriented design implementation of a Restaurant Management System in Java. The system demonstrates key OOP principles including encapsulation, inheritance, abstraction, and polymorphism while managing various aspects of restaurant operations.

## System Features

The Restaurant Management System handles the following core functionalities:

- **Reservation Management**: Create, modify, and track customer reservations
- **Table Management**: Manage table availability, seating arrangements, and table status
- **Order Processing**: Handle customer orders from creation to completion
- **Menu Management**: Organize menu items into sections and manage pricing
- **Employee Management**: Track different types of employees (Receptionists, Managers, Chefs)
- **Payment Processing**: Generate checks and process payments
- **Multi-Branch Support**: Support for restaurant chains with multiple locations

## Architecture

### Class Structure

The system is organized into six main Java files:

#### 1. **Constants.java**
Contains all enumerations and constant classes:
- `ReservationStatus`: Tracks reservation lifecycle
- `SeatType`: Defines different seat types (regular, kid-friendly, accessible)
- `OrderStatus`: Manages order processing states
- `TableStatus`: Tracks table availability
- `AccountStatus`: Manages user account states
- `PaymentStatus`: Handles payment processing states
- `Address`: Represents physical addresses

#### 2. **Account.java**
Manages user accounts and personnel:
- `Account`: User authentication and account management
- `Person`: Abstract base class for all people in the system
- `Employee`: Abstract base class for all employees
- `Receptionist`: Handles reservations and customer service
- `Manager`: Administrative functions and employee management
- `Chef`: Kitchen operations and order preparation

#### 3. **Menu.java**
Handles menu structure and items:
- `MenuItem`: Individual dishes with pricing
- `MenuSection`: Logical groupings of menu items (appetizers, entrees, etc.)
- `Menu`: Complete menu structure with multiple sections

#### 4. **Order.java**
Manages the ordering process:
- `MealItem`: Links menu items with quantities
- `Meal`: Collection of meal items for a single seat
- `Check`: Bill generation and payment tracking
- `Order`: Complete order with multiple meals, status tracking, and staff assignment

#### 5. **Table.java**
Handles seating and reservations:
- `Table`: Restaurant tables with capacity and location
- `TableSeat`: Individual seats with type specifications
- `Reservation`: Customer booking information and status

#### 6. **Restaurant.java**
Top-level organizational structure:
- `Kitchen`: Kitchen facilities and chef assignments
- `Branch`: Individual restaurant locations
- `Restaurant`: Restaurant chain management
- `TableChart`: Visual representation of table layouts

## Design Patterns Used

### 1. **Encapsulation**
All class fields are private with public getter methods, protecting internal state while providing controlled access.

### 2. **Inheritance**
- `Person` → `Employee` → `Receptionist`, `Manager`, `Chef`
- Clear hierarchy representing real-world relationships

### 3. **Abstraction**
Abstract classes (`Person`, `Employee`) define common interfaces while allowing specialized implementations.

### 4. **Composition**
- `Restaurant` contains `Branch` objects
- `Branch` contains `Kitchen` and `Table` objects
- `Order` contains `Meal` objects
- `Menu` contains `MenuSection` objects

## Class Relationships

```
Restaurant
├── Branch
│   ├── Kitchen
│   │   └── Chef[]
│   ├── Table[]
│   │   └── TableSeat[]
│   └── TableChart[]
│
├── Menu
│   └── MenuSection[]
│       └── MenuItem[]
│
├── Order
│   ├── Meal[]
│   │   └── MealItem[]
│   ├── Table
│   ├── Waiter (Employee)
│   ├── Chef
│   └── Check
│
└── Reservation
    ├── Customer
    └── Table[]
```

## Key Workflows

### 1. Making a Reservation
```java
// Customer contacts receptionist
Receptionist receptionist = new Receptionist(...);

// Receptionist creates reservation
Reservation reservation = receptionist.createReservation();

// System checks table availability
List<Table> availableTables = Table.search(capacity, startTime);

// Assign table and confirm reservation
reservation.updateStatus(ReservationStatus.CONFIRMED);
```

### 2. Processing an Order
```java
// Create order for a table
Order order = new Order(orderId, OrderStatus.RECEIVED, table, waiter, chef);

// Add meals for each seat
Meal meal = new Meal(mealId, seat);
MealItem item = new MealItem(itemId, quantity, menuItem);
meal.addMealItem(item);
order.addMeal(meal);

// Chef prepares the order
order.setStatus(OrderStatus.PREPARING);
chef.takeOrder();

// Complete and generate check
order.setStatus(OrderStatus.COMPLETED);
Check check = order.getCheck();
```

### 3. Managing Menu
```java
// Create menu structure
Menu menu = new Menu(menuId, "Dinner Menu", "Evening dining options");

// Add sections
MenuSection appetizers = new MenuSection(sectionId, "Appetizers", "Start your meal");
menu.addMenuSection(appetizers);

// Add items to sections
MenuItem item = new MenuItem(itemId, "Caesar Salad", "Fresh romaine...", 12.99);
appetizers.addMenuItem(item);

// Display menu
menu.print();
```

## Compilation and Usage

### Compilation
Since the classes reference each other, compile all files together:

```bash
javac Constants.java Account.java Menu.java Order.java Table.java Restaurant.java
```

### Creating a Sample Restaurant

```java
// Create restaurant
Restaurant restaurant = new Restaurant("The Golden Fork");

// Create branch with kitchen
Kitchen kitchen = new Kitchen("Main Kitchen");
Address branchAddress = new Address("123 Main St", "New York", "NY", "10001", "USA");
Branch branch = new Branch("Downtown Branch", branchAddress, kitchen);
restaurant.addBranch(branch);

// Add tables
Table table1 = new Table("T1", 4, "Window-1");
branch.addTable(table1);

// Create menu
Menu menu = new Menu("M1", "Dinner Menu", "Evening specials");
MenuSection entrees = new MenuSection("S1", "Entrees", "Main courses");
MenuItem steak = new MenuItem("I1", "Ribeye Steak", "12oz prime beef", 34.99);
entrees.addMenuItem(steak);
menu.addMenuSection(entrees);

// Display menu
menu.print();
```

## Extension Points

The system is designed to be easily extended:

1. **Add New Employee Types**: Extend the `Employee` class
2. **Custom Payment Methods**: Extend payment processing in `Check` class
3. **Loyalty Programs**: Add customer tracking and rewards
4. **Inventory Management**: Track ingredient usage
5. **Analytics**: Add reporting and business intelligence
6. **Online Ordering**: Integrate with web/mobile interfaces

## Best Practices Demonstrated

1. **Single Responsibility**: Each class has a clear, focused purpose
2. **Open/Closed Principle**: Classes are open for extension but closed for modification
3. **Liskov Substitution**: Derived classes can substitute their base classes
4. **Interface Segregation**: Classes only depend on methods they use
5. **Dependency Inversion**: High-level modules don't depend on low-level details

## TODO Items

Throughout the code, you'll find `// TODO:` comments indicating areas where full implementation would include:

- Database integration
- Input validation
- Error handling
- Logging and auditing
- Notification systems
- Security measures
- Transaction management

## Notes

- This implementation focuses on demonstrating OOP design principles
- In a production system, you would add:
  - Database persistence layer
  - RESTful API endpoints
  - Authentication and authorization
  - Input validation and error handling
  - Unit tests and integration tests
  - Logging and monitoring
  - Configuration management

## Learning Objectives

This implementation helps understand:

1. **Class Design**: How to structure classes for real-world systems
2. **Relationships**: Composition vs. inheritance decisions
3. **Encapsulation**: Protecting data while providing access
4. **Abstraction**: Creating reusable, maintainable code
5. **System Architecture**: Organizing complex systems into manageable components

## License

This code is provided for educational purposes as part of the "Grokking the Object-Oriented Design Interview" course.

## Related Files

- Python implementation: `../python/`
- Design documentation: `../../docs/case-studies/design-a-restaurant-management-system.md`

---

**Note**: This is a simplified implementation for educational purposes. A production system would require additional features like database integration, security measures, comprehensive error handling, and extensive testing.
