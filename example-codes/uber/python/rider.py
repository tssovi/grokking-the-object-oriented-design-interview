"""
Rider module for Uber ride-sharing system.

This module provides the Rider class representing a customer who requests rides.
"""

from typing import List, TYPE_CHECKING
from user import User
from constants import RideType
from location import Location

# Avoid circular imports
if TYPE_CHECKING:
    from ride import Ride
    from driver import Driver


class Rider(User):
    """
    Represents a rider (customer) in the Uber system.
    
    This class extends the User class and adds rider-specific functionality
    such as requesting rides and maintaining ride history.
    
    Attributes:
        id (str): Unique identifier for the rider (inherited from User)
        name (str): Full name of the rider (inherited from User)
        email (str): Email address (inherited from User)
        phone (str): Phone number (inherited from User)
        rating (float): Average rating (inherited from User)
        total_ratings (int): Total number of ratings (inherited from User)
        ride_history (List[Ride]): List of all rides taken by this rider
        payment_method (Payment): Preferred payment method (not yet implemented)
    """
    
    def __init__(self, user_id: str, name: str, email: str, phone: str):
        """
        Initialize a Rider.
        
        Args:
            user_id (str): Unique identifier for the rider
            name (str): Full name of the rider
            email (str): Email address
            phone (str): Phone number
            
        Example:
            >>> rider = Rider("R123", "Jane Smith", "jane@example.com", "555-5678")
        """
        super().__init__(user_id, name, email, phone)
        self.ride_history: List['Ride'] = []  # Initialize empty ride history
        self.payment_method = None  # Can be set later
    
    def request_ride(self, pickup: Location, dropoff: Location, ride_type: RideType) -> 'Ride':
        """
        Request a new ride.
        
        This method creates a new Ride object with the specified parameters
        and adds it to the rider's ride history.
        
        Args:
            pickup (Location): The pickup location
            dropoff (Location): The destination location
            ride_type (RideType): The type of ride requested (UBER_X, UBER_XL, etc.)
            
        Returns:
            Ride: The newly created ride request
            
        Example:
            >>> pickup = Location(40.7128, -74.0060)
            >>> dropoff = Location(40.7589, -73.9851)
            >>> ride = rider.request_ride(pickup, dropoff, RideType.UBER_X)
        """
        # Import here to avoid circular dependency
        from ride import Ride
        
        # Create new ride request
        ride = Ride(self, pickup, dropoff, ride_type)
        
        # Add to ride history
        self.ride_history.append(ride)
        
        return ride
    
    def rate_driver(self, driver: 'Driver', rating: float) -> None:
        """
        Rate a driver after completing a ride.
        
        Args:
            driver (Driver): The driver to rate
            rating (float): The rating value (typically 1.0 to 5.0)
            
        Example:
            >>> rider.rate_driver(driver, 5.0)
        """
        driver.update_rating(rating)
