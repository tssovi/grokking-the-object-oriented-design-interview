# Parking Lot System - Java Implementation

This directory contains the Java implementation of the Parking Lot design problem, converted from the Python version.

## Structure

- **Enums**: `VehicleType.java`, `ParkingSpotType.java`, `AccountStatus.java`
- **Models**: `Vehicle.java`, `ParkingSpot.java`, `ParkingFloor.java`, `ParkingLot.java`
- **Supporting Classes**: `Address.java`, `Person.java`, `Account.java`, `ParkingTicket.java`

## Design Patterns Used

- **Singleton Pattern**: `ParkingLot` class ensures only one instance exists
- **Factory Pattern**: Vehicle creation
- **Strategy Pattern**: Different parking spot types

## How to Run

```bash
javac *.java
java ParkingLot
```

## Key Features

- Thread-safe singleton implementation
- Support for multiple vehicle types (Car, Truck, Van, Motorbike, Electric)
- Multiple parking spot types (Compact, Large, Handicapped, Motorbike, Electric)
- Real-time parking spot availability tracking
- Entrance and exit panel management
- Parking rate calculation

## Author

Converted from Python to Java for the Grokking OOD Interview repository.
