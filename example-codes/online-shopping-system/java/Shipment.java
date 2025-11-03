import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Shipment and Notification classes for Online Shopping System
 * 
 * This file contains classes for managing shipments, shipment logs, and notifications.
 */

/**
 * ShipmentLog represents a log entry for tracking shipment status changes
 * Maintains history of shipment state transitions
 */
class ShipmentLog {
    private String shipmentNumber;
    private ShipmentStatus status;
    private Date creationDate;

    /**
     * Constructor to create a new shipment log entry
     * 
     * @param shipmentNumber The shipment number this log belongs to
     * @param status The status at the time of this log entry
     */
    public ShipmentLog(String shipmentNumber, ShipmentStatus status) {
        this.shipmentNumber = shipmentNumber;
        this.status = (status != null) ? status : ShipmentStatus.PENDING;
        this.creationDate = new Date();
    }

    // Getters
    public String getShipmentNumber() { return shipmentNumber; }
    public ShipmentStatus getStatus() { return status; }
    public Date getCreationDate() { return creationDate; }

    // Setters
    public void setShipmentNumber(String shipmentNumber) { this.shipmentNumber = shipmentNumber; }
    public void setStatus(ShipmentStatus status) { this.status = status; }
}

/**
 * Shipment represents a package being shipped to a customer
 * Contains shipment details, tracking information, and shipment history
 */
class Shipment {
    private String shipmentNumber;
    private Date shipmentDate;
    private Date estimatedArrival;
    private String shipmentMethod;  // e.g., "Standard", "Express", "Overnight"
    private List<ShipmentLog> shipmentLogs;

    /**
     * Constructor to create a new shipment
     * 
     * @param shipmentNumber Unique identifier for the shipment
     * @param shipmentMethod The shipping method (e.g., Standard, Express)
     */
    public Shipment(String shipmentNumber, String shipmentMethod) {
        this.shipmentNumber = shipmentNumber;
        this.shipmentDate = new Date();
        this.estimatedArrival = new Date();  // Should be calculated based on method
        this.shipmentMethod = shipmentMethod;
        this.shipmentLogs = new ArrayList<>();
        
        // Add initial log entry
        addShipmentLog(new ShipmentLog(shipmentNumber, ShipmentStatus.PENDING));
    }

    /**
     * Add a log entry to track shipment status changes
     * 
     * @param shipmentLog The log entry to add
     */
    public void addShipmentLog(ShipmentLog shipmentLog) {
        this.shipmentLogs.add(shipmentLog);
    }

    /**
     * Update the shipment status
     * 
     * @param status The new status
     */
    public void updateStatus(ShipmentStatus status) {
        addShipmentLog(new ShipmentLog(this.shipmentNumber, status));
    }

    /**
     * Get the current status of the shipment
     * 
     * @return The current shipment status
     */
    public ShipmentStatus getCurrentStatus() {
        if (shipmentLogs.isEmpty()) {
            return ShipmentStatus.PENDING;
        }
        return shipmentLogs.get(shipmentLogs.size() - 1).getStatus();
    }

    // Getters
    public String getShipmentNumber() { return shipmentNumber; }
    public Date getShipmentDate() { return shipmentDate; }
    public Date getEstimatedArrival() { return estimatedArrival; }
    public String getShipmentMethod() { return shipmentMethod; }
    public List<ShipmentLog> getShipmentLogs() { return shipmentLogs; }

    // Setters
    public void setShipmentNumber(String shipmentNumber) { this.shipmentNumber = shipmentNumber; }
    public void setShipmentDate(Date shipmentDate) { this.shipmentDate = shipmentDate; }
    public void setEstimatedArrival(Date estimatedArrival) { this.estimatedArrival = estimatedArrival; }
    public void setShipmentMethod(String shipmentMethod) { this.shipmentMethod = shipmentMethod; }
}

/**
 * Notification is an abstract base class for sending notifications to users
 * Can be extended for different notification types (Email, SMS, Push, etc.)
 */
abstract class Notification {
    private String notificationId;
    private Date createdOn;
    private String content;

    /**
     * Constructor to create a new notification
     * 
     * @param id Unique identifier for the notification
     * @param content The content/message of the notification
     */
    public Notification(String id, String content) {
        this.notificationId = id;
        this.createdOn = new Date();
        this.content = content;
    }

    /**
     * Send the notification to an account
     * Must be implemented by concrete notification types
     * 
     * @param account The account to send the notification to
     * @return true if notification was sent successfully
     */
    public abstract boolean sendNotification(Account account);

    // Getters
    public String getNotificationId() { return notificationId; }
    public Date getCreatedOn() { return createdOn; }
    public String getContent() { return content; }

    // Setters
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    public void setContent(String content) { this.content = content; }
}

/**
 * EmailNotification sends notifications via email
 */
class EmailNotification extends Notification {
    
    /**
     * Constructor to create a new email notification
     * 
     * @param id Unique identifier for the notification
     * @param content The email content
     */
    public EmailNotification(String id, String content) {
        super(id, content);
    }

    /**
     * Send the notification via email
     * 
     * @param account The account to send the email to
     * @return true if email was sent successfully
     */
    @Override
    public boolean sendNotification(Account account) {
        // Implementation would send email to account.getEmail()
        System.out.println("Sending email to: " + account.getEmail());
        System.out.println("Content: " + getContent());
        return true;
    }
}

/**
 * SMSNotification sends notifications via SMS
 */
class SMSNotification extends Notification {
    
    /**
     * Constructor to create a new SMS notification
     * 
     * @param id Unique identifier for the notification
     * @param content The SMS content
     */
    public SMSNotification(String id, String content) {
        super(id, content);
    }

    /**
     * Send the notification via SMS
     * 
     * @param account The account to send the SMS to
     * @return true if SMS was sent successfully
     */
    @Override
    public boolean sendNotification(Account account) {
        // Implementation would send SMS to account.getPhone()
        System.out.println("Sending SMS to: " + account.getPhone());
        System.out.println("Content: " + getContent());
        return true;
    }
}
