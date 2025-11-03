package models;

public class EmailNotification extends Notification {
    private String emailAddress;

    public EmailNotification(String notificationId, String content, String emailAddress) {
        super(notificationId, content);
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean send(Person person) {
        // Implementation to send email notification
        System.out.println("Sending email to: " + person.getEmail());
        System.out.println("Content: " + getContent());
        return true;
    }

    // Getters
    public String getEmailAddress() {
        return emailAddress;
    }

    // Setters
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
