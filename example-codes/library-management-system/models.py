from abc import ABC, abstractmethod
from datetime import datetime
from typing import List, Optional

try:
    from .constants import *
except ImportError:
    from constants import *


class Book(ABC):
    def __init__(self, ISBN: str, title: str, subject: str, publisher: str, language: str, number_of_pages: int):
        self.__ISBN = ISBN
        self.__title = title
        self.__subject = subject
        self.__publisher = publisher
        self.__language = language
        self.__number_of_pages = number_of_pages
        self.__authors: List[str] = []
    
    def get_ISBN(self) -> str:
        return self.__ISBN
    
    def get_title(self) -> str:
        return self.__title
    
    def get_subject(self) -> str:
        return self.__subject
    
    def get_publisher(self) -> str:
        return self.__publisher
    
    def get_language(self) -> str:
        return self.__language
    
    def get_number_of_pages(self) -> int:
        return self.__number_of_pages
    
    def get_authors(self) -> List[str]:
        return self.__authors
    
    def add_author(self, author: str):
        self.__authors.append(author)


class BookItem(Book):
    def __init__(self, ISBN: str, title: str, subject: str, publisher: str, language: str, 
                 number_of_pages: int, barcode: str, is_reference_only: bool, borrowed: datetime, 
                 due_date: datetime, price: float, book_format: BookFormat, status: BookStatus,
                 date_of_purchase: datetime, publication_date: datetime, placed_at: 'Rack'):
        # Call parent constructor
        super().__init__(ISBN, title, subject, publisher, language, number_of_pages)
        self.__barcode = barcode
        self.__is_reference_only = is_reference_only
        self.__borrowed = borrowed
        self.__due_date = due_date
        self.__price = price
        self.__format = book_format
        self.__status = status
        self.__date_of_purchase = date_of_purchase
        self.__publication_date = publication_date
        self.__placed_at = placed_at
    
    def get_barcode(self) -> str:
        return self.__barcode
    
    def get_is_reference_only(self) -> bool:
        return self.__is_reference_only
    
    def get_borrowed(self) -> datetime:
        return self.__borrowed
    
    def get_due_date(self) -> datetime:
        return self.__due_date
    
    def get_price(self) -> float:
        return self.__price
    
    def get_format(self) -> BookFormat:
        return self.__format
    
    def get_status(self) -> BookStatus:
        return self.__status
    
    def get_date_of_purchase(self) -> datetime:
        return self.__date_of_purchase
    
    def get_publication_date(self) -> datetime:
        return self.__publication_date
    
    def get_placed_at(self) -> 'Rack':
        return self.__placed_at
    
    def update_book_item_status(self, status: BookStatus):
        self.__status = status

    def checkout(self, member_id: str) -> bool:
        if self.get_is_reference_only():
            print("This book is Reference only and can't be issued")
            return False
        self.update_book_item_status(BookStatus.LOANED)
        return True


class Rack:
    def __init__(self, number: int, location_identifier: str):
        self.__number = number
        self.__location_identifier = location_identifier
    
    def get_number(self) -> int:
        return self.__number
    
    def get_location_identifier(self) -> str:
        return self.__location_identifier


class BookReservation:
    def __init__(self, creation_date: datetime, status: ReservationStatus, book_item_barcode: str, member_id: str):
        self.__creation_date = creation_date
        self.__status = status
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id
    
    def get_creation_date(self) -> datetime:
        return self.__creation_date
    
    def get_status(self) -> ReservationStatus:
        return self.__status
    
    def get_book_item_barcode(self) -> str:
        return self.__book_item_barcode
    
    def get_member_id(self) -> str:
        return self.__member_id
    
    def update_status(self, status: ReservationStatus):
        self.__status = status

    def fetch_reservation_details(self, barcode: str) -> Optional['BookReservation']:
        # In a real system, this would query a database
        if self.__book_item_barcode == barcode:
            return self
        return None


class BookLending:
    def __init__(self, creation_date: datetime, due_date: datetime, book_item_barcode: str, member_id: str):
        self.__creation_date = creation_date
        self.__due_date = due_date
        self.__return_date: Optional[datetime] = None
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id
    
    def get_creation_date(self) -> datetime:
        return self.__creation_date
    
    def get_due_date(self) -> datetime:
        return self.__due_date
    
    def get_return_date(self) -> Optional[datetime]:
        return self.__return_date
    
    def set_return_date(self, return_date: datetime):
        self.__return_date = return_date
    
    def get_book_item_barcode(self) -> str:
        return self.__book_item_barcode
    
    def get_member_id(self) -> str:
        return self.__member_id

    def fetch_lending_details(self, barcode: str) -> Optional['BookLending']:
        # In a real system, this would query a database
        if self.__book_item_barcode == barcode:
            return self
        return None


class Fine:
    FINE_PER_DAY = 1.0  # $1 per day overdue
    
    def __init__(self, creation_date: datetime, book_item_barcode: str, member_id: str):
        self.__creation_date = creation_date
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id
        self.__fine_amount = 0.0
    
    def get_creation_date(self) -> datetime:
        return self.__creation_date
    
    def get_book_item_barcode(self) -> str:
        return self.__book_item_barcode
    
    def get_member_id(self) -> str:
        return self.__member_id
    
    def get_fine_amount(self) -> float:
        return self.__fine_amount
    
    def calculate_fine(self, days_overdue: int) -> float:
        """Calculate fine based on days overdue."""
        self.__fine_amount = days_overdue * self.FINE_PER_DAY
        return self.__fine_amount

    def collect_fine(self, member_id: str, days: int) -> float:
        """Collect fine from member."""
        if self.__member_id != member_id:
            raise ValueError("Member ID does not match")
        return self.calculate_fine(days)

