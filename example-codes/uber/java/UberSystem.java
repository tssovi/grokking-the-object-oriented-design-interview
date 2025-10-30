import java.util.HashMap;
import java.util.Map;

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
