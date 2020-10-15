package com.example.CardsGame.GameDao.Interface;

import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Model.Card.Card;
import com.example.CardsGame.Model.Card.Suit;
import com.example.CardsGame.Model.Deck.Deck;
import com.example.CardsGame.Model.Game.Game;
import com.example.CardsGame.Model.Player.Player;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface GameDaoInterface {

    String createGame();

    void deleteGame(String gameId) throws GameNotFoundException;

    Game getGame(String gameId);

}
