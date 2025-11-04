# Cricinfo System - Java Implementation

## Overview

This is a Java implementation of a Cricket Information Management System (Cricinfo). The system is designed to manage cricket matches, teams, players, officials, and provide real-time match information and statistics.

## System Architecture

The system is organized into four main components:

### 1. **Constants.java**
Contains fundamental data structures and enumerations:

- **Enums:**
  - `MatchFormat`: ODI, T20, TEST
  - `MatchResult`: LIVE, FINISHED, DRAWN, CANCELLED
  - `UmpireType`: FIELD, RESERVED, TV
  - `WicketType`: BOLD, CAUGHT, STUMPED, RUN_OUT, LBW, RETIRED_HURT, HIT_WICKET, OBSTRUCTING
  - `BallType`: NORMAL, WIDE, WICKET, NO_BALL
  - `RunType`: NORMAL, FOUR, SIX, LEG_BYE, BYE, NO_BALL, OVERTHROW

- **Classes:**
  - `Address`: Represents physical addresses
  - `Person`: Represents individuals with contact information

### 2. **AccountType.java**
Defines different user roles in the system:

- **Player**: Cricket players with contracts
- **Admin**: System administrators who can manage matches, teams, and tournaments
- **Umpire**: Match officials who make on-field decisions
- **Referee**: Senior officials who oversee matches
- **Commentator**: Provide live commentary and analysis

### 3. **Over.java**
Contains match play-related classes:

- **Over**: Represents a set of 6 legal deliveries
- **Ball**: Individual delivery with bowler, batsman, outcome, and commentary
- **Wicket**: Details of a dismissal (how, who was out, who was involved)
- **Commentary**: Text commentary with timestamp and commentator
- **Inning**: A team's batting turn containing multiple overs
- **Match** (abstract): Base class for all match types
  - **ODI**: One Day International (50 overs per side)
  - **Test**: Test Match (multi-day format)
  - **T20**: Twenty20 (20 overs per side)

### 4. **Team.java**
Manages team structures:

- **Team**: Cricket team with players, coach, and news
- **TournamentSquad**: Subset of players selected for a specific tournament
- **Playing11**: The 11 players selected for a specific match (plus 12th man)

## Key Features

### Match Management
- Track live matches with real-time updates
- Record ball-by-ball details including runs, wickets, and commentary
- Support for multiple match formats (ODI, T20, Test)
- Assign umpires, referees, and commentators to matches

### Team Management
- Maintain team rosters with player information
- Create tournament-specific squads
- Select playing 11 for each match
- Track team news and updates

### Player Management
- Store player personal information and contacts
- Manage player contracts
- Track player statistics across matches

### Officials Management
- Assign umpires (field, reserve, TV) to matches
- Assign referees to oversee matches
- Assign commentators for match coverage

### Statistical Tracking
- Ball-by-ball match data
- Wicket details with dismissal information
- Run scoring with different run types
- Match results and status tracking

## Design Principles

### Object-Oriented Design
- **Encapsulation**: All class attributes are private with public getter methods
- **Inheritance**: Match class is abstract with specific implementations (ODI, Test, T20)
- **Abstraction**: Clear separation of concerns across different modules
- **Composition**: Complex objects built from simpler ones (Match contains Innings, Innings contain Overs, etc.)

### Extensibility
- Easy to add new match formats by extending the Match class
- New account types can be added without modifying existing code
- Statistical tracking can be extended with new metrics

## Usage Example

```java
// Create a person
Address address = new Address("123 Cricket St", "Mumbai", "Maharashtra", "400001", "India");
Person personInfo = new Person("Virat Kohli", address, "virat@cricket.com", "+91-1234567890");

// Create a player
Player player = new Player(personInfo);

// Create a team
Person coachInfo = new Person("Rahul Dravid", address, "rahul@cricket.com", "+91-9876543210");
Team team = new Team("India", coachInfo);
team.addPlayer(player);

// Create match officials
Person refereePerson = new Person("Match Referee", address, "referee@cricket.com", "+91-1111111111");
Referee referee = new Referee(refereePerson);

// Create a match
Date matchDate = new Date();
ODI match = new ODI(1, matchDate, referee);

// Create commentator and add commentary
Person commentatorPerson = new Person("Harsha Bhogle", address, "harsha@cricket.com", "+91-2222222222");
Commentator commentator = new Commentator(commentatorPerson);
Commentary commentary = new Commentary("What a shot! That's a magnificent cover drive!", commentator);
```

## Class Relationships

```
Person
  ├── Player
  ├── Admin
  ├── Umpire
  ├── Referee
  └── Commentator

Match (abstract)
  ├── ODI
  ├── Test
  └── T20

Match contains:
  ├── Team (2 teams)
  ├── Inning (multiple innings)
  ├── Umpire (multiple umpires)
  ├── Referee (1 referee)
  └── Commentator (multiple commentators)

Inning contains:
  └── Over (multiple overs)

Over contains:
  └── Ball (6+ balls)

Ball contains:
  ├── Player (bowler)
  ├── Player (batsman)
  ├── Wicket (optional)
  └── Commentary

Team contains:
  ├── Player (multiple players)
  ├── TournamentSquad
  └── Playing11
```

## Future Enhancements

1. **Database Integration**: Connect to a database for persistent storage
2. **Statistics Engine**: Advanced analytics and player/team statistics
3. **Live Scoring**: Real-time score updates and notifications
4. **Tournament Management**: Complete tournament bracket and scheduling system
5. **User Interface**: Web or mobile interface for fans to view matches
6. **Betting Integration**: Odds and betting information (where legal)
7. **Video Highlights**: Integration with video replay systems
8. **Social Features**: Fan comments, predictions, and discussions

## Notes

- This implementation focuses on the core object-oriented design
- Method implementations are kept minimal (placeholder comments) to emphasize structure
- In a production system, these methods would include full business logic, validation, and database operations
- Error handling, logging, and security features should be added for production use

## Compilation

To compile all Java files:

```bash
javac Constants.java AccountType.java Over.java Team.java
```

## License

This is an educational implementation for learning object-oriented design principles.
