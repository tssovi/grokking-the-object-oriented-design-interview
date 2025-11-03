"""
Ride module for Uber ride-sharing system.

This module provides the Ride class representing a ride request and its lifecycle
from request to completion.
"""

import uuid
from datetime import datetime
from typing import Optional, TYPE_CHECKING
from constants import RideStatus, RideType
from location import Location

# Avoid circular imports
if TYPE_CHECKING:
    from rider import Rider
    from driver import Driver
    from payment import Payment


class Ride:
    """
    Represents a ride in the Uber system.
    
    This class manages the complete lifecycle of a ride from the initial request
    through completion, including tracking locations, times, fare, and payment.
    
    Attributes:
        id (str): Unique identifier for the ride
        rider (Rider): The rider who requested this ride
        driver (Driver): The driver assigned to this ride (None until accepted)
        pickup (Location): The pickup location
        dropoff (Location): The destination location
        type (RideType): The type of ride (UBER_X, UBER_XL, etc.)
        status (RideStatus): Current status of the ride
        fare (float): The calculated fare for the ride
        distance (float): Distance in kilometers between pickup and dropoff
        request_time (datetime): When the ride was requested
        start_time (datetime): When the ride started (None until started)
        end_time (datetime): When the ride completed (None until completed)
        payment (Payment): The payment transaction for this ride
    """
    
    def __init__(self, rider: 'Rider', pickup: Location, dropoff: Location, ride_type: RideType):
        """
        Initialize a new Ride request.
        
        Args:
            rider (Rider): The rider requesting the ride
            pickup (Location): The pickup location
            dropoff (Location): The destination location
            ride_type (RideType): The type of ride requested
            
        Example:
            >>> from rider import Rider
            >>> rider = Rider("R123", "John Doe", "john@example.com", "555-1234")
            >>> pickup = Location(40.7128, -74.0060)
            >>> dropoff = Location(40.7589, -73.9851)
            >>> ride = Ride(rider, pickup, dropoff, RideType.UBER_X)
        """
        self.id = str(uuid.uuid4())  # Generate unique ride ID
        self.rider = rider
        self.driver: Optional['Driver'] = None  # No driver assigned yet
        self.pickup = pickup
        self.dropoff = dropoff
        self.type = ride_type
        self.status = RideStatus.REQUESTED  # Initial status
        self.fare = 0.0  # Fare calculated later
        self.distance = pickup.distance_to(dropoff)  # Calculate distance
        self.request_time = datetime.now()  # Record request time
        self.start_time: Optional[datetime] = None  # Set when ride starts
        self.end_time: Optional[datetime] = None  # Set when ride completes
        self.payment: Optional['Payment'] = None  # Payment processed later
    
    def set_driver(self, driver: 'Driver') -> None:
        """
        Assign a driver to this ride.
        
        Args:
            driver (Driver): The driver accepting this ride
        """
        self.driver = driver
    
    def set_status(self, status: RideStatus) -> None:
        """
        Update the status of the ride.
        
        This method also automatically sets the start_time when the ride starts
        and the end_time when the ride completes.
        
        Args:
            status (RideStatus): The new status for the ride
            
        Example:
            >>> ride.set_status(RideStatus.STARTED)
            >>> # ride.start_time is now set to current time
        """
        self.status = status
        
        # Automatically set timestamps based on status changes
        if status == RideStatus.STARTED:
            self.start_time = datetime.now()
        elif status == RideStatus.COMPLETED:
            self.end_time = datetime.now()
    
    def set_fare(self, fare: float) -> None:
        """
        Set the fare amount for this ride.
        
        Args:
            fare (float): The calculated fare amount
        """
        self.fare = fare
    
    def set_payment(self, payment: 'Payment') -> None:
        """
        Associate a payment transaction with this ride.
        
        Args:
            payment (Payment): The payment transaction for this ride
        """
        self.payment = payment
