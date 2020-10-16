package com.example.CardsGame.Model.Game;

import com.example.CardsGame.Model.Deck.Shoe;
import com.example.CardsGame.Model.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    private UUID id;
    private List<Player> players;
    private final Shoe shoe;

    public Game() {
        this.id = UUID.randomUUID();
        this.players = new ArrayList<>();
        this.shoe = new Shoe();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Shoe getShoe() {
        return shoe;
    }

}
