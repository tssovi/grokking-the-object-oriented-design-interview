import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Search.java
 * 
 * This file contains the search functionality for the Facebook system.
 * It includes an abstract Search interface and a concrete SearchIndex implementation
 * that allows users to search for members, groups, pages, and posts.
 */

/**
 * Search is an abstract interface that defines the contract for search operations
 * in the Facebook system. Any search implementation must provide methods to
 * search for members, groups, pages, and posts.
 */
public interface Search {
    /**
     * Searches for members by name.
     * 
     * @param name The name to search for
     * @return A set of Member objects matching the search criteria
     */
    Set<Member> searchMember(String name);

    /**
     * Searches for groups by name.
     * 
     * @param name The group name to search for
     * @return A set of Group objects matching the search criteria
     */
    Set<Group> searchGroup(String name);

    /**
     * Searches for pages by name.
     * 
     * @param name The page name to search for
     * @return A set of Page objects matching the search criteria
     */
    Set<Page> searchPage(String name);

    /**
     * Searches for posts containing a specific word or phrase.
     * 
     * @param word The word or phrase to search for in posts
     * @return A set of Post objects matching the search criteria
     */
    Set<Post> searchPost(String word);
}

/**
 * SearchIndex is a concrete implementation of the Search interface.
 * It maintains in-memory indexes of members, groups, pages, and posts
 * to enable fast searching.
 * 
 * This implementation uses HashMaps to store indexed data, where:
 * - Keys are search terms (names, words)
 * - Values are sets of matching objects
 * 
 * Note: In a production system, this would likely be replaced with
 * a more sophisticated search engine like Elasticsearch or Solr.
 */
class SearchIndex implements Search {
    // Index data structures for fast lookups
    // Using Map<String, Set<Object>> to handle multiple results per search term
    private Map<String, Set<Member>> memberNames;   // Index of members by name
    private Map<String, Set<Group>> groupNames;     // Index of groups by name
    private Map<String, Set<Page>> pageTitles;      // Index of pages by title
    private Map<String, Set<Post>> posts;           // Index of posts by keywords

    /**
     * Constructor initializes empty indexes for all searchable entities.
     */
    public SearchIndex() {
        this.memberNames = new HashMap<>();
        this.groupNames = new HashMap<>();
        this.pageTitles = new HashMap<>();
        this.posts = new HashMap<>();
    }

    /**
     * Adds a member to the search index.
     * If a member with the same name already exists, adds to the existing set.
     * 
     * @param member The Member to add to the index
     */
    public void addMember(Member member) {
        String name = member.getName();
        
        // If name already exists in index, add to existing set
        if (this.memberNames.containsKey(name)) {
            this.memberNames.get(name).add(member);
        } else {
            // Create new set for this name
            Set<Member> memberSet = new HashSet<>();
            memberSet.add(member);
            this.memberNames.put(name, memberSet);
        }
    }

    /**
     * Adds a group to the search index.
     * 
     * @param group The Group to add to the index
     */
    public void addGroup(Group group) {
        String name = group.getName();
        
        if (this.groupNames.containsKey(name)) {
            this.groupNames.get(name).add(group);
        } else {
            Set<Group> groupSet = new HashSet<>();
            groupSet.add(group);
            this.groupNames.put(name, groupSet);
        }
    }

    /**
     * Adds a page to the search index.
     * 
     * @param page The Page to add to the index
     */
    public void addPage(Page page) {
        String name = page.getName();
        
        if (this.pageTitles.containsKey(name)) {
            this.pageTitles.get(name).add(page);
        } else {
            Set<Page> pageSet = new HashSet<>();
            pageSet.add(page);
            this.pageTitles.put(name, pageSet);
        }
    }

    /**
     * Adds a post to the search index.
     * Indexes the post by keywords extracted from its text.
     * 
     * @param post The Post to add to the index
     */
    public void addPost(Post post) {
        // Extract keywords from post text
        // In a real implementation, this would use more sophisticated text processing
        String text = post.getText();
        String[] words = text.toLowerCase().split("\\s+");
        
        // Index post by each word
        for (String word : words) {
            if (this.posts.containsKey(word)) {
                this.posts.get(word).add(post);
            } else {
                Set<Post> postSet = new HashSet<>();
                postSet.add(post);
                this.posts.put(word, postSet);
            }
        }
    }

    /**
     * Searches for members by name.
     * 
     * @param name The name to search for
     * @return A set of Member objects with matching names, or empty set if none found
     */
    @Override
    public Set<Member> searchMember(String name) {
        return this.memberNames.getOrDefault(name, new HashSet<>());
    }

    /**
     * Searches for groups by name.
     * 
     * @param name The group name to search for
     * @return A set of Group objects with matching names, or empty set if none found
     */
    @Override
    public Set<Group> searchGroup(String name) {
        return this.groupNames.getOrDefault(name, new HashSet<>());
    }

    /**
     * Searches for pages by name.
     * 
     * @param name The page name to search for
     * @return A set of Page objects with matching names, or empty set if none found
     */
    @Override
    public Set<Page> searchPage(String name) {
        return this.pageTitles.getOrDefault(name, new HashSet<>());
    }

    /**
     * Searches for posts containing a specific word.
     * 
     * @param word The word to search for in posts
     * @return A set of Post objects containing the word, or empty set if none found
     */
    @Override
    public Set<Post> searchPost(String word) {
        return this.posts.getOrDefault(word.toLowerCase(), new HashSet<>());
    }
}
