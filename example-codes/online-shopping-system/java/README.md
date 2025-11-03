# Online Shopping System - Java Implementation

This is a comprehensive Java implementation of an Online Shopping System, demonstrating object-oriented design principles and patterns.

## Overview

The Online Shopping System is designed to handle the core functionality of an e-commerce platform, including:
- User account management (Guest and Member accounts)
- Product catalog and search functionality
- Shopping cart management
- Order processing and tracking
- Shipment tracking
- Notification system

## System Architecture

### Class Structure

The system is organized into the following main components:

#### 1. **Constants.java**
Contains all enumerations and basic data structures:
- `Address` - Represents physical addresses
- `OrderStatus` - Order state enumeration
- `AccountStatus` - Account state enumeration
- `ShipmentStatus` - Shipment state enumeration
- `PaymentStatus` - Payment state enumeration

#### 2. **Product.java**
Product-related classes:
- `ProductCategory` - Categorization of products
- `ProductReview` - Customer reviews and ratings
- `Product` - Main product class with pricing and inventory

#### 3. **AccountTypes.java**
User account hierarchy:
- `Account` - Base account class with credentials and payment methods
- `Customer` - Abstract customer class (base for Guest and Member)
- `Guest` - Non-registered users who can browse and shop
- `Member` - Registered users with full account features

#### 4. **Shopping.java**
Shopping and order management:
- `Item` - Represents a product in cart or order
- `ShoppingCart` - Manages items before checkout
- `OrderLog` - Tracks order status changes
- `Order` - Represents a customer's purchase order

#### 5. **Search.java**
Product search and catalog:
- `Search` - Abstract search interface
- `Catalog` - Implements product search by name and category

#### 6. **Shipment.java**
Shipment and notification management:
- `ShipmentLog` - Tracks shipment status changes
- `Shipment` - Manages package delivery
- `Notification` - Abstract notification base class
- `EmailNotification` - Email notification implementation
- `SMSNotification` - SMS notification implementation

## Key Features

### 1. Account Management
- Support for both guest and registered member accounts
- Secure credential storage
- Multiple payment methods (credit cards, bank accounts)
- Shipping address management
- Account status tracking (Active, Blocked, Banned, etc.)

### 2. Product Catalog
- Hierarchical product categorization
- Product search by name and category
- Partial name search for autocomplete
- Product reviews and ratings
- Inventory tracking

### 3. Shopping Cart
- Add/remove items
- Update quantities
- Calculate total cost
- Checkout functionality
- Persistent cart for members

### 4. Order Processing
- Order creation from shopping cart
- Order status tracking with logs
- Payment processing
- Order history

### 5. Shipment Tracking
- Shipment creation and tracking
- Multiple shipping methods
- Status updates with logs
- Estimated delivery dates

### 6. Notification System
- Extensible notification framework
- Email notifications
- SMS notifications
- Order and shipment updates

## Design Patterns Used

### 1. **Inheritance and Polymorphism**
- `Customer` abstract class with `Guest` and `Member` implementations
- `Search` abstract class with `Catalog` implementation
- `Notification` abstract class with multiple notification types

### 2. **Encapsulation**
- All class attributes are private
- Access through getter/setter methods
- Data hiding and validation

### 3. **Composition**
- `Account` contains `Address`, credit cards, and bank accounts
- `Product` contains `ProductCategory`
- `Order` contains multiple `Item` objects
- `Shipment` contains multiple `ShipmentLog` entries

### 4. **Strategy Pattern**
- Different notification strategies (Email, SMS)
- Different search strategies (by name, by category)

## Usage Examples

### Creating a Product
```java
// Create a category
ProductCategory electronics = new ProductCategory("Electronics", "Electronic devices and gadgets");

// Create a product
Product laptop = new Product(
    "PROD001",
    "Gaming Laptop",
    "High-performance gaming laptop with RTX 4080",
    1299.99,
    electronics,
    sellerAccount
);
laptop.setAvailableItemCount(50);
```

