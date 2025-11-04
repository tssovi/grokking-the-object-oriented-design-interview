# Hotel Management System - Java Implementation

## Overview

This is a comprehensive Java implementation of a Hotel Management System designed using Object-Oriented Programming (OOP) principles. The system manages hotel operations including room bookings, guest management, staff operations, and billing.

## System Architecture

The system is organized into several key components:

### 1. **Constants and Enumerations** (`Constants.java`)
Defines all the enums and constant classes used throughout the system:
- **RoomStyle**: Types of rooms (STANDARD, DELUXE, FAMILY_SUITE, BUSINESS_SUITE)
- **RoomStatus**: Current state of rooms (AVAILABLE, RESERVED, OCCUPIED, etc.)
- **BookingStatus**: Status of bookings (REQUESTED, CONFIRMED, CHECKED_IN, etc.)
- **AccountStatus**: Status of user accounts (ACTIVE, CLOSED, BLACKLISTED, etc.)
- **AccountType**: Types of accounts (MEMBER, GUEST, MANAGER, RECEPTIONIST)
- **PaymentStatus**: Payment transaction states (UNPAID, COMPLETED, REFUNDED, etc.)
- **Address**: Class for storing physical addresses

### 2. **Account Management** (`Account.java`)
Manages user accounts and person-related entities:
- **Account**: User account with credentials and status
- **Person**: Abstract base class for all person types
- **Guest**: Hotel guests who can make bookings
- **Receptionist**: Staff who manage bookings and check-ins
- **Server**: Staff who handle room services and charges

### 3. **Room Management** (`Room.java`)
Handles room-related operations:
- **Search**: Interface for searching available rooms
- **Room**: Represents a hotel room with all its properties
- **RoomKey**: Manages room access keys/cards
- **RoomHouseKeeping**: Tracks housekeeping activities and logs

### 4. **Booking Management** (`RoomBooking.java`)
Manages reservations and charges:
- **RoomBooking**: Represents a room reservation
- **RoomCharge**: Abstract base class for all types of charges
- **Amenity**: Charges for hotel amenities (gym, spa, etc.)
- **RoomService**: Charges for room services
- **KitchenService**: Charges for food and beverage services
- **Invoice**: Billing and payment tracking
- **Notification**: Guest notifications and alerts

### 5. **Hotel Structure** (`Hotel.java`)
Top-level hotel management:
- **HotelLocation**: Represents a physical hotel location
- **Hotel**: Main hotel entity managing multiple locations

## Class Relationships

```
Hotel
  └── HotelLocation (multiple)
        └── Room (multiple)
              ├── RoomKey (multiple)
              └── RoomHouseKeeping (multiple)

Person (abstract)
  ├── Guest
  ├── Receptionist
  └── Server

RoomBooking
  ├── Room (reference)
  ├── Invoice
  │     └── RoomCharge (multiple)
  │           ├── Amenity
  │           ├── RoomService
  │           └── KitchenService
  └── Notification (multiple)

Account
  └── Person (associated)
```

## Key Features

### Room Management
- Search for available rooms by style, date, and duration
- Check-in and check-out operations
- Room status tracking
- Housekeeping log management
- Room key/access card management

### Booking System
- Create and manage reservations
- Track booking status through the entire lifecycle
- Support for multiple booking statuses
- Notification system for guests

### Billing and Charges
- Comprehensive invoice system
- Multiple charge types (amenities, room service, kitchen service)
- Payment status tracking
- Automatic charge calculation

### User Management
- Multiple account types with different privileges
- Guest tracking and history
- Staff operations (receptionist, server)
- Account status management

## Usage Examples

### Creating a Hotel with Locations

```java
// Create a hotel
Hotel hotel = new Hotel("Grand Hotel Chain");

// Create a location
Address address = new Address("123 Main St", "New York", "NY", "10001", "USA");
HotelLocation location = new HotelLocation("Downtown Branch", address);

// Add rooms to the location
Room room101 = new Room("101", RoomStyle.STANDARD, RoomStatus.AVAILABLE, 150.0, false);
Room room102 = new Room("102", RoomStyle.DELUXE, RoomStatus.AVAILABLE, 250.0, false);
location.addRoom(room101);
location.addRoom(room102);

// Add location to hotel
hotel.addLocation(location);
```

### Creating a Guest and Booking

