# Stock Brokerage System - Java Implementation

This directory contains a Java implementation of a Stock Brokerage System, converted from the original Python code with comprehensive comments and documentation.

## Overview

The Stock Brokerage System allows members to:
- Place buy and sell limit orders for stocks
- Manage their stock positions
- Track active orders
- Receive callbacks when orders are filled or updated

## Architecture

The system follows object-oriented design principles with the following key components:

### Core Classes

1. **Constants.java**
   - Contains all enums and constant values
   - Defines `ReturnStatus`, `OrderStatus`, `TimeEnforcementType`, `AccountStatus`
   - Includes `Location` class for address management
   - Contains system-wide constants

2. **Order.java**
   - Abstract base class `Order` for all order types
   - `LimitOrder` class for limit orders (buy/sell at specific price)
   - `OrderPart` class to track partial order fills
   - Manages order lifecycle and status

3. **Member.java**
   - Abstract base class `Account` for all account types
   - `Member` class representing trading members
   - `StockPosition` class to track owned stocks
   - Handles order placement and portfolio management

4. **StockExchange.java**
   - Singleton class representing the stock exchange
   - Routes orders to the exchange for execution
   - Notifies members of order updates

## Key Features

### Order Placement
- **Buy Limit Orders**: Members can place orders to buy stocks at or below a specified price
- **Sell Limit Orders**: Members can place orders to sell stocks at or above a specified price
- **Validation**: System validates funds availability and stock ownership before placing orders

### Order Lifecycle
1. Order created by member
2. Order submitted to stock exchange
3. Order matched and filled (can be partial fills)
4. Member notified via callback
5. Order removed from active orders when fully filled or cancelled

### Time Enforcement Types
- **Good Till Cancelled**: Order stays active until explicitly cancelled
- **Fill or Kill**: Order must be filled immediately and completely
- **Immediate or Cancel**: Fill what's possible immediately, cancel the rest
- **On The Open**: Execute at market open
- **On The Close**: Execute at market close

## Design Patterns Used

1. **Singleton Pattern**: `StockExchange` ensures only one exchange instance exists
2. **Abstract Factory Pattern**: `Account` and `Order` are abstract base classes
3. **Observer Pattern**: Callback mechanism for order updates

## Class Relationships

```
Account (abstract)
    └── Member
        ├── uses StockPosition
        └── creates Order

Order (abstract)
    └── LimitOrder

StockExchange (singleton)
    └── processes Order
    └── notifies Member
```

## Usage Example

```java
// Create a member account
Location address = new Location("123 Main St", "New York", "NY", "10001", "USA");
Member member = new Member("M001", "password123", "John Doe", 
                          address, "john@example.com", "555-1234", 
                          AccountStatus.ACTIVE);

// Set available funds
member.setAvailableFundsForTrading(10000.0);

// Place a buy limit order
ReturnStatus status = member.placeBuyLimitOrder(
    "AAPL",                              // Stock ID
    10,                                   // Quantity
    150.0,                                // Limit price
    TimeEnforcementType.GOOD_TILL_CANCELLED
);

if (status == ReturnStatus.SUCCESS) {
    System.out.println("Order placed successfully!");
}
```

## Differences from Python Implementation

1. **Type Safety**: Java's static typing provides compile-time type checking
2. **Access Modifiers**: Explicit use of `private`, `public`, `protected`
3. **Constructors**: Explicit constructors with parameter validation
4. **Enums**: Java enums are more robust than Python's Enum class
5. **Collections**: Uses Java's `HashMap` instead of Python dictionaries
6. **Date Handling**: Uses `java.util.Date` instead of Python's `datetime`

## Improvements Added

1. **Comprehensive Documentation**: Every class, method, and field has detailed JavaDoc comments
2. **Better Encapsulation**: Proper use of getters/setters
3. **Error Handling**: Return status enums for better error communication
4. **Thread Safety**: Synchronized `getInstance()` method in StockExchange
5. **Code Organization**: Clear separation of concerns across files

## Future Enhancements

- Implement actual database persistence (currently placeholder methods)
- Add market orders (in addition to limit orders)
- Implement order matching engine
- Add transaction history tracking
- Implement fund transfer functionality
- Add support for stop-loss orders
- Implement real-time price updates
- Add portfolio analytics and reporting

## Notes

- Database methods (`saveInDB()`, `updateInDB()`) are placeholders and need actual implementation
- Order ID generation is simplified; production systems should use UUIDs or database sequences
- Thread safety should be enhanced for concurrent order processing
- Additional validation and error handling should be added for production use
