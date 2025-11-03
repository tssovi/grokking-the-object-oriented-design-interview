import java.util.ArrayList;
import java.util.List;

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
