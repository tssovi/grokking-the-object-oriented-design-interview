"""
Location module for Uber ride-sharing system.

This module provides the Location class for representing geographic coordinates
and calculating distances between locations.
"""

import math


class Location:
    """
    Represents a geographic location with latitude and longitude coordinates.
    
    This class provides functionality to store location data and calculate
    distances between two locations using the Haversine formula.
    
    Attributes:
        latitude (float): The latitude coordinate in degrees
        longitude (float): The longitude coordinate in degrees
    """
    
    def __init__(self, latitude: float, longitude: float):
        """
        Initialize a Location with latitude and longitude.
        
        Args:
            latitude (float): The latitude coordinate in degrees (-90 to 90)
            longitude (float): The longitude coordinate in degrees (-180 to 180)
        """
        self.latitude = latitude
        self.longitude = longitude
    
    def distance_to(self, other: 'Location') -> float:
        """
        Calculate the distance to another location using the Haversine formula.
        
        The Haversine formula determines the great-circle distance between two points
        on a sphere given their longitudes and latitudes. This is useful for calculating
        distances between geographic coordinates.
        
        Args:
            other (Location): The destination location
            
        Returns:
            float: The distance in kilometers between this location and the other location
            
        Example:
            >>> loc1 = Location(40.7128, -74.0060)  # New York
            >>> loc2 = Location(34.0522, -118.2437)  # Los Angeles
            >>> distance = loc1.distance_to(loc2)
        """
        # Radius of the Earth in kilometers
        R = 6371
        
        # Convert latitude and longitude differences to radians
        lat_distance = math.radians(other.latitude - self.latitude)
        lon_distance = math.radians(other.longitude - self.longitude)
        
        # Apply Haversine formula
        a = (math.sin(lat_distance / 2) * math.sin(lat_distance / 2) +
             math.cos(math.radians(self.latitude)) * math.cos(math.radians(other.latitude)) *
             math.sin(lon_distance / 2) * math.sin(lon_distance / 2))
        
        c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))
        
        # Calculate and return distance in kilometers
        return R * c
