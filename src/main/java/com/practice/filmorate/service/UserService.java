package com.practice.filmorate.service;

import com.practice.filmorate.exception.UserNotFoundException;
import com.practice.filmorate.model.User;
import com.practice.filmorate.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(long userId, long friendId) {
        User user = getExistingUserOrThrow(userId);
        User friend = getExistingUserOrThrow(friendId);

        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    public void removeFriend(long userId, long friendId) {
        User user = getExistingUserOrThrow(userId);
        User friend = getExistingUserOrThrow(friendId);

        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
    }

    public List<User> getCommonFriends(long userId, long otherId) {
        User user = getExistingUserOrThrow(userId);
        User otherUser = getExistingUserOrThrow(otherId);

        Set<Long> userFriends = user.getFriends();
        Set<Long> otherUserFriends = otherUser.getFriends();

        Set<Long> intersection = new HashSet<>(userFriends);
        intersection.retainAll(otherUserFriends);

        List<User> commonFriends = new ArrayList<>();
        for (Long friendId : intersection) {
            commonFriends.add(getExistingUserOrThrow(friendId));
        }

        return commonFriends;
    }

    public List<User> getFriends(long userId) {
        User user = getExistingUserOrThrow(userId);
        List<User> friends = new ArrayList<>();

        for (Long friendId : user.getFriends()) {
            friends.add(getExistingUserOrThrow(friendId));
        }

        return friends;
    }

    public User getUser(long id) {
        return getExistingUserOrThrow(id);
    }

    private User getExistingUserOrThrow(long id) {
        User user = userStorage.getUser(id);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с ID " + id + " не найден.");
        }
        return user;
    }
}
