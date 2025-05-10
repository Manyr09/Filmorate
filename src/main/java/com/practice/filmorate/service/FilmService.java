package com.practice.filmorate.service;

import com.practice.filmorate.exception.FilmNotFoundException;
import com.practice.filmorate.model.Film;
import com.practice.filmorate.storage.FilmStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    public Film updateFilm(Film film) {
        getFilmOrThrow(film.getId());
        return filmStorage.updateFilm(film);
    }

    public Film getFilm(long id) {
        return getFilmOrThrow(id);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void addLike(long filmId, long userId) {
        Film film = getFilmOrThrow(filmId);
        film.getLikes().add(userId);
    }

    public void removeLike(long filmId, long userId) {
        Film film = getFilmOrThrow(filmId);
        film.getLikes().remove(userId);
    }

    public List<Film> getPopularFilms(int count) {
        List<Film> films = filmStorage.getAllFilms();
        films.sort((f1, f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()));
        return films.subList(0, Math.min(count, films.size()));
    }

    private Film getFilmOrThrow(long id) {
        Film film = filmStorage.getFilm(id);
        if (film == null) {
            throw new FilmNotFoundException("Фильм с ID " + id + " не найден.");
        }
        return film;
    }
}
