import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classes related to match play, including overs, balls, wickets, commentary, innings, and matches.
 * This file contains the core game-play related classes for the Cricinfo system.
 */

/**
 * Represents an over in a cricket match.
 * An over consists of 6 legal deliveries (balls) bowled by one bowler.
 */
class Over {
    private int number;           // The over number in the innings (1st over, 2nd over, etc.)
    private List<Ball> balls;     // List of all balls bowled in this over

    /**
     * Constructor to initialize an Over with its number.
     * 
     * @param number The over number in the current innings
     */
    public Over(int number) {
        this.number = number;
        this.balls = new ArrayList<>();
    }

    /**
     * Adds a ball to this over.
     * An over typically contains 6 legal deliveries, but may have more if there are wides or no-balls.
     * 
     * @param ball The ball object to be added to this over
     */
    public void addBall(Ball ball) {
        // Implementation would add the ball to the list
        // balls.add(ball);
    }

    // Getters
    public int getNumber() { return number; }
    public List<Ball> getBalls() { return balls; }
}

/**
 * Represents a single ball (delivery) in a cricket match.
 * Contains information about who bowled it, who played it, the outcome, and commentary.
 */
class Ball {
    private Player balledBy;      // The bowler who delivered this ball
    private Player playedBy;      // The batsman who faced this ball
    private BallType type;        // Type of ball (normal, wide, no-ball, wicket)
    private Wicket wicket;        // Wicket information if a wicket fell on this ball
    private List<Object> runs;    // Runs scored on this ball
    private Commentary commentary; // Commentary for this ball

    /**
     * Constructor to initialize a Ball with all its details.
     * 
     * @param balledBy The player (bowler) who bowled this ball
     * @param playedBy The player (batsman) who faced this ball
     * @param ballType The type of delivery (normal, wide, wicket, no-ball)
     * @param wicket The wicket object if a dismissal occurred, null otherwise
     * @param runs The runs scored on this ball
     * @param commentary The commentary describing this ball
     */
    public Ball(Player balledBy, Player playedBy, BallType ballType, 
                Wicket wicket, List<Object> runs, Commentary commentary) {
        this.balledBy = balledBy;
        this.playedBy = playedBy;
        this.type = ballType;
        this.wicket = wicket;
        this.runs = runs;
        this.commentary = commentary;
    }

    // Getters
    public Player getBalledBy() { return balledBy; }
    public Player getPlayedBy() { return playedBy; }
    public BallType getType() { return type; }
    public Wicket getWicket() { return wicket; }
    public List<Object> getRuns() { return runs; }
    public Commentary getCommentary() { return commentary; }
}

/**
 * Represents a wicket (dismissal) in cricket.
 * Contains information about how the batsman was dismissed and who was involved.
 */
class Wicket {
    private WicketType wicketType;  // Type of dismissal (bowled, caught, lbw, etc.)
    private Player playerOut;       // The batsman who was dismissed
    private Player caughtBy;        // The fielder who caught the ball (if applicable)
    private Player runoutBy;        // The fielder who ran out the batsman (if applicable)
    private Player stumpedBy;       // The wicket-keeper who stumped the batsman (if applicable)

    /**
     * Constructor to initialize a Wicket with all dismissal details.
     * 
     * @param wicketType The type of dismissal
     * @param playerOut The batsman who was dismissed
     * @param caughtBy The fielder who caught the ball (null if not a catch)
     * @param runoutBy The fielder who effected the run-out (null if not a run-out)
     * @param stumpedBy The wicket-keeper who stumped (null if not stumped)
     */
    public Wicket(WicketType wicketType, Player playerOut, Player caughtBy, 
                  Player runoutBy, Player stumpedBy) {
        this.wicketType = wicketType;
        this.playerOut = playerOut;
        this.caughtBy = caughtBy;
        this.runoutBy = runoutBy;
        this.stumpedBy = stumpedBy;
    }

    // Getters
    public WicketType getWicketType() { return wicketType; }
    public Player getPlayerOut() { return playerOut; }
    public Player getCaughtBy() { return caughtBy; }
    public Player getRunoutBy() { return runoutBy; }
    public Player getStumpedBy() { return stumpedBy; }
}

/**
 * Represents commentary for a ball or event in the match.
 * Commentators provide real-time analysis and descriptions of the game.
 */
class Commentary {
    private String text;           // The commentary text
    private Date createdAt;        // Timestamp when the commentary was created
    private Commentator createdBy; // The commentator who provided this commentary

    /**
     * Constructor to initialize Commentary with text and commentator.
     * The creation timestamp is automatically set to the current date/time.
     * 
     * @param text The commentary text describing the ball or event
     * @param commentator The commentator providing this commentary
     */
    public Commentary(String text, Commentator commentator) {
        this.text = text;
        this.createdAt = new Date();  // Current date and time
        this.createdBy = commentator;
    }

