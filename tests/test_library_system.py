"""
Unit tests for Library Management System
Tests all core functionality including book management, member operations, and lending.
"""

import pytest
import sys
from pathlib import Path
from datetime import datetime, timedelta

# Add the example-codes directory to the path
sys.path.insert(0, str(Path(__file__).parent.parent / "example-codes" / "library-management-system"))

from constants import Address, Person, BookFormat, BookStatus, AccountStatus, ReservationStatus
from models import BookItem, Rack, BookLending, BookReservation, Fine
from account_types import Member, Librarian
from library_system import Library


class TestPerson(Person):
    """Concrete implementation of Person for testing."""
    pass


@pytest.fixture
def sample_address():
    """Create a sample address for testing."""
    return Address("123 Test St", "Test City", "TS", "12345", "Test Country")


@pytest.fixture
def sample_person(sample_address):
    """Create a sample person for testing."""
    return TestPerson("Test User", sample_address, "test@example.com", "555-0000")


@pytest.fixture
def sample_rack():
    """Create a sample rack for testing."""
    return Rack(1, "A-1-01")


@pytest.fixture
def sample_book(sample_rack):
    """Create a sample book for testing."""
    book = BookItem(
        ISBN="978-0-123456-78-9",
        title="Test Book",
        subject="Testing",
        publisher="Test Publisher",
        language="English",
        number_of_pages=300,
        barcode="TB001",
        is_reference_only=False,
        borrowed=None,
        due_date=None,
        price=29.99,
        book_format=BookFormat.PAPERBACK,
        status=BookStatus.AVAILABLE,
        date_of_purchase=datetime.now(),
        publication_date=datetime(2020, 1, 1),
        placed_at=sample_rack
    )
    book.add_author("Test Author")
    return book


@pytest.fixture
def reference_book(sample_rack):
    """Create a reference-only book for testing."""
    book = BookItem(
        ISBN="978-0-987654-32-1",
        title="Reference Book",
        subject="Reference",
        publisher="Ref Publisher",
        language="English",
        number_of_pages=500,
        barcode="RB001",
        is_reference_only=True,
        borrowed=None,
        due_date=None,
        price=49.99,
        book_format=BookFormat.HARDCOVER,
        status=BookStatus.AVAILABLE,
        date_of_purchase=datetime.now(),
        publication_date=datetime(2019, 1, 1),
        placed_at=sample_rack
    )
    book.add_author("Reference Author")
    return book


@pytest.fixture
def library():
    """Create a library instance for testing."""
    return Library("Test Library")


@pytest.fixture
def member(sample_person):
    """Create a member for testing."""
    return Member("M001", "password", sample_person, AccountStatus.ACTIVE)


@pytest.fixture
def librarian(sample_person):
    """Create a librarian for testing."""
    return Librarian("L001", "libpass", sample_person, AccountStatus.ACTIVE)


class TestAddress:
    """Test Address class functionality."""
    
    def test_address_creation(self, sample_address):
        assert sample_address.get_street_address() == "123 Test St"
        assert sample_address.get_city() == "Test City"
        assert sample_address.get_state() == "TS"
        assert sample_address.get_zip_code() == "12345"
        assert sample_address.get_country() == "Test Country"


class TestPerson:
    """Test Person class functionality."""
    
    def test_person_creation(self, sample_person, sample_address):
        assert sample_person.get_name() == "Test User"
        assert sample_person.get_address() == sample_address
        assert sample_person.get_email() == "test@example.com"
        assert sample_person.get_phone() == "555-0000"


class TestBookItem:
    """Test BookItem class functionality."""
    
    def test_book_creation(self, sample_book):
        assert sample_book.get_ISBN() == "978-0-123456-78-9"
        assert sample_book.get_title() == "Test Book"
        assert sample_book.get_subject() == "Testing"
        assert sample_book.get_publisher() == "Test Publisher"
        assert sample_book.get_language() == "English"
        assert sample_book.get_number_of_pages() == 300
        assert sample_book.get_barcode() == "TB001"
        assert sample_book.get_is_reference_only() == False
        assert sample_book.get_price() == 29.99
        assert sample_book.get_format() == BookFormat.PAPERBACK
        assert sample_book.get_status() == BookStatus.AVAILABLE
    
    def test_book_authors(self, sample_book):
        assert len(sample_book.get_authors()) == 1
        assert "Test Author" in sample_book.get_authors()
        
        sample_book.add_author("Second Author")
        assert len(sample_book.get_authors()) == 2
        assert "Second Author" in sample_book.get_authors()
    
    def test_book_status_update(self, sample_book):
        sample_book.update_book_item_status(BookStatus.LOANED)
        assert sample_book.get_status() == BookStatus.LOANED
        
        sample_book.update_book_item_status(BookStatus.AVAILABLE)
        assert sample_book.get_status() == BookStatus.AVAILABLE
    
    def test_checkout_regular_book(self, sample_book):
        result = sample_book.checkout("M001")
        assert result == True
        assert sample_book.get_status() == BookStatus.LOANED
    
    def test_checkout_reference_book(self, reference_book, capsys):
        result = reference_book.checkout("M001")
        assert result == False
        captured = capsys.readouterr()
        assert "Reference only" in captured.out


