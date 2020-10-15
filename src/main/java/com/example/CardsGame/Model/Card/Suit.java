package com.example.CardsGame.Model.Card;

public enum Suit {
    CLUB(1),
    SPADE(2),
    HEART(3),
    DIAMOND(4);

    private final int value;

    Suit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
