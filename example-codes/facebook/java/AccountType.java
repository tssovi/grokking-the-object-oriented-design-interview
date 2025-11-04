import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * AccountType.java
 * 
 * This file contains the core account and user-related classes for the Facebook system.
 * It includes Account, Person (abstract base class), Member, Admin, and ConnectionInvitation.
 * 
 * Note: For simplicity, we assume all class attributes are private and accessed through
 * their respective public getter methods and modified only through their public setter methods.
 */

/**
 * Account class represents a user account in the Facebook system.
 * It contains authentication credentials and account status information.
 */
public class Account {
    // Account credentials and status
    private String id;                      // Unique account identifier
    private String password;                // Encrypted password (in real system)
    private AccountStatus status;           // Current account status

    /**
     * Constructor to create an Account with default ACTIVE status.
     * 
     * @param id Unique identifier for the account
     * @param password User's password (should be encrypted in production)
     */
    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Constructor to create an Account with specified status.
     * 
     * @param id Unique identifier for the account
     * @param password User's password (should be encrypted in production)
     * @param status Initial account status
     */
    public Account(String id, String password, AccountStatus status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    /**
     * Resets the account password.
     * In a real implementation, this would involve verification and encryption.
     * 
     * @param newPassword The new password to set
     * @return true if password was successfully reset
     */
    public boolean resetPassword(String newPassword) {
        // TODO: Implement password reset logic with verification
        this.password = newPassword;
        return true;
    }

    // Getter methods
    
    /**
     * @return The account ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return The current account status
     */
    public AccountStatus getStatus() {
        return status;
    }

    /**
     * Sets the account status.
     * 
     * @param status The new account status
     */
    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}

/**
 * Person is an abstract base class representing any person in the Facebook system.
 * It contains common attributes like name, address, contact information, and account.
 * 
 * This class is extended by Member and Admin to create specific user types.
 */
abstract class Person {
    // Personal information
    private String name;            // Full name of the person
    private Address address;        // Physical address
    private String email;           // Email address
    private String phone;           // Phone number
    private Account account;        // Associated account

    /**
     * Constructor to create a Person with all required information.
     * 
     * @param name Full name of the person
     * @param address Physical address
     * @param email Email address
     * @param phone Phone number
     * @param account Associated Account object
     */
    public Person(String name, Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }

    // Getter methods
    
    /**
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The person's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return The person's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The person's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return The associated account
     */
    public Account getAccount() {
        return account;
    }
}

/**
 * Member class represents a regular Facebook user/member.
 * Members can create posts, send messages, connect with other members,
 * follow pages and groups, and manage their profile.
 */
class Member extends Person {
    // Member-specific identifiers
    private String memberId;                    // Unique member identifier
    private Date dateOfMembership;              // Date when member joined
    
    // Profile and social connections
    private Profile profile;                    // Member's profile information
    private List<Member> memberFollows;         // Members this user follows
    private List<Member> memberConnections;     // Connected members (friends)
    private List<Page> pageFollows;             // Pages this member follows
    private List<Member> memberSuggestions;     // Suggested connections
    private List<ConnectionInvitation> connectionInvitations;  // Pending invitations
    private List<Group> groupFollows;           // Groups this member follows

    /**
     * Constructor to create a Member.
     * Initializes all collections and creates a new Profile.
     * 
     * @param memberId Unique identifier for the member
     * @param dateOfMembership Date when the member joined
     * @param name Member's full name
     * @param address Member's address
     * @param email Member's email
     * @param phone Member's phone number
     * @param account Associated Account object
     */
    public Member(String memberId, Date dateOfMembership, String name, 
                  Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
        this.memberId = memberId;
        this.dateOfMembership = dateOfMembership;
        
        // Initialize profile and collections
        this.profile = new Profile(null, null, "");
        this.memberFollows = new ArrayList<>();
        this.memberConnections = new ArrayList<>();
        this.pageFollows = new ArrayList<>();
        this.memberSuggestions = new ArrayList<>();
        this.connectionInvitations = new ArrayList<>();
        this.groupFollows = new ArrayList<>();
    }

    /**
     * Sends a message to another member or group.
     * 
     * @param message The Message object to send
     * @return true if message was sent successfully
     */
    public boolean sendMessage(Message message) {
        // TODO: Implement message sending logic
        return true;
    }

    /**
     * Creates a new post on the member's timeline or in a group.
     * 
     * @param post The Post object to create
     * @return true if post was created successfully
     */
    public boolean createPost(Post post) {
        // TODO: Implement post creation logic
        return true;
    }

    /**
     * Sends a connection invitation to another member.
     * 
     * @param invitation The ConnectionInvitation to send
     * @return true if invitation was sent successfully
     */
    public boolean sendConnectionInvitation(ConnectionInvitation invitation) {
        // TODO: Implement invitation sending logic
        return true;
    }

