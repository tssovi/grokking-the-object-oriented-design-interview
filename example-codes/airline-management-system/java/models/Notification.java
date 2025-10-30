package models;

import java.time.LocalDateTime;

public abstract class Notification {
    private String notificationId;
    private LocalDateTime createdOn;
    private String content;

    public Notification(String notificationId, String content) {
        this.notificationId = notificationId;
        this.content = content;
        this.createdOn = LocalDateTime.now();
    }

    public abstract boolean send(Person person);

    // Getters
    public String getNotificationId() {
        return notificationId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getContent() {
        return content;
    }

    // Setters
    public void setContent(String content) {
        this.content = content;
    }
}
