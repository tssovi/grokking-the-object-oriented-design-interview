package parkinglot;

/**
 * Abstract base class representing a vehicle.
 */
public abstract class Vehicle {
    private String licenseNumber;
    private VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(String licenseNumber, VehicleType type) {
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public void assignTicket(ParkingTicket ticket) {
        this.ticket = ticket;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }
}

/**
 * Car class extending Vehicle.
 */
class Car extends Vehicle {
    public Car(String licenseNumber) {
        super(licenseNumber, VehicleType.CAR);
    }
}

/**
 * Truck class extending Vehicle.
 */
class Truck extends Vehicle {
    public Truck(String licenseNumber) {
        super(licenseNumber, VehicleType.TRUCK);
    }
}

/**
 * Electric vehicle class extending Vehicle.
 */
class Electric extends Vehicle {
    public Electric(String licenseNumber) {
        super(licenseNumber, VehicleType.ELECTRIC);
    }
}

/**
 * Van class extending Vehicle.
 */
class Van extends Vehicle {
    public Van(String licenseNumber) {
        super(licenseNumber, VehicleType.VAN);
    }
}

/**
 * Motorbike class extending Vehicle.
 */
class Motorbike extends Vehicle {
    public Motorbike(String licenseNumber) {
        super(licenseNumber, VehicleType.MOTORBIKE);
    }
}
