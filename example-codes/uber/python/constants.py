"""
constants.py
Defines enums and configuration constants used across the Uber system.
"""

from enum import Enum, auto


class AccountStatus(Enum):
    ACTIVE = auto()
    SUSPENDED = auto()
    CLOSED = auto()


class TripStatus(Enum):
    REQUESTED = auto()
    ONGOING = auto()
    COMPLETED = auto()
    CANCELLED = auto()


class VehicleType(Enum):
    SEDAN = auto()
    SUV = auto()
    HATCHBACK = auto()
    MOTORBIKE = auto()


# Any global configuration (e.g. base fare, per-km charge)
BASE_FARE = 5.0
COST_PER_KM = 2.0
