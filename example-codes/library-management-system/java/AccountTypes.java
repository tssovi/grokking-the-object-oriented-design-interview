import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Account Types for Library Management System
 * 
 * This file contains the account-related classes for the library system:
 * - Account: Abstract base class for all account types
 * - Librarian: Account type for library staff
 * - Member: Account type for library members who can borrow books
 * 
 * Note: For simplicity, we are not defining all getter and setter functions.
 * The reader can assume that all class attributes are private and accessed 
 * through their respective public getter methods and modified only through 
 * their public setter methods.
 */

/**
 * Abstract Account class representing a user account in the library system
 * This is the base class for all account types (Librarian, Member, etc.)
 */
abstract class Account {
    private String id;                  // Unique account identifier
    private String password;            // Account password (should be hashed in production)
    private AccountStatus status;       // Current status of the account
    private Person person;              // Person associated with this account

    /**
     * Constructor to create an Account object
     * 
     * @param id The unique identifier for this account
     * @param password The password for this account
     * @param person The person associated with this account
     * @param status The initial status of the account (default: ACTIVE)
     */
    public Account(String id, String password, Person person, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.person = person;
        this.status = status;
    }

    /**
     * Reset the password for this account
     * In a real implementation, this would involve verification and security measures
     */
    public void resetPassword() {
        // Implementation would handle password reset logic
        // Including verification, security questions, email confirmation, etc.
    }

    // Getters
    public String getId() { return id; }
    public AccountStatus getStatus() { return status; }
    public Person getPerson() { return person; }
    
    /**
     * Set the account status
     * 
     * @param status The new status to set
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

/**
 * Librarian class representing a library staff member
 * Librarians have administrative privileges to manage books and members
 */
class Librarian extends Account {
    
    /**
     * Constructor to create a Librarian account
     * 
     * @param id The unique identifier for this librarian
     * @param password The password for this account
     * @param person The person associated with this librarian account
     * @param status The initial status of the account (default: ACTIVE)
     */
    public Librarian(String id, String password, Person person, AccountStatus status) {
        super(id, password, person, status);
    }

    /**
     * Add a new book item to the library catalog
     * 
     * @param bookItem The book item to add to the library
     */
    public void addBookItem(BookItem bookItem) {
        // Implementation would add book to database/catalog
        System.out.println("Book item added: " + bookItem.getTitle());
    }

    /**
     * Block a member's account (e.g., for policy violations)
     * 
     * @param member The member to block
     */
    public void blockMember(Member member) {
        // Set member status to blacklisted
        member.setStatus(AccountStatus.BLACKLISTED);
        System.out.println("Member " + member.getId() + " has been blocked");
    }

    /**
     * Unblock a previously blocked member's account
     * 
     * @param member The member to unblock
     */
    public void unBlockMember(Member member) {
        // Restore member status to active
        member.setStatus(AccountStatus.ACTIVE);
        System.out.println("Member " + member.getId() + " has been unblocked");
    }
}

/**
 * Member class representing a library member who can borrow books
 * Members can checkout, reserve, renew, and return books
 */
class Member extends Account {
    private LocalDate dateOfMembership;     // Date when member joined
    private int totalBooksCheckedOut;       // Current number of books checked out

    /**
     * Constructor to create a Member account
     * 
     * @param id The unique identifier for this member
     * @param password The password for this account
     * @param person The person associated with this member account
     * @param status The initial status of the account (default: ACTIVE)
     */
    public Member(String id, String password, Person person, AccountStatus status) {
        super(id, password, person, status);
        this.dateOfMembership = LocalDate.now();
        this.totalBooksCheckedOut = 0;
    }

    /**
     * Get the total number of books currently checked out by this member
     * 
     * @return The number of books checked out
     */
    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    /**
     * Reserve a book item for future checkout
     * 
     * @param bookItem The book item to reserve
     */
    public void reserveBookItem(BookItem bookItem) {
        // Implementation would create a reservation record
        System.out.println("Book reserved: " + bookItem.getTitle());
    }

    /**
     * Increment the count of books checked out by this member
     */
    private void incrementTotalBooksCheckedOut() {
        this.totalBooksCheckedOut++;
    }

