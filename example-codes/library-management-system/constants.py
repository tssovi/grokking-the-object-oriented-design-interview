from abc import ABC
from enum import Enum


class BookFormat(Enum):
    HARDCOVER, PAPERBACK, AUDIO_BOOK, EBOOK, NEWSPAPER, MAGAZINE, JOURNAL = 1, 2, 3, 4, 5, 6, 7


class BookStatus(Enum):
    AVAILABLE, RESERVED, LOANED, LOST = 1, 2, 3, 4


class ReservationStatus(Enum):
    WAITING, PENDING, CANCELED, NONE = 1, 2, 3, 4


class AccountStatus(Enum):
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, NONE = 1, 2, 3, 4, 5


class Address:
    def __init__(self, street: str, city: str, state: str, zip_code: str, country: str):
        self.__street_address = street
        self.__city = city
        self.__state = state
        self.__zip_code = zip_code
        self.__country = country
    
    def get_street_address(self) -> str:
        return self.__street_address
    
    def get_city(self) -> str:
        return self.__city
    
    def get_state(self) -> str:
        return self.__state
    
    def get_zip_code(self) -> str:
        return self.__zip_code
    
    def get_country(self) -> str:
        return self.__country


class Person(ABC):
    def __init__(self, name: str, address: Address, email: str, phone: str):
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone
    
    def get_name(self) -> str:
        return self.__name
    
    def get_address(self) -> Address:
        return self.__address
    
    def get_email(self) -> str:
        return self.__email
    
    def get_phone(self) -> str:
        return self.__phone


class Constants:
    def __init__(self):
          self.MAX_BOOKS_ISSUED_TO_A_USER = 5
          self.MAX_LENDING_DAYS = 10

