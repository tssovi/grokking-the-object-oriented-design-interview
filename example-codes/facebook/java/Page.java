import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Page.java
 * 
 * This file contains classes related to Facebook Pages.
 * Pages represent businesses, brands, organizations, or public figures
 * and can receive recommendations and reviews from users.
 */

/**
 * Page class represents a Facebook Page that can be followed by members.
 * Pages are typically used by businesses, brands, celebrities, or organizations
 * to connect with their audience.
 */
public class Page {
    // Page identification and metadata
    private String pageId;              // Unique identifier for the page
    private String name;                // Name of the page
    private String description;         // Description of what the page represents
    private String type;                // Type of page (e.g., "Business", "Celebrity", "Organization")
    private int totalMembers;           // Total number of followers
    
    // Page content
    private List<Recommendation> recommendations;  // List of recommendations/reviews

    /**
     * Constructor to create a Page.
     * 
     * @param pageId Unique identifier for the page
     * @param name Name of the page
     * @param description Description of the page's purpose
     * @param type Type/category of the page
     * @param totalMembers Initial count of followers
     */
    public Page(String pageId, String name, String description, String type, int totalMembers) {
        this.pageId = pageId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.totalMembers = totalMembers;
        this.recommendations = new ArrayList<>();
    }

    /**
     * Retrieves all recommendations for this page.
     * 
     * @return List of Recommendation objects
     */
    public List<Recommendation> getRecommendations() {
        return this.recommendations;
    }

    /**
     * Adds a recommendation to the page.
     * 
     * @param recommendation The Recommendation to add
     * @return true if successfully added
     */
    public boolean addRecommendation(Recommendation recommendation) {
        return this.recommendations.add(recommendation);
    }

    // Getter methods
    
    /**
     * @return The page's unique ID
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @return The page's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The page's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The page's type/category
     */
    public String getType() {
        return type;
    }

    /**
     * @return The total number of followers
     */
    public int getTotalMembers() {
        return totalMembers;
    }

    /**
     * Increments the follower count when a new member follows the page.
     */
    public void addFollower() {
        this.totalMembers++;
    }

    /**
     * Decrements the follower count when a member unfollows the page.
     */
    public void removeFollower() {
        if (this.totalMembers > 0) {
            this.totalMembers--;
        }
    }
}

/**
 * Recommendation class represents a review or recommendation left by a user
 * on a Facebook Page. It includes a rating, description, and timestamp.
 */
class Recommendation {
    // Recommendation details
    private String recommendationId;    // Unique identifier for the recommendation
    private int rating;                 // Rating value (e.g., 1-5 stars)
    private String description;         // Text description of the recommendation
    private Date createdAt;             // Timestamp when the recommendation was created

    /**
     * Constructor to create a Recommendation.
     * Automatically sets the creation timestamp to the current date/time.
     * 
     * @param recommendationId Unique identifier for the recommendation
     * @param rating The rating value (typically 1-5)
     * @param description Text description explaining the recommendation
     */
    public Recommendation(String recommendationId, int rating, String description) {
        this.recommendationId = recommendationId;
        this.rating = rating;
        this.description = description;
        this.createdAt = new Date();  // Set to current date/time
    }

    // Getter methods
    
    /**
     * @return The recommendation's unique ID
     */
    public String getRecommendationId() {
        return recommendationId;
    }

    /**
     * @return The rating value
     */
    public int getRating() {
        return rating;
    }

    /**
     * @return The description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The timestamp when the recommendation was created
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Updates the rating for this recommendation.
     * 
     * @param rating The new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Updates the description for this recommendation.
     * 
     * @param description The new description text
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
