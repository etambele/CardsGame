package com.example.CardsGame.Model.DTO;

public class DealDTO {
    private String gameId;
    private int dealNumber;

    public DealDTO() {
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getDealNumber() {
        return dealNumber;
    }

    public void setDealNumber(int dealNumber) {
        this.dealNumber = dealNumber;
    }
}
