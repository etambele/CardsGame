package com.example.CardsGame.Controller;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.DTO.AddDeckDTO;
import com.example.CardsGame.Model.DTO.DealDTO;
import com.example.CardsGame.Model.DTO.InGamePlayerDTO;
import com.example.CardsGame.Model.Player.Player;
import com.example.CardsGame.Service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController implements GameControllerInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
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
        LOGGER.info("GameCreated");
        return new ResponseEntity<>(gameService.createGame(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteGame(String gameId) {
        LOGGER.info("Deleting game with {}", gameId);
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createDeck() {
        LOGGER.info("Creating deck");
        return new ResponseEntity<>(gameService.createDeck(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity addDeck(AddDeckDTO addDeckDTO) {
        LOGGER.info("adding deck {} in game {}", addDeckDTO.getDeckId(), addDeckDTO.getGameId());
        gameService.addDeck(addDeckDTO.getGameId(), addDeckDTO.getDeckId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity addPlayer(InGamePlayerDTO inGamePlayerDTO) {
        LOGGER.info("adding player {} to game {}", inGamePlayerDTO.getPlayerId(), inGamePlayerDTO.getGameId());
        gameService.addPlayer(inGamePlayerDTO.getGameId(), inGamePlayerDTO.getPlayerId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removePlayer(InGamePlayerDTO inGamePlayerDTO) {
        LOGGER.info("Removing player {} in game {}", inGamePlayerDTO.getPlayerId(), inGamePlayerDTO.getGameId());
        gameService.removePlayer(inGamePlayerDTO.getGameId(), inGamePlayerDTO.getPlayerId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Card>> getPlayerCards(@PathVariable String gameId, @PathVariable String playerId) {
        LOGGER.info("Getting cards of player {} in game {}", playerId, gameId);
        return new ResponseEntity<>(gameService.getPlayerCards(gameId, playerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Player>> getPlayersTotal(String gameId) {
        LOGGER.info("Getting player score board in game {}", gameId);
        return new ResponseEntity<>(gameService.getPlayersTotal(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity dealCards(DealDTO dealDTO) throws GameNotFoundException {
        LOGGER.info("Dealing {} cards in game {}", dealDTO.getDealNumber(), dealDTO.getGameId());
        gameService.dealCards(dealDTO.getGameId(), dealDTO.getDealNumber());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Suit, Integer>> getUndealtCardsPerSuits(String gameId) {
        LOGGER.info("Getting undealt cards in game {} by suits", gameId);
        return new ResponseEntity<>(gameService.getUndealtCardsPerSuits(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Card, Integer>> getUndealtCards(String gameId) {
        LOGGER.info("Getting undealt cards in game {}", gameId);
        return new ResponseEntity<>(gameService.getUndealtCards(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity shuffle(String gameId) {
        LOGGER.info("Shufffling cards in game {}", gameId);
        gameService.shuffle(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
