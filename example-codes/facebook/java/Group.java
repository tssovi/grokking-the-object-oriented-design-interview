import java.util.ArrayList;
import java.util.List;

/**
 * Group.java
 * 
 * This file contains classes related to social interactions in the Facebook system.
 * It includes Group, Post, Message, and Comment classes that enable users to
 * communicate and share content.
 */

/**
 * Group class represents a Facebook group where members can gather around
 * common interests, share posts, and communicate with each other.
 */
public class Group {
    // Group identification and metadata
    private String groupId;             // Unique identifier for the group
    private String name;                // Name of the group
    private String description;         // Description of the group's purpose
    private int totalMembers;           // Total number of members in the group
    
    // Group membership
    private List<Member> members;       // List of members who have joined the group

    /**
     * Constructor to create a Group.
     * 
     * @param groupId Unique identifier for the group
     * @param name Name of the group
     * @param description Description of the group's purpose and rules
     * @param totalMembers Initial count of total members
     */
    public Group(String groupId, String name, String description, int totalMembers) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.totalMembers = totalMembers;
        this.members = new ArrayList<>();
    }

    /**
     * Adds a member to the group.
     * 
     * @param member The Member to add to the group
     * @return true if member was successfully added
     */
    public boolean addMember(Member member) {
        if (this.members.add(member)) {
            this.totalMembers++;
            return true;
        }
        return false;
    }

    /**
     * Updates the group's description.
     * 
     * @param description The new description for the group
     */
    public void updateDescription(String description) {
        this.description = description;
    }

    // Getter methods
    
    /**
     * @return The group's unique ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @return The group's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The group's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The total number of members
     */
    public int getTotalMembers() {
        return totalMembers;
    }

    /**
     * @return List of members in the group
     */
    public List<Member> getMembers() {
        return members;
    }
}

/**
 * Post class represents a post/status update that can be shared on timelines,
 * groups, or pages. Posts can receive likes, shares, and comments.
 */
class Post {
    // Post identification and content
    private String postId;              // Unique identifier for the post
    private String text;                // Text content of the post
    private int totalLikes;             // Number of likes the post has received
    private int totalShares;            // Number of times the post has been shared
    private Member owner;               // The member who created the post

    /**
     * Constructor to create a Post.
     * 
     * @param postId Unique identifier for the post
     * @param text The text content of the post
     * @param totalLikes Initial number of likes (usually 0)
     * @param totalShares Initial number of shares (usually 0)
     * @param owner The Member who created the post
     */
    public Post(String postId, String text, int totalLikes, int totalShares, Member owner) {
        this.postId = postId;
        this.text = text;
        this.totalLikes = totalLikes;
        this.totalShares = totalShares;
        this.owner = owner;
    }

    /**
     * Increments the like count for this post.
     */
    public void addLike() {
        this.totalLikes++;
    }

    /**
     * Increments the share count for this post.
     */
    public void addShare() {
        this.totalShares++;
    }

    // Getter methods
    
    /**
     * @return The post's unique ID
     */
    public String getPostId() {
        return postId;
    }

    /**
     * @return The text content of the post
     */
    public String getText() {
        return text;
    }

    /**
     * @return The total number of likes
     */
    public int getTotalLikes() {
        return totalLikes;
    }

    /**
     * @return The total number of shares
     */
    public int getTotalShares() {
        return totalShares;
    }

    /**
     * @return The member who created the post
     */
    public Member getOwner() {
        return owner;
    }
}

/**
 * Message class represents a private message sent between members.
 * Messages can contain text and media attachments.
 */
class Message {
    // Message identification and content
    private String messageId;           // Unique identifier for the message
    private List<Member> sentTo;        // List of recipients (supports group messages)
    private String messageBody;         // Text content of the message
    private byte[] media;               // Media attachment (image, video, etc.)

    /**
     * Constructor to create a Message.
     * 
     * @param messageId Unique identifier for the message
     * @param sentTo List of Member recipients
     * @param messageBody The text content of the message
     * @param media Media attachment as byte array (can be null)
     */
    public Message(String messageId, List<Member> sentTo, String messageBody, byte[] media) {
        this.messageId = messageId;
        this.sentTo = sentTo;
        this.messageBody = messageBody;
        this.media = media;
    }

    /**
     * Adds a member to the message recipients (for group conversations).
     * 
     * @param member The Member to add as a recipient
     * @return true if member was successfully added
     */
    public boolean addMember(Member member) {
        return this.sentTo.add(member);
    }

    // Getter methods
    
    /**
     * @return The message's unique ID
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @return List of recipients
     */
    public List<Member> getSentTo() {
        return sentTo;
    }

    /**
     * @return The message body text
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * @return The media attachment as byte array
     */
    public byte[] getMedia() {
        return media;
    }
}

/**
 * Comment class represents a comment on a post.
 * Comments can receive likes and are owned by a member.
 */
class Comment {
    // Comment identification and content
    private String commentId;           // Unique identifier for the comment
    private String text;                // Text content of the comment
    private int totalLikes;             // Number of likes the comment has received
    private Member owner;               // The member who created the comment

    /**
     * Constructor to create a Comment.
     * 
     * @param commentId Unique identifier for the comment
     * @param text The text content of the comment
     * @param totalLikes Initial number of likes (usually 0)
     * @param owner The Member who created the comment
     */
    public Comment(String commentId, String text, int totalLikes, Member owner) {
        this.commentId = commentId;
        this.text = text;
        this.totalLikes = totalLikes;
        this.owner = owner;
    }

    /**
     * Increments the like count for this comment.
     */
    public void addLike() {
        this.totalLikes++;
    }

    // Getter methods
    
    /**
     * @return The comment's unique ID
     */
    public String getCommentId() {
        return commentId;
    }

    /**
     * @return The text content of the comment
     */
    public String getText() {
        return text;
    }

    /**
     * @return The total number of likes
     */
    public int getTotalLikes() {
        return totalLikes;
    }

    /**
     * @return The member who created the comment
     */
    public Member getOwner() {
        return owner;
    }
}
