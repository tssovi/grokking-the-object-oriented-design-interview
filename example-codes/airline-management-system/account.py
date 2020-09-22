from abc import ABC
from .constants import *


# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.

class Account:
    def __init__(self, id, password, status=AccountStatus.Active):
        self.__id = id
        self.__password = password
        self.__status = status

    def reset_password(self):
        None


class Person(ABC):
    def __init__(self, name, address, email, phone, account):
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone
        self.__account = account


class Customer(Person):
    def __init__(self, frequent_flyer_number):
        self.__frequent_flyer_number

    def get_itineraries(self):
        None


class Passenger:
    def __init__(self, name, passport_number, date_of_birth):
        self.__name = name
        self.__passport_number = passport_number
        self.__date_of_birth = date_of_birth

    def get_passport_number(self):
        return self.__passport_number

