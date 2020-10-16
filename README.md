Routes:

LocalHost/game/

    CREATE_GAME = /new_game
    DELETE_GAME = /delete/{gameId}

    CREATE_DECK = /new_deck
    ADD_DECK = /add_deck

    ADD_PLAYER = /player/add_player
    REMOVE_PLAYER = /player/remove_player
    GET_PLAYER_CARDS = /player/{gameId}/{playerId}/cards
    GET_PLAYERS_TOTAL = /player/{gameId}/score_board

    DEAL_CARDS = /deal_cards
    GET_UNDEALT_CARDS_PER_SUIT = /{gameId}/undealt_cards/suits
    GET_UNDEALT_CARDS = /{gameId}/undealt_cards
    SHUFFLE = /shuffle/{gameId}
    
    Swagger was not added because of timing and I was unsure if you would want me to use it
