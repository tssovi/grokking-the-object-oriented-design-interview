# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.


from abc import ABC, abstractmethod
from datetime import datetime

from .constants import *
from .models import *


class Account(ABC):
    def __init__(self, id, password, person, status=AccountStatus.Active):
        self.__id = id
        self.__password = password
        self.__status = status
        self.__person = person

    def reset_password(self):
        None


class Librarian(Account):
    def __init__(self, id, password, person, status=AccountStatus.Active):
        super().__init__(id, password, person, status)

    def add_book_item(self, book_item):
        None

    def block_member(self, member):
        None

    def un_block_member(self, member):
        None


class Member(Account):
    def __init__(self, id, password, person, status=AccountStatus.Active):
        super().__init__(id, password, person, status)
        self.__date_of_membership = datetime.date.today()
        self.__total_books_checkedout = 0

    def get_total_books_checkedout(self):
        return self.__total_books_checkedout

    def reserve_book_item(self, book_item):
        None

    def increment_total_books_checkedout(self):
        None

    def renew_book_item(self, book_item):
        None

    def checkout_book_item(self, book_item):
        if self.get_total_books_checked_out() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER:
            print("The user has already checked-out maximum number of books")
            return False
        book_reservation = BookReservation.fetch_reservation_details(book_item.get_barcode())
        if book_reservation != None and book_reservation.get_member_id() != self.get_id():
            # book item has a pending reservation from another user
            print("self book is reserved by another member")
            return False
        elif book_reservation != None:
            # book item has a pending reservation from the give member, update it
            book_reservation.update_status(ReservationStatus.COMPLETED)

        if not book_item.checkout(self.get_id()):
            return False

        self.increment_total_books_checkedout()
        return True

    def check_for_fine(self, book_item_barcode):
        book_lending = BookLending.fetch_lending_details(book_item_barcode)
        due_date = book_lending.get_due_date()
        today = datetime.date.today()
        # check if the book has been returned within the due date
        if today > due_date:
            diff = today - due_date
            diff_days = diff.days
            Fine.collect_fine(self.get_member_id(), diff_days)

    def return_book_item(self, book_item):
        self.check_for_fine(book_item.get_barcode())
        book_reservation = BookReservation.fetch_reservation_details(book_item.get_barcode())
        if book_reservation != None:
            # book item has a pending reservation
            book_item.update_book_item_status(BookStatus.RESERVED)
            book_reservation.send_book_available_notification()
            book_item.update_book_item_status(BookStatus.AVAILABLE)

    def renew_book_item(self, book_item):
        self.check_for_fine(book_item.get_barcode())
        book_reservation = BookReservation.fetch_reservation_details(
        book_item.get_barcode())
        # check if self book item has a pending reservation from another member
        if book_reservation != None and book_reservation.get_member_id() != self.get_member_id():
            print("self book is reserved by another member")
            self.decrement_total_books_checkedout()
            book_item.update_book_item_state(BookStatus.RESERVED)
            book_reservation.send_book_available_notification()
            return False
        elif book_reservation != None:
            # book item has a pending reservation from self member
            book_reservation.update_status(ReservationStatus.COMPLETED)

        BookLending.lend_book(book_item.get_bar_code(), self.get_member_id())
        book_item.update_due_date(datetime.datetime.now().AddDays(Constants.MAX_LENDING_DAYS))
        return True

