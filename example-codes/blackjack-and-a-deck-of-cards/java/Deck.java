package blackjack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Deck {
    private List<BlackjackCard> cards;
    private Date creationDate;

    public Deck() {
        this.cards = new ArrayList<>();
        this.creationDate = new Date();
        for (int value = 1; value <= 13; value++) {
            for (SUIT suit : SUIT.values()) {
                this.cards.add(new BlackjackCard(suit, value));
            }
        }
    }

    public List<BlackjackCard> getCards() {
        return cards;
    }
}

