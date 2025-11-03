"""
Driver module for Uber ride-sharing system.

This module provides the Driver class representing a driver who accepts
and completes ride requests.
"""

from typing import List, TYPE_CHECKING
from user import User
from constants import DriverStatus, RideStatus
from vehicle import Vehicle
from location import Location

# Avoid circular imports
if TYPE_CHECKING:
    from ride import Ride
    from rider import Rider


class Driver(User):
    """
    Represents a driver in the Uber system.
    
    This class extends the User class and adds driver-specific functionality
    such as managing vehicle, location, availability status, and ride operations.
    
    Attributes:
        id (str): Unique identifier for the driver (inherited from User)
        name (str): Full name of the driver (inherited from User)
        email (str): Email address (inherited from User)
        phone (str): Phone number (inherited from User)
        rating (float): Average rating (inherited from User)
        total_ratings (int): Total number of ratings (inherited from User)
        vehicle (Vehicle): The vehicle driven by this driver
        current_location (Location): Current geographic location of the driver
        status (DriverStatus): Current availability status
        ride_history (List[Ride]): List of all rides completed by this driver
    """
    
    def __init__(self, user_id: str, name: str, email: str, phone: str, vehicle: Vehicle):
        """
        Initialize a Driver.
        
        Args:
            user_id (str): Unique identifier for the driver
            name (str): Full name of the driver
            email (str): Email address
            phone (str): Phone number
            vehicle (Vehicle): The vehicle this driver uses
            
        Example:
            >>> vehicle = Vehicle("ABC123", "Toyota", "Camry", 2020, RideType.UBER_X, 4)
            >>> driver = Driver("D456", "Bob Johnson", "bob@example.com", "555-9999", vehicle)
        """
        super().__init__(user_id, name, email, phone)
        self.vehicle = vehicle
        self.current_location: Location = None  # Set when driver goes online
        self.status = DriverStatus.OFFLINE  # Driver starts offline
        self.ride_history: List['Ride'] = []  # Initialize empty ride history
    
    def update_location(self, location: Location) -> None:
        """
        Update the driver's current location.
        
        This method should be called periodically to keep the driver's
        location up-to-date for matching with nearby ride requests.
        
        Args:
            location (Location): The new current location
            
        Example:
            >>> new_location = Location(40.7128, -74.0060)
            >>> driver.update_location(new_location)
        """
        self.current_location = location
    
    def set_status(self, status: DriverStatus) -> None:
        """
        Set the driver's availability status.
        
        Args:
            status (DriverStatus): The new status (AVAILABLE, BUSY, or OFFLINE)
            
        Example:
            >>> driver.set_status(DriverStatus.AVAILABLE)
        """
        self.status = status
    
    def accept_ride(self, ride: 'Ride') -> bool:
        """
        Accept a ride request.
        
        The driver can only accept a ride if they are currently available.
        When a ride is accepted, the driver's status changes to BUSY and
        the ride is added to their history.
        
        Args:
            ride (Ride): The ride request to accept
            
        Returns:
            bool: True if the ride was accepted, False if driver is not available
            
        Example:
            >>> if driver.accept_ride(ride):
            ...     print("Ride accepted!")
            ... else:
            ...     print("Driver is not available")
        """
        # Only accept rides if driver is available
        if self.status == DriverStatus.AVAILABLE:
            # Assign this driver to the ride
            ride.set_driver(self)
            
            # Update ride status to accepted
            ride.set_status(RideStatus.ACCEPTED)
            
            # Driver is now busy
            self.status = DriverStatus.BUSY
            
            # Add to ride history
            self.ride_history.append(ride)
            
            return True
        
        return False
    
    def start_ride(self, ride: 'Ride') -> None:
        """
        Start a ride (when the rider gets in the vehicle).
        
        Args:
            ride (Ride): The ride to start
            
        Example:
            >>> driver.start_ride(ride)
        """
        ride.set_status(RideStatus.STARTED)
    
    def complete_ride(self, ride: 'Ride') -> None:
        """
        Complete a ride (when the rider reaches their destination).
        
        This method updates the ride status to COMPLETED and sets the
        driver's status back to AVAILABLE so they can accept new rides.
        
        Args:
            ride (Ride): The ride to complete
            
        Example:
            >>> driver.complete_ride(ride)
        """
        # Mark ride as completed
        ride.set_status(RideStatus.COMPLETED)
        
        # Driver is now available for new rides
        self.status = DriverStatus.AVAILABLE
    
    def rate_rider(self, rider: 'Rider', rating: float) -> None:
        """
        Rate a rider after completing a ride.
        
        Args:
            rider (Rider): The rider to rate
            rating (float): The rating value (typically 1.0 to 5.0)
            
        Example:
            >>> driver.rate_rider(rider, 4.5)
        """
        rider.update_rating(rating)
