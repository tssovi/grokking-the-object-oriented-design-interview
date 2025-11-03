/**
 * ===========================================================
 * Photo.java
 * -----------------------------------------------------------
 * Contains helper entities for attaching photos or bounty
 * to questions and answers.
 * ===========================================================
 */

import java.time.LocalDateTime;

public class PhotoEntities {

    /** Represents a photo uploaded by a member. */
    public static class Photo {
        private String photoId;
        private String photoPath;
        private LocalDateTime creationDate;
        private String creatingMember;

        public Photo(String id, String path, String member) {
            this.photoId = id;
            this.photoPath = path;
            this.creationDate = LocalDateTime.now();
            this.creatingMember = member;
        }

        public void delete() {
            System.out.println("Photo " + photoId + " deleted.");
        }
    }

    /** Represents a bounty offered on a question. */
    public static class Bounty {
        private int reputation;
        private LocalDateTime expiry;

        public Bounty(int reputation, LocalDateTime expiry) {
            this.reputation = reputation;
            this.expiry = expiry;
        }

        public void modifyReputation(int newRep) {
            this.reputation = newRep;
            System.out.println("Bounty reputation changed to " + newRep);
        }
    }
}
