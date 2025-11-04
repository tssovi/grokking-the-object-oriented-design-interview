# Facebook System Design - Java Implementation

## Overview

This is a Java implementation of a simplified Facebook-like social networking system. The design demonstrates object-oriented principles and patterns commonly used in social media platforms.

## System Architecture

The system is organized into several key components:

### 1. **Constants.java**
Contains fundamental enumerations and value objects:
- `ConnectionInvitationStatus` - Tracks invitation states (PENDING, ACCEPTED, REJECTED, CANCELED)
- `AccountStatus` - Manages account states (ACTIVE, CLOSED, CANCELED, BLACKLISTED, DISABLED)
- `Address` - Value object for storing physical addresses

### 2. **AccountType.java**
Core user and account management classes:
- `Account` - Represents user credentials and account status
- `Person` - Abstract base class for all user types
- `Member` - Regular Facebook users with profiles, connections, and social features
- `Admin` - System administrators with elevated privileges
- `ConnectionInvitation` - Manages connection requests between members

### 3. **Profile.java**
User profile and related information:
- `Profile` - Stores user profile data (pictures, work, education, places)
- `Work` - Represents employment history entries
- `Education` - Stores educational background
- `Place` - Represents locations (lived or visited)

### 4. **Group.java**
Social interaction and content classes:
- `Group` - Represents Facebook groups for shared interests
- `Post` - Status updates and content posts
- `Message` - Private messages between members
- `Comment` - Comments on posts

### 5. **Page.java**
Business and organization pages:
- `Page` - Represents Facebook Pages for businesses, brands, or public figures
- `Recommendation` - Reviews and recommendations for pages

### 6. **Search.java**
Search functionality:
- `Search` - Interface defining search operations
- `SearchIndex` - Implementation using in-memory indexes for fast searching

## Key Features

### Member Capabilities
- Create and manage personal profiles
- Send and receive connection invitations
- Create posts and share content
- Send private messages
- Join and follow groups
- Follow pages
- Search for members, groups, pages, and posts

### Admin Capabilities
- Block and unblock users
- Enable and disable pages
- Manage system-wide settings

### Social Features
- Connection management (friend requests)
- Groups for shared interests
- Pages for businesses and public figures
- Posts with likes and shares
- Comments on posts
- Private messaging
- Search functionality

## Design Patterns Used

### 1. **Inheritance Hierarchy**
```
Person (abstract)
├── Member
└── Admin
```
The `Person` class provides common attributes (name, address, email, phone, account) that are inherited by specific user types.

### 2. **Encapsulation**
All classes use private fields with public getter/setter methods to control access to internal state.

### 3. **Interface Segregation**
The `Search` interface defines a contract for search operations, allowing different implementations (e.g., in-memory, database, external search engine).

### 4. **Composition**
- `Member` contains a `Profile` object
- `Profile` contains lists of `Work`, `Education`, and `Place` objects
- `Group` contains a list of `Member` objects

## Class Relationships

### Core Relationships
- **Member** has a **Profile**
- **Member** can send **ConnectionInvitation** to other **Members**
- **Member** can create **Posts** and **Messages**
- **Member** can join **Groups** and follow **Pages**
- **Post** can have multiple **Comments**
- **Page** can have multiple **Recommendations**

### Aggregation
- Groups aggregate Members
- Members aggregate other Members (connections/followers)
- SearchIndex aggregates all searchable entities

## Usage Examples

### Creating a New Member
```java
// Create an account
Account account = new Account("user123", "encryptedPassword", AccountStatus.ACTIVE);

// Create an address
Address address = new Address("123 Main St", "San Francisco", "CA", "94102", "USA");

// Create a member
Member member = new Member(
    "member001",
    new Date(),
    "John Doe",
    address,
    "john.doe@example.com",
    "+1-555-0100",
    account
);
```

### Sending a Connection Invitation
```java
// Create invitation
ConnectionInvitation invitation = new ConnectionInvitation(recipientMember);

// Send invitation
senderMember.sendConnectionInvitation(invitation);

// Recipient accepts
invitation.acceptConnection();
```

### Creating and Sharing a Post
```java
// Create a post
Post post = new Post(
    "post001",
    "Hello, Facebook!",
    0,  // initial likes
    0,  // initial shares
    member
);

// Member creates the post
member.createPost(post);

// Others can like it
post.addLike();
```

### Searching for Content
```java
// Create search index
SearchIndex searchIndex = new SearchIndex();

// Add members to index
searchIndex.addMember(member1);
searchIndex.addMember(member2);

// Search for members
Set<Member> results = searchIndex.searchMember("John Doe");
```

### Creating a Group
```java
// Create a group
Group group = new Group(
    "group001",
    "Java Developers",
    "A group for Java enthusiasts",
    0  // initial member count
);

// Add members
group.addMember(member1);
group.addMember(member2);
```

