"""
Executable Demo for Library Management System
This script demonstrates the working library management system with real examples.
"""

import sys
from pathlib import Path
from datetime import datetime, timedelta

# Ensure imports work when running directly
sys.path.insert(0, str(Path(__file__).parent))

import constants
import models
import account_types
import library_system

Address = constants.Address
Person = constants.Person
BookFormat = constants.BookFormat
BookStatus = constants.BookStatus
AccountStatus = constants.AccountStatus
BookItem = models.BookItem
Rack = models.Rack
Member = account_types.Member
Librarian = account_types.Librarian
Library = library_system.Library


class LibraryMember(Person):
    """Concrete implementation of Person for library members."""
    pass


def main():
    print("=" * 60)
    print("Library Management System - Executable Demo")
    print("=" * 60)
    print()
    
    # Create library
    library = Library("City Central Library")
    print(f"[OK] Created library: {library.name}\n")
    
    # Create addresses
    member_address = Address("123 Main St", "New York", "NY", "10001", "USA")
    librarian_address = Address("456 Oak Ave", "New York", "NY", "10002", "USA")
    
    # Create persons
    member_person = LibraryMember("John Doe", member_address, "john@example.com", "555-1234")
    librarian_person = LibraryMember("Jane Smith", librarian_address, "jane@library.com", "555-5678")
    
    # Create accounts
    member = Member("M001", "password123", member_person, AccountStatus.ACTIVE)
    librarian = Librarian("L001", "libpass456", librarian_person, AccountStatus.ACTIVE)
    
    # Register member
    library.register_member(member)
    print()
    
    # Create racks
    rack1 = Rack(1, "A-1-01")
    rack2 = Rack(2, "A-1-02")
    rack3 = Rack(3, "B-2-01")
    
    # Create books
    print("Adding books to the library catalog...")
    print("-" * 60)
    
    book1 = BookItem(
        ISBN="978-0-13-468599-1",
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
        date_of_purchase=datetime.now() - timedelta(days=365),
        publication_date=datetime(2008, 8, 1),
        placed_at=rack1
    )
    book1.add_author("Robert C. Martin")
    library.add_book_item(book1)
    
    book2 = BookItem(
        ISBN="978-0-201-63361-0",
        title="Design Patterns",
        subject="Software Engineering",
        publisher="Addison-Wesley",
        language="English",
        number_of_pages=395,
        barcode="BC002",
        is_reference_only=False,
        borrowed=None,
        due_date=None,
        price=54.99,
        book_format=BookFormat.HARDCOVER,
        status=BookStatus.AVAILABLE,
        date_of_purchase=datetime.now() - timedelta(days=730),
        publication_date=datetime(1994, 10, 31),
        placed_at=rack2
    )
    book2.add_author("Erich Gamma")
    book2.add_author("Richard Helm")
    book2.add_author("Ralph Johnson")
    book2.add_author("John Vlissides")
    library.add_book_item(book2)
    
    book3 = BookItem(
        ISBN="978-0-596-00784-8",
        title="Head First Design Patterns",
        subject="Software Engineering",
        publisher="O'Reilly Media",
        language="English",
        number_of_pages=694,
        barcode="BC003",
        is_reference_only=True,  # Reference only book
        borrowed=None,
        due_date=None,
        price=39.99,
        book_format=BookFormat.PAPERBACK,
        status=BookStatus.AVAILABLE,
        date_of_purchase=datetime.now() - timedelta(days=180),
        publication_date=datetime(2004, 10, 25),
        placed_at=rack3
    )
    book3.add_author("Eric Freeman")
    book3.add_author("Elisabeth Robson")
    library.add_book_item(book3)
    
    print()
    
    # Search for books
    print("Searching for books...")
    print("-" * 60)
    search_results = library.search_by_title("Design")
    print(f"Found {len(search_results)} books matching 'Design':")
    for book in search_results:
        print(f"  - {book.get_title()} by {', '.join(book.get_authors())}")
    print()
    
    # Checkout books
    print("Member checking out books...")
    print("-" * 60)
    library.checkout_book("BC001", "M001")
    library.checkout_book("BC002", "M001")
    print()
    
    # Try to checkout reference-only book
    print("Attempting to checkout reference-only book...")
    print("-" * 60)
    library.checkout_book("BC003", "M001")
    print()
    
    # Check member's borrowed books count
    print(f"Member {member.get_person().get_name()} has {member.get_total_books_checkedout()} books checked out.")
    print()
    
    # Return a book
    print("Returning a book...")
    print("-" * 60)
    library.return_book("BC001", "M001")
    print(f"Member now has {member.get_total_books_checkedout()} books checked out.")
    print()
    
    # Reserve a book
    print("Reserving a book...")
    print("-" * 60)
    library.reserve_book("BC002", "M001")
    print()
    
    # Get available books
    print("Available books in library:")
    print("-" * 60)
    available = library.get_available_books()
    print(f"Total available books: {len(available)}")
    for book in available:
        print(f"  - {book.get_title()} (Barcode: {book.get_barcode()})")
    print()
    
    # Librarian operations
    print("Librarian operations...")
    print("-" * 60)
    librarian.add_book_item(book1)
    print()
    
    print("=" * 60)
    print("Demo completed successfully!")
    print("=" * 60)


if __name__ == "__main__":
    main()
