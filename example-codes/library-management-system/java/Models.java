import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Models for Library Management System
 * 
 * This file contains the core domain models for the library system including:
 * - Book and BookItem classes
 * - Rack for physical book placement
 * - BookReservation for managing reservations
 * - BookLending for tracking borrowed books
 * - Fine for managing overdue fines
 */

/**
 * Abstract Book class representing the general information about a book
 * This contains metadata that is common across all copies of the same book
 */
abstract class Book {
    private String ISBN;              // International Standard Book Number
    private String title;             // Book title
    private String subject;           // Book subject/category
    private String publisher;         // Publisher name
    private String language;          // Language of the book
    private int numberOfPages;        // Total number of pages
    private List<String> authors;     // List of authors

    /**
     * Constructor to create a Book object
     * 
     * @param ISBN The International Standard Book Number
     * @param title The title of the book
     * @param subject The subject or category of the book
     * @param publisher The publisher of the book
     * @param language The language the book is written in
     * @param numberOfPages The total number of pages in the book
     */
    public Book(String ISBN, String title, String subject, String publisher, 
                String language, int numberOfPages) {
        this.ISBN = ISBN;
        this.title = title;
        this.subject = subject;
        this.publisher = publisher;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.authors = new ArrayList<>();
    }

    // Getters
    public String getISBN() { return ISBN; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getPublisher() { return publisher; }
    public String getLanguage() { return language; }
    public int getNumberOfPages() { return numberOfPages; }
    public List<String> getAuthors() { return authors; }
    
    /**
     * Add an author to the book's author list
     * 
     * @param author The author name to add
     */
    public void addAuthor(String author) {
        this.authors.add(author);
    }
}

/**
 * BookItem class representing a physical copy of a book
 * Each BookItem is a specific instance that can be borrowed, reserved, or placed on a rack
 */
class BookItem extends Book {
    private String barcode;                 // Unique barcode identifier for this copy
    private boolean isReferenceOnly;        // True if book cannot be borrowed (reference only)
    private Date borrowed;                  // Date when book was borrowed
    private Date dueDate;                   // Date when book is due to be returned
    private double price;                   // Price/value of the book
    private BookFormat format;              // Format of the book (hardcover, paperback, etc.)
    private BookStatus status;              // Current status (available, loaned, etc.)
    private Date dateOfPurchase;            // Date when library purchased this book
    private Date publicationDate;           // Date when book was published
    private Rack placedAt;                  // Rack where this book is placed

    /**
     * Constructor to create a BookItem object
     * 
     * @param ISBN The ISBN of the book
     * @param title The title of the book
     * @param subject The subject of the book
     * @param publisher The publisher of the book
     * @param language The language of the book
     * @param numberOfPages The number of pages
     * @param barcode The unique barcode for this copy
     * @param isReferenceOnly Whether this is a reference-only copy
     * @param borrowed The date borrowed (null if not borrowed)
     * @param dueDate The due date (null if not borrowed)
     * @param price The price of the book
     * @param format The format of the book
     * @param status The current status
     * @param dateOfPurchase The purchase date
     * @param publicationDate The publication date
     * @param placedAt The rack where book is placed
     */
    public BookItem(String ISBN, String title, String subject, String publisher,
                    String language, int numberOfPages, String barcode, 
                    boolean isReferenceOnly, Date borrowed, Date dueDate, 
                    double price, BookFormat format, BookStatus status,
                    Date dateOfPurchase, Date publicationDate, Rack placedAt) {
        super(ISBN, title, subject, publisher, language, numberOfPages);
        this.barcode = barcode;
        this.isReferenceOnly = isReferenceOnly;
        this.borrowed = borrowed;
        this.dueDate = dueDate;
        this.price = price;
        this.format = format;
        this.status = status;
        this.dateOfPurchase = dateOfPurchase;
        this.publicationDate = publicationDate;
        this.placedAt = placedAt;
    }

    /**
     * Attempt to checkout this book item to a member
     * 
     * @param memberId The ID of the member checking out the book
     * @return true if checkout was successful, false otherwise
     */
    public boolean checkout(String memberId) {
        // Reference-only books cannot be checked out
        if (isReferenceOnly) {
            System.out.println("This book is Reference only and can't be issued");
            return false;
        }
        
        // Attempt to create a lending record
        if (!BookLending.lendBook(this.barcode, memberId)) {
            return false;
        }
        
        // Update the book status to loaned
        updateBookItemStatus(BookStatus.LOANED);
        return true;
    }

    /**
     * Update the status of this book item
     * 
     * @param status The new status to set
     */
    public void updateBookItemStatus(BookStatus status) {
        this.status = status;
    }

    /**
     * Update the due date for this book item
     * 
     * @param dueDate The new due date
     */
    public void updateDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Getters
    public String getBarcode() { return barcode; }
    public boolean isReferenceOnly() { return isReferenceOnly; }
    public Date getBorrowed() { return borrowed; }
    public Date getDueDate() { return dueDate; }
    public double getPrice() { return price; }
    public BookFormat getFormat() { return format; }
    public BookStatus getStatus() { return status; }
    public Date getDateOfPurchase() { return dateOfPurchase; }
    public Date getPublicationDate() { return publicationDate; }
    public Rack getPlacedAt() { return placedAt; }
}

/**
 * Rack class representing a physical location where books are stored
 * Each rack has a number and location identifier for easy book retrieval
 */
class Rack {
    private int number;                     // Rack number
    private String locationIdentifier;      // Location identifier (e.g., "A1", "B2")

