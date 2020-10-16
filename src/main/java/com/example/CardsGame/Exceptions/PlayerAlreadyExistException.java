package com.example.CardsGame.Exceptions;

public class PlayerAlreadyExistException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;

    public PlayerAlreadyExistException() {
        super();
    }

    public PlayerAlreadyExistException(String message) {
        super(message);
    }
}
