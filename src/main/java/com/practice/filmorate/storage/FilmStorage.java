package com.practice.filmorate.storage;

import com.practice.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    Film createFilm(Film film);
    Film updateFilm(Film film);
    List<Film> getAllFilms();
    Film getFilm(long filmId);
}
