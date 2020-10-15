package com.example.CardsGame.GameDao.Interface;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Deck.Deck;

public interface DeckDaoInterface {

    String createDeck();

    Deck getDeck(String deckId, String errorMessage);

    void addDeck(String gameId, String deckId) throws GameNotFoundException;
}