class TestRack:
    """Test Rack class functionality."""
    
    def test_rack_creation(self, sample_rack):
        assert sample_rack.get_number() == 1
        assert sample_rack.get_location_identifier() == "A-1-01"


class TestBookLending:
    """Test BookLending class functionality."""
    
    def test_lending_creation(self):
        creation_date = datetime.now()
        due_date = creation_date + timedelta(days=10)
        lending = BookLending(creation_date, due_date, "TB001", "M001")
        
        assert lending.get_creation_date() == creation_date
        assert lending.get_due_date() == due_date
        assert lending.get_book_item_barcode() == "TB001"
        assert lending.get_member_id() == "M001"
        assert lending.get_return_date() is None
    
    def test_set_return_date(self):
        creation_date = datetime.now()
        due_date = creation_date + timedelta(days=10)
        lending = BookLending(creation_date, due_date, "TB001", "M001")
        
        return_date = datetime.now()
        lending.set_return_date(return_date)
        assert lending.get_return_date() == return_date


class TestBookReservation:
    """Test BookReservation class functionality."""
    
    def test_reservation_creation(self):
        creation_date = datetime.now()
        reservation = BookReservation(creation_date, ReservationStatus.WAITING, "TB001", "M001")
        
        assert reservation.get_creation_date() == creation_date
        assert reservation.get_status() == ReservationStatus.WAITING
        assert reservation.get_book_item_barcode() == "TB001"
        assert reservation.get_member_id() == "M001"
    
    def test_update_status(self):
        creation_date = datetime.now()
        reservation = BookReservation(creation_date, ReservationStatus.WAITING, "TB001", "M001")
        
        reservation.update_status(ReservationStatus.PENDING)
        assert reservation.get_status() == ReservationStatus.PENDING


class TestFine:
    """Test Fine class functionality."""
    
    def test_fine_creation(self):
        creation_date = datetime.now()
        fine = Fine(creation_date, "TB001", "M001")
        
        assert fine.get_creation_date() == creation_date
        assert fine.get_book_item_barcode() == "TB001"
        assert fine.get_member_id() == "M001"
        assert fine.get_fine_amount() == 0.0
    
    def test_calculate_fine(self):
        creation_date = datetime.now()
        fine = Fine(creation_date, "TB001", "M001")
        
        amount = fine.calculate_fine(5)
        assert amount == 5.0  # $1 per day * 5 days
        assert fine.get_fine_amount() == 5.0
    
    def test_collect_fine(self):
        creation_date = datetime.now()
        fine = Fine(creation_date, "TB001", "M001")
        
        amount = fine.collect_fine("M001", 3)
        assert amount == 3.0
    
    def test_collect_fine_wrong_member(self):
        creation_date = datetime.now()
        fine = Fine(creation_date, "TB001", "M001")
        
        with pytest.raises(ValueError, match="Member ID does not match"):
            fine.collect_fine("M002", 3)


class TestMember:
    """Test Member class functionality."""
    
    def test_member_creation(self, member, sample_person):
        assert member.get_id() == "M001"
        assert member.get_person() == sample_person
        assert member.get_status() == AccountStatus.ACTIVE
        assert member.get_total_books_checkedout() == 0
    
    def test_increment_books_checkedout(self, member):
        member.increment_total_books_checkedout()
        assert member.get_total_books_checkedout() == 1
        
        member.increment_total_books_checkedout()
        assert member.get_total_books_checkedout() == 2
    
    def test_decrement_books_checkedout(self, member):
        member.increment_total_books_checkedout()
        member.increment_total_books_checkedout()
        assert member.get_total_books_checkedout() == 2
        
        member.decrement_total_books_checkedout()
        assert member.get_total_books_checkedout() == 1
    
    def test_decrement_books_checkedout_zero(self, member):
        # Should not go below zero
        member.decrement_total_books_checkedout()
        assert member.get_total_books_checkedout() == 0
    
    def test_checkout_book_item(self, member, sample_book):
        result = member.checkout_book_item(sample_book)
        assert result == True
        assert member.get_total_books_checkedout() == 1
        assert sample_book.get_status() == BookStatus.LOANED


