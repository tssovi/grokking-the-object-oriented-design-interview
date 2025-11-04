import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Profile.java
 * 
 * This file contains classes related to user profiles in the Facebook system.
 * It includes the Profile class which stores user information, and the Work class
 * which represents employment history.
 */

/**
 * Profile class represents a user's profile information including pictures,
 * work experience, education, and places they've been.
 * 
 * This class encapsulates all the personal and professional information
 * that a member wants to display on their profile page.
 */
public class Profile {
    // Profile media and basic information
    private byte[] profilePicture;  // Stored as byte array for image data
    private byte[] coverPhoto;      // Stored as byte array for image data
    private String gender;          // User's gender

    // Collections to store profile details
    private List<Work> workExperiences;     // List of work experiences
    private List<Education> educations;     // List of educational background
    private List<Place> places;             // List of places lived or visited
    private List<String> stats;             // Various profile statistics

    /**
     * Constructor to create a Profile with basic information.
     * Initializes empty lists for experiences, education, places, and stats.
     * 
     * @param profilePicture Byte array containing the profile picture image
     * @param coverPhoto Byte array containing the cover photo image
     * @param gender The user's gender
     */
    public Profile(byte[] profilePicture, byte[] coverPhoto, String gender) {
        this.profilePicture = profilePicture;
        this.coverPhoto = coverPhoto;
        this.gender = gender;
        
        // Initialize empty lists
        this.workExperiences = new ArrayList<>();
        this.educations = new ArrayList<>();
        this.places = new ArrayList<>();
        this.stats = new ArrayList<>();
    }

    /**
     * Adds a work experience entry to the user's profile.
     * 
     * @param work The Work object containing employment details
     * @return true if successfully added, false otherwise
     */
    public boolean addWorkExperience(Work work) {
        return this.workExperiences.add(work);
    }

    /**
     * Adds an education entry to the user's profile.
     * 
     * @param education The Education object containing educational details
     * @return true if successfully added, false otherwise
     */
    public boolean addEducation(Education education) {
        return this.educations.add(education);
    }

    /**
     * Adds a place to the user's profile (places lived or visited).
     * 
     * @param place The Place object containing location details
     * @return true if successfully added, false otherwise
     */
    public boolean addPlace(Place place) {
        return this.places.add(place);
    }

    // Getter methods
    
    /**
     * @return The profile picture as a byte array
     */
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    /**
     * @return The cover photo as a byte array
     */
    public byte[] getCoverPhoto() {
        return coverPhoto;
    }

    /**
     * @return The user's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return List of work experiences
     */
    public List<Work> getWorkExperiences() {
        return workExperiences;
    }

    /**
     * @return List of education entries
     */
    public List<Education> getEducations() {
        return educations;
    }

    /**
     * @return List of places
     */
    public List<Place> getPlaces() {
        return places;
    }
}

/**
 * Work class represents a single work experience entry in a user's profile.
 * It contains all relevant information about a job or position held by the user.
 */
class Work {
    // Work experience details
    private String title;           // Job title (e.g., "Software Engineer")
    private String company;         // Company name
    private String location;        // Work location
    private Date dateFrom;          // Start date of employment
    private Date dateTo;            // End date of employment (null if current)
    private String description;     // Description of role and responsibilities

    /**
     * Constructor to create a Work experience entry.
     * 
     * @param title The job title or position
     * @param company The name of the company or organization
     * @param location The location where the work was performed
     * @param dateFrom The start date of employment
     * @param dateTo The end date of employment (can be null for current positions)
     * @param description A description of the role and responsibilities
     */
    public Work(String title, String company, String location, Date dateFrom, Date dateTo, String description) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
    }

    // Getter methods
    
    /**
     * @return The job title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return The work location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return The start date of employment
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * @return The end date of employment
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * @return The description of the role
     */
    public String getDescription() {
        return description;
    }
}

/**
 * Education class represents an educational background entry.
 * Placeholder class for storing education information.
 */
class Education {
    private String school;
    private String degree;
    private Date dateFrom;
    private Date dateTo;

    /**
     * Constructor for Education entry.
     * 
     * @param school The name of the educational institution
     * @param degree The degree or qualification obtained
     * @param dateFrom The start date
     * @param dateTo The end date
     */
    public Education(String school, String degree, Date dateFrom, Date dateTo) {
        this.school = school;
        this.degree = degree;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    // Getters
    public String getSchool() { return school; }
    public String getDegree() { return degree; }
    public Date getDateFrom() { return dateFrom; }
    public Date getDateTo() { return dateTo; }
}

/**
 * Place class represents a location (lived or visited).
 * Placeholder class for storing place information.
 */
class Place {
    private String name;
    private Date dateFrom;
    private Date dateTo;

    /**
     * Constructor for Place entry.
     * 
     * @param name The name of the place
     * @param dateFrom The start date
     * @param dateTo The end date
     */
    public Place(String name, Date dateFrom, Date dateTo) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    // Getters
    public String getName() { return name; }
    public Date getDateFrom() { return dateFrom; }
    public Date getDateTo() { return dateTo; }
}
