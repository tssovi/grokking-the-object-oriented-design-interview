/**
 * ===========================================================
 * Badge.java
 * -----------------------------------------------------------
 * Defines lightweight data models for:
 * - Badge
 * - Tag
 * - Notification
 * 
 * These are mostly informational or helper entities.
 * ===========================================================
 */

import java.time.LocalDateTime;

public class BadgeSystem {

    /** Represents a recognition badge earned by a member. */
    public static class Badge {
        private String name;
        private String description;

        public Badge(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
    }

    /** Represents a tag assigned to a question for categorization. */
    public static class Tag {
        private String name;
        private String description;
        private int dailyAskedFrequency;
        private int weeklyAskedFrequency;

        public Tag(String name, String description) {
            this.name = name;
            this.description = description;
            this.dailyAskedFrequency = 0;
            this.weeklyAskedFrequency = 0;
        }

        public void incrementDailyFrequency() {
            dailyAskedFrequency++;
        }

        public void incrementWeeklyFrequency() {
            weeklyAskedFrequency++;
        }
    }

    /** Represents a notification sent to users. */
    public static class Notification {
        private String notificationId;
        private LocalDateTime createdOn;
        private String content;

        public Notification(String id, String content) {
            this.notificationId = id;
            this.content = content;
            this.createdOn = LocalDateTime.now();
        }

        public void sendNotification() {
            // Placeholder for actual message sending
            System.out.println("Notification: " + content);
        }
    }
}
