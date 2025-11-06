# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.


from abc import ABC, abstractmethod
from datetime import datetime
from typing import Optional

try:
    from .constants import *
    from .models import *
except ImportError:
    from constants import *
    from models import *


class Account(ABC):
    def __init__(self, id: str, password: str, person: Person, status=AccountStatus.ACTIVE):
        self.__id = id
        self.__password = password
        self.__status = status
        self.__person = person
    
    def get_id(self) -> str:
        return self.__id
    
    def get_status(self) -> AccountStatus:
        return self.__status
    
    def get_person(self) -> Person:
        return self.__person

    def reset_password(self, new_password: str):
        self.__password = new_password


class Librarian(Account):
    def __init__(self, id: str, password: str, person: Person, status=AccountStatus.ACTIVE):
        super().__init__(id, password, person, status)

    def add_book_item(self, book_item: BookItem) -> bool:
        # In a real system, this would add to database
        print(f"Book '{book_item.get_title()}' added by librarian.")
        return True

    def block_member(self, member: 'Member'):
        member.update_status(AccountStatus.BLACKLISTED)
        print(f"Member {member.get_id()} has been blocked.")

    def un_block_member(self, member: 'Member'):
        member.update_status(AccountStatus.ACTIVE)
        print(f"Member {member.get_id()} has been unblocked.")


class Member(Account):
    def __init__(self, id: str, password: str, person: Person, status=AccountStatus.ACTIVE):
        super().__init__(id, password, person, status)
        self.__date_of_membership = datetime.now().date()
        self.__total_books_checkedout = 0

    def get_total_books_checkedout(self) -> int:
        return self.__total_books_checkedout
    
    def update_status(self, status: AccountStatus):
        self._Account__status = status

    def reserve_book_item(self, book_item: BookItem) -> bool:
        # In a real system, this would create a reservation
        print(f"Book '{book_item.get_title()}' reserved successfully.")
        return True

    def increment_total_books_checkedout(self):
        self.__total_books_checkedout += 1
    
    def decrement_total_books_checkedout(self):
        if self.__total_books_checkedout > 0:
            self.__total_books_checkedout -= 1

    def checkout_book_item(self, book_item: BookItem) -> bool:
        constants = Constants()
        if self.get_total_books_checkedout() >= constants.MAX_BOOKS_ISSUED_TO_A_USER:
            print("The user has already checked-out maximum number of books")
            return False
        
        # Note: In a real system, we would fetch from database
        # book_reservation = BookReservation.fetch_reservation_details(book_item.get_barcode())
        # For now, we'll skip reservation checking in this method
        
        if not book_item.checkout(self.get_id()):
            return False

        self.increment_total_books_checkedout()
        return True

    def check_for_fine(self, book_item_barcode: str) -> Optional[float]:
        # Note: In a real system, we would fetch from database
        # book_lending = BookLending.fetch_lending_details(book_item_barcode)
        # For now, return None as this requires database integration
        return None

    def return_book_item(self, book_item: BookItem) -> bool:
        self.check_for_fine(book_item.get_barcode())
        # Note: In a real system, we would check for reservations
        book_item.update_book_item_status(BookStatus.AVAILABLE)
        self.decrement_total_books_checkedout()
        print(f"Book '{book_item.get_title()}' returned successfully.")
        return True

    def renew_book_item(self, book_item: BookItem) -> bool:
        self.check_for_fine(book_item.get_barcode())
        # Note: In a real system, we would check for reservations
        # For now, simplified implementation
        print(f"Book '{book_item.get_title()}' renewed successfully.")
        return True
        return True

