# Design Uber

Uber is a ride-sharing platform that connects riders with drivers. Let's design a simplified version of Uber's core functionality.

## System Requirements

We will focus on the following set of requirements while designing Uber:

1. There are two types of users: **Riders** and **Drivers**.
2. Riders can request rides by specifying pickup and drop-off locations.
3. Drivers can accept or decline ride requests.
4. The system should match riders with nearby available drivers.
5. The system should calculate fare based on distance and time.
6. Riders can rate drivers and vice versa.
7. The system should track ride status (requested, accepted, started, completed, cancelled).
8. Drivers can update their availability status.
9. The system should support different ride types (UberX, UberXL, UberBlack, etc.).
10. Payment processing should be handled.

## Use Case Diagram

We have three main Actors in our system:

* **Rider**: Can request rides, view available drivers, make payments, and rate drivers.
* **Driver**: Can accept/decline rides, update availability, start/complete rides, and rate riders.
* **System**: Matches riders with drivers, calculates fares, processes payments, and manages ride lifecycle.

Here are the top use cases of the Uber system:

* **Request Ride**: Rider requests a ride by providing pickup and drop-off locations.
* **Accept Ride**: Driver accepts a ride request.
* **Start Ride**: Driver starts the ride when rider is picked up.
* **Complete Ride**: Driver completes the ride at drop-off location.
* **Cancel Ride**: Either rider or driver can cancel a ride.
* **Rate User**: Both rider and driver can rate each other after ride completion.
* **Process Payment**: System processes payment from rider to driver.
* **Update Location**: Driver continuously updates their location.
* **Calculate Fare**: System calculates fare based on distance, time, and ride type.

## Class Diagram

Here are the main classes of our Uber system:

* **User**: Base class for Rider and Driver with common attributes like name, email, phone, rating.
* **Rider**: Extends User, can request rides and make payments.
* **Driver**: Extends User, has vehicle information, can accept rides and update availability.
* **Ride**: Represents a ride with pickup/drop-off locations, status, fare, and timestamps.
* **Location**: Represents geographic coordinates (latitude, longitude).
* **Vehicle**: Represents driver's vehicle with type, license plate, model, etc.
* **RideType**: Enum for different ride types (UBER_X, UBER_XL, UBER_BLACK, etc.).
* **RideStatus**: Enum for ride states (REQUESTED, ACCEPTED, STARTED, COMPLETED, CANCELLED).
* **Payment**: Handles payment processing with amount, method, and status.
* **Rating**: Stores ratings given by riders and drivers.
* **UberSystem**: Main system class that manages rides, matches drivers, and calculates fares.

![Class Diagram for Uber](uber-class-diagram.png)

## Activity Diagrams

### Request and Complete a Ride

![Activity Diagram for Uber - Request Ride](uber-activity-diagram.png)

## Code

Here is the code for the major classes:

**Enums and Constants:**

```java
public enum RideStatus {
  REQUESTED,
  ACCEPTED,
  ARRIVED,
  STARTED,
  COMPLETED,
  CANCELLED
}

public enum RideType {
  UBER_X(1.0),
  UBER_XL(1.5),
  UBER_BLACK(2.0),
  UBER_POOL(0.8);

  private final double multiplier;

  RideType(double multiplier) {
    this.multiplier = multiplier;
  }

  public double getMultiplier() {
    return multiplier;
  }
}

public enum PaymentStatus {
  PENDING,
  COMPLETED,
  FAILED,
  REFUNDED
}

public enum DriverStatus {
  AVAILABLE,
  BUSY,
  OFFLINE
}
```

**Location:**

```java
public class Location {
  private double latitude;
  private double longitude;

  public Location(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  // Calculate distance between two locations using Haversine formula
  public double distanceTo(Location other) {
    final int R = 6371; // Radius of the earth in km

    double latDistance = Math.toRadians(other.latitude - this.latitude);
    double lonDistance = Math.toRadians(other.longitude - this.longitude);
    
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    
    return R * c; // Distance in km
  }
}
```

**User, Rider, and Driver:**

