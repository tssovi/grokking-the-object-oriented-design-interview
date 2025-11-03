"""
Uber System module for Uber ride-sharing system.

This module provides the main UberSystem class that manages the entire
ride-sharing platform including drivers, riders, and ride matching.
"""

from typing import Dict, Optional
from driver import Driver
from rider import Rider
from ride import Ride
from location import Location
from constants import DriverStatus, RideStatus, RideType
from payment import Payment


class UberSystem:
    """
    Singleton class managing the entire Uber ride-sharing system.
    
    This class implements the Singleton pattern to ensure only one instance
    of the system exists. It manages all drivers, riders, and rides, and
    provides core functionality like driver matching and fare calculation.
    
    Attributes:
        _instance (UberSystem): The singleton instance (class variable)
        drivers (Dict[str, Driver]): Dictionary of all registered drivers
        riders (Dict[str, Rider]): Dictionary of all registered riders
        rides (Dict[str, Ride]): Dictionary of all rides in the system
        BASE_FARE (float): Base fare charged for every ride
        PER_KM_RATE (float): Rate charged per kilometer
        PER_MINUTE_RATE (float): Rate charged per minute
    """
    
    # Class variable to hold the singleton instance
    _instance: Optional['UberSystem'] = None
    
    # Pricing constants
    BASE_FARE = 2.0  # Base fare in currency units
    PER_KM_RATE = 1.5  # Rate per kilometer
    PER_MINUTE_RATE = 0.3  # Rate per minute
    
    def __new__(cls):
        """
        Create or return the singleton instance.
        
        This method ensures only one instance of UberSystem exists.
        
        Returns:
            UberSystem: The singleton instance
        """
        if cls._instance is None:
            cls._instance = super(UberSystem, cls).__new__(cls)
            cls._instance._initialized = False
        return cls._instance
    
    def __init__(self):
        """
        Initialize the UberSystem (only runs once due to singleton pattern).
        """
        # Only initialize once
        if self._initialized:
            return
            
        self.drivers: Dict[str, Driver] = {}  # Driver ID -> Driver object
        self.riders: Dict[str, Rider] = {}  # Rider ID -> Rider object
        self.rides: Dict[str, Ride] = {}  # Ride ID -> Ride object
        self._initialized = True
    
    @classmethod
    def get_instance(cls) -> 'UberSystem':
        """
        Get the singleton instance of UberSystem.
        
        Returns:
            UberSystem: The singleton instance
            
        Example:
            >>> uber_system = UberSystem.get_instance()
        """
        if cls._instance is None:
            cls._instance = cls()
        return cls._instance
    
    def add_driver(self, driver: Driver) -> None:
        """
        Register a new driver in the system.
        
        Args:
            driver (Driver): The driver to register
            
        Example:
            >>> uber_system.add_driver(driver)
        """
        self.drivers[driver.id] = driver
    
    def add_rider(self, rider: Rider) -> None:
        """
        Register a new rider in the system.
        
        Args:
            rider (Rider): The rider to register
            
        Example:
            >>> uber_system.add_rider(rider)
        """
        self.riders[rider.id] = rider
    
    def find_nearest_driver(self, pickup: Location, ride_type: RideType) -> Optional[Driver]:
        """
        Find the nearest available driver for a given pickup location and ride type.
        
        This method searches through all available drivers who have a vehicle
        matching the requested ride type and finds the one closest to the
        pickup location.
        
        Args:
            pickup (Location): The pickup location
            ride_type (RideType): The type of ride requested
            
        Returns:
            Optional[Driver]: The nearest available driver, or None if no driver is available
            
        Example:
            >>> pickup = Location(40.7128, -74.0060)
            >>> driver = uber_system.find_nearest_driver(pickup, RideType.UBER_X)
        """
        nearest_driver = None
        min_distance = float('inf')  # Start with infinity
        
        # Search through all drivers
        for driver in self.drivers.values():
            # Check if driver is available and has the right vehicle type
            if (driver.status == DriverStatus.AVAILABLE and 
                driver.vehicle.type == ride_type):
                
                # Calculate distance from driver to pickup location
                distance = driver.current_location.distance_to(pickup)
                
                # Update if this driver is closer
                if distance < min_distance:
                    min_distance = distance
                    nearest_driver = driver
        
        return nearest_driver
    
    def calculate_fare(self, ride: Ride) -> float:
        """
        Calculate the fare for a ride based on distance, duration, and ride type.
        
        The fare is calculated using the formula:
        fare = (BASE_FARE + distance * PER_KM_RATE + duration * PER_MINUTE_RATE) * ride_type_multiplier
        
        Args:
            ride (Ride): The ride to calculate fare for
            
        Returns:
            float: The calculated fare rounded to 2 decimal places
            
        Example:
            >>> fare = uber_system.calculate_fare(ride)
            >>> print(f"Fare: ${fare:.2f}")
        """
        distance = ride.distance
        duration = 0
        
        # Calculate actual duration if ride has started and ended
        if ride.start_time and ride.end_time:
            # Duration in minutes
            duration = (ride.end_time - ride.start_time).total_seconds() / 60
        else:
            # Estimate duration based on distance (assuming 30 km/h average speed)
            duration = (distance / 30) * 60
        
        # Calculate base fare
        fare = self.BASE_FARE + (distance * self.PER_KM_RATE) + (duration * self.PER_MINUTE_RATE)
        
        # Apply ride type multiplier
        fare *= ride.type.multiplier
        
        # Round to 2 decimal places
        return round(fare, 2)
    
    def request_ride(self, rider: Rider, pickup: Location, 
                    dropoff: Location, ride_type: RideType) -> Ride:
        """
        Process a ride request from a rider.
        
        This method creates a new ride, adds it to the system, and attempts
        to find and notify the nearest available driver.
        
        Args:
            rider (Rider): The rider requesting the ride
            pickup (Location): The pickup location
            dropoff (Location): The destination location
            ride_type (RideType): The type of ride requested
            
        Returns:
            Ride: The created ride request
            
        Example:
            >>> pickup = Location(40.7128, -74.0060)
            >>> dropoff = Location(40.7589, -73.9851)
            >>> ride = uber_system.request_ride(rider, pickup, dropoff, RideType.UBER_X)
        """
        # Create the ride through the rider
        ride = rider.request_ride(pickup, dropoff, ride_type)
        
        # Add ride to system
        self.rides[ride.id] = ride
        
        # Find nearest available driver
        nearest_driver = self.find_nearest_driver(pickup, ride_type)
        
        if nearest_driver:
            # In a real system, this would send a notification to the driver
            print(f"Ride request sent to driver: {nearest_driver.name}")
        else:
            print("No available drivers found for this ride type")
        
        return ride
    
    def complete_ride(self, ride: Ride) -> None:
        """
        Complete a ride by calculating fare, processing payment, and updating status.
        
        This method handles all the steps needed to finalize a ride:
        1. Calculate the fare
        2. Create and process payment
        3. Update ride status through the driver
        
        Args:
            ride (Ride): The ride to complete
            
        Example:
            >>> uber_system.complete_ride(ride)
        """
        # Calculate fare for the ride
        fare = self.calculate_fare(ride)
        ride.set_fare(fare)
        
        # Create and process payment
        payment = Payment(fare, "Credit Card")
        payment.process_payment()
        ride.set_payment(payment)
        
        # Complete the ride through the driver
        ride.driver.complete_ride(ride)
    
    def cancel_ride(self, ride: Ride) -> None:
        """
        Cancel a ride and free up the driver if one was assigned.
        
        Args:
            ride (Ride): The ride to cancel
            
        Example:
            >>> uber_system.cancel_ride(ride)
        """
        # Update ride status to cancelled
        ride.set_status(RideStatus.CANCELLED)
        
        # If a driver was assigned, make them available again
        if ride.driver:
            ride.driver.set_status(DriverStatus.AVAILABLE)
