package com.practice.filmorate.controller;

import com.practice.filmorate.exception.UserNotFoundException;
import com.practice.filmorate.exception.ValidationException;
import com.practice.filmorate.exception.FilmNotFoundException;
import com.practice.filmorate.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationException(final ValidationException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Ошибка валидации.", ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(final UserNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Пользователь не найден.", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleFilmNotFoundException(final FilmNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Фильм не найден.", ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleThrowable(final Throwable ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Произошла ошибка.", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
