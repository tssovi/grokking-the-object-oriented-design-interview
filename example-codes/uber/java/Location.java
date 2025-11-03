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
