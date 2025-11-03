"""
User module for Uber ride-sharing system.

This module provides the base User class that serves as the parent class
for both Driver and Rider classes.
"""

from abc import ABC


class User(ABC):
    """
    Abstract base class representing a user in the Uber system.
    
    This class contains common attributes and methods shared by both
    drivers and riders, such as personal information and rating system.
    
    Attributes:
        id (str): Unique identifier for the user
        name (str): Full name of the user
        email (str): Email address of the user
        phone (str): Phone number of the user
        rating (float): Current average rating of the user (0.0 to 5.0)
        total_ratings (int): Total number of ratings received
    """
    
    def __init__(self, user_id: str, name: str, email: str, phone: str):
        """
        Initialize a User with basic information.
        
        Args:
            user_id (str): Unique identifier for the user
            name (str): Full name of the user
            email (str): Email address of the user
            phone (str): Phone number of the user
        """
        self.id = user_id
        self.name = name
        self.email = email
        self.phone = phone
        self.rating = 5.0  # Default rating starts at perfect 5.0
        self.total_ratings = 0  # No ratings initially
    
    def update_rating(self, new_rating: float) -> None:
        """
        Update the user's rating with a new rating value.
        
        This method calculates the new average rating by incorporating
        the new rating into the existing average using a weighted average formula.
        
        Args:
            new_rating (float): The new rating to add (typically 1.0 to 5.0)
            
        Example:
            >>> user = User("123", "John Doe", "john@example.com", "555-1234")
            >>> user.update_rating(4.5)
            >>> print(user.rating)  # Will be between 4.5 and 5.0
        """
        # Calculate weighted average: (old_avg * old_count + new_rating) / (old_count + 1)
        self.rating = ((self.rating * self.total_ratings) + new_rating) / (self.total_ratings + 1)
        self.total_ratings += 1
