package com.example.CardsGame.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GameExceptionController {
    @ExceptionHandler(value = GameNotFoundException.class)
    public ResponseEntity<Object> gameNotFoundException(GameNotFoundException exception) {
        return new ResponseEntity<>("Game not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeckNotFoundException.class)
    public ResponseEntity<Object> deckNotFoundException(DeckNotFoundException exception) {
        return new ResponseEntity<>("Deck not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = PlayerAlreadyExistException.class)
    public ResponseEntity<Object> playerAlreadyExistException(PlayerAlreadyExistException exception) {
        return new ResponseEntity<>("Player Already Exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<Object> playerNotFoundException(PlayerNotFoundException exception) {
        return new ResponseEntity<>("Player Not Found", HttpStatus.CONFLICT);
    }
}
