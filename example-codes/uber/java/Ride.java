import java.util.Date;
import java.util.UUID;

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
