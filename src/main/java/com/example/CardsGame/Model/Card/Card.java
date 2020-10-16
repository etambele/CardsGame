package com.example.CardsGame.Model.Card;

import java.util.Comparator;

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

}
