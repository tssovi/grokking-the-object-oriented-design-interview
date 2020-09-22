from enum import Enum


class FlightStatus(Enum):
    ACTIVE, SCHEDULED, DELAYED, DEPARTED, LANDED, IN_AIR, ARRIVED, CANCELLED, DIVERTED, UNKNOWN = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10


class PaymentStatus(Enum):
    UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10


class ReservationStatus(Enum):
    REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELLED, ABANDONED = 1, 2, 3, 4, 5, 6


class SeatClass(Enum):
    ECONOMY, ECONOMY_PLUS, PREFERRED_ECONOMY, BUSINESS, FIRST_CLASS = 1, 2, 3, 4, 5


class SeatType(Enum):
    REGULAR, ACCESSIBLE, EMERGENCY_EXIT, EXTRA_LEG_ROOM = 1, 2, 3, 4, 5


class AccountStatus(Enum):
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED = 1, 2, 3, 4, 5, 6


class Address:
    def __init__(self, street, city, state, zip_code, country):
        self.__street_address = street
        self.__city = city
        self.__state = state
        self.__zip_code = zip_code
        self.__country = country