    /**
     * Decrement the count of books checked out by this member
     */
    private void decrementTotalBooksCheckedOut() {
        this.totalBooksCheckedOut--;
    }

    /**
     * Checkout a book item
     * This method checks various conditions before allowing checkout:
     * - Member hasn't exceeded maximum books limit
     * - Book isn't reserved by another member
     * - Book is available for checkout
     * 
     * @param bookItem The book item to checkout
     * @return true if checkout was successful, false otherwise
     */
    public boolean checkoutBookItem(BookItem bookItem) {
        // Check if member has reached maximum checkout limit
        if (getTotalBooksCheckedOut() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
            System.out.println("The user has already checked-out maximum number of books");
            return false;
        }

        // Check if book has a pending reservation
        BookReservation bookReservation = BookReservation.fetchReservationDetails(
            bookItem.getBarcode());
        
        if (bookReservation != null && !bookReservation.getMemberId().equals(getId())) {
            // Book item has a pending reservation from another user
            System.out.println("This book is reserved by another member");
            return false;
        } else if (bookReservation != null) {
            // Book item has a pending reservation from this member, complete it
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }

        // Attempt to checkout the book
        if (!bookItem.checkout(getId())) {
            return false;
        }

        // Increment the checkout counter
        incrementTotalBooksCheckedOut();
        return true;
    }

    /**
     * Check if there are any fines for an overdue book
     * If the book is overdue, calculate and collect the fine
     * 
     * @param bookItemBarcode The barcode of the book to check
     */
    private void checkForFine(String bookItemBarcode) {
        // Fetch the lending details for this book
        BookLending bookLending = BookLending.fetchLendingDetails(bookItemBarcode);
        
        if (bookLending == null) {
            return;
        }

        Date dueDate = bookLending.getDueDate();
        Date today = new Date();

        // Check if the book has been returned after the due date
        if (today.after(dueDate)) {
            // Calculate the number of days overdue
            long diffInMillis = today.getTime() - dueDate.getTime();
            int diffDays = (int) (diffInMillis / (1000 * 60 * 60 * 24));
            
            // Collect the fine
            Fine.collectFine(getId(), diffDays);
        }
    }

    /**
     * Return a book item to the library
     * This method:
     * - Checks for any fines
     * - Updates book status
     * - Notifies next member if book is reserved
     * 
     * @param bookItem The book item to return
     */
    public void returnBookItem(BookItem bookItem) {
        // Check if there are any fines for this book
        checkForFine(bookItem.getBarcode());

        // Check if there's a pending reservation for this book
        BookReservation bookReservation = BookReservation.fetchReservationDetails(
            bookItem.getBarcode());
        
        if (bookReservation != null) {
            // Book item has a pending reservation
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
        } else {
            // No reservation, mark as available
            bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
        }

        // Decrement the checkout counter
        decrementTotalBooksCheckedOut();
    }

    /**
     * Renew a book item to extend the lending period
     * This method:
     * - Checks for any fines
     * - Verifies no other member has reserved the book
     * - Extends the due date if renewal is allowed
     * 
     * @param bookItem The book item to renew
     * @return true if renewal was successful, false otherwise
     */
    public boolean renewBookItem(BookItem bookItem) {
        // Check if there are any fines for this book
        checkForFine(bookItem.getBarcode());

        // Check if there's a pending reservation for this book
        BookReservation bookReservation = BookReservation.fetchReservationDetails(
            bookItem.getBarcode());

        // Check if this book item has a pending reservation from another member
        if (bookReservation != null && !bookReservation.getMemberId().equals(getId())) {
            System.out.println("This book is reserved by another member");
            decrementTotalBooksCheckedOut();
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
            return false;
        } else if (bookReservation != null) {
            // Book item has a pending reservation from this member
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }

        // Create a new lending record with extended due date
        BookLending.lendBook(bookItem.getBarcode(), getId());
        
        // Calculate new due date (current date + MAX_LENDING_DAYS)
        LocalDate newDueDate = LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS);
        bookItem.updateDueDate(java.sql.Date.valueOf(newDueDate));
        
        return true;
    }

    // Getters
    public LocalDate getDateOfMembership() { return dateOfMembership; }
}
