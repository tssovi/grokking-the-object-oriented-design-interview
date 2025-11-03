# Uber Ride-Sharing System - Python Implementation

This is a Python implementation of an Uber-like ride-sharing system, translated from Java with comprehensive documentation.

## Overview

This system demonstrates object-oriented design principles for a ride-sharing platform with the following key features:

- **User Management**: Riders and Drivers with rating systems
- **Ride Matching**: Automatic matching of riders with nearest available drivers
- **Fare Calculation**: Dynamic fare calculation based on distance, duration, and ride type
- **Payment Processing**: Payment handling with support for different payment methods
- **Real-time Tracking**: Location-based driver matching using Haversine formula

## Architecture

### Core Components

1. **constants.py** - Enumerations for system states
   - `RideStatus`: States of a ride (REQUESTED, ACCEPTED, STARTED, COMPLETED, CANCELLED)
   - `DriverStatus`: Driver availability (AVAILABLE, BUSY, OFFLINE)
   - `PaymentStatus`: Payment states (PENDING, COMPLETED, FAILED, REFUNDED)
   - `RideType`: Ride types with pricing multipliers (UBER_X, UBER_XL, UBER_BLACK, UBER_POOL)

2. **location.py** - Geographic location handling
   - Stores latitude/longitude coordinates
   - Calculates distances using Haversine formula

3. **user.py** - Base user class
   - Abstract base class for Driver and Rider
   - Manages user information and ratings

4. **vehicle.py** - Vehicle information
   - Stores vehicle specifications
   - Associates vehicles with ride types

5. **payment.py** - Payment processing
   - Handles payment transactions
   - Supports payment processing and refunds

6. **ride.py** - Ride lifecycle management
   - Manages ride from request to completion
   - Tracks locations, times, and fare

7. **rider.py** - Rider (customer) functionality
   - Extends User class
   - Handles ride requests and driver ratings

8. **driver.py** - Driver functionality
   - Extends User class
   - Manages ride acceptance and completion

9. **uber_system.py** - Main system controller
   - Singleton pattern implementation
   - Coordinates all system operations
   - Handles driver matching and fare calculation

## Design Patterns Used

- **Singleton Pattern**: UberSystem ensures only one instance manages the entire system
- **Inheritance**: User class is extended by Driver and Rider
- **Enumeration**: Type-safe constants for statuses and types

## Key Features

### Fare Calculation
```
Fare = (BASE_FARE + distance × PER_KM_RATE + duration × PER_MINUTE_RATE) × ride_type_multiplier
```

- Base fare: $2.00
- Per kilometer: $1.50
- Per minute: $0.30
- Multipliers: UBER_X (1.0x), UBER_XL (1.5x), UBER_BLACK (2.0x), UBER_POOL (0.8x)

### Driver Matching
The system finds the nearest available driver by:
1. Filtering drivers by availability status
2. Matching vehicle type to requested ride type
3. Calculating distances using Haversine formula
4. Selecting the closest driver

### Rating System
Both riders and drivers can rate each other:
- Ratings are averaged over all rides
- New ratings are incorporated using weighted average
- Default rating starts at 5.0

## Usage Example

See `example_usage.py` for a complete demonstration of the system.

## File Structure

```
uber/python/
├── constants.py          # Enumerations and constants
├── location.py          # Location and distance calculations
├── user.py              # Base user class
├── vehicle.py           # Vehicle information
├── payment.py           # Payment processing
├── ride.py              # Ride management
├── rider.py             # Rider functionality
├── driver.py            # Driver functionality
├── uber_system.py       # Main system controller
├── example_usage.py     # Usage demonstration
└── README.md            # This file
```

## Notes

- All classes include comprehensive docstrings following Python conventions
- Type hints are used throughout for better code clarity
- Circular import issues are handled using TYPE_CHECKING
- The system uses Python's built-in `enum.Enum` for type-safe constants
- UUID is used for generating unique IDs
- Datetime is used for timestamp management
