from abc import ABC
from .constants import  *


class Vehicle(ABC):
    def __init__(self, license_number, vehicle_type, ticket=None):
        self.__license_number = license_number
        self.__type = vehicle_type
        self.__ticket = ticket

    def assign_ticket(self, ticket):
        self.__ticket = ticket


class Car(Vehicle):
    def __init__(self, license_number, ticket=None):
        super().__init__(license_number, VehicleType.CAR, ticket)


class Van(Vehicle):
    def __init__(self, license_number, ticket=None):
        super().__init__(license_number, VehicleType.VAN, ticket)


class Truck(Vehicle):
    def __init__(self, license_number, ticket=None):
        super().__init__(license_number, VehicleType.TRUCK, ticket)


# Similarly we can define classes for Motorcycle and Electric vehicles

