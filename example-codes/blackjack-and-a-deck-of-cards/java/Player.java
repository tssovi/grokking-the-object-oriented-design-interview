package blackjack;

public class Player extends BasePlayer {
    private double bet;
    private double totalCash;

    public Player(String id, String password, double balance, String status, Person person) {
        super(id, password, balance, status, person);
        this.bet = 0;
        this.totalCash = 0;
    }

    public Player() {
        super("", "", 0, "", new Person("Player"));
        this.bet = 0;
        this.totalCash = 0;
    }

    public void placeBet(double bet) {
        this.bet = bet;
    }

    public double getBet() {
        return bet;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }
}

