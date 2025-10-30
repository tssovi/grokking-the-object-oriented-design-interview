package parkinglot;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a floor in the parking lot.
 */
public class ParkingFloor {
    private String name;
    private Map<String, HandicappedSpot> handicappedSpots;
    private Map<String, CompactSpot> compactSpots;
    private Map<String, LargeSpot> largeSpots;
    private Map<String, MotorbikeSpot> motorbikeSpots;
    private Map<String, ElectricSpot> electricSpots;
    private int freeHandicappedSpotCount;
    private int freeCompactSpotCount;
    private int freeLargeSpotCount;
    private int freeMotorbikeSpotCount;
    private int freeElectricSpotCount;

    public ParkingFloor(String name) {
        this.name = name;
        this.handicappedSpots = new HashMap<>();
        this.compactSpots = new HashMap<>();
        this.largeSpots = new HashMap<>();
        this.motorbikeSpots = new HashMap<>();
        this.electricSpots = new HashMap<>();
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getType()) {
            case HANDICAPPED:
                handicappedSpots.put(spot.getNumber(), (HandicappedSpot) spot);
                freeHandicappedSpotCount++;
                break;
            case COMPACT:
                compactSpots.put(spot.getNumber(), (CompactSpot) spot);
                freeCompactSpotCount++;
                break;
            case LARGE:
                largeSpots.put(spot.getNumber(), (LargeSpot) spot);
                freeLargeSpotCount++;
                break;
            case MOTORBIKE:
                motorbikeSpots.put(spot.getNumber(), (MotorbikeSpot) spot);
                freeMotorbikeSpotCount++;
                break;
            case ELECTRIC:
                electricSpots.put(spot.getNumber(), (ElectricSpot) spot);
                freeElectricSpotCount++;
                break;
        }
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicle(vehicle);
        switch (spot.getType()) {
            case HANDICAPPED:
                freeHandicappedSpotCount--;
                break;
            case COMPACT:
                freeCompactSpotCount--;
                break;
            case LARGE:
                freeLargeSpotCount--;
                break;
            case MOTORBIKE:
                freeMotorbikeSpotCount--;
                break;
            case ELECTRIC:
                freeElectricSpotCount--;
                break;
        }
    }

    public void freeSpot(ParkingSpot spot) {
        spot.removeVehicle();
        switch (spot.getType()) {
            case HANDICAPPED:
                freeHandicappedSpotCount++;
                break;
            case COMPACT:
                freeCompactSpotCount++;
                break;
            case LARGE:
                freeLargeSpotCount++;
                break;
            case MOTORBIKE:
                freeMotorbikeSpotCount++;
                break;
            case ELECTRIC:
                freeElectricSpotCount++;
                break;
        }
    }
}
