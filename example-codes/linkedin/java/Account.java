import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Account.java
 * 
 * This file contains classes related to user accounts and user types in the LinkedIn system.
 * It includes the Account class for authentication, the abstract Person class,
 * and concrete implementations for Member and Admin user types.
 */

/**
 * Class representing a user account with authentication credentials.
 * 
 * An account stores login credentials and account status information.
 * It provides functionality for password management and account status tracking.
 */
public class Account {
    private String id;
    private String password;
    private AccountStatus status;

    /**
     * Constructor to create a new Account with default ACTIVE status.
     * 
     * @param id The unique account identifier (username or email)
     * @param password The account password (should be hashed in production)
     */
    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Constructor to create a new Account with specified status.
     * 
     * @param id The unique account identifier
     * @param password The account password
     * @param status The initial account status
     */
    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    /**
     * Resets the account password.
     * In a real implementation, this would involve security verification
     * and password validation logic.
     * 
     * @param newPassword The new password to set
     * @return true if password was successfully reset
     */
    public boolean resetPassword(String newPassword) {
        // TODO: Implement password reset logic with security checks
        // - Verify user identity
        // - Validate new password strength
        // - Hash the password before storing
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public AccountStatus getStatus() {
        return status;
    }

    // Setters
    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

/**
 * Abstract base class representing a person in the LinkedIn system.
 * 
 * This class contains common attributes shared by all person types
 * (Members and Admins) including personal information and account details.
 */
abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Account account;

    /**
     * Constructor to create a new Person.
     * 
     * @param name The person's full name
     * @param address The person's physical address
     * @param email The person's email address
     * @param phone The person's phone number
     * @param account The associated account object
     */
    public Person(String name, Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Account getAccount() {
        return account;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

/**
 * Class representing a regular LinkedIn member (user).
 * 
 * Members can create profiles, connect with other members, follow companies,
 * join groups, create posts, and send messages. This class extends Person
 * and adds member-specific functionality.
 */
class Member extends Person {
    private Date dateOfMembership;
    private String headline;
    private List<byte[]> photos;
    private List<Member> memberSuggestions;
    private List<Member> memberFollows;
    private List<Member> memberConnections;
    private List<String> companyFollows;
    private List<String> groupFollows;
    private Profile profile;

    /**
     * Constructor to create a new Member.
     * 
     * @param name The member's full name
     * @param address The member's address
     * @param email The member's email
     * @param phone The member's phone number
     * @param account The associated account
     */
    public Member(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
        this.dateOfMembership = new Date();
        this.headline = "";
        this.photos = new ArrayList<>();
        this.memberSuggestions = new ArrayList<>();
        this.memberFollows = new ArrayList<>();
        this.memberConnections = new ArrayList<>();
        this.companyFollows = new ArrayList<>();
        this.groupFollows = new ArrayList<>();
        this.profile = new Profile("", new ArrayList<>(), new ArrayList<>(), 
                                   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Sends a message to another member or connection.
     * 
     * @param message The Message object to send
     * @return true if message was successfully sent
     */
    public boolean sendMessage(Message message) {
        // TODO: Implement message sending logic
        // - Validate recipient
        // - Check if sender can message recipient (connection/premium)
        // - Store message in database
        // - Send notification to recipient
        if (message != null) {
            return true;
        }
        return false;
    }

    /**
     * Creates a new post on the member's profile or feed.
     * 
     * @param post The Post object to create
     * @return true if post was successfully created
     */
    public boolean createPost(Post post) {
        // TODO: Implement post creation logic
        // - Validate post content
        // - Store post in database
        // - Notify connections
        // - Update feed
        if (post != null) {
            return true;
        }
        return false;
    }

    /**
     * Sends a connection invitation to another member.
     * 
     * @param invitation The connection invitation to send
     * @return true if invitation was successfully sent
     */
    public boolean sendConnectionInvitation(ConnectionInvitation invitation) {
        // TODO: Implement connection invitation logic
        // - Validate recipient exists
        // - Check if already connected
        // - Create invitation record
        // - Send notification to recipient
        if (invitation != null) {
            return true;
        }
        return false;
    }

    /**
     * Adds a member to the connections list.
     * 
     * @param member The member to add as a connection
     * @return true if successfully added
     */
    public boolean addConnection(Member member) {
        if (member != null && !memberConnections.contains(member)) {
            return memberConnections.add(member);
        }
        return false;
    }

    /**
     * Follows a company to receive updates.
     * 
     * @param companyId The ID of the company to follow
     * @return true if successfully followed
     */
    public boolean followCompany(String companyId) {
        if (companyId != null && !companyFollows.contains(companyId)) {
            return companyFollows.add(companyId);
        }
        return false;
    }

    /**
     * Joins a group to participate in discussions.
     * 
     * @param groupId The ID of the group to join
     * @return true if successfully joined
     */
    public boolean joinGroup(String groupId) {
        if (groupId != null && !groupFollows.contains(groupId)) {
            return groupFollows.add(groupId);
        }
        return false;
    }

    // Getters
    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    public String getHeadline() {
        return headline;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Member> getMemberConnections() {
        return memberConnections;
    }

    public List<Member> getMemberFollows() {
        return memberFollows;
    }

    public List<String> getCompanyFollows() {
        return companyFollows;
    }

    public List<String> getGroupFollows() {
        return groupFollows;
    }

    // Setters
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}

/**
 * Class representing an administrator of the LinkedIn system.
 * 
 * Admins have elevated privileges to manage users, including the ability
 * to block and unblock user accounts for policy violations.
 */
class Admin extends Person {

    /**
     * Constructor to create a new Admin.
     * 
     * @param name The admin's full name
     * @param address The admin's address
     * @param email The admin's email
     * @param phone The admin's phone number
     * @param account The associated account
     */
    public Admin(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Blocks a user account, preventing them from accessing the platform.
     * This is typically done for policy violations or suspicious activity.
     * 
     * @param member The member to block
     * @return true if successfully blocked
     */
    public boolean blockUser(Member member) {
        // TODO: Implement user blocking logic
        // - Validate admin permissions
        // - Update account status to BLOCKED
        // - Log the action
        // - Notify the user
        if (member != null) {
            Account account = member.getAccount();
            if (account != null) {
                account.setStatus(AccountStatus.BLOCKED);
                return true;
            }
        }
        return false;
    }

    /**
     * Unblocks a previously blocked user account.
     * 
     * @param member The member to unblock
     * @return true if successfully unblocked
     */
    public boolean unblockUser(Member member) {
        // TODO: Implement user unblocking logic
        // - Validate admin permissions
        // - Update account status to ACTIVE
        // - Log the action
        // - Notify the user
        if (member != null) {
            Account account = member.getAccount();
            if (account != null && account.getStatus() == AccountStatus.BLOCKED) {
                account.setStatus(AccountStatus.ACTIVE);
                return true;
            }
        }
        return false;
    }

    /**
     * Bans a user account permanently.
     * 
     * @param member The member to ban
     * @return true if successfully banned
     */
    public boolean banUser(Member member) {
        if (member != null) {
            Account account = member.getAccount();
            if (account != null) {
                account.setStatus(AccountStatus.BANNED);
                return true;
            }
        }
        return false;
    }
}

/**
 * Class representing a connection invitation between members.
 * 
 * Connection invitations are sent by one member to another to establish
 * a professional connection on the platform.
 */
class ConnectionInvitation {
    private Member sender;
    private Member receiver;
    private Date dateSent;
    private ConnectionInvitationStatus status;
    private String message;

    /**
     * Constructor to create a new ConnectionInvitation.
     * 
     * @param sender The member sending the invitation
     * @param receiver The member receiving the invitation
     * @param message Optional personal message with the invitation
     */
    public ConnectionInvitation(Member sender, Member receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.dateSent = new Date();
        this.status = ConnectionInvitationStatus.PENDING;
    }

    /**
     * Accepts the connection invitation.
     * 
     * @return true if successfully accepted
     */
    public boolean accept() {
        if (this.status == ConnectionInvitationStatus.PENDING) {
            this.status = ConnectionInvitationStatus.ACCEPTED;
            // Add each member to the other's connections
            sender.addConnection(receiver);
            receiver.addConnection(sender);
            return true;
        }
        return false;
    }

    /**
     * Rejects the connection invitation.
     * 
     * @return true if successfully rejected
     */
    public boolean reject() {
        if (this.status == ConnectionInvitationStatus.PENDING) {
            this.status = ConnectionInvitationStatus.REJECTED;
            return true;
        }
        return false;
    }

    // Getters
    public Member getSender() {
        return sender;
    }

    public Member getReceiver() {
        return receiver;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public ConnectionInvitationStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
