# Movie Ticket Booking System - Java Implementation

## Overview

This is a comprehensive Java implementation of a movie ticket booking system. The system allows customers to browse movies, search by various criteria, book tickets for shows, and manage their bookings. It also provides administrative functions for managing movies, shows, and users.

## System Architecture

The system is designed using object-oriented principles and follows a modular architecture. The implementation consists of the following main components:

### Core Components

1. **Constants.java** - Enumerations and constant classes
2. **AccountType.java** - User account management and user types
3. **Cinema.java** - Cinema infrastructure (cities, cinemas, halls, seats)
4. **Show.java** - Movies and show scheduling
5. **Booking.java** - Booking management and payment processing
6. **Search.java** - Movie search and catalog functionality

## Class Descriptions

### 1. Constants.java

Contains all enumerations and constant classes used throughout the system:

#### Enumerations:
- **BookingStatus**: Tracks the lifecycle of a booking
  - `REQUESTED`, `PENDING`, `CONFIRMED`, `CHECKED_IN`, `CANCELED`, `ABANDONED`

- **SeatType**: Different types of cinema seats
  - `REGULAR`, `PREMIUM`, `ACCESSIBLE`, `SHIPPED`, `EMERGENCY_EXIT`, `OTHER`

- **AccountStatus**: User account states
  - `ACTIVE`, `BLOCKED`, `BANNED`, `COMPROMISED`, `ARCHIVED`, `UNKNOWN`

- **PaymentStatus**: Payment transaction states
  - `UNPAID`, `PENDING`, `COMPLETED`, `FILLED`, `DECLINED`, `CANCELLED`, `ABANDONED`, `SETTLING`, `SETTLED`, `REFUNDED`

#### Classes:
- **Address**: Represents a physical address with street, city, state, zip code, and country

### 2. AccountType.java

Manages user accounts and different user roles:

#### Classes:
- **Account**: User authentication and account management
  - Stores credentials and account status
  - Provides password reset functionality

- **Person** (Abstract): Base class for all user types
  - Contains common attributes: name, address, email, phone, account

- **Customer**: End users who book tickets
  - Can make bookings
  - Can view booking history

- **Admin**: System administrators
  - Can add movies to the catalog
  - Can create shows
  - Can block/unblock users

- **FrontDeskOfficer**: Staff members at the cinema
  - Can create bookings on behalf of customers
  - Assists walk-in customers

- **Guest**: Unregistered users
  - Can browse movies
  - Can register for an account

### 3. Cinema.java

Represents the physical cinema infrastructure:

#### Classes:
- **City**: Geographic location information
  - Name, state, and zip code

- **Cinema**: Movie theater complex
  - Contains multiple cinema halls
  - Has a physical address
  - Manages all halls in the complex

- **CinemaHall**: Individual screening room
  - Has a specific seating arrangement
  - Hosts multiple shows throughout the day
  - Tracks total seating capacity

- **CinemaHallSeat**: Physical seat in a hall
  - Unique identifier (e.g., "A1", "B5")
  - Seat type (regular, premium, accessible, etc.)

### 4. Show.java

Manages movies and their screenings:

#### Classes:
- **Movie**: Film information
  - Title, description, duration
  - Language, release date, country, genre
  - List of associated shows
  - Admin who added the movie

- **Show**: Specific screening of a movie
  - Show ID and creation date
  - Start and end time
  - Cinema hall where it's playing
  - Associated movie

### 5. Booking.java

Handles the booking process:

#### Classes:
- **Booking**: Customer's ticket reservation
  - Booking number and creation date
  - Number of seats and booking status
  - Associated show and seats
  - Payment information
  - Methods: `makePayment()`, `cancel()`, `assignSeats()`

- **ShowSeat**: Seat for a specific show
  - Extends CinemaHallSeat
  - Adds reservation status and pricing
  - Show-specific seat identifier

- **Payment**: Payment transaction details
  - Amount and transaction ID
  - Payment status and creation date

### 6. Search.java

Provides movie search functionality:

#### Interface:
- **Search**: Defines search operations
  - Search by title, language, genre
  - Search by release date
  - Search by city

#### Class:
- **Catalog**: Implements search interface
  - Uses hash maps for efficient indexing
  - Indexes movies by multiple attributes
  - Provides fast lookup operations
  - Methods: `addMovie()`, `removeMovie()`, `addMovieToCity()`

## Key Design Patterns Used

### 1. **Inheritance**
- `Person` is an abstract base class
- `Customer`, `Admin`, and `FrontDeskOfficer` extend `Person`
- `ShowSeat` extends `CinemaHallSeat`

### 2. **Encapsulation**
- All class attributes are private
- Access through public getter/setter methods
- Protects data integrity

### 3. **Interface Implementation**
- `Catalog` implements the `Search` interface
- Allows for multiple search implementations

### 4. **Composition**
- `Cinema` contains multiple `CinemaHall` objects
- `Booking` contains `Show`, `ShowSeat`, and `Payment` objects
- `Movie` contains a list of `Show` objects

## System Workflow

### 1. User Registration
```
Guest → registerAccount() → Account + Customer
```

