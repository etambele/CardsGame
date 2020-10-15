package com.example.CardsGame.GameDao.Interface;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.Player.Player;

import java.util.List;
import java.util.Map;

public interface GameFunctionDaoInterface {

    void dealCards(String gameId, int dealNumber) throws GameNotFoundException;

    Map<Suit, Integer> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException;

    Map<Card, Integer> getUndealtCards(String gameId) throws GameNotFoundException;

    void shuffle(String gameId) throws GameNotFoundException;
}
