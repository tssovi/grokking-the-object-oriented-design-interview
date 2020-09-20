from abc import ABC, abstractmethod
from .constants import *


class Book(ABC):
    def __init__(self, ISBN, title, subject, publisher, language, number_of_pages):
        self.__ISBN = ISBN
        self.__title = title
        self.__subject = subject
        self.__publisher = publisher
        self.__language = language
        self.__number_of_pages = number_of_pages
        self.__authors = []


class BookItem(Book):
    def __init__(self, barcode, is_reference_only, borrowed, due_date, price, book_format, status,
                 date_of_purchase, publication_date, placed_at):
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

    def checkout(self, member_id):
        if self.get_is_reference_only():
            print("self book is Reference only and can't be issued")
            return False
        if not BookLending.lend_book(self.get_bar_code(), member_id):
            return False
        self.update_book_item_status(BookStatus.LOANED)
        return True


class Rack:
    def __init__(self, number, location_identifier):
        self.__number = number
        self.__location_identifier = location_identifier


class BookReservation:
    def __init__(self, creation_date, status, book_item_barcode, member_id):
        self.__creation_date = creation_date
        self.__status = status
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id

    def fetch_reservation_details(self, barcode):
        None


class BookLending:
    def __init__(self, creation_date, due_date, book_item_barcode, member_id):
        self.__creation_date = creation_date
        self.__due_date = due_date
        self.__return_date = None
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id

    def lend_book(self, barcode, member_id):
        None

    def fetch_lending_details(self, barcode):
        None


class Fine:
    def __init__(self, creation_date, book_item_barcode, member_id):
        self.__creation_date = creation_date
        self.__book_item_barcode = book_item_barcode
        self.__member_id = member_id

    def collect_fine(self, member_id, days):
        None

