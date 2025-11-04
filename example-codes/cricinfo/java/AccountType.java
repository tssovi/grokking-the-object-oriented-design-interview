import java.util.ArrayList;
import java.util.List;

/**
 * Account types for different roles in the Cricinfo system.
 * This file contains classes representing Players, Admins, Umpires, Referees, and Commentators.
 * Each role has specific responsibilities and permissions within the system.
 * 
 * Note: For simplicity, we assume all class attributes are private and accessed through
 * their respective public getter methods and modified only through their public methods.
 */

/**
 * Represents a cricket player in the system.
 * A player can have multiple contracts with different teams or organizations.
 */
class Player {
    private Person person;
    private List<Object> contracts;  // List of contracts the player has signed

    /**
     * Constructor to initialize a Player with personal information.
     * 
     * @param person The personal information of the player (name, address, contact details)
     */
    public Player(Person person) {
        this.person = person;
        this.contracts = new ArrayList<>();
    }

    /**
     * Adds a new contract to the player's contract list.
     * This could be a team contract, sponsorship deal, etc.
     * 
     * @param contract The contract object to be added
     */
    public void addContract(Object contract) {
        // Implementation would add the contract to the list
        // contracts.add(contract);
    }

    // Getters
    public Person getPerson() { return person; }
    public List<Object> getContracts() { return contracts; }
}

/**
 * Represents an administrator in the Cricinfo system.
 * Admins have elevated privileges to manage matches, teams, and tournaments.
 */
class Admin {
    private Person person;

    /**
     * Constructor to initialize an Admin with personal information.
     * 
     * @param person The personal information of the admin
     */
    public Admin(Person person) {
        this.person = person;
    }

    /**
     * Adds a new match to the system.
     * Only admins have the authority to create and schedule matches.
     * 
     * @param match The match object to be added to the system
     */
    public void addMatch(Object match) {
        // Implementation would add the match to the system database
    }

    /**
     * Adds a new team to the system.
     * Admins can register new teams for tournaments and matches.
     * 
     * @param team The team object to be added to the system
     */
    public void addTeam(Team team) {
        // Implementation would add the team to the system database
    }

    /**
     * Adds a new tournament to the system.
     * Admins can create and manage tournaments (e.g., World Cup, IPL).
     * 
     * @param tournament The tournament object to be added to the system
     */
    public void addTournament(Object tournament) {
        // Implementation would add the tournament to the system database
    }

    // Getter
    public Person getPerson() { return person; }
}

/**
 * Represents an umpire in the cricket match.
 * Umpires are responsible for making decisions during the match.
 * There can be field umpires, reserve umpires, and TV umpires.
 */
class Umpire {
    private Person person;

    /**
     * Constructor to initialize an Umpire with personal information.
     * 
     * @param person The personal information of the umpire
     */
    public Umpire(Person person) {
        this.person = person;
    }

    /**
     * Assigns this umpire to a specific match.
     * An umpire can be assigned to multiple matches over time.
     * 
     * @param match The match object to which the umpire is being assigned
     */
    public void assignMatch(Object match) {
        // Implementation would assign the umpire to the match
    }

    // Getter
    public Person getPerson() { return person; }
}

/**
 * Represents a referee in the cricket match.
 * The referee oversees the match and ensures rules are followed.
 * The referee has authority over umpires and can intervene in disputes.
 */
class Referee {
    private Person person;

    /**
     * Constructor to initialize a Referee with personal information.
     * 
     * @param person The personal information of the referee
     */
    public Referee(Person person) {
        this.person = person;
    }

    /**
     * Assigns this referee to a specific match.
     * Each match typically has one referee overseeing the proceedings.
     * 
     * @param match The match object to which the referee is being assigned
     */
    public void assignMatch(Object match) {
        // Implementation would assign the referee to the match
    }

    // Getter
    public Person getPerson() { return person; }
}

/**
 * Represents a commentator providing live commentary for matches.
 * Commentators provide analysis and play-by-play descriptions for viewers.
 */
class Commentator {
    private Person person;

    /**
     * Constructor to initialize a Commentator with personal information.
     * 
     * @param person The personal information of the commentator
     */
    public Commentator(Person person) {
        this.person = person;
    }

    /**
     * Assigns this commentator to a specific match.
     * Multiple commentators can be assigned to a single match.
     * 
     * @param match The match object to which the commentator is being assigned
     */
    public void assignMatch(Object match) {
        // Implementation would assign the commentator to the match
    }

    // Getter
    public Person getPerson() { return person; }
}
