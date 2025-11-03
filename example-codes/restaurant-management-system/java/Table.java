import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Table.java
 * 
 * This file contains classes related to table management in the restaurant,
 * including tables, table seats, and reservations.
 */

/**
 * Represents a table in the restaurant.
 * 
 * Each table has a unique identifier, maximum capacity, location,
 * and current status. Tables contain multiple seats and can be reserved.
 */
class Table {
    // Private fields for table information
    private String tableId;
    private int maxCapacity;
    private String locationIdentifier;
    private TableStatus status;
    private List<TableSeat> seats;

    /**
     * Constructor to create a new Table with specified status.
     * 
     * @param tableId Unique identifier for the table
     * @param maxCapacity Maximum number of people the table can accommodate
     * @param locationIdentifier Location description (e.g., "Window-1", "Patio-3")
     * @param status Initial status of the table
     */
    public Table(String tableId, int maxCapacity, String locationIdentifier, TableStatus status) {
        this.tableId = tableId;
        this.maxCapacity = maxCapacity;
        this.locationIdentifier = locationIdentifier;
        this.status = status;
        this.seats = new ArrayList<>();
    }

    /**
     * Constructor to create a new Table with default FREE status.
     * 
     * @param tableId Unique identifier for the table
     * @param maxCapacity Maximum number of people the table can accommodate
     * @param locationIdentifier Location description (e.g., "Window-1", "Patio-3")
     */
    public Table(String tableId, int maxCapacity, String locationIdentifier) {
        this(tableId, maxCapacity, locationIdentifier, TableStatus.FREE);
    }

    /**
     * Checks if the table is currently free (available for seating).
     * 
     * @return true if the table is free, false otherwise
     */
    public boolean isTableFree() {
        // TODO: Implement table availability check
        // This would typically involve:
        // 1. Check current table status
        // 2. Verify no active reservations
        // 3. Confirm table is clean and ready
        return status == TableStatus.FREE;
    }

    /**
     * Adds a reservation to this table.
     * 
     * @param reservation The Reservation object to associate with this table
     * @return boolean indicating whether the reservation was successfully added
     */
    public boolean addReservation(Reservation reservation) {
        // TODO: Implement reservation addition logic
        // This would typically involve:
        // 1. Validate reservation details
        // 2. Check table availability for the time slot
        // 3. Update table status to RESERVED
        // 4. Store reservation information
        if (reservation != null && status == TableStatus.FREE) {
            status = TableStatus.RESERVED;
            return true;
        }
        return false;
    }

    /**
     * Searches for available tables matching the given criteria.
     * 
     * This is a static method that would search across all tables
     * in the restaurant to find suitable options.
     * 
     * @param capacity Required seating capacity
     * @param startTime Desired reservation start time
     * @return List of available tables matching the criteria
     */
    public static List<Table> search(int capacity, LocalDateTime startTime) {
        // TODO: Implement table search logic
        // This would typically involve:
        // 1. Query database for all tables
        // 2. Filter by capacity requirement
        // 3. Check availability at specified time
        // 4. Return list of matching tables
        return new ArrayList<>();
    }

    /**
     * Adds a seat to this table.
     * 
     * @param seat The TableSeat to add
     * @return boolean indicating success
     */
    public boolean addSeat(TableSeat seat) {
        if (seat != null && seats.size() < maxCapacity) {
            seats.add(seat);
            return true;
        }
        return false;
    }

    // Getter and setter methods
    
