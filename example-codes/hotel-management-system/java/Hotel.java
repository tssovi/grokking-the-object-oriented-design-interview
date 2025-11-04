import java.util.ArrayList;
import java.util.List;

/**
 * Hotel.java
 * 
 * This file contains the main hotel management classes including Hotel and
 * HotelLocation. These classes represent the top-level structure of the
 * hotel management system.
 */

/**
 * Class representing a physical hotel location.
 * A hotel chain may have multiple locations, each with its own set of rooms.
 */
class HotelLocation {
    private String name;
    private Address location;
    private List<Room> rooms;

    /**
     * Constructor to create a HotelLocation object.
     * 
     * @param name Name of the hotel location (e.g., "Downtown Branch", "Airport Hotel")
     * @param location Physical address of this hotel location
     */
    public HotelLocation(String name, Address location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

    /**
     * Retrieves all rooms at this hotel location.
     * 
     * @return List of all Room objects at this location
     */
    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * Adds a room to this hotel location.
     * 
     * @param room The room to add
     * @return true if room was added successfully, false otherwise
     */
    public boolean addRoom(Room room) {
        if (room != null) {
            this.rooms.add(room);
            return true;
        }
        return false;
    }

    /**
     * Searches for available rooms at this location matching the criteria.
     * 
     * @param style The desired room style
     * @param startDate The desired check-in date
     * @param duration Duration of stay in days
     * @return List of available rooms matching the criteria
     */
    public List<Room> searchAvailableRooms(RoomStyle style, java.util.Date startDate, int duration) {
        List<Room> availableRooms = new ArrayList<>();
        
        for (Room room : rooms) {
            // Check if room matches the desired style and is available
            if (room.getStyle() == style && room.isRoomAvailable()) {
                availableRooms.add(room);
            }
        }
        
        return availableRooms;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Address getLocation() {
        return location;
    }
}

/**
 * Class representing the main Hotel entity.
 * A hotel can have multiple locations, and this class manages them all.
 * This is the central class for the hotel management system.
 */
class Hotel {
    private String name;
    private List<HotelLocation> locations;

    /**
     * Constructor to create a Hotel object.
     * 
     * @param name Name of the hotel or hotel chain
     */
    public Hotel(String name) {
        this.name = name;
        this.locations = new ArrayList<>();
    }

    /**
     * Adds a new location to this hotel chain.
     * 
     * @param location The HotelLocation to add
     * @return true if location was added successfully, false otherwise
     */
    public boolean addLocation(HotelLocation location) {
        if (location != null) {
            this.locations.add(location);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all locations of this hotel.
     * 
     * @return List of all HotelLocation objects
     */
    public List<HotelLocation> getLocations() {
        return this.locations;
    }

    /**
     * Searches for available rooms across all hotel locations.
     * 
     * @param style The desired room style
     * @param startDate The desired check-in date
     * @param duration Duration of stay in days
     * @return List of available rooms across all locations
     */
    public List<Room> searchRoomsAcrossLocations(RoomStyle style, java.util.Date startDate, int duration) {
        List<Room> allAvailableRooms = new ArrayList<>();
        
        // Search through all locations
        for (HotelLocation hotelLocation : locations) {
            List<Room> roomsAtLocation = hotelLocation.searchAvailableRooms(style, startDate, duration);
            allAvailableRooms.addAll(roomsAtLocation);
        }
        
        return allAvailableRooms;
    }

    /**
     * Gets the total number of rooms across all locations.
     * 
     * @return Total count of rooms
     */
    public int getTotalRoomCount() {
        int count = 0;
        for (HotelLocation location : locations) {
            count += location.getRooms().size();
        }
        return count;
    }

    // Getter
    public String getName() {
        return name;
    }
}
