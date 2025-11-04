package blackjack;

public class Card {
    private SUIT suit;
    private int faceValue;

    public Card(SUIT suit, int faceValue) {
        this.suit = suit;
        this.faceValue = faceValue;
    }

    public SUIT getSuit() {
        return suit;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

