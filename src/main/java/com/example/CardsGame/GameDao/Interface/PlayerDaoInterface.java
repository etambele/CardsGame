package com.example.CardsGame.GameDao.Interface;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Player.Player;

import java.util.List;

public interface PlayerDaoInterface {

    void addPlayer(String gameId, String playerId) throws IllegalArgumentException;

    void removePlayer(String gameId, String playerId) throws GameNotFoundException;

    List<Card> getPlayerCards(String gameId, String playerId) throws GameNotFoundException;

    List<Player> getPlayersTotal(String gameId) throws GameNotFoundException;
}
