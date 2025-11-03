"""
Uber Ride-Sharing System Package

A complete implementation of an Uber-like ride-sharing system demonstrating
object-oriented design principles.

Main Components:
    - UberSystem: Main system controller (Singleton)
    - Driver: Driver class with ride management
    - Rider: Rider class with ride requests
    - Ride: Ride lifecycle management
    - Vehicle: Vehicle information
    - Location: Geographic location handling
    - Payment: Payment processing

Usage:
    from uber_system import UberSystem
    from driver import Driver
    from rider import Rider
    from location import Location
    from constants import RideType
    
    # Get system instance
    uber = UberSystem.get_instance()
    
    # Create and register users
    rider = Rider("R001", "John Doe", "john@example.com", "555-1234")
    uber.add_rider(rider)
    
    # Request a ride
    pickup = Location(40.7128, -74.0060)
    dropoff = Location(40.7589, -73.9851)
    ride = uber.request_ride(rider, pickup, dropoff, RideType.UBER_X)
"""

__version__ = "1.0.0"
__author__ = "Uber System Design Team"

# Import main classes for easier access
from .uber_system import UberSystem
from .driver import Driver
from .rider import Rider
from .ride import Ride
from .vehicle import Vehicle
from .location import Location
from .payment import Payment
from .user import User
from .constants import RideStatus, DriverStatus, PaymentStatus, RideType

__all__ = [
    'UberSystem',
    'Driver',
    'Rider',
    'Ride',
    'Vehicle',
    'Location',
    'Payment',
    'User',
    'RideStatus',
    'DriverStatus',
    'PaymentStatus',
    'RideType',
]
