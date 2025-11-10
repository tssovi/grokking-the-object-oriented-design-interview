"""
Executable Library Management System Example
This module provides a working implementation of the library management system.
"""

from datetime import datetime, timedelta
from typing import List, Dict, Optional

try:
    from .constants import *
    from .models import *
    from .account_types import *
except ImportError:
    from constants import *
    from models import *
    from account_types import *


class Library:
    """Main Library class that manages the entire library system."""
    
    def __init__(self, name: str):
        self.name = name
        self.book_catalog: Dict[str, BookItem] = {}  # barcode -> BookItem
        self.members: Dict[str, Member] = {}  # member_id -> Member
        self.librarians: Dict[str, Librarian] = {}  # librarian_id -> Librarian
        self.active_lendings: Dict[str, BookLending] = {}  # barcode -> BookLending
        self.reservations: Dict[str, BookReservation] = {}  # barcode -> BookReservation
        self.constants = Constants()
        
    def add_book_item(self, book_item: BookItem) -> bool:
        """Add a book item to the library catalog."""
        barcode = book_item.get_barcode()
        if barcode in self.book_catalog:
            print(f"Book with barcode {barcode} already exists.")
            return False
        self.book_catalog[barcode] = book_item
        print(f"Book '{book_item.get_title()}' added successfully.")
        return True
    
    def remove_book_item(self, barcode: str) -> bool:
        """Remove a book item from the library catalog."""
        if barcode not in self.book_catalog:
            print(f"Book with barcode {barcode} not found.")
            return False
        book = self.book_catalog.pop(barcode)
        print(f"Book '{book.get_title()}' removed successfully.")
        return True
    
    def register_member(self, member: Member) -> bool:
        """Register a new member."""
        member_id = member.get_id()
        if member_id in self.members:
            print(f"Member with ID {member_id} already exists.")
            return False
        self.members[member_id] = member
        print(f"Member {member.get_person().get_name()} registered successfully.")
        return True
    
    def checkout_book(self, barcode: str, member_id: str) -> bool:
        """Checkout a book for a member."""
        if barcode not in self.book_catalog:
            print(f"Book with barcode {barcode} not found.")
            return False
        
        if member_id not in self.members:
            print(f"Member with ID {member_id} not found.")
            return False
        
        book_item = self.book_catalog[barcode]
        member = self.members[member_id]
        
        # Check if member has reached the limit
        if member.get_total_books_checkedout() >= self.constants.MAX_BOOKS_ISSUED_TO_A_USER:
            print(f"Member has reached the maximum limit of {self.constants.MAX_BOOKS_ISSUED_TO_A_USER} books.")
            return False
        
        # Check if book is available
        if book_item.get_status() != BookStatus.AVAILABLE:
            print(f"Book is not available. Current status: {book_item.get_status().name}")
            return False
        
        # Check if it's reference only
        if book_item.get_is_reference_only():
            print("This book is reference only and cannot be checked out.")
            return False
        
        # Create lending record
        creation_date = datetime.now()
        due_date = creation_date + timedelta(days=self.constants.MAX_LENDING_DAYS)
        lending = BookLending(creation_date, due_date, barcode, member_id)
        
        # Update book status
        book_item.update_book_item_status(BookStatus.LOANED)
        self.active_lendings[barcode] = lending
        member.increment_total_books_checkedout()
        
        print(f"Book '{book_item.get_title()}' checked out successfully. Due date: {due_date.strftime('%Y-%m-%d')}")
        return True
    
    def return_book(self, barcode: str, member_id: str) -> bool:
        """Return a book."""
        if barcode not in self.active_lendings:
            print(f"No active lending found for barcode {barcode}.")
            return False
        
        lending = self.active_lendings[barcode]
        if lending.get_member_id() != member_id:
            print("This book was not checked out by this member.")
            return False
        
        book_item = self.book_catalog[barcode]
        member = self.members[member_id]
        
        # Calculate fine if overdue
        return_date = datetime.now()
        lending.set_return_date(return_date)
        
        if return_date > lending.get_due_date():
            days_overdue = (return_date - lending.get_due_date()).days
            fine = Fine(datetime.now(), barcode, member_id)
            fine_amount = fine.calculate_fine(days_overdue)
            print(f"Book returned late. Fine: ${fine_amount:.2f} for {days_overdue} days overdue.")
        else:
            print(f"Book '{book_item.get_title()}' returned successfully.")
        
        # Update status
        book_item.update_book_item_status(BookStatus.AVAILABLE)
        del self.active_lendings[barcode]
        member.decrement_total_books_checkedout()
        
        return True
    
    def reserve_book(self, barcode: str, member_id: str) -> bool:
        """Reserve a book for a member."""
        if barcode not in self.book_catalog:
            print(f"Book with barcode {barcode} not found.")
            return False
        
        if member_id not in self.members:
            print(f"Member with ID {member_id} not found.")
            return False
        
        book_item = self.book_catalog[barcode]
        
        if book_item.get_status() == BookStatus.AVAILABLE:
            print("Book is available. Please checkout instead of reserving.")
            return False
        
        reservation = BookReservation(datetime.now(), ReservationStatus.WAITING, barcode, member_id)
        self.reservations[barcode] = reservation
        book_item.update_book_item_status(BookStatus.RESERVED)
        
        print(f"Book '{book_item.get_title()}' reserved successfully.")
        return True
    
    def search_by_title(self, title: str) -> List[BookItem]:
        """Search books by title."""
        results = [book for book in self.book_catalog.values() 
                  if title.lower() in book.get_title().lower()]
        return results
    
    def search_by_author(self, author: str) -> List[BookItem]:
        """Search books by author."""
        results = [book for book in self.book_catalog.values() 
                  if any(author.lower() in a.lower() for a in book.get_authors())]
        return results
    
    def get_available_books(self) -> List[BookItem]:
        """Get all available books."""
        return [book for book in self.book_catalog.values() 
                if book.get_status() == BookStatus.AVAILABLE]
