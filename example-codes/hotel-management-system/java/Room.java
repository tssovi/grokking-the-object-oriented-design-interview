import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Room.java
 * 
 * This file contains classes related to hotel rooms, including the Room class,
 * RoomKey for access control, RoomHouseKeeping for maintenance tracking,
 * and the Search interface for room availability queries.
 */

/**
 * Interface for searching rooms based on various criteria.
 * Classes implementing this interface can search for available rooms.
 */
interface Search {
    /**
     * Searches for available rooms matching the specified criteria.
     * 
     * @param style The style/type of room desired
     * @param startDate The desired check-in date
     * @param duration Duration of stay in days
     * @return List of available rooms matching the criteria
     */
    List<Room> search(RoomStyle style, Date startDate, int duration);
}

/**
 * Class representing a hotel room.
 * Contains all room information including number, style, status, pricing,
 * and associated keys and housekeeping logs.
 */
class Room implements Search {
    private String roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;
    
    // List of keys associated with this room
    private List<RoomKey> keys;
    
    // Log of housekeeping activities for this room
    private List<RoomHouseKeeping> houseKeepingLog;

    /**
     * Constructor to create a Room object.
     * 
     * @param roomNumber Unique identifier for the room (e.g., "101", "205")
     * @param style The style/type of the room (STANDARD, DELUXE, etc.)
     * @param status Current status of the room
     * @param bookingPrice Price per night for booking this room
     * @param isSmoking Whether smoking is allowed in this room
     */
    public Room(String roomNumber, RoomStyle style, RoomStatus status, 
                double bookingPrice, boolean isSmoking) {
        this.roomNumber = roomNumber;
        this.style = style;
        this.status = status;
        this.bookingPrice = bookingPrice;
        this.isSmoking = isSmoking;
        this.keys = new ArrayList<>();
        this.houseKeepingLog = new ArrayList<>();
    }

    /**
     * Checks if the room is currently available for booking.
     * 
     * @return true if room status is AVAILABLE, false otherwise
     */
    public boolean isRoomAvailable() {
        return this.status == RoomStatus.AVAILABLE;
    }

    /**
     * Performs check-in operation for this room.
     * Changes room status from RESERVED to OCCUPIED.
     * 
     * @return true if check-in was successful, false otherwise
     */
    public boolean checkIn() {
        if (this.status == RoomStatus.RESERVED) {
            this.status = RoomStatus.OCCUPIED;
            return true;
        }
        return false;
    }

    /**
     * Performs check-out operation for this room.
     * Changes room status from OCCUPIED to BEING_SERVICED for cleaning.
     * 
     * @return true if check-out was successful, false otherwise
     */
    public boolean checkOut() {
        if (this.status == RoomStatus.OCCUPIED) {
            this.status = RoomStatus.BEING_SERVICED;
            return true;
        }
        return false;
    }

    /**
     * Searches for available rooms matching the specified criteria.
     * This implementation would typically query a database or room catalog.
     * 
     * @param style The desired room style
     * @param startDate The desired check-in date
     * @param duration Duration of stay in days
     * @return List of available rooms matching the criteria
     */
    @Override
    public List<Room> search(RoomStyle style, Date startDate, int duration) {
        // TODO: Implement room search logic
        // Should query the hotel's room inventory and check availability
        // for the specified date range and room style
        return new ArrayList<>();
    }

    /**
     * Adds a room key to this room's key list.
     * 
     * @param key The RoomKey to add
     */
    public void addKey(RoomKey key) {
        this.keys.add(key);
    }

    /**
     * Adds a housekeeping log entry for this room.
     * 
     * @param log The RoomHouseKeeping log to add
     */
    public void addHouseKeepingLog(RoomHouseKeeping log) {
        this.houseKeepingLog.add(log);
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomStyle getStyle() {
        return style;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public List<RoomKey> getKeys() {
        return keys;
    }

    public List<RoomHouseKeeping> getHouseKeepingLog() {
        return houseKeepingLog;
    }
}

/**
 * Class representing a room key or access card.
 * Tracks key information including ID, barcode, activation status, and issue date.
 */
class RoomKey {
    private String keyId;
    private String barcode;
    private Date issuedAt;
    private boolean isActive;
    private boolean isMaster;

    /**
     * Constructor to create a RoomKey object.
     * 
     * @param keyId Unique identifier for the key
     * @param barcode Barcode or magnetic strip data for the key
     * @param isActive Whether the key is currently active
     * @param isMaster Whether this is a master key (can open multiple rooms)
     */
    public RoomKey(String keyId, String barcode, boolean isActive, boolean isMaster) {
        this.keyId = keyId;
        this.barcode = barcode;
        this.issuedAt = new Date(); // Set to current date/time
        this.isActive = isActive;
        this.isMaster = isMaster;
    }

    /**
     * Assigns this key to a specific room.
     * 
     * @param room The room to assign this key to
     * @return true if assignment was successful, false otherwise
     */
    public boolean assignRoom(Room room) {
        // TODO: Implement key assignment logic
        // Should link this key to the specified room in the system
        return false;
    }

    /**
     * Checks if this key is currently active.
     * 
     * @return true if the key is active, false otherwise
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Activates or deactivates this key.
     * 
     * @param active true to activate, false to deactivate
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Getters
    public String getKeyId() {
        return keyId;
    }

    public String getBarcode() {
        return barcode;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public boolean isMaster() {
        return isMaster;
    }
}

/**
 * Class representing a housekeeping activity log entry.
 * Tracks when housekeeping was performed, by whom, and for how long.
 */
class RoomHouseKeeping {
    private String description;
    private Date startDateTime;
    private int duration; // Duration in minutes
    private Person houseKeeper;

    /**
     * Constructor to create a RoomHouseKeeping log entry.
     * 
     * @param description Description of the housekeeping activity
     * @param duration Duration of the activity in minutes
     * @param houseKeeper The person who performed the housekeeping
     */
    public RoomHouseKeeping(String description, int duration, Person houseKeeper) {
        this.description = description;
        this.startDateTime = new Date(); // Set to current date/time
        this.duration = duration;
        this.houseKeeper = houseKeeper;
    }

    /**
     * Adds this housekeeping log entry to a room's log.
     * 
     * @param room The room to add this log entry to
     * @return true if log was added successfully, false otherwise
     */
    public boolean addHouseKeeping(Room room) {
        if (room != null) {
            room.addHouseKeepingLog(this);
            return true;
        }
        return false;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public Person getHouseKeeper() {
        return houseKeeper;
    }
}
