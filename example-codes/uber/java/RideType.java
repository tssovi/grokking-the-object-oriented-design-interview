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
