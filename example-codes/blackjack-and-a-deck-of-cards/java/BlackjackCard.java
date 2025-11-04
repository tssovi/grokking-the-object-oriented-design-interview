package blackjack;

public class BlackjackCard extends Card {
    private int gameValue;

    public BlackjackCard(SUIT suit, int faceValue) {
        super(suit, faceValue);
        this.gameValue = faceValue;
        if (this.gameValue > 10) {
            this.gameValue = 10;
        }
    }

    public int getGameValue() {
        return gameValue;
    }
}

