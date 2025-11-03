import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Search.java
 * 
 * This file contains classes for searching members, companies, and job postings
 * on LinkedIn. It includes an abstract Search interface and a concrete
 * SearchIndex implementation using hash maps for efficient lookups.
 */

/**
 * Abstract class defining the search interface for LinkedIn.
 * 
 * This class provides the contract for searching different types of entities
 * on the platform including members, companies, and job postings.
 */
public abstract class Search {
    
    /**
     * Searches for members by name.
     * 
     * @param name The name to search for
     * @return List of Member objects matching the search
     */
    public abstract List<Member> searchMember(String name);

    /**
     * Searches for companies by name.
     * 
     * @param name The company name to search for
     * @return List of Company objects matching the search
     */
    public abstract List<Company> searchCompany(String name);

    /**
     * Searches for job postings by title.
     * 
     * @param title The job title to search for
     * @return List of JobPosting objects matching the search
     */
    public abstract List<JobPosting> searchJob(String title);
}

/**
 * Concrete implementation of the Search class using indexed hash maps.
 * 
 * SearchIndex maintains in-memory indexes of members, companies, and job postings
 * for fast lookup operations. It uses hash maps where keys are searchable attributes
 * (names, titles) and values are sets of matching entities.
 * 
 * Note: In a production system, this would be replaced with a proper search engine
 * like Elasticsearch or a database with full-text search capabilities.
 */
class SearchIndex extends Search {
    // Index mapping member names to sets of members with that name
    private Map<String, Set<Member>> memberNames;
    
    // Index mapping company names to sets of companies with that name
    private Map<String, Set<Company>> companyNames;
    
    // Index mapping job titles to sets of job postings with that title
    private Map<String, Set<JobPosting>> jobTitles;

    /**
     * Constructor to create a new SearchIndex.
     * Initializes empty hash maps for all search indexes.
     */
    public SearchIndex() {
        this.memberNames = new HashMap<>();
        this.companyNames = new HashMap<>();
        this.jobTitles = new HashMap<>();
    }

    /**
     * Adds a member to the search index.
     * 
     * This method indexes the member by their name, allowing for efficient
     * name-based searches. Multiple members can have the same name.
     * 
     * @param member The Member to add to the index
     * @return true if successfully added
     */
    public boolean addMember(Member member) {
        if (member == null || member.getName() == null) {
            return false;
        }

        String name = member.getName().toLowerCase(); // Case-insensitive search
        
        // If this name already exists in the index, add to the existing set
        if (memberNames.containsKey(name)) {
            memberNames.get(name).add(member);
        } else {
            // Create a new set for this name
            Set<Member> members = new HashSet<>();
            members.add(member);
            memberNames.put(name, members);
        }
        return true;
    }

    /**
     * Removes a member from the search index.
     * 
     * @param member The Member to remove
     * @return true if successfully removed
     */
    public boolean removeMember(Member member) {
        if (member == null || member.getName() == null) {
            return false;
        }

        String name = member.getName().toLowerCase();
        if (memberNames.containsKey(name)) {
            Set<Member> members = memberNames.get(name);
            boolean removed = members.remove(member);
            
            // If the set is now empty, remove the key entirely
            if (members.isEmpty()) {
                memberNames.remove(name);
            }
            return removed;
        }
        return false;
    }

    /**
     * Adds a company to the search index.
     * 
     * This method indexes the company by its name for efficient searching.
     * 
     * @param company The Company to add to the index
     * @return true if successfully added
     */
    public boolean addCompany(Company company) {
        if (company == null || company.getName() == null) {
            return false;
        }

        String name = company.getName().toLowerCase();
        
        if (companyNames.containsKey(name)) {
            companyNames.get(name).add(company);
        } else {
            Set<Company> companies = new HashSet<>();
            companies.add(company);
            companyNames.put(name, companies);
        }
        return true;
    }

    /**
     * Removes a company from the search index.
     * 
     * @param company The Company to remove
     * @return true if successfully removed
     */
    public boolean removeCompany(Company company) {
        if (company == null || company.getName() == null) {
            return false;
        }

        String name = company.getName().toLowerCase();
        if (companyNames.containsKey(name)) {
            Set<Company> companies = companyNames.get(name);
            boolean removed = companies.remove(company);
            
            if (companies.isEmpty()) {
                companyNames.remove(name);
            }
            return removed;
        }
        return false;
    }

