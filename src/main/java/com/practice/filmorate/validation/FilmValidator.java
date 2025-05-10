package com.practice.filmorate.validation;

import com.practice.filmorate.exception.ValidationException;
import com.practice.filmorate.model.Film;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Calendar;

@Component
public class FilmValidator {

    private static final Date CINEMA_BIRTH_DATE;

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1895, Calendar.DECEMBER, 28, 0, 0, 0);
        CINEMA_BIRTH_DATE = calendar.getTime();
    }

    public void validate(Film film) throws ValidationException {
        if (film.getName() == null || film.getName().isBlank()) {
            throw new ValidationException("Название фильма не может быть пустым.");
        }
        if (film.getDescription() != null && film.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма не может превышать 200 символов.");
        }
        if (film.getReleaseDate() != null && film.getReleaseDate().before(CINEMA_BIRTH_DATE)) {
            throw new ValidationException("Дата релиза не может быть раньше 28 декабря 1895 года.");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("Продолжительность фильма должна быть положительной.");
        }
    }
}
