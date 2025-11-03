package movie.ticket.booking.system;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Show.java
 * 
 * This file contains classes related to movies and their showings.
 * It includes the Movie class (representing a film) and the Show class
 * (representing a specific screening of a movie at a particular time and place).
 */

/**
 * Class representing a movie/film in the system.
 * Contains all metadata about the movie including title, description, duration,
 * language, release date, and associated shows.
 */
class Movie {
    private String title;
    private String description;
    private int durationInMins;
    private String language;
    private LocalDate releaseDate;
    private String country;
    private String genre;
    private Admin movieAddedBy;
    private List<Show> shows;

    /**
     * Constructor to create a Movie instance.
     * 
     * @param title Title of the movie
     * @param description Brief synopsis or description of the movie
     * @param durationInMins Runtime of the movie in minutes
     * @param language Primary language of the movie
     * @param releaseDate Date when the movie was released
     * @param country Country of origin
     * @param genre Genre of the movie (e.g., "Action", "Comedy", "Drama")
     * @param movieAddedBy Admin who added this movie to the system
     */
    public Movie(String title, String description, int durationInMins, String language, 
                 LocalDate releaseDate, String country, String genre, Admin movieAddedBy) {
        this.title = title;
        this.description = description;
        this.durationInMins = durationInMins;
        this.language = language;
        this.releaseDate = releaseDate;
        this.country = country;
        this.genre = genre;
        this.movieAddedBy = movieAddedBy;
        this.shows = new ArrayList<>();
    }

    /**
     * Retrieves all shows scheduled for this movie.
     * 
     * @return List of all shows for this movie
     */
    public List<Show> getShows() {
        return shows;
    }

    /**
     * Adds a new show for this movie.
     * 
     * @param show The show to be added
     */
    public void addShow(Show show) {
        if (show != null) {
            this.shows.add(show);
        }
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    public String getLanguage() {
        return language;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public Admin getMovieAddedBy() {
        return movieAddedBy;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}

/**
 * Class representing a specific showing/screening of a movie.
 * A show is a movie played at a specific cinema hall at a specific time.
 */
class Show {
    private String showId;
    private LocalDate createdOn;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private CinemaHall playedAt;
    private Movie movie;

    /**
     * Constructor to create a Show instance.
     * 
     * @param showId Unique identifier for the show
     * @param playedAt The cinema hall where this show will be screened
     * @param movie The movie being shown
     * @param startTime Date and time when the show starts
     * @param endTime Date and time when the show ends
     */
    public Show(String showId, CinemaHall playedAt, Movie movie, 
                LocalDateTime startTime, LocalDateTime endTime) {
        this.showId = showId;
        this.createdOn = LocalDate.now();
        this.startTime = startTime;
        this.endTime = endTime;
        this.playedAt = playedAt;
        this.movie = movie;
    }

    // Getters
    public String getShowId() {
        return showId;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public CinemaHall getPlayedAt() {
        return playedAt;
    }

    public Movie getMovie() {
        return movie;
    }

    // Setters
    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setPlayedAt(CinemaHall playedAt) {
        this.playedAt = playedAt;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
