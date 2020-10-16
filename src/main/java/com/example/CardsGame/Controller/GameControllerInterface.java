package com.example.CardsGame.Controller;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.DTO.AddDeckDTO;
import com.example.CardsGame.Model.DTO.DealDTO;
import com.example.CardsGame.Model.DTO.InGamePlayerDTO;
import com.example.CardsGame.Model.Player.Player;
import com.example.CardsGame.Routes.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResponseEntity deleteGame(@PathVariable String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.CREATE_DECK, method= RequestMethod.POST)
    ResponseEntity<String> createDeck();

    @RequestMapping(value = Routes.ADD_DECK, method= RequestMethod.POST)
    ResponseEntity addDeck(@RequestBody AddDeckDTO addDeckDTO) throws GameNotFoundException;

    @RequestMapping(value = Routes.ADD_PLAYER, method= RequestMethod.POST)
    ResponseEntity addPlayer(@RequestBody InGamePlayerDTO inGamePlayerDTO) throws IllegalArgumentException;

    @RequestMapping(value = Routes.REMOVE_PLAYER, method= RequestMethod.POST)
    ResponseEntity removePlayer(@RequestBody InGamePlayerDTO inGamePlayerDTO) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_PLAYER_CARDS, method= RequestMethod.GET)
    ResponseEntity<List<Card>> getPlayerCards(@PathVariable String gameId, @PathVariable String playerId) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_PLAYERS_TOTAL, method= RequestMethod.GET)
    ResponseEntity<List<Player>> getPlayersTotal(@PathVariable String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.DEAL_CARDS, method= RequestMethod.POST)
    ResponseEntity dealCards(@RequestBody DealDTO dealDTO) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_UNDEALT_CARDS_PER_SUIT, method= RequestMethod.GET)
    ResponseEntity<Map<Suit, Integer>> getUndealtCardsPerSuits(@PathVariable String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.GET_UNDEALT_CARDS, method= RequestMethod.GET)
    ResponseEntity<Map<Card, Integer>> getUndealtCards(@PathVariable String gameId) throws GameNotFoundException;

    @RequestMapping(value = Routes.SHUFFLE, method= RequestMethod.GET)
    ResponseEntity shuffle(@PathVariable String gameId) throws GameNotFoundException;
}
