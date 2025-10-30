package parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Singleton class representing the parking lot system.
 * Thread-safe implementation using double-checked locking.
 */
public class ParkingLot {
    private static ParkingLot instance = null;
    private static final Lock lock = new ReentrantLock();

    private String name;
    private String address;
    private ParkingRate parkingRate;

    private int compactSpotCount;
    private int largeSpotCount;
    private int motorbikeSpotCount;
    private int electricSpotCount;
    private int maxCompactCount;
    private int maxLargeCount;
    private int maxMotorbikeCount;
    private int maxElectricCount;

    private Map<String, EntrancePanel> entrancePanels;
    private Map<String, ExitPanel> exitPanels;
    private Map<String, ParkingFloor> parkingFloors;
    private Map<String, ParkingTicket> activeTickets;

    private ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        this.parkingRate = new ParkingRate();
        this.entrancePanels = new HashMap<>();
        this.exitPanels = new HashMap<>();
        this.parkingFloors = new HashMap<>();
        this.activeTickets = new HashMap<>();
    }

    /**
     * Thread-safe singleton instance getter.
     */
    public static ParkingLot getInstance(String name, String address) {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ParkingLot(name, address);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public boolean isFull(ParkingSpotType type) {
        if (type == ParkingSpotType.COMPACT) {
            return compactSpotCount >= maxCompactCount;
        } else if (type == ParkingSpotType.LARGE) {
            return largeSpotCount >= maxLargeCount;
        } else if (type == ParkingSpotType.MOTORBIKE) {
            return motorbikeSpotCount >= maxMotorbikeCount;
        } else if (type == ParkingSpotType.ELECTRIC) {
            return electricSpotCount >= maxElectricCount;
        }
        return true;
    }

    public ParkingTicket getNewParkingTicket(Vehicle vehicle) {
        if (this.isFull(getParkingSpotTypeForVehicle(vehicle.getType()))) {
            System.out.println("Parking full!");
            return null;
        }

        lock.lock();
        try {
            ParkingTicket ticket = new ParkingTicket();
            vehicle.assignTicket(ticket);
            ticket.setTicketNumber(generateTicketNumber());
            activeTickets.put(ticket.getTicketNumber(), ticket);
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    public boolean isFull() {
        return compactSpotCount >= maxCompactCount &&
               largeSpotCount >= maxLargeCount &&
               motorbikeSpotCount >= maxMotorbikeCount &&
               electricSpotCount >= maxElectricCount;
    }

    private ParkingSpotType getParkingSpotTypeForVehicle(VehicleType type) {
        switch (type) {
            case CAR:
                return ParkingSpotType.COMPACT;
            case TRUCK:
            case VAN:
                return ParkingSpotType.LARGE;
            case MOTORBIKE:
                return ParkingSpotType.MOTORBIKE;
            case ELECTRIC:
                return ParkingSpotType.ELECTRIC;
            default:
                return ParkingSpotType.COMPACT;
        }
    }

    private String generateTicketNumber() {
        return "TICKET-" + System.currentTimeMillis();
    }
}

class ParkingRate {
    // Parking rate calculation logic
}

class EntrancePanel {
    // Entrance panel logic
}

class ExitPanel {
    // Exit panel logic
}