```java
// Create a guest account
Account guestAccount = new Account("guest001", "password123", AccountStatus.ACTIVE);
Address guestAddress = new Address("456 Oak Ave", "Boston", "MA", "02101", "USA");
Guest guest = new Guest("John Doe", guestAddress, "john@example.com", "555-1234", guestAccount);

// Create a booking
Date startDate = new Date(); // Today
RoomBooking booking = new RoomBooking("RES001", startDate, 3, BookingStatus.CONFIRMED);
booking.setGuestId(1);
booking.setRoom(room101);

// Create an invoice
Invoice invoice = new Invoice("INV001");
booking.setInvoice(invoice);
```

### Adding Charges to a Room

```java
// Add amenity charge
Amenity gymAccess = new Amenity("Gym Access", "24-hour fitness center access");
invoice.addCharge(gymAccess, 25.0);

// Add room service charge
RoomService towelService = new RoomService(false, new Date());
invoice.addCharge(towelService, 0.0); // Free service

// Add kitchen service charge
KitchenService breakfast = new KitchenService("Continental breakfast in room");
invoice.addCharge(breakfast, 35.0);
```

### Room Check-in and Check-out

```java
// Check-in
if (room101.checkIn()) {
    booking.setCheckInTime(new Date());
    booking.setStatus(BookingStatus.CHECKED_IN);
    guest.incrementCheckInCount();
}

// Check-out
if (room101.checkOut()) {
    booking.setCheckOutTime(new Date());
    booking.setStatus(BookingStatus.CHECKED_OUT);
}
```

### Searching for Available Rooms

```java
// Search for available deluxe rooms
Date checkInDate = new Date();
int duration = 5; // 5 nights
List<Room> availableRooms = hotel.searchRoomsAcrossLocations(
    RoomStyle.DELUXE, 
    checkInDate, 
    duration
);

for (Room room : availableRooms) {
    System.out.println("Room " + room.getRoomNumber() + 
                      " - $" + room.getBookingPrice() + " per night");
}
```

## Design Patterns Used

1. **Abstract Factory Pattern**: Used in the Person hierarchy (Guest, Receptionist, Server)
2. **Strategy Pattern**: Search interface allows different search implementations
3. **Observer Pattern**: Notification system for booking updates
4. **Composite Pattern**: Hotel contains multiple HotelLocations, each containing multiple Rooms

## Object-Oriented Principles

### Encapsulation
- All class fields are private with public getters/setters
- Internal implementation details are hidden from clients

### Inheritance
- Person is the base class for Guest, Receptionist, and Server
- RoomCharge is the base class for Amenity, RoomService, and KitchenService

### Polymorphism
- Different person types can be treated uniformly through the Person interface
- Different charge types can be processed uniformly through RoomCharge

### Abstraction
- Abstract classes (Person, RoomCharge) define common behavior
- Interfaces (Search) define contracts for implementations

## Future Enhancements

1. **Database Integration**: Connect to a database for persistent storage
2. **Payment Processing**: Integrate with payment gateways
3. **Reporting System**: Generate reports for occupancy, revenue, etc.
4. **Loyalty Program**: Implement a points-based rewards system
5. **Multi-language Support**: Add internationalization
6. **Mobile App Integration**: REST API for mobile applications
7. **Advanced Search**: Filter by amenities, price range, floor level, etc.
8. **Maintenance Scheduling**: Automated room maintenance planning
9. **Staff Scheduling**: Manage housekeeping and service staff schedules
10. **Analytics Dashboard**: Real-time metrics and business intelligence

## Compilation and Execution

To compile all Java files:

```bash
javac *.java
```

Note: This implementation provides the class structure and interfaces. To create a working application, you would need to:
1. Implement the TODO methods with actual business logic
2. Add database connectivity
3. Create a user interface (CLI, GUI, or Web)
4. Add error handling and validation
5. Implement security measures (password hashing, authentication, etc.)

## Testing Recommendations

1. **Unit Tests**: Test individual classes and methods
2. **Integration Tests**: Test interactions between classes
3. **Booking Flow Tests**: Test complete booking lifecycle
4. **Concurrency Tests**: Test simultaneous bookings for the same room
5. **Payment Tests**: Test various payment scenarios
6. **Edge Cases**: Test boundary conditions and error scenarios

## License

This code is provided as an educational example for learning object-oriented design principles.

## Contributing

This is an educational project. Feel free to extend and modify the code for learning purposes.

## Contact

For questions or suggestions about this implementation, please refer to the main project documentation.