    // Getters
    public String getText() { return text; }
    public Date getCreatedAt() { return createdAt; }
    public Commentator getCreatedBy() { return createdBy; }
}

/**
 * Represents an innings in a cricket match.
 * An innings is one team's turn to bat. A match can have multiple innings.
 */
class Inning {
    private int number;          // The innings number (1st innings, 2nd innings, etc.)
    private Date startTime;      // When this innings started
    private List<Over> overs;    // List of all overs bowled in this innings

    /**
     * Constructor to initialize an Inning with its number and start time.
     * 
     * @param number The innings number in the match
     * @param startTime The date and time when this innings began
     */
    public Inning(int number, Date startTime) {
        this.number = number;
        this.startTime = startTime;
        this.overs = new ArrayList<>();
    }

    /**
     * Adds an over to this innings.
     * Overs are added sequentially as they are completed.
     * 
     * @param over The over object to be added to this innings
     */
    public void addOver(Over over) {
        // Implementation would add the over to the list
        // overs.add(over);
    }

    // Getters
    public int getNumber() { return number; }
    public Date getStartTime() { return startTime; }
    public List<Over> getOvers() { return overs; }
}

/**
 * Abstract base class representing a cricket match.
 * Different match formats (ODI, Test, T20) extend this class.
 * Contains all match-related information including teams, innings, officials, and statistics.
 */
abstract class Match {
    private int number;                    // Match number or identifier
    private Date startTime;                // When the match started
    private MatchResult result;            // Current status/result of the match
    private List<Team> teams;              // Teams playing in this match (typically 2)
    private List<Inning> innings;          // All innings in this match
    private List<Umpire> umpires;          // Umpires officiating this match
    private Referee referee;               // Referee overseeing this match
    private List<Commentator> commentators; // Commentators covering this match
    private List<Object> matchStats;       // Statistical data for this match

    /**
     * Constructor to initialize a Match with basic details.
     * 
     * @param number The match number or identifier
     * @param startTime The date and time when the match starts
     * @param referee The referee assigned to oversee this match
     */
    public Match(int number, Date startTime, Referee referee) {
        this.number = number;
        this.startTime = startTime;
        this.result = MatchResult.LIVE;  // Match starts in LIVE status
        this.teams = new ArrayList<>();
        this.innings = new ArrayList<>();
        this.umpires = new ArrayList<>();
        this.referee = referee;
        this.commentators = new ArrayList<>();
        this.matchStats = new ArrayList<>();
    }

    /**
     * Assigns a stadium/venue to this match.
     * 
     * @param stadium The stadium object where the match will be played
     */
    public void assignStadium(Object stadium) {
        // Implementation would assign the stadium to this match
    }

    /**
     * Assigns or changes the referee for this match.
     * 
     * @param referee The referee to be assigned to this match
     */
    public void assignReferee(Referee referee) {
        this.referee = referee;
    }

    // Getters
    public int getNumber() { return number; }
    public Date getStartTime() { return startTime; }
    public MatchResult getResult() { return result; }
    public List<Team> getTeams() { return teams; }
    public List<Inning> getInnings() { return innings; }
    public List<Umpire> getUmpires() { return umpires; }
    public Referee getReferee() { return referee; }
    public List<Commentator> getCommentators() { return commentators; }
    public List<Object> getMatchStats() { return matchStats; }

    // Setter for result
    public void setResult(MatchResult result) { this.result = result; }
}

/**
 * Represents an ODI (One Day International) cricket match.
 * ODI matches have 50 overs per side and specific rules.
 */
class ODI extends Match {
    /**
     * Constructor to initialize an ODI match.
     * 
     * @param number The match number or identifier
     * @param startTime The date and time when the match starts
     * @param referee The referee assigned to oversee this match
     */
    public ODI(int number, Date startTime, Referee referee) {
        super(number, startTime, referee);
        // ODI-specific initialization can be added here
    }

    // ODI-specific methods can be added here
}

/**
 * Represents a Test cricket match.
 * Test matches can last up to 5 days with unlimited overs per innings.
 */
class Test extends Match {
    /**
     * Constructor to initialize a Test match.
     * 
     * @param number The match number or identifier
     * @param startTime The date and time when the match starts
     * @param referee The referee assigned to oversee this match
     */
    public Test(int number, Date startTime, Referee referee) {
        super(number, startTime, referee);
        // Test-specific initialization can be added here
    }

    // Test-specific methods can be added here
}

/**
 * Represents a T20 (Twenty20) cricket match.
 * T20 matches have 20 overs per side and are the shortest format.
 */
class T20 extends Match {
    /**
     * Constructor to initialize a T20 match.
     * 
     * @param number The match number or identifier
     * @param startTime The date and time when the match starts
     * @param referee The referee assigned to oversee this match
     */
    public T20(int number, Date startTime, Referee referee) {
        super(number, startTime, referee);
        // T20-specific initialization can be added here
    }

    // T20-specific methods can be added here
}
