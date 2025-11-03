# Library Management System - Java Implementation

This directory contains a Java implementation of a Library Management System, demonstrating object-oriented design principles and patterns.

## Overview

The Library Management System is designed to manage the operations of a library, including:
- Managing books and their physical copies (book items)
- Handling member accounts and librarian accounts
- Processing book checkouts, returns, and renewals
- Managing book reservations
- Tracking overdue books and collecting fines
- Searching the library catalog

## System Architecture

The system is organized into four main components:

### 1. Constants.java
Contains all enumerations and constant values used throughout the system:
- **Enums:**
  - `BookFormat`: Different formats of books (HARDCOVER, PAPERBACK, EBOOK, etc.)
  - `BookStatus`: Status of a book item (AVAILABLE, RESERVED, LOANED, LOST)
  - `ReservationStatus`: Status of reservations (WAITING, PENDING, CANCELED, COMPLETED)
  - `AccountStatus`: Status of user accounts (ACTIVE, CLOSED, BLACKLISTED, etc.)

- **Classes:**
  - `Address`: Represents a physical address
  - `Person`: Abstract base class for all person types
  - `Constants`: System-wide configuration values (max books per user, lending period)

### 2. Models.java
Contains the core domain models:
- **Book**: Abstract class representing book metadata (ISBN, title, author, etc.)
- **BookItem**: Concrete class representing a physical copy of a book
- **Rack**: Represents physical storage location for books
- **BookReservation**: Manages book reservations by members
- **BookLending**: Tracks active book loans
- **Fine**: Handles overdue book fines

### 3. AccountTypes.java
Contains account-related classes:
- **Account**: Abstract base class for all account types
- **Librarian**: Library staff with administrative privileges
  - Can add books to the catalog
  - Can block/unblock member accounts
- **Member**: Library member who can borrow books
  - Can checkout, return, and renew books
  - Can reserve books
  - Subject to checkout limits and lending periods

### 4. Search.java
Contains search functionality:
- **Search**: Abstract interface defining search operations
- **Catalog**: Implementation of the library catalog with search capabilities
  - Search by title
  - Search by author
  - Search by subject
  - Search by publication date

## Key Features

### Book Management
- Track multiple copies of the same book
- Manage book status (available, loaned, reserved, lost)
- Support different book formats
- Track book location on racks

### Member Operations
- **Checkout**: Members can borrow books (up to 5 books at a time)
- **Return**: Return books and automatically check for fines
- **Renew**: Extend lending period if no other reservations exist
- **Reserve**: Reserve books that are currently unavailable

### Business Rules
- Maximum books per user: **5 books**
- Maximum lending period: **10 days**
- Reference-only books cannot be checked out
- Books with reservations from other members cannot be renewed
- Fines are automatically calculated for overdue books

### Reservation System
- Members can reserve books that are currently loaned out
- When a reserved book is returned, the system notifies the member
- Reservations are automatically completed when the book is checked out

### Fine Management
- Automatic fine calculation based on days overdue
- Fines are checked during return and renewal operations
- Default fine rate: $1 per day overdue

## Class Relationships

```
Person (abstract)
    └── Used by Account

Account (abstract)
    ├── Librarian
    └── Member

Book (abstract)
    └── BookItem

Search (abstract)
    └── Catalog

Independent Classes:
- Address
- Rack
- BookReservation
- BookLending
- Fine
- Constants
```

## Usage Examples

### Creating a Member Account
```java
Address address = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
Person person = new Person("John Doe", address, "john@example.com", "555-1234");
Member member = new Member("M001", "password123", person, AccountStatus.ACTIVE);
```

### Adding a Book to the Library
```java
Rack rack = new Rack(1, "A1");
BookItem bookItem = new BookItem(
    "978-0134685991",           // ISBN
    "Effective Java",           // Title
    "Programming",              // Subject
    "Addison-Wesley",           // Publisher
    "English",                  // Language
    416,                        // Number of pages
    "BOOK001",                  // Barcode
    false,                      // Is reference only
    null,                       // Borrowed date
    null,                       // Due date
    45.99,                      // Price
    BookFormat.HARDCOVER,       // Format
    BookStatus.AVAILABLE,       // Status
    new Date(),                 // Date of purchase
    new Date(),                 // Publication date
    rack                        // Placed at rack
);
```

### Checking Out a Book
```java
Member member = // ... get member
BookItem bookItem = // ... get book item

if (member.checkoutBookItem(bookItem)) {
    System.out.println("Book checked out successfully!");
} else {
    System.out.println("Checkout failed!");
}
```

### Searching the Catalog
```java
Catalog catalog = new Catalog();
List<BookItem> results = catalog.searchByTitle("Effective Java");
for (BookItem book : results) {
    System.out.println(book.getTitle() + " - " + book.getStatus());
}
```

### Returning a Book
```java
Member member = // ... get member
BookItem bookItem = // ... get book item

member.returnBookItem(bookItem);
// System automatically checks for fines and updates status
```

## Design Patterns Used

1. **Abstract Factory Pattern**: Used for creating different types of accounts and books
2. **Template Method Pattern**: Base classes define the structure, subclasses implement specifics
3. **Strategy Pattern**: Different search strategies in the Catalog class
4. **State Pattern**: Book status management (Available, Loaned, Reserved, Lost)

## Future Enhancements

Potential improvements for a production system:
- Database integration for persistence
- Authentication and authorization system
- Email/SMS notification system
- Payment gateway integration for fines
- Advanced search with fuzzy matching
- Book recommendation system
- Digital book lending support
- Mobile app integration
- Analytics and reporting dashboard
- Multi-library support

## Notes

- This is a simplified implementation for educational purposes
- In production, passwords should be properly hashed and secured
- Database operations are represented as placeholder methods
- Error handling should be more comprehensive
- Concurrency control would be needed for multi-user access
- All monetary values should use proper decimal types (e.g., BigDecimal)

## Compilation

To compile all Java files:
```bash
javac *.java
```

Note: This implementation focuses on demonstrating OOP concepts and design patterns rather than being a complete, production-ready system.