    /**
     * Adds a job posting to the search index.
     * 
     * This method indexes the job posting by extracting a title from its description.
     * In a real implementation, job postings would have a dedicated title field.
     * 
     * @param jobPosting The JobPosting to add to the index
     * @return true if successfully added
     */
    public boolean addJobPosting(JobPosting jobPosting) {
        if (jobPosting == null || jobPosting.getDescription() == null) {
            return false;
        }

        // Extract title from description (first line or first N characters)
        // In a real system, JobPosting would have a separate title field
        String description = jobPosting.getDescription().toLowerCase();
        String title = extractTitleFromDescription(description);
        
        if (jobTitles.containsKey(title)) {
            jobTitles.get(title).add(jobPosting);
        } else {
            Set<JobPosting> postings = new HashSet<>();
            postings.add(jobPosting);
            jobTitles.put(title, postings);
        }
        return true;
    }

    /**
     * Removes a job posting from the search index.
     * 
     * @param jobPosting The JobPosting to remove
     * @return true if successfully removed
     */
    public boolean removeJobPosting(JobPosting jobPosting) {
        if (jobPosting == null || jobPosting.getDescription() == null) {
            return false;
        }

        String description = jobPosting.getDescription().toLowerCase();
        String title = extractTitleFromDescription(description);
        
        if (jobTitles.containsKey(title)) {
            Set<JobPosting> postings = jobTitles.get(title);
            boolean removed = postings.remove(jobPosting);
            
            if (postings.isEmpty()) {
                jobTitles.remove(title);
            }
            return removed;
        }
        return false;
    }

    /**
     * Searches for members by name.
     * 
     * This method performs a case-insensitive exact match search.
     * In a production system, this would support fuzzy matching and partial matches.
     * 
     * @param name The name to search for
     * @return List of Member objects with matching names, or empty list if none found
     */
    @Override
    public List<Member> searchMember(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        }

        String searchName = name.toLowerCase();
        Set<Member> members = memberNames.get(searchName);
        
        if (members != null) {
            return new ArrayList<>(members);
        }
        return new ArrayList<>();
    }

    /**
     * Searches for companies by name.
     * 
     * @param name The company name to search for
     * @return List of Company objects with matching names, or empty list if none found
     */
    @Override
    public List<Company> searchCompany(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>();
        }

        String searchName = name.toLowerCase();
        Set<Company> companies = companyNames.get(searchName);
        
        if (companies != null) {
            return new ArrayList<>(companies);
        }
        return new ArrayList<>();
    }

    /**
     * Searches for job postings by title.
     * 
     * @param title The job title to search for
     * @return List of JobPosting objects with matching titles, or empty list if none found
     */
    @Override
    public List<JobPosting> searchJob(String title) {
        if (title == null || title.isEmpty()) {
            return new ArrayList<>();
        }

        String searchTitle = title.toLowerCase();
        Set<JobPosting> postings = jobTitles.get(searchTitle);
        
        if (postings != null) {
            return new ArrayList<>(postings);
        }
        return new ArrayList<>();
    }

    /**
     * Helper method to extract a title from a job description.
     * 
     * This is a simplified implementation. In a real system, job postings
     * would have a dedicated title field.
     * 
     * @param description The job description
     * @return Extracted title (first 50 characters or until newline)
     */
    private String extractTitleFromDescription(String description) {
        if (description == null || description.isEmpty()) {
            return "";
        }

        // Take first line or first 50 characters, whichever is shorter
        int newlineIndex = description.indexOf('\n');
        if (newlineIndex > 0 && newlineIndex < 50) {
            return description.substring(0, newlineIndex).trim();
        }
        
        if (description.length() > 50) {
            return description.substring(0, 50).trim();
        }
        
        return description.trim();
    }

    /**
     * Clears all search indexes.
     * Useful for testing or resetting the search system.
     */
    public void clearAllIndexes() {
        memberNames.clear();
        companyNames.clear();
        jobTitles.clear();
    }

    /**
     * Gets the total number of indexed members.
     * 
     * @return Total count of unique members in the index
     */
    public int getMemberCount() {
        int count = 0;
        for (Set<Member> members : memberNames.values()) {
            count += members.size();
        }
        return count;
    }

    /**
     * Gets the total number of indexed companies.
     * 
     * @return Total count of unique companies in the index
     */
    public int getCompanyCount() {
        int count = 0;
        for (Set<Company> companies : companyNames.values()) {
            count += companies.size();
        }
        return count;
    }

    /**
     * Gets the total number of indexed job postings.
     * 
     * @return Total count of unique job postings in the index
     */
    public int getJobPostingCount() {
        int count = 0;
        for (Set<JobPosting> postings : jobTitles.values()) {
            count += postings.size();
        }
        return count;
    }
}
