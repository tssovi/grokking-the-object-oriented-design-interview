import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Company.java
 * 
 * This file contains classes related to companies and job postings on LinkedIn.
 * It includes the Company class for representing organizations and the
 * JobPosting class for representing job opportunities.
 */

/**
 * Class representing a company or organization on LinkedIn.
 * 
 * Companies can create profiles, post jobs, and interact with members.
 * This class stores company information and manages active job postings.
 */
public class Company {
    private String name;
    private String description;
    private String type;
    private int companySize;
    private List<JobPosting> activeJobPostings;

    /**
     * Constructor to create a new Company.
     * 
     * @param name The company name
     * @param description A description of the company and its business
     * @param type The type of company (e.g., "Public", "Private", "Non-profit")
     * @param companySize The number of employees
     */
    public Company(String name, String description, String type, int companySize) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.companySize = companySize;
        this.activeJobPostings = new ArrayList<>();
    }

    /**
     * Adds a new job posting to the company's active listings.
     * 
     * @param jobPosting The JobPosting to add
     * @return true if successfully added
     */
    public boolean addJobPosting(JobPosting jobPosting) {
        if (jobPosting != null && !jobPosting.isFulfilled()) {
            return this.activeJobPostings.add(jobPosting);
        }
        return false;
    }

    /**
     * Removes a job posting from active listings.
     * This is typically called when a position is filled.
     * 
     * @param jobPosting The JobPosting to remove
     * @return true if successfully removed
     */
    public boolean removeJobPosting(JobPosting jobPosting) {
        if (jobPosting != null) {
            jobPosting.setFulfilled(true);
            return this.activeJobPostings.remove(jobPosting);
        }
        return false;
    }

    /**
     * Gets all active (unfilled) job postings for this company.
     * 
     * @return List of active JobPosting objects
     */
    public List<JobPosting> getActiveJobPostings() {
        List<JobPosting> active = new ArrayList<>();
        for (JobPosting posting : activeJobPostings) {
            if (!posting.isFulfilled()) {
                active.add(posting);
            }
        }
        return active;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getCompanySize() {
        return companySize;
    }

    public List<JobPosting> getAllJobPostings() {
        return activeJobPostings;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }
}

/**
 * Class representing a job posting on LinkedIn.
 * 
 * Job postings contain information about open positions including
 * job description, employment type, location, and fulfillment status.
 */
class JobPosting {
    private Date dateOfPosting;
    private String description;
    private String employmentType;
    private String location;
    private boolean isFulfilled;

    /**
     * Constructor to create a new JobPosting.
     * The posting date is automatically set to the current date.
     * 
     * @param description Detailed description of the job role and requirements
     * @param employmentType Type of employment (e.g., "Full-time", "Part-time", "Contract")
     * @param location Job location (city, state, or "Remote")
     * @param isFulfilled Whether the position has been filled
     */
    public JobPosting(String description, String employmentType, String location, boolean isFulfilled) {
        this.dateOfPosting = new Date();
        this.description = description;
        this.employmentType = employmentType;
        this.location = location;
        this.isFulfilled = isFulfilled;
    }

    /**
     * Marks the job posting as fulfilled (position filled).
     * 
     * @return true if status was changed
     */
    public boolean markAsFulfilled() {
        if (!this.isFulfilled) {
            this.isFulfilled = true;
            return true;
        }
        return false;
    }

    /**
     * Reopens a fulfilled job posting.
     * 
     * @return true if status was changed
     */
    public boolean reopen() {
        if (this.isFulfilled) {
            this.isFulfilled = false;
            return true;
        }
        return false;
    }

    // Getters
    public Date getDateOfPosting() {
        return dateOfPosting;
    }

    public String getDescription() {
        return description;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public String getLocation() {
        return location;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    // Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFulfilled(boolean fulfilled) {
        this.isFulfilled = fulfilled;
    }
}
