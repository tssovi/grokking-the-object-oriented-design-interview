package models;

public class SMSNotification extends Notification {
    private String phoneNumber;

    public SMSNotification(String notificationId, String content, String phoneNumber) {
        super(notificationId, content);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean send(Person person) {
        // Implementation to send SMS notification
        System.out.println("Sending SMS to: " + person.getPhone());
        System.out.println("Content: " + getContent());
        return true;
    }

    // Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