### Creating a Page with Recommendations
```java
// Create a page
Page page = new Page(
    "page001",
    "Tech Company Inc.",
    "Leading technology company",
    "Business",
    0  // initial followers
);

// Add a recommendation
Recommendation rec = new Recommendation(
    "rec001",
    5,  // 5-star rating
    "Great company with excellent products!"
);
page.addRecommendation(rec);
```

## Data Flow

### Connection Request Flow
1. Member A creates a `ConnectionInvitation` for Member B
2. Member A sends the invitation using `sendConnectionInvitation()`
3. Member B receives the invitation (stored in their `connectionInvitations` list)
4. Member B can call `acceptConnection()` or `rejectConnection()`
5. If accepted, both members are added to each other's `memberConnections` list

### Post Creation and Interaction Flow
1. Member creates a `Post` object
2. Post is published using `createPost()`
3. Other members can view the post
4. Members can like the post (`addLike()`)
5. Members can share the post (`addShare()`)
6. Members can add `Comment` objects to the post

### Search Flow
1. System maintains a `SearchIndex` with indexed data
2. As new members, groups, pages, and posts are created, they're added to the index
3. Users perform searches using `searchMember()`, `searchGroup()`, `searchPage()`, or `searchPost()`
4. SearchIndex returns matching results from its in-memory indexes

## Extensibility

The design can be extended in several ways:

### 1. **Additional User Types**
Create new classes extending `Person` for different user roles (e.g., `Moderator`, `BusinessAccount`)

### 2. **Enhanced Search**
Implement additional `Search` interface implementations:
- Database-backed search
- Full-text search using Elasticsearch
- Fuzzy matching and autocomplete

### 3. **Rich Media Support**
Extend `Post` and `Message` classes to support:
- Multiple images
- Videos
- Links with previews
- Polls and surveys

### 4. **Privacy Controls**
Add privacy settings to control:
- Who can view posts
- Who can send connection requests
- Profile visibility

### 5. **Notification System**
Add a notification system to alert users about:
- New connection requests
- Post likes and comments
- Messages
- Group activities

### 6. **Event System**
Add classes for creating and managing events:
- Event creation and management
- RSVP functionality
- Event reminders

## Limitations and Simplifications

This implementation is simplified for educational purposes:

1. **No Persistence Layer** - Data is stored in memory only
2. **No Authentication** - Password handling is simplified
3. **No Validation** - Input validation is minimal
4. **No Concurrency Control** - Not thread-safe
5. **Simplified Search** - Basic keyword matching only
6. **No API Layer** - Direct object manipulation only
7. **No Business Logic** - Many methods are stubs (marked with TODO)

## Best Practices Demonstrated

1. **Encapsulation** - Private fields with public accessors
2. **Single Responsibility** - Each class has a clear, focused purpose
3. **Open/Closed Principle** - Classes are open for extension (inheritance) but closed for modification
4. **Interface Segregation** - Search interface defines only necessary methods
5. **Dependency Inversion** - Code depends on abstractions (Search interface) not concrete implementations
6. **Comprehensive Documentation** - All classes and methods have Javadoc comments

## Future Enhancements

To make this production-ready, consider adding:

1. **Database Integration** - Use JPA/Hibernate for persistence
2. **REST API** - Add Spring Boot REST controllers
3. **Security** - Implement Spring Security for authentication/authorization
4. **Validation** - Add Bean Validation annotations
5. **Testing** - Add JUnit tests for all classes
6. **Logging** - Add SLF4J/Logback for logging
7. **Caching** - Use Redis for caching frequently accessed data
8. **Message Queue** - Use RabbitMQ/Kafka for asynchronous processing
9. **Microservices** - Split into separate services (User Service, Post Service, etc.)
10. **Real-time Features** - Add WebSocket support for real-time notifications

## Compilation and Execution

To compile all Java files:
```bash
javac *.java
```

Note: This is a design demonstration. To run actual code, you would need to:
1. Implement the TODO methods
2. Add a main class with example usage
3. Add proper error handling
4. Integrate with a database

## License

This code is provided for educational purposes as part of the "Grokking the Object-Oriented Design Interview" course.

## Related Design Patterns

- **Factory Pattern** - Could be used to create different types of Posts or Messages
- **Observer Pattern** - Could be used for notification system
- **Strategy Pattern** - Could be used for different search algorithms
- **Singleton Pattern** - Could be used for SearchIndex to ensure single instance
- **Builder Pattern** - Could be used for complex object creation (e.g., Post with multiple media)

## Conclusion

This implementation demonstrates a well-structured object-oriented design for a social networking system. While simplified, it showcases important OOP principles and provides a foundation that can be extended into a full-featured application.
