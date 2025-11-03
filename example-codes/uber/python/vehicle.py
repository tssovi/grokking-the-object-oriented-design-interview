"""
Vehicle module for Uber ride-sharing system.

This module provides the Vehicle class representing a driver's vehicle
with its specifications and type.
"""

from constants import RideType


class Vehicle:
    """
    Represents a vehicle used by a driver in the Uber system.
    
    This class stores all relevant information about a vehicle including
    its identification, specifications, and the type of rides it can provide.
    
    Attributes:
        license_plate (str): The vehicle's license plate number
        make (str): The manufacturer of the vehicle (e.g., "Toyota", "Honda")
        model (str): The model name of the vehicle (e.g., "Camry", "Accord")
        year (int): The manufacturing year of the vehicle
        type (RideType): The type of Uber service this vehicle provides
        capacity (int): Maximum number of passengers the vehicle can carry
    """
    
    def __init__(self, license_plate: str, make: str, model: str, 
                 year: int, ride_type: RideType, capacity: int):
        """
        Initialize a Vehicle with its specifications.
        
        Args:
            license_plate (str): The vehicle's license plate number
            make (str): The manufacturer of the vehicle
            model (str): The model name of the vehicle
            year (int): The manufacturing year of the vehicle
            ride_type (RideType): The type of Uber service (UBER_X, UBER_XL, etc.)
            capacity (int): Maximum number of passengers
            
        Example:
            >>> vehicle = Vehicle("ABC123", "Toyota", "Camry", 2020, RideType.UBER_X, 4)
        """
        self.license_plate = license_plate
        self.make = make
        self.model = model
        self.year = year
        self.type = ride_type
        self.capacity = capacity
