package com.example.CardsGame.Model.DTO;

public class InGamePlayerDTO {
    private String gameId;
    private String playerId;

    public InGamePlayerDTO() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
}
