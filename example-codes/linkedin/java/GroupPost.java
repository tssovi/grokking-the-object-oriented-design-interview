import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GroupPost.java
 * 
 * This file contains classes related to groups, posts, and messages on LinkedIn.
 * It includes the Group class for professional communities, the Post class for
 * content sharing, and the Message class for direct communication.
 */

/**
 * Class representing a LinkedIn group.
 * 
 * Groups are communities where members can share content, engage in discussions,
 * and network around common professional interests or industries.
 */
public class Group {
    private String name;
    private String description;
    private int totalMembers;
    private List<Member> members;
    private List<Post> posts;

    /**
     * Constructor to create a new Group.
     * 
     * @param name The name of the group
     * @param description A description of the group's purpose and focus
     */
    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        this.totalMembers = 0;
        this.members = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    /**
     * Adds a new member to the group.
     * 
     * @param member The Member to add to the group
     * @return true if successfully added
     */
    public boolean addMember(Member member) {
        if (member != null && !members.contains(member)) {
            boolean added = members.add(member);
            if (added) {
                this.totalMembers++;
            }
            return added;
        }
        return false;
    }

    /**
     * Removes a member from the group.
     * 
     * @param member The Member to remove
     * @return true if successfully removed
     */
    public boolean removeMember(Member member) {
        if (member != null && members.contains(member)) {
            boolean removed = members.remove(member);
            if (removed) {
                this.totalMembers--;
            }
            return removed;
        }
        return false;
    }

    /**
     * Updates the group description.
     * 
     * @param description The new description
     * @return true if successfully updated
     */
    public boolean updateDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
            return true;
        }
        return false;
    }

    /**
     * Adds a post to the group.
     * 
     * @param post The Post to add
     * @return true if successfully added
     */
    public boolean addPost(Post post) {
        if (post != null) {
            return posts.add(post);
        }
        return false;
    }

    /**
     * Gets all posts in the group.
     * 
     * @return List of Post objects
     */
    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
}

/**
 * Class representing a post on LinkedIn.
 * 
 * Posts are content shared by members on their profiles, in groups,
 * or on company pages. Posts can receive likes, comments, and shares.
 */
class Post {
    private String text;
    private int totalLikes;
    private int totalShares;
    private int totalComments;
    private Member owner;
    private Date dateCreated;
    private List<String> comments;
    private List<byte[]> media;

    /**
     * Constructor to create a new Post.
     * The creation date is automatically set to the current date.
     * 
     * @param text The text content of the post
     * @param owner The Member who created the post
     */
    public Post(String text, Member owner) {
        this.text = text;
        this.owner = owner;
        this.totalLikes = 0;
        this.totalShares = 0;
        this.totalComments = 0;
        this.dateCreated = new Date();
        this.comments = new ArrayList<>();
        this.media = new ArrayList<>();
    }

    /**
     * Increments the like count for this post.
     * 
     * @return The new total number of likes
     */
    public int addLike() {
        return ++this.totalLikes;
    }

    /**
     * Decrements the like count for this post (unlike).
     * 
     * @return The new total number of likes
     */
    public int removeLike() {
        if (this.totalLikes > 0) {
            return --this.totalLikes;
        }
        return this.totalLikes;
    }

    /**
     * Increments the share count for this post.
     * 
     * @return The new total number of shares
     */
    public int addShare() {
        return ++this.totalShares;
    }

    /**
     * Adds a comment to the post.
     * 
     * @param comment The comment text to add
     * @return true if successfully added
     */
    public boolean addComment(String comment) {
        if (comment != null && !comment.isEmpty()) {
            boolean added = comments.add(comment);
            if (added) {
                this.totalComments++;
            }
            return added;
        }
        return false;
    }

    /**
     * Adds media (image, video) to the post.
     * 
     * @param mediaData The media data as byte array
     * @return true if successfully added
     */
    public boolean addMedia(byte[] mediaData) {
        if (mediaData != null && mediaData.length > 0) {
            return media.add(mediaData);
        }
        return false;
    }

    // Getters
    public String getText() {
        return text;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public Member getOwner() {
        return owner;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<String> getComments() {
        return new ArrayList<>(comments);
    }

    public List<byte[]> getMedia() {
        return new ArrayList<>(media);
    }

    // Setters
    public void setText(String text) {
        this.text = text;
    }
}

/**
 * Class representing a direct message between LinkedIn members.
 * 
 * Messages allow members to communicate privately with their connections
 * or other members (depending on privacy settings and premium features).
 */
class Message {
    private List<Member> sentTo;
    private String messageBody;
    private List<byte[]> media;
    private Date dateSent;
    private Member sender;

    /**
     * Constructor to create a new Message.
     * The send date is automatically set to the current date.
     * 
     * @param sentTo List of Members receiving the message
     * @param messageBody The text content of the message
     * @param media Optional media attachments (images, files)
     */
    public Message(List<Member> sentTo, String messageBody, List<byte[]> media) {
        this.sentTo = sentTo;
        this.messageBody = messageBody;
        this.media = media != null ? media : new ArrayList<>();
        this.dateSent = new Date();
    }

    /**
     * Constructor to create a new Message with a single recipient.
     * 
     * @param sentTo The Member receiving the message
     * @param messageBody The text content of the message
     * @param media Optional media attachments
     */
    public Message(Member sentTo, String messageBody, List<byte[]> media) {
        this.sentTo = new ArrayList<>();
        this.sentTo.add(sentTo);
        this.messageBody = messageBody;
        this.media = media != null ? media : new ArrayList<>();
        this.dateSent = new Date();
    }

    /**
     * Adds a recipient to the message.
     * 
     * @param member The Member to add as recipient
     * @return true if successfully added
     */
    public boolean addRecipient(Member member) {
        if (member != null && !sentTo.contains(member)) {
            return sentTo.add(member);
        }
        return false;
    }

    /**
     * Adds media attachment to the message.
     * 
     * @param mediaData The media data as byte array
     * @return true if successfully added
     */
    public boolean addMedia(byte[] mediaData) {
        if (mediaData != null && mediaData.length > 0) {
            return media.add(mediaData);
        }
        return false;
    }

    // Getters
    public List<Member> getSentTo() {
        return new ArrayList<>(sentTo);
    }

    public String getMessageBody() {
        return messageBody;
    }

    public List<byte[]> getMedia() {
        return new ArrayList<>(media);
    }

    public Date getDateSent() {
        return dateSent;
    }

    public Member getSender() {
        return sender;
    }

    // Setters
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }
}
