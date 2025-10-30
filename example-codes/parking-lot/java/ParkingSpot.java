package parkinglot;

/**
 * Abstract base class for parking spots.
 */
public abstract class ParkingSpot {
    private String number;
    private boolean free;
    private Vehicle vehicle;
    private ParkingSpotType type;

    public ParkingSpot(ParkingSpotType type) {
        this.type = type;
        this.free = true;
    }

    public boolean isFree() {
        return free;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        free = false;
        return true;
    }

    public boolean removeVehicle() {
        this.vehicle = null;
        free = true;
        return true;
    }

    public ParkingSpotType getType() {
        return type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class HandicappedSpot extends ParkingSpot {
    public HandicappedSpot() {
        super(ParkingSpotType.HANDICAPPED);
    }
}

class CompactSpot extends ParkingSpot {
    public CompactSpot() {
        super(ParkingSpotType.COMPACT);
    }
}

class LargeSpot extends ParkingSpot {
    public LargeSpot() {
        super(ParkingSpotType.LARGE);
    }
}

class MotorbikeSpot extends ParkingSpot {
    public MotorbikeSpot() {
        super(ParkingSpotType.MOTORBIKE);
    }
}

class ElectricSpot extends ParkingSpot {
    public ElectricSpot() {
        super(ParkingSpotType.ELECTRIC);
    }
}
