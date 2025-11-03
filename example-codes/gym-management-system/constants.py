"""
Constants Module - Gym Management System

This module contains all enumerations, constants, and base classes used throughout
the gym management system. It defines various status types, base entity classes,
and system-wide configuration values.
"""

from abc import ABC
from enum import Enum


class MembershipType(Enum):
    """
    Enumeration for different types of gym memberships.
    
    Attributes:
        BASIC: Basic membership with standard gym access
        PREMIUM: Premium membership with additional benefits
        VIP: VIP membership with all amenities and priority access
    """
    BASIC, PREMIUM, VIP = 1, 2, 3


class MembershipStatus(Enum):
    """
    Enumeration for membership status states.
    
    Attributes:
        ACTIVE: Membership is currently active and valid
        EXPIRED: Membership has passed its end date
        SUSPENDED: Membership is temporarily suspended
        CANCELED: Membership has been permanently canceled
    """
    ACTIVE, EXPIRED, SUSPENDED, CANCELED = 1, 2, 3, 4


class EquipmentStatus(Enum):
    """
    Enumeration for gym equipment status.
    
    Attributes:
        AVAILABLE: Equipment is available for use
        IN_USE: Equipment is currently being used
        UNDER_MAINTENANCE: Equipment is undergoing maintenance
        OUT_OF_ORDER: Equipment is broken and not usable
    """
    AVAILABLE, IN_USE, UNDER_MAINTENANCE, OUT_OF_ORDER = 1, 2, 3, 4


class ClassStatus(Enum):
    """
    Enumeration for fitness class status.
    
    Attributes:
        SCHEDULED: Class is scheduled for future
        ONGOING: Class is currently in progress
        COMPLETED: Class has been completed
        CANCELED: Class has been canceled
    """
    SCHEDULED, ONGOING, COMPLETED, CANCELED = 1, 2, 3, 4


class BookingStatus(Enum):
    """
    Enumeration for class booking status.
    
    Attributes:
        CONFIRMED: Booking is confirmed
        CANCELED: Booking has been canceled
        COMPLETED: Member attended the class
        NO_SHOW: Member did not show up for the class
    """
    CONFIRMED, CANCELED, COMPLETED, NO_SHOW = 1, 2, 3, 4


class PaymentStatus(Enum):
    """
    Enumeration for payment transaction status.
    
    Attributes:
        PENDING: Payment is pending processing
        COMPLETED: Payment has been successfully processed
        FAILED: Payment processing failed
        REFUNDED: Payment has been refunded
    """
    PENDING, COMPLETED, FAILED, REFUNDED = 1, 2, 3, 4


class AccountStatus(Enum):
    """
    Enumeration for user account status.
    
    Attributes:
        ACTIVE: Account is active and can access the system
        INACTIVE: Account is inactive but not closed
        SUSPENDED: Account has been suspended due to violations
        CLOSED: Account has been permanently closed
    """
    ACTIVE, INACTIVE, SUSPENDED, CLOSED = 1, 2, 3, 4


class Address:
    """
    Represents a physical address.
    
    This class encapsulates address information for persons, gyms, and branches.
    """
    
    def __init__(self, street, city, state, zip_code, country):
        """
        Initialize an Address object.
        
        Args:
            street (str): Street address
            city (str): City name
            state (str): State or province
            zip_code (str): Postal/ZIP code
            country (str): Country name
        """
        self.__street_address = street
        self.__city = city
        self.__state = state
        self.__zip_code = zip_code
        self.__country = country


class Person(ABC):
    """
    Abstract base class representing a person in the system.
    
    This class serves as the base for all person-related entities
    (members, trainers, managers, etc.) and contains common attributes.
    """
    
    def __init__(self, name, address, email, phone):
        """
        Initialize a Person object.
        
        Args:
            name (str): Full name of the person
            address (Address): Address object containing location details
            email (str): Email address for communication
            phone (str): Phone number for contact
        """
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone


class Constants:
    """
    System-wide constants and configuration values.
    
    This class contains all configurable parameters used throughout
    the gym management system for business rules and limits.
    """
    
    def __init__(self):
        """Initialize system constants with default values."""
        # Maximum number of participants allowed in a single class
        self.MAX_CLASS_CAPACITY = 30
        
        # Maximum number of classes a member can book simultaneously
        self.MAX_BOOKINGS_PER_MEMBER = 10
        
        # Number of days before membership expiry to send renewal reminder
        self.MEMBERSHIP_RENEWAL_DAYS = 30
        
        # Fee charged for canceling a class booking within the limit period
        self.LATE_CANCELLATION_FEE = 10.0
        
        # Minimum hours before class start time to cancel without fee
        self.CANCELLATION_HOURS_LIMIT = 24
