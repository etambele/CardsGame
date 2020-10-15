package com.example.CardsGame.Model.Deck;

import com.example.CardsGame.Model.Card.Card;

import java.util.Stack;

public class Shoe {
    private Stack<Card> cards;

    public Shoe() {
        cards = new Stack<Card>();
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public void setCards(Stack<Card> cards) {
        this.cards = cards;
    }
}
