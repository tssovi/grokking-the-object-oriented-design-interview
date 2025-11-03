package movie.ticket.booking.system;

import java.util.List;

/**
 * Cinema.java
 * 
 * This file contains classes related to cinema infrastructure including
 * City, Cinema, CinemaHall, and CinemaHallSeat. These classes represent
 * the physical structure and organization of movie theaters.
 */

/**
 * Class representing a city where cinemas are located.
 * Used for location-based movie searches and cinema organization.
 */
class City {
    private String name;
    private String state;
    private String zipCode;

    /**
     * Constructor to create a City instance.
     * 
     * @param name Name of the city
     * @param state State or province where the city is located
     * @param zipCode Primary ZIP/postal code for the city
     */
    public City(String name, String state, String zipCode) {
        this.name = name;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

/**
 * Class representing a cinema/movie theater complex.
 * A cinema can have multiple cinema halls, each showing different movies.
 */
class Cinema {
    private String name;
    private int totalCinemaHalls;
    private Address location;
    private List<CinemaHall> halls;

    /**
     * Constructor to create a Cinema instance.
     * 
     * @param name Name of the cinema (e.g., "AMC Downtown", "Cineplex Mall")
     * @param totalCinemaHalls Total number of halls in this cinema
     * @param location Physical address of the cinema
     * @param halls List of cinema halls in this complex
     */
    public Cinema(String name, int totalCinemaHalls, Address location, List<CinemaHall> halls) {
        this.name = name;
        this.totalCinemaHalls = totalCinemaHalls;
        this.location = location;
        this.halls = halls;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getTotalCinemaHalls() {
        return totalCinemaHalls;
    }

    public Address getLocation() {
        return location;
    }

    public List<CinemaHall> getHalls() {
        return halls;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTotalCinemaHalls(int totalCinemaHalls) {
        this.totalCinemaHalls = totalCinemaHalls;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public void setHalls(List<CinemaHall> halls) {
        this.halls = halls;
    }
}

/**
 * Class representing a single cinema hall/screen within a cinema complex.
 * Each hall has a specific seating arrangement and shows multiple movies throughout the day.
 */
class CinemaHall {
    private String name;
    private int totalSeats;
    private List<CinemaHallSeat> seats;
    private List<Show> shows;

    /**
     * Constructor to create a CinemaHall instance.
     * 
     * @param name Name or identifier of the hall (e.g., "Hall 1", "IMAX Screen")
     * @param totalSeats Total seating capacity of the hall
     * @param seats List of all seats in the hall with their configurations
     * @param shows List of shows scheduled in this hall
     */
    public CinemaHall(String name, int totalSeats, List<CinemaHallSeat> seats, List<Show> shows) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.seats = seats;
        this.shows = shows;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public List<CinemaHallSeat> getSeats() {
        return seats;
    }

    public List<Show> getShows() {
        return shows;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setSeats(List<CinemaHallSeat> seats) {
        this.seats = seats;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}

/**
 * Class representing a physical seat in a cinema hall.
 * Each seat has a unique identifier and a type (regular, premium, accessible, etc.).
 */
class CinemaHallSeat {
    private String hallSeatId;
    private SeatType seatType;

    /**
     * Constructor to create a CinemaHallSeat instance.
     * 
     * @param hallSeatId Unique identifier for the seat (e.g., "A1", "B5", "C12")
     * @param seatType Type of seat (REGULAR, PREMIUM, ACCESSIBLE, etc.)
     */
    public CinemaHallSeat(String hallSeatId, SeatType seatType) {
        this.hallSeatId = hallSeatId;
        this.seatType = seatType;
    }

    // Getters
    public String getHallSeatId() {
        return hallSeatId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    // Setters
    public void setHallSeatId(String hallSeatId) {
        this.hallSeatId = hallSeatId;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
