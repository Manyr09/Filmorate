package com.practice.filmorate.storage;

import org.springframework.stereotype.Component;
import com.practice.filmorate.model.User;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();
    private static Long idGlobal = 0L;

    @Override
    public User create(User user) {
        user.setId(getNextId());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NoSuchElementException("User not found");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    private static Long getNextId(){
        return ++idGlobal;
    }
}