package com.example.CardsGame.Model.Card;

public class Card implements Comparable<Card> {
    private final FaceValue faceValue;
    private Suit suit;

    public Card(Suit suit, FaceValue faceValue) {
        this.faceValue = faceValue;
        this.suit = suit;
    }


    public Suit getSuit() {
        return suit;
    }

    public FaceValue getFaceValue() {
        return faceValue;
    }

    @Override
    public String toString() {
        return "Card{" +
                "faceValue=" + faceValue +
                ", suit=" + suit +
                '}';
    }

    @Override
    public int compareTo(Card card) {
        return card.faceValue.compareTo(faceValue);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return faceValue == card.faceValue &&
                suit == card.suit;
    }
}