    /**
     * Constructor to create a Rack object
     * 
     * @param number The rack number
     * @param locationIdentifier The location identifier for the rack
     */
    public Rack(int number, String locationIdentifier) {
        this.number = number;
        this.locationIdentifier = locationIdentifier;
    }

    // Getters
    public int getNumber() { return number; }
    public String getLocationIdentifier() { return locationIdentifier; }
}

/**
 * BookReservation class representing a reservation made by a member for a book
 * Reservations allow members to hold a book when it becomes available
 */
class BookReservation {
    private Date creationDate;              // Date when reservation was created
    private ReservationStatus status;       // Current status of the reservation
    private String bookItemBarcode;         // Barcode of the reserved book
    private String memberId;                // ID of the member who made the reservation

    /**
     * Constructor to create a BookReservation object
     * 
     * @param creationDate The date the reservation was created
     * @param status The initial status of the reservation
     * @param bookItemBarcode The barcode of the book being reserved
     * @param memberId The ID of the member making the reservation
     */
    public BookReservation(Date creationDate, ReservationStatus status, 
                          String bookItemBarcode, String memberId) {
        this.creationDate = creationDate;
        this.status = status;
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
    }

    /**
     * Fetch reservation details for a specific book barcode
     * This would typically query a database
     * 
     * @param barcode The barcode to search for
     * @return BookReservation object if found, null otherwise
     */
    public static BookReservation fetchReservationDetails(String barcode) {
        // Implementation would query database
        // Placeholder for actual implementation
        return null;
    }

    /**
     * Update the status of this reservation
     * 
     * @param status The new reservation status
     */
    public void updateStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     * Send notification to member that reserved book is available
     */
    public void sendBookAvailableNotification() {
        // Implementation would send notification (email, SMS, etc.)
        System.out.println("Notification sent: Book is now available for member " + memberId);
    }

    // Getters
    public Date getCreationDate() { return creationDate; }
    public ReservationStatus getStatus() { return status; }
    public String getBookItemBarcode() { return bookItemBarcode; }
    public String getMemberId() { return memberId; }
}

/**
 * BookLending class representing an active loan of a book to a member
 * Tracks when the book was borrowed, when it's due, and when it was returned
 */
class BookLending {
    private Date creationDate;              // Date when book was lent
    private Date dueDate;                   // Date when book is due
    private Date returnDate;                // Date when book was returned (null if not returned)
    private String bookItemBarcode;         // Barcode of the lent book
    private String memberId;                // ID of the member who borrowed the book

    /**
     * Constructor to create a BookLending object
     * 
     * @param creationDate The date the book was lent
     * @param dueDate The date the book is due
     * @param bookItemBarcode The barcode of the book being lent
     * @param memberId The ID of the member borrowing the book
     */
    public BookLending(Date creationDate, Date dueDate, 
                      String bookItemBarcode, String memberId) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
    }

    /**
     * Create a new lending record for a book
     * This would typically create a record in the database
     * 
     * @param barcode The barcode of the book to lend
     * @param memberId The ID of the member borrowing the book
     * @return true if lending was successful, false otherwise
     */
    public static boolean lendBook(String barcode, String memberId) {
        // Implementation would create lending record in database
        // Placeholder for actual implementation
        return true;
    }

    /**
     * Fetch lending details for a specific book barcode
     * This would typically query a database
     * 
     * @param barcode The barcode to search for
     * @return BookLending object if found, null otherwise
     */
    public static BookLending fetchLendingDetails(String barcode) {
        // Implementation would query database
        // Placeholder for actual implementation
        return null;
    }

    // Getters
    public Date getCreationDate() { return creationDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public String getBookItemBarcode() { return bookItemBarcode; }
    public String getMemberId() { return memberId; }
    
    // Setter for return date
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

/**
 * Fine class representing a fine charged to a member for overdue books
 * Calculates and collects fines based on number of days overdue
 */
class Fine {
    private Date creationDate;              // Date when fine was created
    private String bookItemBarcode;         // Barcode of the overdue book
    private String memberId;                // ID of the member being fined
    private double amount;                  // Amount of the fine

    /**
     * Constructor to create a Fine object
     * 
     * @param creationDate The date the fine was created
     * @param bookItemBarcode The barcode of the overdue book
     * @param memberId The ID of the member being fined
     */
    public Fine(Date creationDate, String bookItemBarcode, String memberId) {
        this.creationDate = creationDate;
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
        this.amount = 0.0;
    }

    /**
     * Calculate and collect fine for overdue book
     * Fine amount is typically based on number of days overdue
     * 
     * @param memberId The ID of the member to fine
     * @param days The number of days the book is overdue
     */
    public static void collectFine(String memberId, int days) {
        // Implementation would calculate fine (e.g., $1 per day)
        // and update member's account
        double fineAmount = days * 1.0; // $1 per day
        System.out.println("Fine of $" + fineAmount + " collected from member " + memberId);
    }

    // Getters
    public Date getCreationDate() { return creationDate; }
    public String getBookItemBarcode() { return bookItemBarcode; }
    public String getMemberId() { return memberId; }
    public double getAmount() { return amount; }
    
    // Setter for amount
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
