package blackjack;

public enum SUIT {
    HEART(1),
    SPADE(2),
    CLUB(3),
    DIAMOND(4);

    private final int value;

    SUIT(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

