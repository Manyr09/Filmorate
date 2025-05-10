package com.practice.filmorate.storage;

import com.practice.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User createUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUser(long userId);
}
