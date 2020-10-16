package com.example.CardsGame.GameDao;

import com.example.CardsGame.Exceptions.DeckNotFoundException;
import com.example.CardsGame.Exceptions.GameNotFoundException;
import com.example.CardsGame.Exceptions.PlayerAlreadyExistException;
import com.example.CardsGame.Exceptions.PlayerNotFoundException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("GameImpl")
public class GameDataAccessService implements GameDaoInterface, GameFunctionDaoInterface, PlayerDaoInterface, DeckDaoInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameDataAccessService.class);
    private final Map<String, Game> games = new HashMap<>();
    private final Map<String, Deck> decks = new HashMap<>();
    private final String INVALID_GAME = "Invalid Game: ";
    private final String INVALID_PLAYER = "Player Not Found: ";


    @Override
    public String createGame() {
        Game game = new Game();
        String gameId = game.getId().toString();
        games.put(gameId, game);
        LOGGER.info("Deck deck created gameid= {}", gameId);
        return gameId;
    }

    @Override
    public void deleteGame(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        this.games.remove(game.getId().toString());
        LOGGER.info("Gameid= {} has been deleted", gameId);
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
        LOGGER.info("Deck deck created deck id= {}", deckId);
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
        decks.remove(deckId);
        LOGGER.info("Deck added to shoe for game {}", gameId);
    }

    @Override
    public void addPlayer(String gameId, String playerId) throws PlayerAlreadyExistException {
        Game game = this.getGame(gameId);
        if(getPlayer(playerId, game).isPresent()){
            throw new PlayerAlreadyExistException("Player with " + playerId + " already Exist!");
        }
        game.getPlayers().add(new Player(playerId));
    }

    @Override
    public void removePlayer(String gameId, String playerId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Player player = getPlayer(playerId, game)
                .orElseThrow(() -> new PlayerNotFoundException(INVALID_PLAYER));

        game.getPlayers().remove(player);
    }

    @Override
    public List<Card> getPlayerCards(String gameId, String playerId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Player player = getPlayer(playerId, game)
                .orElseThrow(() -> new PlayerNotFoundException(INVALID_PLAYER));

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
                LOGGER.info("counter is less than deal number");
                game.getPlayers().forEach(player -> {
                    Optional<Card> cardToDeal = dealCard(game.getShoe());
                    cardToDeal.ifPresent(card -> player.getCards().add(card));
                });
                counter++;
            }
        }else{
           LOGGER.info("shoe is empty, size {}", shoe.size());
        }
    }

    @Override
    public Map<Suit, Integer> getUndealtCardsPerSuits(String gameId) throws GameNotFoundException {
        Game game = this.getGame(gameId);
        Map<Suit, Integer> cardsPerSuit = new HashMap<>();
        for (Card card : game.getShoe().getCards()) {
            if (cardsPerSuit.containsKey(card.getSuit())) {
                cardsPerSuit.computeIfPresent(card.getSuit(), (key, val) -> ++val);
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
                undealtCards.computeIfPresent(card, (key, val) -> val + 1);
            } else {
                undealtCards.put(card, 1);
            }
        }

        Map<Card, Integer> sortedUndealthCards = new TreeMap<>(Comparator.comparing(Card::getSuit).thenComparing(Card::getFaceValue).reversed());
        sortedUndealthCards.putAll(undealtCards);
        return sortedUndealthCards;
    }

    @Override
    public void shuffle(String gameId) throws GameNotFoundException {
        LOGGER.info("Shuffle for game {}", gameId);
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
                .filter(player -> playerId.equals(player.getPlayerId()))
                .findFirst();
    }
    private Optional<Card> dealCard(Shoe shoe) {
        return shoe.getCards().isEmpty() ? Optional.empty() : Optional.of(shoe.getCards().pop());
    }

}
