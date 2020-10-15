package com.example.CardsGame.Controller;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.Player.Player;
import com.example.CardsGame.Routes.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

public interface GameControllerInterface {
    @RequestMapping(value = Routes.ROOT_URL)
    String redirToList();

    @RequestMapping(value = Routes.CREATE_GAME, method= RequestMethod.POST)
    ResponseEntity<String> createGame();

    @RequestMapping(value = Routes.DELETE_GAME, method= RequestMethod.DELETE)
    ResponseEntity deleteGame(String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.CREATE_DECK, method= RequestMethod.POST)
    ResponseEntity<String> createDeck();

    @RequestMapping(value = Routes.ADD_DECK, method= RequestMethod.POST)
    ResponseEntity addDeck(String gameId, String deckId) throws GameNotFoundException;

    @RequestMapping(value = Routes.ADD_PLAYER, method= RequestMethod.POST)
    ResponseEntity addPlayer(String gameId, String playerId) throws IllegalArgumentException;

    @RequestMapping(value = Routes.REMOVE_PLAYER, method= RequestMethod.POST)
    ResponseEntity removePlayer(String gameId, String playerId) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_PLAYER_CARDS, method= RequestMethod.GET)
    ResponseEntity<List<Card>> getPlayerCards(String gameId, String playerId) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_PLAYERS_TOTAL, method= RequestMethod.GET)
    ResponseEntity<List<Player>> getPlayersTotal(String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.DEAL_CARDS, method= RequestMethod.POST)
    ResponseEntity dealCards(String gameId, int dealNumber) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_UNDEALT_CARDS_PER_SUIT, method= RequestMethod.GET)
    ResponseEntity<Map<Suit, Integer>> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_UNDEALT_CARDS, method= RequestMethod.GET)
    ResponseEntity<Map<Card, Integer>> getUndealtCards(String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.SHUFFLE, method= RequestMethod.POST)
    ResponseEntity shuffle(String gameId) throws GameNotFoundException;
}
