# Library Management System - Executable Example

This is a fully functional implementation of a Library Management System demonstrating object-oriented design principles.

## Features

✅ **Complete Implementation**: All classes are fully implemented with working methods
✅ **Type Hints**: Modern Python type hints throughout the codebase
✅ **Executable Demo**: Run `demo.py` to see the system in action
✅ **Unit Tests**: Comprehensive test suite with 50+ tests
✅ **Real-world Operations**: Book checkout, return, reservation, and fine calculation

## Project Structure

```
library-management-system/
├── __init__.py              # Package initialization
├── constants.py             # Enums, Address, Person, Constants
├── models.py                # Book, BookItem, Rack, BookLending, BookReservation, Fine
├── account_types.py         # Account, Member, Librarian
├── library_system.py        # Main Library class with all operations
├── search.py                # Search functionality (original)
├── demo.py                  # Executable demonstration
└── README.md                # This file
```

## Quick Start

### Running the Demo

```bash
cd example-codes/library-management-system
python demo.py
```

The demo will:
1. Create a library with books and members
2. Demonstrate book checkout and return
3. Show search functionality
4. Handle reference-only books
5. Display member operations

### Running Tests

```bash
# From project root
pytest tests/test_library_system.py -v

# With coverage
pytest tests/test_library_system.py --cov=example-codes/library-management-system
```

## Usage Examples

### Creating a Library

```python
from library_system import Library
from constants import Address, BookFormat, BookStatus
from models import BookItem, Rack
from account_types import Member

# Create library
library = Library("City Library")

# Create a book
rack = Rack(1, "A-1-01")
book = BookItem(
    ISBN="978-0-123456-78-9",
    title="Clean Code",
    subject="Software Engineering",
    publisher="Prentice Hall",
    language="English",
    number_of_pages=464,
    barcode="BC001",
    is_reference_only=False,
    borrowed=None,
    due_date=None,
    price=45.99,
    book_format=BookFormat.HARDCOVER,
    status=BookStatus.AVAILABLE,
    date_of_purchase=datetime.now(),
    publication_date=datetime(2008, 8, 1),
    placed_at=rack
)
book.add_author("Robert C. Martin")

# Add book to library
library.add_book_item(book)
```

### Member Operations

```python
# Create member
address = Address("123 Main St", "New York", "NY", "10001", "USA")
person = LibraryMember("John Doe", address, "john@example.com", "555-1234")
member = Member("M001", "password", person)

# Register member
library.register_member(member)

# Checkout book
library.checkout_book("BC001", "M001")

# Return book
library.return_book("BC001", "M001")
```

### Search Operations

```python
# Search by title
results = library.search_by_title("Clean Code")

# Search by author
results = library.search_by_author("Robert Martin")

# Get available books
available = library.get_available_books()
```

## Key Classes

### Library
Main class managing the entire library system with methods for:
- Adding/removing books
- Registering members
- Checkout/return operations
- Book reservations
- Search functionality

### BookItem
Represents a physical book with:
- ISBN, title, author(s)
- Status tracking (Available, Loaned, Reserved, Lost)
- Reference-only flag
- Price and format information

### Member
Library member with:
- Checkout/return capabilities
- Book reservation
- Fine tracking
- Maximum book limit enforcement

### Librarian
Administrative user with:
- Book management
- Member blocking/unblocking
- System administration

## Design Patterns Used

- **Singleton Pattern**: Library instance management
- **Factory Pattern**: Object creation
- **Strategy Pattern**: Search implementations
- **Observer Pattern**: Status notifications (simplified)

## Improvements Over Original

1. ✅ Fixed missing `super().__init__()` call in BookItem
2. ✅ Added all getter methods for encapsulated attributes
3. ✅ Implemented placeholder methods (replaced `None` with actual logic)
4. ✅ Fixed typo: "self book" → "This book"
5. ✅ Added type hints throughout
6. ✅ Created working Library class for system integration
7. ✅ Made code executable with demo script

## Future Enhancements

- Database integration for persistence
- REST API endpoints
- Web interface
- Email notifications
- Advanced search with filters
- Book recommendations
- Multi-library support

## Contributing

When contributing to this example:
1. Maintain type hints
2. Add tests for new functionality
3. Update the demo if adding new features
4. Keep code simple and educational
