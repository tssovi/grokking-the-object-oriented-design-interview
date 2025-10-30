package parkinglot;

/**
 * Enum representing different types of vehicles that can be parked.
 */
public enum VehicleType {
    CAR(1),
    TRUCK(2),
    ELECTRIC(3),
    VAN(4),
    MOTORBIKE(5);

    private final int value;

    VehicleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
