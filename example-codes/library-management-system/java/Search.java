import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search functionality for Library Management System
 * 
 * This file contains the search-related classes:
 * - Search: Abstract interface defining search operations
 * - Catalog: Implementation of search functionality for the library catalog
 * 
 * The catalog allows searching for books by various criteria:
 * - Title
 * - Author
 * - Subject
 * - Publication Date
 */

/**
 * Abstract Search interface defining the contract for search operations
 * All search implementations must provide these search methods
 */
abstract class Search {
    
    /**
     * Search for books by title
     * 
     * @param title The title or partial title to search for
     * @return List of BookItem objects matching the search criteria
     */
    public abstract List<BookItem> searchByTitle(String title);

    /**
     * Search for books by author name
     * 
     * @param author The author name or partial name to search for
     * @return List of BookItem objects matching the search criteria
     */
    public abstract List<BookItem> searchByAuthor(String author);

    /**
     * Search for books by subject/category
     * 
     * @param subject The subject or category to search for
     * @return List of BookItem objects matching the search criteria
     */
    public abstract List<BookItem> searchBySubject(String subject);

    /**
     * Search for books by publication date
     * 
     * @param publishDate The publication date to search for
     * @return List of BookItem objects matching the search criteria
     */
    public abstract List<BookItem> searchByPubDate(Date publishDate);
}

/**
 * Catalog class implementing the search functionality
 * Maintains indexed collections of books for efficient searching
 * 
 * In a production system, these would typically be database indexes
 * or use a search engine like Elasticsearch for better performance
 */
class Catalog extends Search {
    // Maps to store indexed book information for fast lookup
    private Map<String, List<BookItem>> bookTitles;          // Index by title
    private Map<String, List<BookItem>> bookAuthors;         // Index by author
    private Map<String, List<BookItem>> bookSubjects;        // Index by subject
    private Map<Date, List<BookItem>> bookPublicationDates;  // Index by publication date

    /**
     * Constructor to create a Catalog object
     * Initializes all the index maps
     */
    public Catalog() {
        this.bookTitles = new HashMap<>();
        this.bookAuthors = new HashMap<>();
        this.bookSubjects = new HashMap<>();
        this.bookPublicationDates = new HashMap<>();
    }

    /**
     * Search for books by title
     * Returns all books that match the given title query
     * 
     * @param query The title or partial title to search for
     * @return List of BookItem objects with matching titles, or null if none found
     */
    @Override
    public List<BookItem> searchByTitle(String query) {
        // Return all books containing the string query in their title
        // In a real implementation, this might use fuzzy matching or full-text search
        return bookTitles.get(query);
    }

    /**
     * Search for books by author name
     * Returns all books written by authors matching the query
     * 
     * @param query The author name or partial name to search for
     * @return List of BookItem objects by matching authors, or null if none found
     */
    @Override
    public List<BookItem> searchByAuthor(String query) {
        // Return all books containing the string query in their author's name
        // In a real implementation, this might search across multiple authors per book
        return bookAuthors.get(query);
    }

    /**
     * Search for books by subject/category
     * Returns all books in the specified subject area
     * 
     * @param query The subject or category to search for
     * @return List of BookItem objects in the matching subject, or null if none found
     */
    @Override
    public List<BookItem> searchBySubject(String query) {
        // Return all books in the specified subject category
        return bookSubjects.get(query);
    }

    /**
     * Search for books by publication date
     * Returns all books published on the specified date
     * 
     * @param publishDate The publication date to search for
     * @return List of BookItem objects published on that date, or null if none found
     */
    @Override
    public List<BookItem> searchByPubDate(Date publishDate) {
        // Return all books published on the specified date
        // In a real implementation, this might support date ranges
        return bookPublicationDates.get(publishDate);
    }

    /**
     * Add a book item to the catalog indexes
     * This method should be called whenever a new book is added to the library
     * 
     * @param bookItem The book item to add to the catalog
     */
    public void addBookToCatalog(BookItem bookItem) {
        // Add to title index
        String title = bookItem.getTitle();
        bookTitles.computeIfAbsent(title, k -> new java.util.ArrayList<>()).add(bookItem);

        // Add to author index (for each author)
        for (String author : bookItem.getAuthors()) {
            bookAuthors.computeIfAbsent(author, k -> new java.util.ArrayList<>()).add(bookItem);
        }

        // Add to subject index
        String subject = bookItem.getSubject();
        bookSubjects.computeIfAbsent(subject, k -> new java.util.ArrayList<>()).add(bookItem);

        // Add to publication date index
        Date pubDate = bookItem.getPublicationDate();
        bookPublicationDates.computeIfAbsent(pubDate, k -> new java.util.ArrayList<>()).add(bookItem);
    }

    /**
     * Remove a book item from the catalog indexes
     * This method should be called when a book is removed from the library
     * 
     * @param bookItem The book item to remove from the catalog
     */
    public void removeBookFromCatalog(BookItem bookItem) {
        // Remove from title index
        String title = bookItem.getTitle();
        if (bookTitles.containsKey(title)) {
            bookTitles.get(title).remove(bookItem);
        }

        // Remove from author index
        for (String author : bookItem.getAuthors()) {
            if (bookAuthors.containsKey(author)) {
                bookAuthors.get(author).remove(bookItem);
            }
        }

        // Remove from subject index
        String subject = bookItem.getSubject();
        if (bookSubjects.containsKey(subject)) {
            bookSubjects.get(subject).remove(bookItem);
        }

        // Remove from publication date index
        Date pubDate = bookItem.getPublicationDate();
        if (bookPublicationDates.containsKey(pubDate)) {
            bookPublicationDates.get(pubDate).remove(bookItem);
        }
    }
}
