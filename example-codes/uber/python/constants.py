"""
Constants module for Uber ride-sharing system.

This module defines all the enumerations used throughout the system including
ride statuses, driver statuses, payment statuses, and ride types.
"""

from enum import Enum


class RideStatus(Enum):
    """
    Enumeration representing the various states of a ride.
    
    Attributes:
        REQUESTED: Ride has been requested by a rider
        ACCEPTED: Driver has accepted the ride request
        ARRIVED: Driver has arrived at the pickup location
        STARTED: Ride has started (rider is in the vehicle)
        COMPLETED: Ride has been completed successfully
        CANCELLED: Ride has been cancelled by rider or driver
    """
    REQUESTED = "REQUESTED"
    ACCEPTED = "ACCEPTED"
    ARRIVED = "ARRIVED"
    STARTED = "STARTED"
    COMPLETED = "COMPLETED"
    CANCELLED = "CANCELLED"


class DriverStatus(Enum):
    """
    Enumeration representing the availability status of a driver.
    
    Attributes:
        AVAILABLE: Driver is online and available to accept rides
        BUSY: Driver is currently on a ride
        OFFLINE: Driver is not available for rides
    """
    AVAILABLE = "AVAILABLE"
    BUSY = "BUSY"
    OFFLINE = "OFFLINE"


class PaymentStatus(Enum):
    """
    Enumeration representing the status of a payment transaction.
    
    Attributes:
        PENDING: Payment is awaiting processing
        COMPLETED: Payment has been successfully processed
        FAILED: Payment processing failed
        REFUNDED: Payment has been refunded to the customer
    """
    PENDING = "PENDING"
    COMPLETED = "COMPLETED"
    FAILED = "FAILED"
    REFUNDED = "REFUNDED"


class RideType(Enum):
    """
    Enumeration representing different types of Uber rides with their pricing multipliers.
    
    Each ride type has an associated multiplier that affects the final fare calculation.
    
    Attributes:
        UBER_X: Standard ride (1.0x multiplier)
        UBER_XL: Larger vehicle for more passengers (1.5x multiplier)
        UBER_BLACK: Premium luxury ride (2.0x multiplier)
        UBER_POOL: Shared ride with other passengers (0.8x multiplier)
    """
    UBER_X = 1.0
    UBER_XL = 1.5
    UBER_BLACK = 2.0
    UBER_POOL = 0.8
    
    @property
    def multiplier(self):
        """
        Get the pricing multiplier for this ride type.
        
        Returns:
            float: The multiplier value for fare calculation
        """
        return self.value
