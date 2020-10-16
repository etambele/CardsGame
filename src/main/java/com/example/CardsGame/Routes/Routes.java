package com.example.CardsGame.Routes;

public class Routes {
    public static final String ROOT_URL = "/";

    public static final String CREATE_GAME = "/new_game";// POST
    public static final String DELETE_GAME = "/delete/{gameId}"; // DELETE

    public static final String CREATE_DECK = "/new_deck";// POST
    public static final String ADD_DECK = "/add_deck";// POST

    public static final String ADD_PLAYER ="/player/add_player";// POST
    public static final String REMOVE_PLAYER = "/player/remove_player";// POST
    public static final String GET_PLAYER_CARDS = "/player/{gameId}/{playerId}/cards";// GET
    public static final String GET_PLAYERS_TOTAL ="/player/{gameId}/score_board";// GET

    public static final String DEAL_CARDS = "/deal_cards";// POST
    public static final String GET_UNDEALT_CARDS_PER_SUIT = "/{gameId}/undealt_cards/suits";// GET
    public static final String GET_UNDEALT_CARDS ="/{gameId}/undealt_cards";// GET
    public static final String SHUFFLE = "/shuffle/{gameId}";// GET
}
