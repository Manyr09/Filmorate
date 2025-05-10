package com.practice.filmorate.validation;

import com.practice.filmorate.exception.ValidationException;
import com.practice.filmorate.model.User;
public class UserValidator {

    public void validate(User user) {
        if (user == null) {
            throw new ValidationException("Пользователь не должен быть null.");
        }

        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ValidationException("Некорректный email.");
        }

        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            throw new ValidationException("Некорректный логин.");
        }

        if (user.getBirthday() != null) {
            throw new ValidationException("Дата рождения не может быть в будущем.");
        }
    }
}
