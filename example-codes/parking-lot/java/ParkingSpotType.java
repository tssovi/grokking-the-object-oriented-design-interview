package parkinglot;

/**
 * Enum representing different types of parking spots available.
 */
public enum ParkingSpotType {
    HANDICAPPED(1),
    COMPACT(2),
    LARGE(3),
    MOTORBIKE(4),
    ELECTRIC(5);

    private final int value;

    ParkingSpotType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
