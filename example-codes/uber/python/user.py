"""
user.py
Contains definitions for Account, Rider, and Driver classes.
"""

from constants import AccountStatus


class Account:
    """Generic user account in the Uber system."""

    def __init__(self, name: str, email: str, phone: str):
        self.name = name
        self.email = email
        self.phone = phone
        self.status = AccountStatus.ACTIVE

    def deactivate(self):
        """Suspend the account (e.g. if user violates policy)."""
        self.status = AccountStatus.SUSPENDED
        print(f"[Account] {self.name}'s account suspended.")


class Rider(Account):
    """Represents a passenger who can request rides."""

    def __init__(self, name, email, phone):
        super().__init__(name, email, phone)
        self.active_trip = None

    def request_trip(self, trip_manager, pickup, dropoff):
        """Ask the system to create a trip request."""
        print(f"[Rider] {self.name} requesting a ride from {pickup} to {dropoff}.")
        trip = trip_manager.create_trip(self, pickup, dropoff)
        self.active_trip = trip
        return trip


class Driver(Account):
    """Represents a driver with a vehicle."""

    def __init__(self, name, email, phone, vehicle):
        super().__init__(name, email, phone)
        self.vehicle = vehicle
        self.is_available = True

    def accept_trip(self, trip):
        """Driver accepts the trip if available."""
        if self.is_available:
            self.is_available = False
            trip.assign_driver(self)
            print(f"[Driver] {self.name} accepted trip {trip.trip_id}.")
        else:
            print(f"[Driver] {self.name} is currently unavailable.")

    def complete_trip(self, trip):
        """Mark trip as completed."""
        trip.complete()
        self.is_available = True
        print(f"[Driver] {self.name} completed trip {trip.trip_id}.")
