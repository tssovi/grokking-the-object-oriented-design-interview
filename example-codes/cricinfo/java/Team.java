import java.util.ArrayList;
import java.util.List;

/**
 * Classes related to team management in the Cricinfo system.
 * This file contains Team, TournamentSquad, and Playing11 classes
 * that manage team rosters, tournament squads, and match lineups.
 */

/**
 * Represents a cricket team in the system.
 * A team has a roster of players, a coach, and can participate in multiple tournaments.
 */
class Team {
    private String name;                      // Team name (e.g., "India", "Australia")
    private List<Player> players;             // All players registered with this team
    private List<Object> news;                // News articles and updates about the team
    private Person coach;                     // The head coach of the team

    /**
     * Constructor to initialize a Team with a name and coach.
     * 
     * @param name The name of the team
     * @param coach The head coach of the team
     */
    public Team(String name, Person coach) {
        this.name = name;
        this.coach = coach;
        this.players = new ArrayList<>();
        this.news = new ArrayList<>();
    }

    /**
     * Adds a tournament squad for this team.
     * A tournament squad is a subset of players selected for a specific tournament.
     * 
     * @param tournamentSquad The tournament squad to be added
     */
    public void addTournamentSquad(TournamentSquad tournamentSquad) {
        // Implementation would associate the tournament squad with this team
    }

    /**
     * Adds a player to the team's roster.
     * This adds the player to the overall team, not necessarily to a specific match or tournament.
     * 
     * @param player The player to be added to the team
     */
    public void addPlayer(Player player) {
        // Implementation would add the player to the team roster
        // players.add(player);
    }

    /**
     * Adds a news article or update about the team.
     * This could include transfer news, injury updates, match results, etc.
     * 
     * @param news The news object to be added
     */
    public void addNews(Object news) {
        // Implementation would add the news to the team's news feed
        // this.news.add(news);
    }

    // Getters
    public String getName() { return name; }
    public List<Player> getPlayers() { return players; }
    public List<Object> getNews() { return news; }
    public Person getCoach() { return coach; }
}

/**
 * Represents a squad of players selected for a specific tournament.
 * A tournament squad is typically a subset of the full team roster,
 * selected based on the tournament format and requirements.
 */
class TournamentSquad {
    private List<Player> players;              // Players selected for this tournament
    private List<Object> tournamentStats;      // Statistics for this squad in the tournament

    /**
     * Constructor to initialize an empty TournamentSquad.
     * Players are added after creation using the addPlayer method.
     */
    public TournamentSquad() {
        this.players = new ArrayList<>();
        this.tournamentStats = new ArrayList<>();
    }

    /**
     * Adds a player to the tournament squad.
     * Tournament squads typically have size limits (e.g., 15 players for ODI/T20).
     * 
     * @param player The player to be added to the tournament squad
     */
    public void addPlayer(Player player) {
        // Implementation would add the player to the squad
        // players.add(player);
    }

    // Getters
    public List<Player> getPlayers() { return players; }
    public List<Object> getTournamentStats() { return tournamentStats; }
}

/**
 * Represents the playing 11 (starting lineup) for a specific match.
 * In cricket, each team fields 11 players, with a 12th man available as a substitute fielder.
 */
class Playing11 {
    private List<Player> players;    // The 11 players in the starting lineup
    private Player twelfthMan;       // The 12th man (substitute fielder)

    /**
     * Constructor to initialize an empty Playing11.
     * Players are added after creation using the addPlayer method.
     */
    public Playing11() {
        this.players = new ArrayList<>();
        this.twelfthMan = null;
    }

    /**
     * Adds a player to the playing 11.
     * The playing 11 should contain exactly 11 players for a match.
     * 
     * @param player The player to be added to the playing 11
     */
    public void addPlayer(Player player) {
        // Implementation would add the player to the playing 11
        // Should validate that no more than 11 players are added
        // if (players.size() < 11) {
        //     players.add(player);
        // }
    }

    /**
     * Sets the 12th man (substitute fielder) for the match.
     * The 12th man can field but cannot bat or bowl.
     * 
     * @param player The player to be designated as the 12th man
     */
    public void setTwelfthMan(Player player) {
        this.twelfthMan = player;
    }

    // Getters
    public List<Player> getPlayers() { return players; }
    public Player getTwelfthMan() { return twelfthMan; }
}
