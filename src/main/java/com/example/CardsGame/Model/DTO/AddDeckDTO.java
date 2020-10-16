package com.example.CardsGame.Model.DTO;

public class AddDeckDTO {

    private String gameId;
    private String deckId;

    public AddDeckDTO() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
