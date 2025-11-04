package blackjack;

public class Dealer extends BasePlayer {
    public Dealer(String id, String password, double balance, String status, Person person) {
        super(id, password, balance, status, person);
    }

    public Dealer() {
        super("", "", 0, "", new Person("Dealer"));
    }
}

