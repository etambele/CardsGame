package com.example.CardsGame.Routes;

public class Routes {
    public static final String ROOT_URL = "/";

    public static final String CREATE_GAME = "/new_game";
    public static final String DELETE_GAME = "/list";

    public static final String CREATE_DECK = "/new_deck";
    public static final String ADD_DECK = "/insert_player";

    public static final String ADD_PLAYER ="/players/compare";
    public static final String REMOVE_PLAYER = "/start_compare";
    public static final String GET_PLAYER_CARDS = "/next_compare";
    public static final String GET_PLAYERS_TOTAL ="/players/compare";

    public static final String DEAL_CARDS = "/start_compare";
    public static final String GET_UNDEALT_CARDS_PER_SUIT = "/next_compare";
    public static final String GET_UNDEALT_CARDS ="/players/compare";
    public static final String SHUFFLE = "/start_compare";
}