    public String getTableId() {
        return tableId;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getLocationIdentifier() {
        return locationIdentifier;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public List<TableSeat> getSeats() {
        return new ArrayList<>(seats); // Return a copy to prevent external modification
    }
}

/**
 * Represents a single seat at a table.
 * 
 * Each seat has a number and a type (regular, kid-friendly, accessible, etc.).
 * This allows for detailed tracking of seating arrangements and special requirements.
 */
class TableSeat {
    // Private fields for seat information
    private int tableSeatNumber;
    private SeatType type;

    /**
     * Default constructor to create a new TableSeat.
     * Initializes with default values (seat number 0, REGULAR type).
     */
    public TableSeat() {
        this.tableSeatNumber = 0;
        this.type = SeatType.REGULAR;
    }

    /**
     * Constructor to create a new TableSeat with specific details.
     * 
     * @param tableSeatNumber The seat number at the table
     * @param type The type of seat (REGULAR, KID, ACCESSIBLE, OTHER)
     */
    public TableSeat(int tableSeatNumber, SeatType type) {
        this.tableSeatNumber = tableSeatNumber;
        this.type = type;
    }

    /**
     * Updates the type of this seat.
     * 
     * This allows for reconfiguring seats based on customer needs
     * (e.g., converting a regular seat to a kid-friendly seat).
     * 
     * @param seatType The new SeatType to set
     * @return boolean indicating whether the update was successful
     */
    public boolean updateSeatType(SeatType seatType) {
        // TODO: Implement seat type update logic
        // This would typically involve:
        // 1. Validate new seat type
        // 2. Check if seat is currently unoccupied
        // 3. Update seat type
        // 4. Update database
        if (seatType != null) {
            this.type = seatType;
            return true;
        }
        return false;
    }

    // Getter methods
    
    public int getTableSeatNumber() {
        return tableSeatNumber;
    }

    public SeatType getType() {
        return type;
    }

    public void setTableSeatNumber(int tableSeatNumber) {
        this.tableSeatNumber = tableSeatNumber;
    }
}

/**
 * Represents a reservation for a table at the restaurant.
 * 
 * Reservations track customer booking information including party size,
 * reservation time, status, and any special notes or requirements.
 */
class Reservation {
    // Private fields for reservation information
    private String reservationId;
    private LocalDateTime timeOfReservation;
    private int peopleCount;
    private ReservationStatus status;
    private String notes;
    private LocalDateTime checkinTime;
    private Object customer; // Would be a Customer class in full implementation
    private List<Table> tables;
    private List<Object> notifications; // Would be a Notification class in full implementation

    /**
     * Constructor to create a new Reservation.
     * 
     * @param reservationId Unique identifier for the reservation
     * @param peopleCount Number of people in the party
     * @param notes Special requests or notes (dietary restrictions, occasion, etc.)
     * @param customer The customer making the reservation
     */
    public Reservation(String reservationId, int peopleCount, String notes, Object customer) {
        this.reservationId = reservationId;
        this.timeOfReservation = LocalDateTime.now();
        this.peopleCount = peopleCount;
        this.status = ReservationStatus.REQUESTED;
        this.notes = notes;
        this.checkinTime = LocalDateTime.now();
        this.customer = customer;
        this.tables = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    /**
     * Updates the number of people in the reservation.
     * 
     * This allows customers to modify their party size before the reservation time.
     * 
     * @param count The new number of people
     * @return boolean indicating whether the update was successful
     */
    public boolean updatePeopleCount(int count) {
        // TODO: Implement people count update logic
        // This would typically involve:
        // 1. Validate new count (must be positive)
        // 2. Check if reservation can still be modified
        // 3. Verify table capacity can accommodate new count
        // 4. Update reservation and notify restaurant
        if (count > 0 && status == ReservationStatus.REQUESTED || status == ReservationStatus.PENDING) {
            this.peopleCount = count;
            return true;
        }
        return false;
    }

    /**
     * Adds a table to this reservation.
     * 
     * @param table The Table to assign to this reservation
     * @return boolean indicating success
     */
    public boolean addTable(Table table) {
        if (table != null && !tables.contains(table)) {
            tables.add(table);
            return true;
        }
        return false;
    }

    /**
     * Updates the status of this reservation.
     * 
     * @param newStatus The new ReservationStatus
     * @return boolean indicating success
     */
    public boolean updateStatus(ReservationStatus newStatus) {
        if (newStatus != null) {
            this.status = newStatus;
            return true;
        }
        return false;
    }

    // Getter methods
    
    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getTimeOfReservation() {
        return timeOfReservation;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public Object getCustomer() {
        return customer;
    }

    public List<Table> getTables() {
        return new ArrayList<>(tables); // Return a copy to prevent external modification
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }
}
