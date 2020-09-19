from datetime import datetime
from .constants import *


class Item:
    def __init__(self, id, quantity, price):
        self.__product_id = id
        self.__quantity = quantity
        self.__price = price

    def update_quantity(self, quantity):
        None


class ShoppingCart:
    def __init__(self):
        self.__items = []

    def add_item(self, item):
        None

    def remove_item(self, item):
        None

    def update_item_quantity(self, item, quantity):
        None

    def get_items(self):
        return self.__items

    def checkout(self):
        None


class OrderLog:
    def __init__(self, order_number, status=OrderStatus.PENDING):
        self.__order_number = order_number
        self.__creation_date = datetime.date.today()
        self.__status = status


class Order:
    def __init__(self, order_number, status=OrderStatus.PENDING):
        self.__order_number = 0
        self.__status = status
        self.__order_date = datetime.date.today()
        self.__order_log = []

    def send_for_shipment(self):
        None

    def make_payment(self, payment):
        None

    def add_order_log(self, order_log):
        None

