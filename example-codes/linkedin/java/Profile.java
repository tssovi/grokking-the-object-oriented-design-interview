import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Profile.java
 * 
 * This file contains classes related to user profiles on LinkedIn.
 * It includes the Profile class which manages professional information,
 * and the Experience class which represents work experience entries.
 */

/**
 * Class representing a LinkedIn user's profile.
 * 
 * A profile contains comprehensive professional information including:
 * - Professional summary
 * - Work experiences
 * - Educational background
 * - Skills and endorsements
 * - Accomplishments and awards
 * - Recommendations from connections
 * - Profile statistics
 */
public class Profile {
    private String summary;
    private List<Experience> experiences;
    private List<String> educations;
    private List<String> skills;
    private List<String> accomplishments;
    private List<String> recommendations;
    private List<String> stats;

    /**
     * Constructor to create a new Profile.
     * 
     * @param summary A brief professional summary or headline
     * @param experiences List of work experiences
     * @param educations List of educational qualifications
     * @param skills List of professional skills
     * @param accomplishments List of accomplishments and awards
     * @param recommendations List of recommendations from connections
     */
    public Profile(String summary, List<Experience> experiences, List<String> educations,
                   List<String> skills, List<String> accomplishments, List<String> recommendations) {
        this.summary = summary;
        this.experiences = experiences;
        this.educations = educations;
        this.skills = skills;
        this.accomplishments = accomplishments;
        this.recommendations = recommendations;
        this.stats = new ArrayList<>();
    }

    /**
     * Adds a new work experience to the profile.
     * 
     * @param experience The Experience object to add
     * @return true if successfully added, false otherwise
     */
    public boolean addExperience(Experience experience) {
        if (experience != null) {
            return this.experiences.add(experience);
        }
        return false;
    }

    /**
     * Adds a new education entry to the profile.
     * 
     * @param education The education entry to add
     * @return true if successfully added, false otherwise
     */
    public boolean addEducation(String education) {
        if (education != null && !education.isEmpty()) {
            return this.educations.add(education);
        }
        return false;
    }

    /**
     * Adds a new skill to the profile.
     * 
     * @param skill The skill to add
     * @return true if successfully added, false otherwise
     */
    public boolean addSkill(String skill) {
        if (skill != null && !skill.isEmpty()) {
            return this.skills.add(skill);
        }
        return false;
    }

    /**
     * Adds a new accomplishment to the profile.
     * 
     * @param accomplishment The accomplishment to add
     * @return true if successfully added, false otherwise
     */
    public boolean addAccomplishment(String accomplishment) {
        if (accomplishment != null && !accomplishment.isEmpty()) {
            return this.accomplishments.add(accomplishment);
        }
        return false;
    }

    /**
     * Adds a new recommendation to the profile.
     * 
     * @param recommendation The recommendation to add
     * @return true if successfully added, false otherwise
     */
    public boolean addRecommendation(String recommendation) {
        if (recommendation != null && !recommendation.isEmpty()) {
            return this.recommendations.add(recommendation);
        }
        return false;
    }

    // Getters
    public String getSummary() {
        return summary;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<String> getEducations() {
        return educations;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getAccomplishments() {
        return accomplishments;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public List<String> getStats() {
        return stats;
    }

    // Setters
    public void setSummary(String summary) {
        this.summary = summary;
    }
}

/**
 * Class representing a work experience entry in a user's profile.
 * 
 * An experience includes job title, company, location, duration,
 * and a description of responsibilities and achievements.
 */
class Experience {
    private String title;
    private String company;
    private String location;
    private Date dateFrom;
    private Date dateTo;
    private String description;

    /**
     * Constructor to create a new Experience entry.
     * 
     * @param title The job title or position held
     * @param company The name of the company or organization
     * @param location The location of the job (city, state/country)
     * @param dateFrom The start date of employment
     * @param dateTo The end date of employment (null if current position)
     * @param description Description of responsibilities and achievements
     */
    public Experience(String title, String company, String location, 
                     Date dateFrom, Date dateTo, String description) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if this is a current position (no end date).
     * 
     * @return true if dateTo is null, false otherwise
     */
    public boolean isCurrentPosition() {
        return this.dateTo == null;
    }
}
