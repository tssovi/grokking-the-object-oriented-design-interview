/**
 * ===========================================================
 * AccountTypes.java
 * -----------------------------------------------------------
 * Defines core user-related classes including:
 * - Account
 * - Member
 * - Admin
 * - Moderator
 * 
 * These represent users of different privilege levels.
 * ===========================================================
 */

import java.util.ArrayList;
import java.util.List;
import Constants.AccountStatus;

public class AccountTypes {

    /**
     * Represents a basic user account. Holds authentication
     * and personal data, along with the account's reputation.
     */
    public static class Account {
        private String id;
        private String password;
        private String name;
        private String address;
        private String email;
        private String phone;
        private AccountStatus status;
        private int reputation;

        public Account(String id, String password, String name,
                       String address, String email, String phone,
                       AccountStatus status) {
            this.id = id;
            this.password = password;
            this.name = name;
            this.address = address;
            this.email = email;
            this.phone = phone;
            this.status = status != null ? status : AccountStatus.ACTIVE;
            this.reputation = 0;
        }

        /** Simulates a password reset (placeholder). */
        public void resetPassword() {
            System.out.println("Password reset for account: " + id);
        }

        public int getReputation() {
            return reputation;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public String getEmail() {
            return email;
        }

        public String getId() {
            return id;
        }

        public AccountStatus getStatus() {
            return status;
        }
    }

    /**
     * Represents a member — a normal user who can ask questions,
     * create tags, and earn badges.
     */
    public static class Member {
        private Account account;
        private List<String> badges;

        public Member(Account account) {
            this.account = account;
            this.badges = new ArrayList<String>();
        }

        public int getReputation() {
            return account.getReputation();
        }

        public String getEmail() {
            return account.getEmail();
        }

        /** Simulated question creation. */
        public void createQuestion(String title) {
            System.out.println("Member created a question: " + title);
        }

        public void createTag(String tag) {
            System.out.println("Member created a tag: " + tag);
        }
    }

    /**
     * Represents an administrator — can manage members.
     */
    public static class Admin extends Member {
        public Admin(Account account) {
            super(account);
        }

        public void blockMember(Member member) {
            System.out.println("Admin blocked a member.");
        }

        public void unblockMember(Member member) {
            System.out.println("Admin unblocked a member.");
        }
    }

    /**
     * Represents a moderator — focuses on content moderation.
     */
    public static class Moderator extends Member {
        public Moderator(Account account) {
            super(account);
        }

        public void closeQuestion(String questionTitle) {
            System.out.println("Moderator closed question: " + questionTitle);
        }

        public void undeleteQuestion(String questionTitle) {
            System.out.println("Moderator undeleted question: " + questionTitle);
        }
    }
}
