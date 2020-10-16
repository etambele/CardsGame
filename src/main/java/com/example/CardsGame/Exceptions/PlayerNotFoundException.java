package com.example.CardsGame.Exceptions;

public class PlayerNotFoundException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public PlayerNotFoundException() {
        super();
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
