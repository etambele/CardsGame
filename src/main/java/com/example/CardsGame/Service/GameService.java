package com.example.CardsGame.Service;

import com.example.CardsGame.Exceptions.DeckNotFoundException;
import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.GameDao.Interface.DeckDaoInterface;
import com.example.CardsGame.GameDao.Interface.GameDaoInterface;
import com.example.CardsGame.GameDao.Interface.GameFunctionDaoInterface;
import com.example.CardsGame.GameDao.Interface.PlayerDaoInterface;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.Deck.Deck;
import com.example.CardsGame.Model.Deck.Shoe;
import com.example.CardsGame.Model.Game.Game;
import com.example.CardsGame.Model.Player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private final GameDaoInterface gameDaoInterface;
    private final GameFunctionDaoInterface gameFunctionDaoInterface;
    private final PlayerDaoInterface playerDaoInterface;
    private final DeckDaoInterface deckDaoInterface;

    @Autowired
    public GameService(@Qualifier("GameImpl") GameDaoInterface gameDaoInterface,
                       @Qualifier("GameImpl") GameFunctionDaoInterface gameFunctionDaoInterface,
                       @Qualifier("GameImpl") PlayerDaoInterface playerDaoInterface,
                       @Qualifier("GameImpl") DeckDaoInterface deckDaoInterface) {

        this.gameDaoInterface = gameDaoInterface;
        this.gameFunctionDaoInterface = gameFunctionDaoInterface;
        this.playerDaoInterface = playerDaoInterface;
        this.deckDaoInterface = deckDaoInterface;
    }


    public String createGame() {
        return gameDaoInterface.createGame();
    }

    public void deleteGame(String gameId) throws GameNotFoundException {
        gameDaoInterface.deleteGame(gameId);
    }

    public String createDeck() {
        return deckDaoInterface.createDeck();

    }

    public void addDeck(String gameId, String deckId) throws GameNotFoundException {
        deckDaoInterface.addDeck(gameId, deckId);

    }

    public void addPlayer(String gameId, String playerId) throws IllegalArgumentException {
        playerDaoInterface.addPlayer(gameId, playerId);
    }

    public void removePlayer(String gameId, String playerId) throws GameNotFoundException {
        playerDaoInterface.removePlayer(gameId, playerId);
    }

    public List<Card> getPlayerCards(String gameId, String playerId) throws GameNotFoundException {
        return playerDaoInterface.getPlayerCards(gameId, playerId);
    }

    public List<Player> getPlayersTotal(String gameId) throws GameNotFoundException {
        return playerDaoInterface.getPlayersTotal(gameId);
    }

    public void dealCards(String gameId, int dealNumber) throws GameNotFoundException {
        gameFunctionDaoInterface.dealCards(gameId, dealNumber);
    }

    public Map<Suit, Integer> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException {
        return gameFunctionDaoInterface.getUndealtCardsPerSuits(gameId);
    }

    public Map<Card, Integer> getUndealtCards(String gameId) throws GameNotFoundException {
        return gameFunctionDaoInterface.getUndealtCards(gameId);
    }

    public void shuffle(String gameId) throws GameNotFoundException {
        gameFunctionDaoInterface.shuffle(gameId);
    }

}
