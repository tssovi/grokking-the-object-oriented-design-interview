"""
trip.py
Contains Trip and TripManager classes.
"""

import itertools
from constants import TripStatus, BASE_FARE, COST_PER_KM


class Trip:
    """Represents a ride from pickup to dropoff."""

    _id_counter = itertools.count(1)

    def __init__(self, rider, pickup, dropoff):
        self.trip_id = next(self._id_counter)
        self.rider = rider
        self.driver = None
        self.pickup = pickup
        self.dropoff = dropoff
        self.status = TripStatus.REQUESTED
        self.fare = self.calculate_fare(distance_km=10)  # demo constant distance

    def assign_driver(self, driver):
        self.driver = driver
        self.status = TripStatus.ONGOING
        print(f"[Trip] Trip {self.trip_id} assigned to driver {driver.name}.")

    def complete(self):
        self.status = TripStatus.COMPLETED
        print(f"[Trip] Trip {self.trip_id} completed. Total fare: ${self.fare:.2f}")

    def calculate_fare(self, distance_km):
        """Compute trip cost with base fare + distance charge."""
        return BASE_FARE + COST_PER_KM * distance_km


class TripManager:
    """Handles trip creation and assignment."""

    def __init__(self):
        self.trips = []
        self.drivers = []

    def register_driver(self, driver):
        """Register a driver to the pool."""
        self.drivers.append(driver)
        print(f"[TripManager] Driver {driver.name} registered.")

    def find_available_driver(self):
        """Pick the first available driver."""
        for driver in self.drivers:
            if driver.is_available:
                return driver
        print("[TripManager] No available drivers.")
        return None

    def create_trip(self, rider, pickup, dropoff):
        """Create a trip and assign a driver."""
        trip = Trip(rider, pickup, dropoff)
        driver = self.find_available_driver()
        if driver:
            driver.accept_trip(trip)
        self.trips.append(trip)
        return trip
