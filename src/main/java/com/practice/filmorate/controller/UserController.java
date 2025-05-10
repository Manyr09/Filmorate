package com.practice.filmorate.controller;

import com.practice.filmorate.model.User;
import com.practice.filmorate.service.UserService;
import com.practice.filmorate.exception.*;
import com.practice.filmorate.validation.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.userValidator = new UserValidator();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден.");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        User user = userService.getUser(id);
        User friend = userService.getUser(friendId);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден.");
        }
        if (friend == null) {
            throw new UserNotFoundException("Пользователь с ID " + friendId + " не найден.");
        }
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable long id, @PathVariable long friendId) {
        User user = userService.getUser(id);
        User friend = userService.getUser(friendId);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден.");
        }
        if (friend == null) {
            throw new UserNotFoundException("Пользователь с ID " + friendId + " не найден.");
        }
        userService.removeFriend(id, friendId);
    }
}
