package com.practice.filmorate.storage;

import com.practice.filmorate.model.User;
import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User create(User user);
    User update(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
}

