package com.practice.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.practice.filmorate.model.User;
import com.practice.filmorate.storage.UserStorage;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public User create(User user) {
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public User findById(Long id) {
        return userStorage.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void addFriend(Long userId, Long friendId) {
        User user = findById(userId);
        User friend = findById(friendId);
        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    public void removeFriend(Long userId, Long friendId) {
        User user = findById(userId);
        User friend = findById(friendId);
        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
    }

    public List<User> getFriends(Long userId) {
        User user = findById(userId);
        List<User> friends = new ArrayList<>();
        for (Long friendId : user.getFriends()) {
            userStorage.findById(friendId).ifPresent(friends::add);
        }
        return friends;
    }

    public List<User> getCommonFriends(Long userId, Long otherId) {
        Set<Long> userFriends = findById(userId).getFriends();
        Set<Long> otherFriends = findById(otherId).getFriends();
        Set<Long> common = new HashSet<>(userFriends);
        common.retainAll(otherFriends);
        List<User> result = new ArrayList<>();
        for (Long id : common) {
            userStorage.findById(id).ifPresent(result::add);
        }
        return result;
    }
}