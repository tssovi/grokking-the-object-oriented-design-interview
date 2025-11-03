# Airline Management System - Java Implementation

A comprehensive object-oriented design implementation of an Airline Management System in Java.

## Overview

This system manages all operations of an airline including flight scheduling, ticket reservations, flight cancellations, customer support, and staff management.

## Features

- **Flight Search**: Search for flights by source/destination airports and date
- **Reservations**: Create, modify, and cancel flight reservations
- **Multi-passenger Support**: Book tickets for multiple passengers under one itinerary
- **Seat Assignment**: Assign seats to passengers with different seat classes
- **Payment Processing**: Support for multiple payment methods (Credit Card, Cash)
- **Staff Management**: Assign pilots and crew members to flights
- **Notifications**: Email and SMS notifications for reservations and flight updates
- **Flight Management**: Admin can add flights, aircraft, and manage schedules

## System Architecture

### Package Structure

```
java/
├── enums/
│   ├── AccountStatus.java
│   ├── FlightStatus.java
│   ├── PaymentStatus.java
│   ├── ReservationStatus.java
│   ├── SeatClass.java
│   └── SeatType.java
├── models/
│   ├── Account.java
│   ├── Address.java
│   ├── Admin.java
│   ├── Aircraft.java
│   ├── Airline.java
│   ├── Airport.java
│   ├── CashPayment.java
│   ├── CreditCardPayment.java
│   ├── Crew.java
│   ├── Customer.java
│   ├── CustomSchedule.java
│   ├── EmailNotification.java
│   ├── Flight.java
│   ├── FlightInstance.java
│   ├── FlightReservation.java
│   ├── FlightSeat.java
│   ├── FrontDeskOfficer.java
│   ├── Itinerary.java
│   ├── Notification.java
│   ├── Passenger.java
│   ├── Payment.java
│   ├── Person.java
│   ├── Pilot.java
│   ├── Seat.java
│   ├── SMSNotification.java
│   └── WeeklySchedule.java
└── services/
    ├── FlightSearch.java
    └── ReservationService.java
```

## Core Classes

### Enums

- **FlightStatus**: ACTIVE, SCHEDULED, DELAYED, DEPARTED, LANDED, IN_AIR, ARRIVED, CANCELLED, DIVERTED, UNKNOWN
- **PaymentStatus**: UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
- **ReservationStatus**: REQUESTED, PENDING, CONFIRMED, CHECKED_IN, CANCELLED, ABANDONED
- **SeatClass**: ECONOMY, ECONOMY_PLUS, PREFERRED_ECONOMY, BUSINESS, FIRST_CLASS
- **SeatType**: REGULAR, ACCESSIBLE, EMERGENCY_EXIT, EXTRA_LEG_ROOM
- **AccountStatus**: ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED

### Main Entities

#### Airline
Represents the airline organization with name, code, aircraft fleet, and flights.

#### Airport
Represents an airport with name, address, unique code, and associated flights.

#### Aircraft
Represents an aircraft with model, manufacturing year, and seat configuration.

#### Flight
Represents a flight route with flight number, departure/arrival airports, duration, and schedules.

#### FlightInstance
Represents a specific occurrence of a flight with departure time, gate, status, assigned aircraft, pilots, and crew.

#### FlightReservation
Manages reservations with reservation number, passenger-seat mapping, status, and payment.

#### Itinerary
Represents a multi-flight journey with multiple reservations.

### People

- **Person** (Abstract): Base class for all people in the system
- **Customer**: Can search flights, make reservations, and manage itineraries
- **Admin**: Can add aircraft, flights, schedules, and assign crew
- **FrontDeskOfficer**: Can create and cancel reservations
- **Pilot**: Has license number and assigned flights
- **Crew**: Has assigned flights
- **Passenger**: Represents a passenger with passport details

### Payment

- **Payment** (Abstract): Base payment class
- **CreditCardPayment**: Credit card payment implementation
- **CashPayment**: Cash payment implementation

### Notifications

- **Notification** (Abstract): Base notification class
- **EmailNotification**: Email notification implementation
- **SMSNotification**: SMS notification implementation

## Key Design Patterns

1. **Inheritance**: Person hierarchy, Payment hierarchy, Notification hierarchy
2. **Composition**: FlightInstance contains Aircraft, Seats, Pilots, and Crew
3. **Encapsulation**: Private fields with public getters/setters
4. **Abstraction**: Abstract classes for Person, Payment, and Notification

## Usage Example

```java
// Create an airport
Address airportAddress = new Address("123 Airport Rd", "New York", "NY", "10001", "USA");
Airport jfk = new Airport("John F Kennedy International", airportAddress, "JFK");

// Create an aircraft
Aircraft boeing747 = new Aircraft("Boeing 747", "747-400", 2010);

// Add seats to aircraft
boeing747.addSeat(new Seat("1A", SeatType.REGULAR, SeatClass.FIRST_CLASS));
boeing747.addSeat(new Seat("2A", SeatType.REGULAR, SeatClass.BUSINESS));

// Create a flight
Flight flight = new Flight("BA212", jfk, lhr, 420); // 7 hours

// Create a flight instance
FlightInstance flightInstance = new FlightInstance(
    flight,
    LocalDateTime.of(2024, 12, 25, 10, 0),
    "A1",
    FlightStatus.SCHEDULED,
    boeing747
);

// Create a customer
Account account = new Account("customer1", "password123");
Address customerAddress = new Address("456 Main St", "Boston", "MA", "02101", "USA");
Customer customer = new Customer("John Doe", customerAddress, "john@email.com", 
                                "555-1234", account, "FF123456");

// Search for flights
FlightSearch searchService = new FlightSearch();
List<FlightInstance> flights = searchService.searchFlights(jfk, lhr, LocalDate.of(2024, 12, 25));

// Create a reservation
ReservationService reservationService = new ReservationService();
FlightReservation reservation = reservationService.createReservation(customer, flightInstance);

// Add passenger and assign seat
Passenger passenger = new Passenger("John Doe", "P123456", new Date());
FlightSeat seat = flightInstance.getAvailableSeats().get(0);
reservationService.assignSeatsToReservation(reservation, passenger, seat);

// Process payment
Payment payment = new CreditCardPayment(
    reservation.calculateTotalFare(),
    "TXN123",
    "4111111111111111",
    "John Doe",
    "12/25",
    "123"
);

// Confirm reservation
reservationService.confirmReservation(reservation, payment);
```

## System Requirements Covered

1. ✅ Search for flights by date and source/destination airport
2. ✅ Reserve tickets and build multi-flight itineraries
3. ✅ Check flight schedules and details
4. ✅ Multiple passengers per reservation
5. ✅ Admin can add aircraft, flights, and schedules
6. ✅ Cancel reservations and itineraries
7. ✅ Assign pilots and crew to flights
8. ✅ Handle payments for reservations
9. ✅ Send notifications for reservations and flight updates

## Future Enhancements

- Database integration for persistence
- RESTful API layer
- Web/mobile UI
- Real-time flight tracking
- Loyalty program management
- Baggage tracking
- Check-in system
- Reporting and analytics

## License

This is an educational implementation for learning object-oriented design principles.