class TestLibrarian:
    """Test Librarian class functionality."""
    
    def test_librarian_creation(self, librarian, sample_person):
        assert librarian.get_id() == "L001"
        assert librarian.get_person() == sample_person
        assert librarian.get_status() == AccountStatus.ACTIVE
    
    def test_add_book_item(self, librarian, sample_book, capsys):
        result = librarian.add_book_item(sample_book)
        assert result == True
        captured = capsys.readouterr()
        assert "added by librarian" in captured.out
    
    def test_block_member(self, librarian, member, capsys):
        librarian.block_member(member)
        assert member.get_status() == AccountStatus.BLACKLISTED
        captured = capsys.readouterr()
        assert "blocked" in captured.out
    
    def test_unblock_member(self, librarian, member, capsys):
        librarian.block_member(member)
        librarian.un_block_member(member)
        assert member.get_status() == AccountStatus.ACTIVE
        captured = capsys.readouterr()
        assert "unblocked" in captured.out


class TestLibrary:
    """Test Library class functionality."""
    
    def test_library_creation(self, library):
        assert library.name == "Test Library"
        assert len(library.book_catalog) == 0
        assert len(library.members) == 0
    
    def test_add_book_item(self, library, sample_book, capsys):
        result = library.add_book_item(sample_book)
        assert result == True
        assert "TB001" in library.book_catalog
        captured = capsys.readouterr()
        assert "added successfully" in captured.out
    
    def test_add_duplicate_book(self, library, sample_book, capsys):
        library.add_book_item(sample_book)
        result = library.add_book_item(sample_book)
        assert result == False
        captured = capsys.readouterr()
        assert "already exists" in captured.out
    
    def test_remove_book_item(self, library, sample_book, capsys):
        library.add_book_item(sample_book)
        result = library.remove_book_item("TB001")
        assert result == True
        assert "TB001" not in library.book_catalog
        captured = capsys.readouterr()
        assert "removed successfully" in captured.out
    
    def test_register_member(self, library, member, capsys):
        result = library.register_member(member)
        assert result == True
        assert "M001" in library.members
        captured = capsys.readouterr()
        assert "registered successfully" in captured.out
    
    def test_checkout_book(self, library, sample_book, member, capsys):
        library.add_book_item(sample_book)
        library.register_member(member)
        
        result = library.checkout_book("TB001", "M001")
        assert result == True
        assert sample_book.get_status() == BookStatus.LOANED
        assert member.get_total_books_checkedout() == 1
        captured = capsys.readouterr()
        assert "checked out successfully" in captured.out
    
    def test_checkout_nonexistent_book(self, library, member, capsys):
        library.register_member(member)
        result = library.checkout_book("INVALID", "M001")
        assert result == False
        captured = capsys.readouterr()
        assert "not found" in captured.out
    
    def test_checkout_reference_book(self, library, reference_book, member, capsys):
        library.add_book_item(reference_book)
        library.register_member(member)
        
        result = library.checkout_book("RB001", "M001")
        assert result == False
        captured = capsys.readouterr()
        assert "reference only" in captured.out
    
    def test_return_book(self, library, sample_book, member, capsys):
        library.add_book_item(sample_book)
        library.register_member(member)
        library.checkout_book("TB001", "M001")
        
        result = library.return_book("TB001", "M001")
        assert result == True
        assert sample_book.get_status() == BookStatus.AVAILABLE
        assert member.get_total_books_checkedout() == 0
        captured = capsys.readouterr()
        assert "returned successfully" in captured.out
    
    def test_search_by_title(self, library, sample_book):
        library.add_book_item(sample_book)
        results = library.search_by_title("Test")
        assert len(results) == 1
        assert results[0].get_title() == "Test Book"
    
    def test_search_by_author(self, library, sample_book):
        library.add_book_item(sample_book)
        results = library.search_by_author("Test Author")
        assert len(results) == 1
        assert results[0].get_title() == "Test Book"
    
    def test_get_available_books(self, library, sample_book, reference_book):
        library.add_book_item(sample_book)
        library.add_book_item(reference_book)
        
        available = library.get_available_books()
        assert len(available) == 2
        
        # Checkout one book
        member_address = Address("123 St", "City", "ST", "12345", "Country")
        member_person = TestPerson("User", member_address, "user@test.com", "555-0000")
        member = Member("M001", "pass", member_person)
        library.register_member(member)
        library.checkout_book("TB001", "M001")
        
        available = library.get_available_books()
        assert len(available) == 1


if __name__ == "__main__":
    pytest.main([__file__, "-v"])
