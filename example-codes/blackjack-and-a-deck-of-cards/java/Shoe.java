package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shoe {
    private List<BlackjackCard> cards;
    private int numberOfDecks;

    public Shoe(int numberOfDecks) {
        this.cards = new ArrayList<>();
        this.numberOfDecks = numberOfDecks;
        createShoe();
        shuffle();
    }

    public void createShoe() {
        for (int i = 0; i < numberOfDecks; i++) {
            Deck deck = new Deck();
            this.cards.addAll(deck.getCards());
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            BlackjackCard temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    // Get the next card from the shoe
    public BlackjackCard dealCard() {
        if (cards.size() == 0) {
            createShoe();
        }
        return cards.remove(0);
    }
}

