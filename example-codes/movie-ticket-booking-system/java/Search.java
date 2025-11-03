package movie.ticket.booking.system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search.java
 * 
 * This file contains classes related to searching for movies in the system.
 * It includes the Search interface (defining search operations) and the
 * Catalog class (implementing the search functionality with various indexes).
 */

/**
 * Interface defining search operations for movies.
 * Provides multiple search criteria including title, language, genre, release date, and city.
 */
interface Search {
    
    /**
     * Searches for movies by title.
     * 
     * @param title The movie title to search for
     * @return List of movies matching the title
     */
    List<Movie> searchByTitle(String title);
    
    /**
     * Searches for movies by language.
     * 
     * @param language The language to filter by (e.g., "English", "Spanish")
     * @return List of movies in the specified language
     */
    List<Movie> searchByLanguage(String language);
    
    /**
     * Searches for movies by genre.
     * 
     * @param genre The genre to filter by (e.g., "Action", "Comedy", "Drama")
     * @return List of movies in the specified genre
     */
    List<Movie> searchByGenre(String genre);
    
    /**
     * Searches for movies by release date.
     * 
     * @param releaseDate The release date to filter by
     * @return List of movies released on the specified date
     */
    List<Movie> searchByReleaseDate(LocalDate releaseDate);
    
    /**
     * Searches for movies currently showing in a specific city.
     * 
     * @param cityName The name of the city
     * @return List of movies showing in the specified city
     */
    List<Movie> searchByCity(String cityName);
}

/**
 * Class implementing the Search interface to provide movie catalog search functionality.
 * Uses multiple hash maps to index movies by different attributes for efficient searching.
 * This is an example of the Strategy pattern for search operations.
 */
class Catalog implements Search {
    // Hash maps to store movies indexed by different attributes for fast lookup
    private Map<String, List<Movie>> movieTitles;
    private Map<String, List<Movie>> movieLanguages;
    private Map<String, List<Movie>> movieGenres;
    private Map<LocalDate, List<Movie>> movieReleaseDates;
    private Map<String, List<Movie>> movieCities;

    /**
     * Constructor to initialize the Catalog with empty indexes.
     * In a real implementation, these would be populated from a database.
     */
    public Catalog() {
        this.movieTitles = new HashMap<>();
        this.movieLanguages = new HashMap<>();
        this.movieGenres = new HashMap<>();
        this.movieReleaseDates = new HashMap<>();
        this.movieCities = new HashMap<>();
    }

    /**
     * Adds a movie to all relevant indexes in the catalog.
     * This method should be called whenever a new movie is added to the system.
     * 
     * @param movie The movie to be added to the catalog
     */
    public void addMovie(Movie movie) {
        if (movie == null) {
            return;
        }

        // Index by title
        movieTitles.computeIfAbsent(movie.getTitle().toLowerCase(), k -> new ArrayList<>()).add(movie);
        
        // Index by language
        movieLanguages.computeIfAbsent(movie.getLanguage().toLowerCase(), k -> new ArrayList<>()).add(movie);
        
        // Index by genre
        movieGenres.computeIfAbsent(movie.getGenre().toLowerCase(), k -> new ArrayList<>()).add(movie);
        
        // Index by release date
        movieReleaseDates.computeIfAbsent(movie.getReleaseDate(), k -> new ArrayList<>()).add(movie);
    }

    /**
     * Adds a movie to the city index.
     * Should be called when a movie is scheduled to show in a specific city.
     * 
     * @param movie The movie being shown
     * @param cityName The city where the movie is showing
     */
    public void addMovieToCity(Movie movie, String cityName) {
        if (movie == null || cityName == null) {
            return;
        }
        movieCities.computeIfAbsent(cityName.toLowerCase(), k -> new ArrayList<>()).add(movie);
    }

    /**
     * Searches for movies by title.
     * Search is case-insensitive and returns exact matches.
     * 
     * @param title The movie title to search for
     * @return List of movies matching the title, or empty list if none found
     */
    @Override
    public List<Movie> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return movieTitles.getOrDefault(title.toLowerCase(), new ArrayList<>());
    }

    /**
     * Searches for movies by language.
     * Returns all movies available in the specified language.
     * 
     * @param language The language to filter by
     * @return List of movies in the specified language, or empty list if none found
     */
    @Override
    public List<Movie> searchByLanguage(String language) {
        if (language == null || language.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return movieLanguages.getOrDefault(language.toLowerCase(), new ArrayList<>());
    }

    /**
     * Searches for movies by genre.
     * Returns all movies in the specified genre.
     * 
     * @param genre The genre to filter by
     * @return List of movies in the specified genre, or empty list if none found
     */
    @Override
    public List<Movie> searchByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return movieGenres.getOrDefault(genre.toLowerCase(), new ArrayList<>());
    }

    /**
     * Searches for movies by release date.
     * Returns all movies released on the specified date.
     * 
     * @param releaseDate The release date to filter by
     * @return List of movies released on the specified date, or empty list if none found
     */
    @Override
    public List<Movie> searchByReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null) {
            return new ArrayList<>();
        }
        return movieReleaseDates.getOrDefault(releaseDate, new ArrayList<>());
    }

    /**
     * Searches for movies currently showing in a specific city.
     * Returns all movies that have shows scheduled in the specified city.
     * 
     * @param cityName The name of the city
     * @return List of movies showing in the specified city, or empty list if none found
     */
    @Override
    public List<Movie> searchByCity(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return movieCities.getOrDefault(cityName.toLowerCase(), new ArrayList<>());
    }

    /**
     * Removes a movie from all indexes in the catalog.
     * Should be called when a movie is removed from the system.
     * 
     * @param movie The movie to be removed
     */
    public void removeMovie(Movie movie) {
        if (movie == null) {
            return;
        }

        // Remove from all indexes
        removeFromIndex(movieTitles, movie.getTitle().toLowerCase(), movie);
        removeFromIndex(movieLanguages, movie.getLanguage().toLowerCase(), movie);
        removeFromIndex(movieGenres, movie.getGenre().toLowerCase(), movie);
        removeFromIndex(movieReleaseDates, movie.getReleaseDate(), movie);
    }

    /**
     * Helper method to remove a movie from a specific index.
     * 
     * @param index The index map to remove from
     * @param key The key in the index
     * @param movie The movie to remove
     */
    private <K> void removeFromIndex(Map<K, List<Movie>> index, K key, Movie movie) {
        List<Movie> movies = index.get(key);
        if (movies != null) {
            movies.remove(movie);
            if (movies.isEmpty()) {
                index.remove(key);
            }
        }
    }
}
