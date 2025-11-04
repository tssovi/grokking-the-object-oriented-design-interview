import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RoomBooking.java
 * 
 * This file contains classes related to room bookings and charges in the
 * Hotel Management System. It includes RoomBooking, RoomCharge (abstract),
 * and specific charge types like Amenity, RoomService, and KitchenService.
 */

/**
 * Class representing a room booking/reservation.
 * Contains all booking details including dates, guest information, and status.
 */
class RoomBooking {
    private String reservationNumber;
    private Date startDate;
    private int durationInDays;
    private BookingStatus status;
    private Date checkInTime;
    private Date checkOutTime;
    
    private int guestId;
    private Room room;
    private Invoice invoice;
    private List<Notification> notifications;

    /**
     * Constructor to create a RoomBooking object.
     * 
     * @param reservationNumber Unique identifier for this reservation
     * @param startDate The check-in date
     * @param durationInDays Number of days for the stay
     * @param bookingStatus Initial status of the booking
     */
    public RoomBooking(String reservationNumber, Date startDate, 
                       int durationInDays, BookingStatus bookingStatus) {
        this.reservationNumber = reservationNumber;
        this.startDate = startDate;
        this.durationInDays = durationInDays;
        this.status = bookingStatus;
        this.checkInTime = null;
        this.checkOutTime = null;
        this.guestId = 0;
        this.room = null;
        this.invoice = null;
        this.notifications = new ArrayList<>();
    }

    /**
     * Fetches complete booking details from the system.
     * 
     * @param reservationNumber The reservation number to look up
     * @return RoomBooking object with complete details, or null if not found
     */
    public static RoomBooking fetchDetails(String reservationNumber) {
        // TODO: Implement logic to retrieve booking details from database
        // Should query the booking system using the reservation number
        return null;
    }

    /**
     * Adds a notification to this booking.
     * 
     * @param notification The notification to add
     */
    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

    // Getters and Setters
    public String getReservationNumber() {
        return reservationNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}

/**
 * Abstract base class for all types of room charges.
 * Specific charge types (Amenity, RoomService, KitchenService) extend this class.
 */
abstract class RoomCharge {
    private Date issueAt;

    /**
     * Constructor for RoomCharge.
     * Sets the issue date to the current date/time.
     */
    public RoomCharge() {
        this.issueAt = new Date();
    }

    /**
     * Adds this charge as an item to an invoice.
     * 
     * @param invoice The invoice to add this charge to
     * @return true if charge was added successfully, false otherwise
     */
    public boolean addInvoiceItem(Invoice invoice) {
        // TODO: Implement logic to add this charge to the invoice
        // Should calculate the charge amount and add it to the invoice
        return false;
    }

    // Getter
    public Date getIssueAt() {
        return issueAt;
    }
}

/**
 * Class representing a charge for hotel amenities.
 * Examples: gym access, spa services, pool access, etc.
 */
class Amenity extends RoomCharge {
    private String name;
    private String description;

    /**
     * Constructor to create an Amenity charge.
     * 
     * @param name Name of the amenity (e.g., "Gym Access", "Spa Treatment")
     * @param description Detailed description of the amenity
     */
    public Amenity(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

/**
 * Class representing a room service charge.
 * Examples: extra towels, pillow requests, wake-up calls, etc.
 */
class RoomService extends RoomCharge {
    private boolean isChargeable;
    private Date requestTime;

    /**
     * Constructor to create a RoomService charge.
     * 
     * @param isChargeable Whether this service incurs a charge
     * @param requestTime Time when the service was requested
     */
    public RoomService(boolean isChargeable, Date requestTime) {
        super();
        this.isChargeable = isChargeable;
        this.requestTime = requestTime;
    }

    // Getters
    public boolean isChargeable() {
        return isChargeable;
    }

    public Date getRequestTime() {
        return requestTime;
    }
}

/**
 * Class representing a kitchen/food service charge.
 * Examples: room service meals, minibar items, in-room dining, etc.
 */
class KitchenService extends RoomCharge {
    private String description;

    /**
     * Constructor to create a KitchenService charge.
     * 
     * @param description Description of the food/beverage service
     */
    public KitchenService(String description) {
        super();
        this.description = description;
    }

    // Getter
    public String getDescription() {
        return description;
    }
}

/**
 * Class representing an invoice for a booking.
 * Tracks all charges and payment status.
 */
class Invoice {
    private String invoiceId;
    private double totalAmount;
    private List<RoomCharge> charges;
    private PaymentStatus paymentStatus;

    /**
     * Constructor to create an Invoice.
     * 
     * @param invoiceId Unique identifier for the invoice
     */
    public Invoice(String invoiceId) {
        this.invoiceId = invoiceId;
        this.totalAmount = 0.0;
        this.charges = new ArrayList<>();
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    /**
     * Adds a charge to this invoice.
     * 
     * @param charge The charge to add
     * @param amount The amount of the charge
     */
    public void addCharge(RoomCharge charge, double amount) {
        this.charges.add(charge);
        this.totalAmount += amount;
    }

    // Getters and Setters
    public String getInvoiceId() {
        return invoiceId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<RoomCharge> getCharges() {
        return charges;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

/**
 * Class representing a notification sent to a guest.
 * Examples: booking confirmation, check-in reminder, payment receipt, etc.
 */
class Notification {
    private String notificationId;
    private String message;
    private Date sentAt;

    /**
     * Constructor to create a Notification.
     * 
     * @param notificationId Unique identifier for the notification
     * @param message The notification message content
     */
    public Notification(String notificationId, String message) {
        this.notificationId = notificationId;
        this.message = message;
        this.sentAt = new Date();
    }

    // Getters
    public String getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public Date getSentAt() {
        return sentAt;
    }
}
