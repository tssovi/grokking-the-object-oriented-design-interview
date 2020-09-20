from abc import ABC
from .constants import *


# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.


class Account:
    def __init__(self, user_name, password, name, email, phone, shipping_address, status=AccountStatus):
        self.__user_name = user_name
        self.__password = password
        self.__name = name
        self.__email = email
        self.__phone = phone
        self.__shipping_address = shipping_address
        self.__status = status.ACTIVE
        self.__credit_cards = []
        self.__bank_accounts = []

    def add_product(self, product):
        None

    def add_productReview(self, review):
        None

    def reset_password(self):
        None


class Customer(ABC):
    def __init__(self, cart, order):
        self.__cart = cart
        self.__order = order

    def get_shopping_cart(self):
        return self.__cart

    def add_item_to_cart(self, item):
        None

    def remove_item_from_cart(self, item):
        None


class Guest(Customer):
    def register_account(self):
        None


class Member(Customer):
    def __init__(self, account):
        self.__account = account

    def place_order(self, order):
        None

