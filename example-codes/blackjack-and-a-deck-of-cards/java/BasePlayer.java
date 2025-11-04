package blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePlayer {
    private String id;
    private String password;
    private double balance;
    private String status;
    private Person person;
    private List<Hand> hands;

    public BasePlayer(String id, String password, double balance, String status, Person person) {
        this.id = id;
        this.password = password;
        this.balance = balance;
        this.status = status;
        this.person = person;
        this.hands = new ArrayList<>();
    }

    public void resetPassword() {
        // Implementation needed
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void addHand(Hand hand) {
        this.hands.add(hand);
    }

    public void removeHand(Hand hand) {
        this.hands.remove(hand);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getTotalScore() {
        int bestScore = 0;
        for (Hand hand : hands) {
            int score = hand.resolveScore();
            if (score > bestScore) {
                bestScore = score;
            }
        }
        return bestScore;
    }
}

