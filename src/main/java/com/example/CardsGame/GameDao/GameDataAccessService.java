package com.example.CardsGame.GameDao;

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
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("GameImpl")
public class GameDataAccessService implements GameDaoInterface, GameFunctionDaoInterface, PlayerDaoInterface, DeckDaoInterface {

    private final Map<String, Game> games = new HashMap<>();
    private final Map<String, Deck> decks = new HashMap<>();
    private final String INVALID_GAME = "Invalid Game: ";
    private final String INVALID_PLAYER = "Player Not Found: ";


    @Override
    public String createGame() {
        Game game = new Game();
        String gameId = game.getGameId().toString();
        games.put(gameId, game);
        return gameId;
    }

    @Override
    public void deleteGame(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        this.games.remove(game.getGameId().toString());
    }

    @Override
    public Game getGame(String gameId) throws GameNotFoundException {
        return Optional.ofNullable(games.get(gameId)).orElseThrow(()-> new GameNotFoundException(INVALID_GAME + gameId));
    }

    @Override
    public String createDeck() {
        Deck deck = new Deck();
        String deckId = deck.getId().toString();
        decks.put(deckId, deck);
        return deckId;
    }

    @Override
    public Deck getDeck(String deckId, String errorMessage) {
        return Optional.ofNullable(decks.get(deckId)).orElseThrow(() -> new DeckNotFoundException(errorMessage + deckId));
    }

    @Override
    public void addDeck(String gameId, String deckId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Deck deck = this.getDeck(deckId, "Invalid Deck: ");
        game.getShoe().getCards().addAll(deck.getCards());
    }

    @Override
    public void addPlayer(String gameId, String playerId) throws IllegalArgumentException {
        Game game = this.getGame(gameId);
        if(getPlayer(playerId, game).isPresent()){
            throw new IllegalArgumentException("Player with " + playerId + " already Exist!");
        }
        game.getPlayers().add(new Player(playerId));
    }

    @Override
    public void removePlayer(String gameId, String playerId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Player player = getPlayer(playerId, game)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER));

        game.getPlayers().remove(player);
    }

    @Override
    public List<Card> getPlayerCards(String gameId, String playerId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Player player = getPlayer(playerId, game)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PLAYER));

        return player.getCards();
    }

    @Override
    public List<Player> getPlayersTotal(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Collections.sort(game.getPlayers());
        return game.getPlayers();
    }

    @Override
    public void dealCards(String gameId, int dealNumber) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Stack<Card> shoe = game.getShoe().getCards();
        if(shoe != null && !shoe.isEmpty()){
            int counter = 0;
            while (counter < dealNumber){
                game.getPlayers().forEach(player -> {
                    Optional<Card> cardToDeal = dealCard(game.getShoe());
                    cardToDeal.ifPresent(card -> player.getCards().add(card));
                });
                counter++;
            }
        }
    }

    @Override
    public Map<Suit, Integer> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Map<Suit, Integer> cardsPerSuit = new HashMap<>();
        for (Card card : game.getShoe().getCards()) {
            if (cardsPerSuit.containsKey(card.getSuit())) {
                cardsPerSuit.computeIfPresent(card.getSuit(), (key, val) -> val++);
            } else {
                cardsPerSuit.put(card.getSuit(), 1);
            }
        }
        return cardsPerSuit;
    }

    @Override
    public Map<Card, Integer> getUndealtCards(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Map<Card, Integer> undealtCards = new HashMap<>();
        for (Card card : game.getShoe().getCards()) {
            if (undealtCards.containsKey(card)) {
                undealtCards.computeIfPresent(card, (key, val) -> val++);
            } else {
                undealtCards.put(card, 1);
            }
        }
        Map<Card, Integer> sortedUndealthCards = new TreeMap<>(Card::compareTo);

        sortedUndealthCards.putAll(undealtCards);
        return sortedUndealthCards;
    }

    @Override
    public void shuffle(String gameId) throws GameNotFoundException {
        Random random = new Random();
        Game game = this.getGame(gameId);
        int shoeSize = game.getShoe().getCards().size();
        for(int i=0; i< shoeSize; i++){
            int rand = random.nextInt(shoeSize);
            Card temp = game.getShoe().getCards().get(i);
            game.getShoe().getCards().set(i, game.getShoe().getCards().get(rand));
            game.getShoe().getCards().set(rand, temp);
        }
    }

    private Optional<Player> getPlayer(String playerId, Game game) {
        return game.getPlayers().stream()
                .filter(p -> playerId.equals(p.getPlayerId()))
                .findFirst();
    }
    private Optional<Card> dealCard(Shoe shoe) {
        return shoe.getCards().isEmpty() ? Optional.empty() : Optional.of(shoe.getCards().pop());
    }

}