```java
public abstract class User {
  private String id;
  private String name;
  private String email;
  private String phone;
  private double rating;
  private int totalRatings;

  public User(String id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.rating = 5.0;
    this.totalRatings = 0;
  }

  public void updateRating(double newRating) {
    this.rating = ((this.rating * this.totalRatings) + newRating) / (this.totalRatings + 1);
    this.totalRatings++;
  }

  // Getters
  public String getId() { return id; }
  public String getName() { return name; }
  public String getEmail() { return email; }
  public String getPhone() { return phone; }
  public double getRating() { return rating; }
}

public class Rider extends User {
  private List<Ride> rideHistory;
  private Payment paymentMethod;

  public Rider(String id, String name, String email, String phone) {
    super(id, name, email, phone);
    this.rideHistory = new ArrayList<>();
  }

  public Ride requestRide(Location pickup, Location dropoff, RideType type) {
    Ride ride = new Ride(this, pickup, dropoff, type);
    rideHistory.add(ride);
    return ride;
  }

  public void rateDriver(Driver driver, double rating) {
    driver.updateRating(rating);
  }

  public List<Ride> getRideHistory() {
    return rideHistory;
  }
}

public class Driver extends User {
  private Vehicle vehicle;
  private Location currentLocation;
  private DriverStatus status;
  private List<Ride> rideHistory;

  public Driver(String id, String name, String email, String phone, Vehicle vehicle) {
    super(id, name, email, phone);
    this.vehicle = vehicle;
    this.status = DriverStatus.OFFLINE;
    this.rideHistory = new ArrayList<>();
  }

  public void updateLocation(Location location) {
    this.currentLocation = location;
  }

  public void setStatus(DriverStatus status) {
    this.status = status;
  }

  public boolean acceptRide(Ride ride) {
    if (this.status == DriverStatus.AVAILABLE) {
      ride.setDriver(this);
      ride.setStatus(RideStatus.ACCEPTED);
      this.status = DriverStatus.BUSY;
      this.rideHistory.add(ride);
      return true;
    }
    return false;
  }

  public void startRide(Ride ride) {
    ride.setStatus(RideStatus.STARTED);
  }

  public void completeRide(Ride ride) {
    ride.setStatus(RideStatus.COMPLETED);
    this.status = DriverStatus.AVAILABLE;
  }

  public void rateRider(Rider rider, double rating) {
    rider.updateRating(rating);
  }

  // Getters
  public Vehicle getVehicle() { return vehicle; }
  public Location getCurrentLocation() { return currentLocation; }
  public DriverStatus getStatus() { return status; }
  public List<Ride> getRideHistory() { return rideHistory; }
}
```

**Vehicle:**

```java
public class Vehicle {
  private String licensePlate;
  private String make;
  private String model;
  private int year;
  private RideType type;
  private int capacity;

  public Vehicle(String licensePlate, String make, String model, int year, RideType type, int capacity) {
    this.licensePlate = licensePlate;
    this.make = make;
    this.model = model;
    this.year = year;
    this.type = type;
    this.capacity = capacity;
  }

  // Getters
  public String getLicensePlate() { return licensePlate; }
  public String getMake() { return make; }
  public String getModel() { return model; }
  public int getYear() { return year; }
  public RideType getType() { return type; }
  public int getCapacity() { return capacity; }
}
```

**Ride:**

```java
public class Ride {
  private String id;
  private Rider rider;
  private Driver driver;
  private Location pickup;
  private Location dropoff;
  private RideType type;
  private RideStatus status;
  private double fare;
  private double distance;
  private Date requestTime;
  private Date startTime;
  private Date endTime;
  private Payment payment;

  public Ride(Rider rider, Location pickup, Location dropoff, RideType type) {
    this.id = UUID.randomUUID().toString();
    this.rider = rider;
    this.pickup = pickup;
    this.dropoff = dropoff;
    this.type = type;
    this.status = RideStatus.REQUESTED;
    this.requestTime = new Date();
    this.distance = pickup.distanceTo(dropoff);
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public void setStatus(RideStatus status) {
    this.status = status;
    if (status == RideStatus.STARTED) {
      this.startTime = new Date();
    } else if (status == RideStatus.COMPLETED) {
      this.endTime = new Date();
    }
  }

  public void setFare(double fare) {
    this.fare = fare;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  // Getters
  public String getId() { return id; }
  public Rider getRider() { return rider; }
  public Driver getDriver() { return driver; }
  public Location getPickup() { return pickup; }
  public Location getDropoff() { return dropoff; }
  public RideType getType() { return type; }
  public RideStatus getStatus() { return status; }
  public double getFare() { return fare; }
  public double getDistance() { return distance; }
  public Date getRequestTime() { return requestTime; }
  public Date getStartTime() { return startTime; }
  public Date getEndTime() { return endTime; }
}
```

**Payment:**

