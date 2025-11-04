package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<BlackjackCard> cards;

    public Hand(BlackjackCard blackjackCard1, BlackjackCard blackjackCard2) {
        this.cards = new ArrayList<>();
        this.cards.add(blackjackCard1);
        this.cards.add(blackjackCard2);
    }

    public List<Integer> getScores() {
        List<Integer> totals = new ArrayList<>();
        totals.add(0);

        for (BlackjackCard card : cards) {
            List<Integer> newTotals = new ArrayList<>();
            for (int score : totals) {
                newTotals.add(card.getFaceValue() + score);
                if (card.getFaceValue() == 1) {
                    newTotals.add(11 + score);
                }
            }
            totals = newTotals;
        }

        return totals;
    }

    public void addCard(BlackjackCard card) {
        this.cards.add(card);
    }

    // Get highest score which is less than or equal to 21
    public int resolveScore() {
        List<Integer> scores = getScores();
        int bestScore = 0;
        for (int score : scores) {
            if (score <= 21 && score > bestScore) {
                bestScore = score;
            }
        }
        return bestScore;
    }

    public List<BlackjackCard> getCards() {
        return cards;
    }
}