    /**
     * Searches for suggested member connections based on mutual friends,
     * interests, and other factors.
     * 
     * @return List of suggested Member connections
     */
    public List<Member> searchMemberSuggestions() {
        // TODO: Implement member suggestion algorithm
        return this.memberSuggestions;
    }

    // Getter methods
    
    /**
     * @return The member's unique ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @return The date when the member joined
     */
    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    /**
     * @return The member's profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * @return List of members this user follows
     */
    public List<Member> getMemberFollows() {
        return memberFollows;
    }

    /**
     * @return List of connected members (friends)
     */
    public List<Member> getMemberConnections() {
        return memberConnections;
    }

    /**
     * @return List of pages this member follows
     */
    public List<Page> getPageFollows() {
        return pageFollows;
    }

    /**
     * @return List of groups this member follows
     */
    public List<Group> getGroupFollows() {
        return groupFollows;
    }
}

/**
 * Admin class represents a system administrator with elevated privileges.
 * Admins can block/unblock users and enable/disable pages.
 */
class Admin extends Person {
    /**
     * Constructor to create an Admin.
     * 
     * @param name Admin's full name
     * @param address Admin's address
     * @param email Admin's email
     * @param phone Admin's phone number
     * @param account Associated Account object
     */
    public Admin(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    /**
     * Blocks a user account, preventing them from accessing the system.
     * 
     * @param member The Member to block
     * @return true if user was successfully blocked
     */
    public boolean blockUser(Member member) {
        // TODO: Implement user blocking logic
        member.getAccount().setStatus(AccountStatus.BLACKLISTED);
        return true;
    }

    /**
     * Unblocks a previously blocked user account.
     * 
     * @param member The Member to unblock
     * @return true if user was successfully unblocked
     */
    public boolean unblockUser(Member member) {
        // TODO: Implement user unblocking logic
        member.getAccount().setStatus(AccountStatus.ACTIVE);
        return true;
    }

    /**
     * Enables a page, making it visible and accessible to users.
     * 
     * @param page The Page to enable
     * @return true if page was successfully enabled
     */
    public boolean enablePage(Page page) {
        // TODO: Implement page enabling logic
        return true;
    }

    /**
     * Disables a page, making it invisible and inaccessible to users.
     * 
     * @param page The Page to disable
     * @return true if page was successfully disabled
     */
    public boolean disablePage(Page page) {
        // TODO: Implement page disabling logic
        return true;
    }
}

/**
 * ConnectionInvitation class represents an invitation to connect between two members.
 * It tracks the invitation status and timestamps for creation and updates.
 */
class ConnectionInvitation {
    // Invitation details
    private Member memberInvited;               // The member who received the invitation
    private ConnectionInvitationStatus status;  // Current status of the invitation
    private Date dateCreated;                   // When the invitation was created
    private Date dateUpdated;                   // When the invitation was last updated

    /**
     * Constructor to create a ConnectionInvitation with PENDING status.
     * 
     * @param memberInvited The Member who is being invited to connect
     */
    public ConnectionInvitation(Member memberInvited) {
        this.memberInvited = memberInvited;
        this.status = ConnectionInvitationStatus.PENDING;
        this.dateCreated = new Date();
        this.dateUpdated = new Date();
    }

    /**
     * Constructor to create a ConnectionInvitation with specified status.
     * 
     * @param memberInvited The Member who is being invited to connect
     * @param status Initial status of the invitation
     */
    public ConnectionInvitation(Member memberInvited, ConnectionInvitationStatus status) {
        this.memberInvited = memberInvited;
        this.status = status;
        this.dateCreated = new Date();
        this.dateUpdated = new Date();
    }

    /**
     * Accepts the connection invitation, establishing a connection between members.
     * 
     * @return true if invitation was successfully accepted
     */
    public boolean acceptConnection() {
        // TODO: Implement connection acceptance logic
        this.status = ConnectionInvitationStatus.ACCEPTED;
        this.dateUpdated = new Date();
        return true;
    }

    /**
     * Rejects the connection invitation.
     * 
     * @return true if invitation was successfully rejected
     */
    public boolean rejectConnection() {
        // TODO: Implement connection rejection logic
        this.status = ConnectionInvitationStatus.REJECTED;
        this.dateUpdated = new Date();
        return true;
    }

    // Getter methods
    
    /**
     * @return The member who was invited
     */
    public Member getMemberInvited() {
        return memberInvited;
    }

    /**
     * @return The current status of the invitation
     */
    public ConnectionInvitationStatus getStatus() {
        return status;
    }

    /**
     * @return The date when the invitation was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @return The date when the invitation was last updated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }
}
