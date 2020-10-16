package com.example.CardsGame.Model.Player;

import com.example.CardsGame.Model.Card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{
    private String playerId;
    private List<Card> cards = new ArrayList<>();
    private int totalCardValue = 0;

    public Player(String playerId) {
        super();
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getTotalCardValue() {
        this.totalCardValue = cards.stream()
                .map(card -> card.getFaceValue().getValue())
                .reduce(0, Integer::sum);
        return this.totalCardValue;
    }

    public void setTotalCardValue(int totalCardValue) {
        this.totalCardValue = totalCardValue;
    }

    @Override
    public int compareTo(Player player) {
        return Integer.compare(player.getTotalCardValue(), this.getTotalCardValue());
    }

}
