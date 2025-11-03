package movie.ticket.booking.system;

import java.time.LocalDate;
import java.util.List;

/**
 * Booking.java
 * 
 * This file contains classes related to the booking process including
 * Booking (main booking class), ShowSeat (seat for a specific show),
 * and Payment (payment transaction details).
 */

/**
 * Class representing a booking made by a customer.
 * A booking contains information about the show, seats, payment, and booking status.
 */
class Booking {
    private String bookingNumber;
    private int numberOfSeats;
    private LocalDate createdOn;
    private BookingStatus status;
    private Show show;
    private List<ShowSeat> seats;
    private Payment payment;

    /**
     * Constructor to create a Booking instance.
     * 
     * @param bookingNumber Unique identifier for the booking
     * @param numberOfSeats Number of seats booked
     * @param status Current status of the booking
     * @param show The show for which seats are booked
     * @param seats List of specific seats booked for the show
     * @param payment Payment information for this booking
     */
    public Booking(String bookingNumber, int numberOfSeats, BookingStatus status, 
                   Show show, List<ShowSeat> seats, Payment payment) {
        this.bookingNumber = bookingNumber;
        this.numberOfSeats = numberOfSeats;
        this.createdOn = LocalDate.now();
        this.status = status;
        this.show = show;
        this.seats = seats;
        this.payment = payment;
    }

    /**
     * Processes payment for this booking.
     * Updates the payment status and booking status accordingly.
     * 
     * @param payment Payment object containing payment details
     * @return true if payment was successful, false otherwise
     */
    public boolean makePayment(Payment payment) {
        // Implementation would process payment through payment gateway
        // and update booking status to CONFIRMED upon successful payment
        if (payment != null && payment.getStatus() == PaymentStatus.COMPLETED) {
            this.payment = payment;
            this.status = BookingStatus.CONFIRMED;
            return true;
        }
        return false;
    }

    /**
     * Cancels this booking.
     * Updates the booking status to CANCELED and releases the reserved seats.
     * May also process refund depending on cancellation policy.
     * 
     * @return true if cancellation was successful, false otherwise
     */
    public boolean cancel() {
        // Implementation would:
        // 1. Update booking status to CANCELED
        // 2. Release reserved seats
        // 3. Process refund if applicable
        if (this.status == BookingStatus.CONFIRMED || this.status == BookingStatus.PENDING) {
            this.status = BookingStatus.CANCELED;
            
            // Release all seats
            if (seats != null) {
                for (ShowSeat seat : seats) {
                    seat.setReserved(false);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Assigns specific seats to this booking.
     * 
     * @param seats List of seats to be assigned
     * @return true if seats were successfully assigned, false otherwise
     */
    public boolean assignSeats(List<ShowSeat> seats) {
        // Implementation would validate seat availability and assign them
        if (seats != null && seats.size() == this.numberOfSeats) {
            this.seats = seats;
            // Mark seats as reserved
            for (ShowSeat seat : seats) {
                seat.setReserved(true);
            }
            return true;
        }
        return false;
    }

    // Getters
    public String getBookingNumber() {
        return bookingNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public Payment getPayment() {
        return payment;
    }

    // Setters
    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

/**
 * Class representing a seat for a specific show.
 * Extends CinemaHallSeat to add show-specific properties like pricing and reservation status.
 */
class ShowSeat extends CinemaHallSeat {
    private String showSeatId;
    private boolean isReserved;
    private double price;

    /**
     * Constructor to create a ShowSeat instance.
     * 
     * @param showSeatId Unique identifier for this seat in the context of a show
     * @param hallSeatId The underlying cinema hall seat identifier
     * @param seatType Type of seat (REGULAR, PREMIUM, etc.)
     * @param isReserved Whether the seat is currently reserved
     * @param price Price of the seat for this specific show
     */
    public ShowSeat(String showSeatId, String hallSeatId, SeatType seatType, 
                    boolean isReserved, double price) {
        super(hallSeatId, seatType);
        this.showSeatId = showSeatId;
        this.isReserved = isReserved;
        this.price = price;
    }

    // Getters
    public String getShowSeatId() {
        return showSeatId;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setShowSeatId(String showSeatId) {
        this.showSeatId = showSeatId;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * Class representing a payment transaction for a booking.
 * Contains payment amount, transaction details, and payment status.
 */
class Payment {
    private double amount;
    private LocalDate createdOn;
    private String transactionId;
    private PaymentStatus status;

    /**
     * Constructor to create a Payment instance.
     * 
     * @param amount Total amount to be paid
     * @param transactionId Unique transaction identifier from payment gateway
     * @param status Current status of the payment
     */
    public Payment(double amount, String transactionId, PaymentStatus status) {
        this.amount = amount;
        this.createdOn = LocalDate.now();
        this.transactionId = transactionId;
        this.status = status;
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    // Setters
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
