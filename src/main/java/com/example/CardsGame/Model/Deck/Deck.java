package com.example.CardsGame.Model.Deck;

import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.FaceValue;
import com.example.CardsGame.Model.Card.Suit;

import java.util.Stack;
import java.util.UUID;

public class Deck {

    private UUID id;
    private final Stack<Card> cards;

    public Deck() {
        this.id = UUID.randomUUID();
        this.cards = createDeck();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    private Stack<Card> createDeck() {
        Stack<Card> deck = new Stack<>();
        for(Suit suit : Suit.values()) {
            for (FaceValue faceValue : FaceValue.values()) {
                deck.push(new Card(suit, faceValue));
            }
        }
        return deck;
    }
}