```java
public class Payment {
  private String id;
  private double amount;
  private PaymentStatus status;
  private String method; // Credit card, PayPal, etc.
  private Date timestamp;

  public Payment(double amount, String method) {
    this.id = UUID.randomUUID().toString();
    this.amount = amount;
    this.method = method;
    this.status = PaymentStatus.PENDING;
    this.timestamp = new Date();
  }

  public boolean processPayment() {
    // Simulate payment processing
    this.status = PaymentStatus.COMPLETED;
    return true;
  }

  public void refund() {
    this.status = PaymentStatus.REFUNDED;
  }

  // Getters
  public String getId() { return id; }
  public double getAmount() { return amount; }
  public PaymentStatus getStatus() { return status; }
  public String getMethod() { return method; }
}
```

**UberSystem:**

```java
public class UberSystem {
  private static UberSystem instance;
  private Map<String, Driver> drivers;
  private Map<String, Rider> riders;
  private Map<String, Ride> rides;
  private final double BASE_FARE = 2.0;
  private final double PER_KM_RATE = 1.5;
  private final double PER_MINUTE_RATE = 0.3;

  private UberSystem() {
    this.drivers = new HashMap<>();
    this.riders = new HashMap<>();
    this.rides = new HashMap<>();
  }

  public static synchronized UberSystem getInstance() {
    if (instance == null) {
      instance = new UberSystem();
    }
    return instance;
  }

  public void addDriver(Driver driver) {
    drivers.put(driver.getId(), driver);
  }

  public void addRider(Rider rider) {
    riders.put(rider.getId(), rider);
  }

  public Driver findNearestDriver(Location pickup, RideType type) {
    Driver nearestDriver = null;
    double minDistance = Double.MAX_VALUE;

    for (Driver driver : drivers.values()) {
      if (driver.getStatus() == DriverStatus.AVAILABLE && 
          driver.getVehicle().getType() == type) {
        double distance = driver.getCurrentLocation().distanceTo(pickup);
        if (distance < minDistance) {
          minDistance = distance;
          nearestDriver = driver;
        }
      }
    }

    return nearestDriver;
  }

  public double calculateFare(Ride ride) {
    double distance = ride.getDistance();
    double duration = 0;
    
    if (ride.getStartTime() != null && ride.getEndTime() != null) {
      duration = (ride.getEndTime().getTime() - ride.getStartTime().getTime()) / (1000 * 60); // minutes
    } else {
      // Estimate duration based on distance (assuming 30 km/h average speed)
      duration = (distance / 30) * 60;
    }

    double fare = BASE_FARE + (distance * PER_KM_RATE) + (duration * PER_MINUTE_RATE);
    fare *= ride.getType().getMultiplier();

    return Math.round(fare * 100.0) / 100.0; // Round to 2 decimal places
  }

  public Ride requestRide(Rider rider, Location pickup, Location dropoff, RideType type) {
    Ride ride = rider.requestRide(pickup, dropoff, type);
    rides.put(ride.getId(), ride);

    // Find and notify nearest driver
    Driver nearestDriver = findNearestDriver(pickup, type);
    if (nearestDriver != null) {
      // In a real system, this would send a notification to the driver
      System.out.println("Ride request sent to driver: " + nearestDriver.getName());
    }

    return ride;
  }

  public void completeRide(Ride ride) {
    double fare = calculateFare(ride);
    ride.setFare(fare);

    Payment payment = new Payment(fare, "Credit Card");
    payment.processPayment();
    ride.setPayment(payment);

    ride.getDriver().completeRide(ride);
  }

  public void cancelRide(Ride ride) {
    ride.setStatus(RideStatus.CANCELLED);
    if (ride.getDriver() != null) {
      ride.getDriver().setStatus(DriverStatus.AVAILABLE);
    }
  }
}
```

## Concurrency

The Uber system needs to handle concurrent operations:

1. **Multiple riders requesting rides simultaneously**: Use thread-safe collections and synchronization.
2. **Drivers updating locations**: Implement location update queues to avoid race conditions.
3. **Ride matching**: Use locks to ensure a driver is only matched to one ride at a time.
4. **Payment processing**: Ensure atomic payment transactions.

## Additional Considerations

1. **Surge Pricing**: Implement dynamic pricing based on demand and supply.
2. **Ride Sharing**: Support multiple riders sharing the same ride (UberPool).
3. **Driver Earnings**: Track driver earnings and provide analytics.
4. **Notifications**: Real-time notifications for ride updates.
5. **Route Optimization**: Integrate with mapping services for optimal routes.
6. **Safety Features**: Emergency buttons, ride sharing with contacts, driver verification.
7. **Scheduling**: Allow riders to schedule rides in advance.
8. **Promotions**: Support promo codes and discounts.
