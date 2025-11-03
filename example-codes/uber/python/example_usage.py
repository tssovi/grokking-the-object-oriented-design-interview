"""
Example usage of the Uber ride-sharing system.

This script demonstrates how to use the various components of the system
to simulate a complete ride lifecycle.
"""

from uber_system import UberSystem
from driver import Driver
from rider import Rider
from vehicle import Vehicle
from location import Location
from constants import RideType, DriverStatus


def main():
    """
    Demonstrate the Uber system with a complete ride scenario.
    """
    print("=" * 60)
    print("Uber Ride-Sharing System - Example Usage")
    print("=" * 60)
    
    # Get the singleton instance of UberSystem
    uber_system = UberSystem.get_instance()
    
    # Create vehicles
    print("\n1. Creating vehicles...")
    vehicle1 = Vehicle("ABC123", "Toyota", "Camry", 2020, RideType.UBER_X, 4)
    vehicle2 = Vehicle("XYZ789", "Honda", "Pilot", 2021, RideType.UBER_XL, 6)
    print(f"   - Created {vehicle1.make} {vehicle1.model} ({vehicle1.license_plate})")
    print(f"   - Created {vehicle2.make} {vehicle2.model} ({vehicle2.license_plate})")
    
    # Create drivers
    print("\n2. Creating and registering drivers...")
    driver1 = Driver("D001", "Bob Johnson", "bob@uber.com", "555-1111", vehicle1)
    driver2 = Driver("D002", "Alice Smith", "alice@uber.com", "555-2222", vehicle2)
    
    # Register drivers in the system
    uber_system.add_driver(driver1)
    uber_system.add_driver(driver2)
    print(f"   - Registered driver: {driver1.name}")
    print(f"   - Registered driver: {driver2.name}")
    
    # Set driver locations and make them available
    print("\n3. Setting driver locations and status...")
    driver1.update_location(Location(40.7128, -74.0060))  # New York
    driver1.set_status(DriverStatus.AVAILABLE)
    print(f"   - {driver1.name} is now AVAILABLE at (40.7128, -74.0060)")
    
    driver2.update_location(Location(40.7589, -73.9851))  # Times Square
    driver2.set_status(DriverStatus.AVAILABLE)
    print(f"   - {driver2.name} is now AVAILABLE at (40.7589, -73.9851)")
    
    # Create a rider
    print("\n4. Creating and registering rider...")
    rider = Rider("R001", "John Doe", "john@example.com", "555-3333")
    uber_system.add_rider(rider)
    print(f"   - Registered rider: {rider.name}")
    
    # Request a ride
    print("\n5. Requesting a ride...")
    pickup = Location(40.7128, -74.0060)  # New York
    dropoff = Location(40.7589, -73.9851)  # Times Square
    print(f"   - Pickup: (40.7128, -74.0060)")
    print(f"   - Dropoff: (40.7589, -73.9851)")
    print(f"   - Ride type: UBER_X")
    
    ride = uber_system.request_ride(rider, pickup, dropoff, RideType.UBER_X)
    print(f"   - Ride ID: {ride.id}")
    print(f"   - Distance: {ride.distance:.2f} km")
    print(f"   - Status: {ride.status.value}")
    
    # Find and assign nearest driver
    print("\n6. Finding nearest driver...")
    nearest_driver = uber_system.find_nearest_driver(pickup, RideType.UBER_X)
    if nearest_driver:
        print(f"   - Found: {nearest_driver.name}")
        
        # Driver accepts the ride
        print("\n7. Driver accepting ride...")
        if nearest_driver.accept_ride(ride):
            print(f"   - {nearest_driver.name} accepted the ride")
            print(f"   - Driver status: {nearest_driver.status.value}")
            print(f"   - Ride status: {ride.status.value}")
        
        # Simulate ride progression
        print("\n8. Starting the ride...")
        nearest_driver.start_ride(ride)
        print(f"   - Ride status: {ride.status.value}")
        print(f"   - Start time: {ride.start_time}")
        
        # Complete the ride
        print("\n9. Completing the ride...")
        uber_system.complete_ride(ride)
        print(f"   - Ride status: {ride.status.value}")
        print(f"   - End time: {ride.end_time}")
        print(f"   - Fare: ${ride.fare:.2f}")
        print(f"   - Payment status: {ride.payment.status.value}")
        print(f"   - Driver status: {nearest_driver.status.value}")
        
        # Exchange ratings
        print("\n10. Exchanging ratings...")
        rider.rate_driver(nearest_driver, 5.0)
        nearest_driver.rate_rider(rider, 4.5)
        print(f"   - Rider rated driver: 5.0 stars")
        print(f"   - Driver rated rider: 4.5 stars")
        print(f"   - Driver's new rating: {nearest_driver.rating:.2f}")
        print(f"   - Rider's new rating: {rider.rating:.2f}")
    
    # Display ride history
    print("\n11. Ride history...")
    print(f"   - {rider.name} has {len(rider.ride_history)} ride(s)")
    print(f"   - {nearest_driver.name} has {len(nearest_driver.ride_history)} ride(s)")
    
    print("\n" + "=" * 60)
    print("Example completed successfully!")
    print("=" * 60)


if __name__ == "__main__":
    main()
