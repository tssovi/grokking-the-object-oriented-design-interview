import java.util.ArrayList;
import java.util.List;

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
