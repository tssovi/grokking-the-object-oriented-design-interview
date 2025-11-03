"""
vehicle.py
Defines the Vehicle class.
"""

from constants import VehicleType


class Vehicle:
    """Represents a driver's vehicle."""

    def __init__(self, license_plate: str, vehicle_type: VehicleType):
        self.license_plate = license_plate
        self.vehicle_type = vehicle_type
        self.is_active = True

    def deactivate(self):
        """Deactivate a vehicle from the system."""
        self.is_active = False
        print(f"[Vehicle] {self.license_plate} is no longer active.")
