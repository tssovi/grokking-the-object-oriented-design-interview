# LinkedIn System - Java Implementation

This directory contains a Java implementation of a LinkedIn-like professional networking system. The design follows object-oriented principles and demonstrates key concepts used in designing social networking platforms.

## 📋 Table of Contents

- [Overview](#overview)
- [System Architecture](#system-architecture)
- [Class Descriptions](#class-descriptions)
- [Key Features](#key-features)
- [Design Patterns](#design-patterns)
- [How to Use](#how-to-use)
- [Compilation and Execution](#compilation-and-execution)
- [Future Enhancements](#future-enhancements)

## 🎯 Overview

This implementation models the core functionality of LinkedIn, including:
- User account management
- Professional profiles with work experience and skills
- Company pages and job postings
- Groups and discussions
- Posts and messaging
- Search functionality for members, companies, and jobs

## 🏗️ System Architecture

The system is organized into six main Java files:

```
linkedin/java/
├── Constants.java      # Enums and common classes (Address, AccountStatus, etc.)
├── Account.java        # User accounts and person types (Member, Admin)
├── Profile.java        # User profiles and work experience
├── Company.java        # Company pages and job postings
├── GroupPost.java      # Groups, posts, and messages
├── Search.java         # Search functionality with indexing
└── README.md          # This file
```

## 📚 Class Descriptions

### Constants.java

**Enumerations:**
- `ConnectionInvitationStatus`: Tracks the status of connection requests (PENDING, ACCEPTED, CONFIRMED, REJECTED, CANCELED)
- `AccountStatus`: Represents account states (ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN)

**Classes:**
- `Address`: Stores physical address information (street, city, state, zip code, country)

### Account.java

**Classes:**
- `Account`: Manages user authentication credentials and account status
  - Methods: `resetPassword()`
  
- `Person` (Abstract): Base class for all person types with common attributes
  - Attributes: name, address, email, phone, account
  
- `Member`: Regular LinkedIn user with full platform access
  - Methods: `sendMessage()`, `createPost()`, `sendConnectionInvitation()`, `addConnection()`, `followCompany()`, `joinGroup()`
  - Attributes: profile, connections, follows, groups, etc.
  
- `Admin`: System administrator with elevated privileges
  - Methods: `blockUser()`, `unblockUser()`, `banUser()`
  
- `ConnectionInvitation`: Represents connection requests between members
  - Methods: `accept()`, `reject()`

### Profile.java

**Classes:**
- `Profile`: Comprehensive professional profile for members
  - Methods: `addExperience()`, `addEducation()`, `addSkill()`, `addAccomplishment()`, `addRecommendation()`
  - Attributes: summary, experiences, educations, skills, accomplishments, recommendations, stats
  
- `Experience`: Represents a work experience entry
  - Attributes: title, company, location, dateFrom, dateTo, description
  - Methods: `isCurrentPosition()`

### Company.java

**Classes:**
- `Company`: Represents an organization on LinkedIn
  - Methods: `addJobPosting()`, `removeJobPosting()`, `getActiveJobPostings()`
  - Attributes: name, description, type, companySize, activeJobPostings
  
- `JobPosting`: Represents a job opportunity
  - Methods: `markAsFulfilled()`, `reopen()`
  - Attributes: dateOfPosting, description, employmentType, location, isFulfilled

### GroupPost.java

**Classes:**
- `Group`: Professional community for discussions
  - Methods: `addMember()`, `removeMember()`, `updateDescription()`, `addPost()`
  - Attributes: name, description, totalMembers, members, posts
  
- `Post`: Content shared by members
  - Methods: `addLike()`, `removeLike()`, `addShare()`, `addComment()`, `addMedia()`
  - Attributes: text, totalLikes, totalShares, totalComments, owner, dateCreated
  
- `Message`: Direct message between members
  - Methods: `addRecipient()`, `addMedia()`
  - Attributes: sentTo, messageBody, media, dateSent, sender

### Search.java

**Classes:**
- `Search` (Abstract): Defines the search interface
  - Methods: `searchMember()`, `searchCompany()`, `searchJob()`
  
- `SearchIndex`: Concrete implementation using hash map indexes
  - Methods: `addMember()`, `addCompany()`, `addJobPosting()`, `searchMember()`, `searchCompany()`, `searchJob()`
  - Additional methods: `removeMember()`, `removeCompany()`, `removeJobPosting()`, `clearAllIndexes()`
  - Attributes: memberNames, companyNames, jobTitles (all using HashMap for efficient lookups)

## ✨ Key Features

### 1. User Management
- Account creation with authentication
- Profile management with work experience, education, and skills
- Connection system for networking
- Admin capabilities for user moderation

### 2. Content Sharing
- Create and share posts
- Like, comment, and share functionality
- Direct messaging between members
- Group discussions

### 3. Professional Networking
- Send and accept connection invitations
- Follow companies and join groups
- View member suggestions
- Track connections and followers

### 4. Job Market
- Companies can post job opportunities
- Members can search for jobs by title
- Track job posting status (active/fulfilled)
- Employment type and location filtering

### 5. Search Functionality
- Efficient indexed search for members by name
- Company search by name
- Job search by title
- Case-insensitive search with hash map indexing

## 🎨 Design Patterns

### 1. **Inheritance Hierarchy**
- `Person` is an abstract base class
- `Member` and `Admin` extend `Person` with specific functionality
- `Search` is abstract with `SearchIndex` as concrete implementation

### 2. **Encapsulation**
- All class attributes are private
- Public getter and setter methods for controlled access
- Validation in setter methods

### 3. **Composition**
- `Member` has a `Profile`
- `Profile` has a list of `Experience` objects
- `Company` has a list of `JobPosting` objects
- `Group` has lists of `Member` and `Post` objects

### 4. **Strategy Pattern**
- `Search` abstract class allows different search implementations
- `SearchIndex` provides hash map-based searching
- Can be extended with database or Elasticsearch implementations

## 🚀 How to Use

### Creating a Member

```java
// Create an address
Address address = new Address("123 Main St", "San Francisco", "CA", "94105", "USA");

// Create an account
Account account = new Account("john.doe@email.com", "securePassword123");

// Create a member
Member member = new Member("John Doe", address, "john.doe@email.com", "+1-555-0100", account);

// Set headline
member.setHeadline("Software Engineer at Tech Company");

// Add work experience
Experience exp = new Experience(
    "Senior Software Engineer",
    "Tech Company",
    "San Francisco, CA",
    new Date(2020, 1, 1),
    null, // Current position
    "Leading backend development team"
);
member.getProfile().addExperience(exp);

// Add skills
member.getProfile().addSkill("Java");
member.getProfile().addSkill("Python");
member.getProfile().addSkill("System Design");
```

### Creating a Company and Job Posting

```java
// Create a company
Company company = new Company(
    "Tech Innovations Inc.",
    "Leading technology company specializing in AI and ML",
    "Public",
    5000
);

// Create a job posting
JobPosting job = new JobPosting(
    "Senior Java Developer - Work on cutting-edge distributed systems",
    "Full-time",
    "Remote",
    false
);

// Add job to company
company.addJobPosting(job);
```

### Sending Connection Invitations

```java
// Create another member
Member member2 = new Member("Jane Smith", address2, "jane@email.com", "+1-555-0200", account2);

// Send connection invitation
ConnectionInvitation invitation = new ConnectionInvitation(
    member,
    member2,
    "Hi Jane, I'd like to connect with you!"
);
member.sendConnectionInvitation(invitation);

// Accept invitation
invitation.accept(); // This adds both members to each other's connections
```

### Creating and Interacting with Posts

```java
// Create a post
Post post = new Post("Excited to share my new project!", member);

// Add media
byte[] imageData = loadImageData();
post.addMedia(imageData);

// Create the post
member.createPost(post);

// Other members can interact
post.addLike();
post.addComment("Great work!");
post.addShare();
```

### Using Search Functionality

```java
// Create search index
SearchIndex searchIndex = new SearchIndex();

// Add members to index
searchIndex.addMember(member);
searchIndex.addMember(member2);

// Add companies
searchIndex.addCompany(company);

// Add job postings
searchIndex.addJobPosting(job);

// Search for members
List<Member> results = searchIndex.searchMember("John Doe");

// Search for companies
List<Company> companies = searchIndex.searchCompany("Tech Innovations");

// Search for jobs
List<JobPosting> jobs = searchIndex.searchJob("Senior Java Developer");
```

### Creating Groups

```java
// Create a group
Group group = new Group(
    "Java Developers Network",
    "A community for Java developers to share knowledge and opportunities"
);

// Add members
group.addMember(member);
group.addMember(member2);

// Create a post in the group
Post groupPost = new Post("What's your favorite Java framework?", member);
group.addPost(groupPost);
```

### Sending Messages

```java
// Create a message
List<byte[]> attachments = new ArrayList<>();
Message message = new Message(
    member2,
    "Hi Jane, would you be interested in collaborating on a project?",
    attachments
);

// Send the message
member.sendMessage(message);
```

### Admin Operations

```java
// Create an admin
Admin admin = new Admin("Admin User", adminAddress, "admin@linkedin.com", "+1-555-9999", adminAccount);

// Block a user for policy violation
admin.blockUser(member);

// Later, unblock the user
admin.unblockUser(member);

// Permanently ban a user
admin.banUser(spammerMember);
```

## 🔧 Compilation and Execution

### Compiling the Code

```bash
# Navigate to the java directory
cd example-codes/linkedin/java

# Compile all Java files
javac *.java

# Or compile individually
javac Constants.java
javac Profile.java
javac Account.java
javac Company.java
javac GroupPost.java
javac Search.java
```

### Creating a Test Class

Create a `LinkedInDemo.java` file to test the system:

```java
public class LinkedInDemo {
    public static void main(String[] args) {
        // Create members, companies, and test functionality
        System.out.println("LinkedIn System Demo");
        
        // Your test code here
    }
}
```

Compile and run:
```bash
javac LinkedInDemo.java
java LinkedInDemo
```

## 🔮 Future Enhancements

### Potential Improvements:

1. **Database Integration**
   - Replace in-memory storage with persistent database
   - Use JPA/Hibernate for ORM
   - Implement proper data persistence

2. **Advanced Search**
   - Fuzzy matching for names and titles
   - Partial string matching
   - Search filters (location, industry, experience level)
   - Integration with Elasticsearch or Apache Solr

3. **Security Enhancements**
   - Password hashing (BCrypt, Argon2)
   - JWT token-based authentication
   - OAuth integration
   - Two-factor authentication

4. **Privacy Controls**
   - Profile visibility settings
   - Connection privacy levels
   - Message filtering
   - Block/report functionality

5. **Recommendation System**
   - Connection suggestions based on mutual connections
   - Job recommendations based on profile
   - Content recommendations for feed
   - Skills endorsement system

6. **Analytics**
   - Profile view tracking
   - Post engagement metrics
   - Network growth analytics
   - Job posting performance

7. **Real-time Features**
   - WebSocket for instant messaging
   - Real-time notifications
   - Live feed updates
   - Online status indicators

8. **Additional Features**
   - Events and webinars
   - Learning courses and certifications
   - Premium membership tiers
   - Company reviews and ratings
   - Salary insights
   - Newsletter/articles publishing

9. **API Development**
   - RESTful API endpoints
   - GraphQL API
   - Rate limiting
   - API documentation (Swagger/OpenAPI)

10. **Testing**
    - Unit tests with JUnit
    - Integration tests
    - Mock objects for testing
    - Test coverage reporting

## 📝 Notes

- This is a simplified implementation for educational purposes
- In production, you would need proper database integration, security measures, and scalability considerations
- The search implementation uses simple hash maps; a real system would use a dedicated search engine
- Password storage should use proper hashing algorithms (not plain text)
- Error handling and validation should be more comprehensive
- Consider using dependency injection frameworks like Spring
- Implement proper logging and monitoring

## 📄 License

This code is provided as part of the "Grokking the Object-Oriented Design Interview" educational material.

## 🤝 Contributing

This is an educational project. Feel free to extend it with additional features or improvements as learning exercises.

---

**Happy Coding!** 🚀
