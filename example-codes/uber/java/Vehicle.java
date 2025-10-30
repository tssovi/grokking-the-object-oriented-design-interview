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
