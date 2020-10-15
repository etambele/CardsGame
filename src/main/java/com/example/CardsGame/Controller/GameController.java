package com.example.CardsGame.Controller;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.Player.Player;
import com.example.CardsGame.Service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController implements GameControllerInterface {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String redirToList() {
        return null;
    }

    @Override
    public ResponseEntity<String> createGame() {
        return new ResponseEntity<>(gameService.createGame(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteGame(String gameId) throws GameNotFoundException {
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createDeck() {
        return new ResponseEntity<>(gameService.createDeck(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity addDeck(String gameId, String deckId) throws GameNotFoundException {
        gameService.addDeck(gameId, deckId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity addPlayer(String gameId, String playerId) throws IllegalArgumentException {
        gameService.addPlayer(gameId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removePlayer(String gameId, String playerId) throws GameNotFoundException {
        gameService.removePlayer(gameId, playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Card>> getPlayerCards(String gameId, String playerId) throws GameNotFoundException {
        return new ResponseEntity<>(gameService.getPlayerCards(gameId, playerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Player>> getPlayersTotal(String gameId) throws GameNotFoundException {
        return new ResponseEntity<>(gameService.getPlayersTotal(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity dealCards(String gameId, int dealNumber) throws GameNotFoundException {
        gameService.dealCards(gameId, dealNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Suit, Integer>> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException {
        return new ResponseEntity<>(gameService.getUndealtCardsPerSuits(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Card, Integer>> getUndealtCards(String gameId) throws GameNotFoundException {
        return new ResponseEntity<>(gameService.getUndealtCards(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity shuffle(String gameId) throws GameNotFoundException {
        gameService.shuffle(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
