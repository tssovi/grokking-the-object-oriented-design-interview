"""
main.py
Demo runner for the Uber-like Ride-Sharing System.
"""

from constants import VehicleType
from vehicle import Vehicle
from user import Rider, Driver
from trip import TripManager


def main():
    trip_manager = TripManager()

    # Setup drivers
    vehicle1 = Vehicle("ABC123", VehicleType.SEDAN)
    driver1 = Driver("Alice", "alice@uber.com", "555-1000", vehicle1)
    trip_manager.register_driver(driver1)

    # Setup rider
    rider = Rider("Bob", "bob@mail.com", "555-2000")

    # Rider requests a trip
    trip = rider.request_trip(trip_manager, "Downtown", "Airport")

    # Driver completes the trip
    if trip.driver:
        trip.driver.complete_trip(trip)


if __name__ == "__main__":
    main()