### 2. Movie Search
```
Customer → Catalog.searchByTitle/Language/Genre/City() → List<Movie>
```

### 3. Booking Process
```
1. Customer selects a Movie
2. Customer selects a Show
3. Customer selects available ShowSeats
4. System creates Booking (status: PENDING)
5. Customer makes Payment
6. Payment processed → Booking status: CONFIRMED
7. Seats marked as reserved
```

### 4. Booking Cancellation
```
Customer → Booking.cancel() → Status: CANCELED + Seats released + Refund processed
```

### 5. Admin Operations
```
Admin → addMovie() → Movie added to Catalog
Admin → addShow() → Show created for Movie
Admin → blockUser() → Customer account blocked
```

## Usage Example

```java
// Create an admin account
Account adminAccount = new Account("admin001", "securePassword", AccountStatus.ACTIVE);
Address adminAddress = new Address("123 Admin St", "New York", "NY", "10001", "USA");
Admin admin = new Admin("John Admin", adminAddress, "admin@cinema.com", "555-0001", adminAccount);

// Add a movie
Movie movie = new Movie(
    "The Great Adventure",
    "An epic adventure story",
    150,
    "English",
    LocalDate.of(2024, 1, 15),
    "USA",
    "Adventure",
    admin
);

// Create a cinema and hall
Address cinemaAddress = new Address("456 Cinema Blvd", "New York", "NY", "10002", "USA");
List<CinemaHallSeat> seats = new ArrayList<>();
// Add seats...
List<Show> shows = new ArrayList<>();
CinemaHall hall = new CinemaHall("Hall 1", 100, seats, shows);

// Create a show
Show show = new Show(
    "SHOW001",
    hall,
    movie,
    LocalDateTime.of(2024, 1, 20, 19, 0),
    LocalDateTime.of(2024, 1, 20, 21, 30)
);

// Customer makes a booking
Account customerAccount = new Account("cust001", "password123", AccountStatus.ACTIVE);
Address customerAddress = new Address("789 Customer Ave", "New York", "NY", "10003", "USA");
Customer customer = new Customer("Jane Doe", customerAddress, "jane@email.com", "555-0002", customerAccount);

List<ShowSeat> selectedSeats = new ArrayList<>();
// Select seats...

Payment payment = new Payment(50.0, "TXN123456", PaymentStatus.COMPLETED);
Booking booking = new Booking("BOOK001", 2, BookingStatus.PENDING, show, selectedSeats, payment);
booking.makePayment(payment); // Status changes to CONFIRMED
```

## Features

### Customer Features
- ✅ Browse available movies
- ✅ Search movies by title, language, genre, release date, or city
- ✅ View show timings and available seats
- ✅ Book tickets for shows
- ✅ Make payments
- ✅ View booking history
- ✅ Cancel bookings

### Admin Features
- ✅ Add new movies to the catalog
- ✅ Create shows for movies
- ✅ Manage cinema halls and seats
- ✅ Block/unblock users
- ✅ View all bookings

### Front Desk Features
- ✅ Create bookings for walk-in customers
- ✅ Assist with ticket purchases

## Data Structures Used

- **HashMap**: For efficient movie indexing and searching (O(1) lookup)
- **ArrayList**: For storing collections of movies, shows, seats, and bookings
- **LocalDate/LocalDateTime**: For date and time handling (Java 8+ Time API)

## Future Enhancements

1. **Database Integration**: Connect to a relational database (MySQL, PostgreSQL)
2. **Seat Selection UI**: Interactive seat map for customers
3. **Payment Gateway Integration**: Integrate with Stripe, PayPal, etc.
4. **Email Notifications**: Send booking confirmations and reminders
5. **Discount System**: Implement coupons and promotional codes
6. **Reviews and Ratings**: Allow customers to rate movies
7. **Concurrency Control**: Handle simultaneous booking requests
8. **Waiting List**: Queue system for sold-out shows
9. **Mobile App Integration**: REST API for mobile applications
10. **Analytics Dashboard**: Admin dashboard with booking statistics

## Requirements

- Java 8 or higher (for LocalDate/LocalDateTime support)
- No external dependencies for core functionality

## Compilation

```bash
javac -d bin movie/ticket/booking/system/*.java
```

## Notes

- This is a skeletal implementation focusing on class structure and relationships
- Method implementations contain placeholder logic and should be completed based on specific requirements
- In a production system, you would need:
  - Database persistence layer
  - Transaction management
  - Concurrency control (synchronized blocks, locks)
  - Input validation and error handling
  - Logging and monitoring
  - Security measures (password hashing, SQL injection prevention)
  - Unit tests and integration tests

## Design Principles Applied

1. **Single Responsibility Principle**: Each class has a single, well-defined purpose
2. **Open/Closed Principle**: Classes are open for extension but closed for modification
3. **Liskov Substitution Principle**: Derived classes can substitute their base classes
4. **Interface Segregation**: Search interface provides focused functionality
5. **Dependency Inversion**: High-level modules depend on abstractions (interfaces)

## License

This code is provided as an educational example for learning object-oriented design principles.

## Author

This implementation is based on the Python version and translated to Java with comprehensive documentation and comments for better understanding.