### Managing Shopping Cart
```java
// Create a shopping cart
ShoppingCart cart = new ShoppingCart();

// Add items to cart
Item item1 = new Item("PROD001", 1, 1299.99);
cart.addItem(item1);

// Update quantity
cart.updateItemQuantity(item1, 2);

// Get total cost
double total = cart.getTotalCost();

// Checkout
Order order = cart.checkout();
```

### Creating an Account
```java
// Create an address
Address address = new Address(
    "123 Main St",
    "New York",
    "NY",
    "10001",
    "USA"
);

// Create an account
Account account = new Account(
    "john_doe",
    "securePassword123",
    "John Doe",
    "john@example.com",
    "+1-555-0123",
    address,
    AccountStatus.ACTIVE
);
```

### Searching Products
```java
// Create a catalog
Catalog catalog = new Catalog();

// Add products
catalog.addProduct(laptop);
catalog.addProduct(mouse);
catalog.addProduct(keyboard);

// Search by name
List<Product> results = catalog.searchProductsByName("Gaming Laptop");

// Search by category
List<Product> electronics = catalog.searchProductsByCategory("Electronics");

// Partial name search
List<Product> gamingProducts = catalog.searchProductsByPartialName("gaming");
```

### Processing Orders
```java
// Create an order
Order order = new Order("ORD001", OrderStatus.PENDING);

// Add items
order.addItem(item1);
order.addItem(item2);

// Process payment
order.makePayment(paymentInfo);

// Ship the order
order.sendForShipment();

// Track status
OrderStatus currentStatus = order.getStatus();
```

### Managing Shipments
```java
// Create a shipment
Shipment shipment = new Shipment("SHIP001", "Express");

// Update status
shipment.updateStatus(ShipmentStatus.SHIPPED);
shipment.updateStatus(ShipmentStatus.DELIVERED);

// Get current status
ShipmentStatus status = shipment.getCurrentStatus();

// View shipment history
List<ShipmentLog> logs = shipment.getShipmentLogs();
```

### Sending Notifications
```java
// Create email notification
EmailNotification emailNotif = new EmailNotification(
    "NOTIF001",
    "Your order has been shipped!"
);
emailNotif.sendNotification(account);

// Create SMS notification
SMSNotification smsNotif = new SMSNotification(
    "NOTIF002",
    "Your package will arrive today!"
);
smsNotif.sendNotification(account);
```

## Class Relationships

```
Account
  └── used by → Member
  
Customer (abstract)
  ├── Guest
  └── Member
  
Search (abstract)
  └── Catalog
  
Notification (abstract)
  ├── EmailNotification
  └── SMSNotification

ShoppingCart
  └── contains → Item[]
  
Order
  ├── contains → Item[]
  └── contains → OrderLog[]
  
Shipment
  └── contains → ShipmentLog[]
  
Product
  └── belongs to → ProductCategory
```

## Future Enhancements

Potential improvements and extensions:

1. **Payment Processing**
   - Add concrete payment classes (CreditCardPayment, PayPalPayment, etc.)
   - Implement payment gateway integration
   - Add refund processing

2. **Advanced Search**
   - Price range filtering
   - Rating-based filtering
   - Sorting options (price, popularity, rating)
   - Full-text search

3. **Inventory Management**
   - Automatic stock updates
   - Low stock alerts
   - Reorder point management

4. **Recommendation System**
   - Product recommendations based on browsing history
   - Related products
   - Frequently bought together

5. **Wishlist**
   - Save items for later
   - Share wishlists
   - Price drop notifications

6. **Discount System**
   - Coupon codes
   - Promotional discounts
   - Loyalty programs

7. **Review System**
   - Review verification
   - Helpful votes
   - Review moderation

8. **Multi-language Support**
   - Internationalization
   - Currency conversion
   - Regional pricing

## Notes

- This implementation focuses on the core object-oriented design and structure
- Some methods are left with minimal implementation as placeholders
- In a production system, you would add:
  - Database persistence
  - Security features (encryption, authentication)
  - Input validation
  - Error handling
  - Logging
  - Unit tests
  - API endpoints

## Compilation

To compile all Java files:
```bash
javac *.java
```

## License

This code is provided for educational purposes as part of the "Grokking the Object Oriented Design Interview" course.
