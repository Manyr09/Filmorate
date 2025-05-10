package com.practice.filmorate.storage;

import com.practice.filmorate.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage{
    private final Map<Long, User> users = new HashMap<>();
    private long idCounter = 1;

    @Override
    public User createUser(User user) {
        user.setId(idCounter++);
        users.put(user.getId(), user);
        return user;
    }
    @Override
    public User updateUser(User user) {
        users.put(user.getId(), user);
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    @Override
    public User getUser(long userId) {
        return users.get(userId);
    }
}
