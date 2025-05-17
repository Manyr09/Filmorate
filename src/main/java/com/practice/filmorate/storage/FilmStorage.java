package com.practice.filmorate.storage;

import com.practice.filmorate.model.Film;
import java.util.List;
import java.util.Optional;

public interface FilmStorage {
    Film create(Film film);
    Film update(Film film);
    List<Film> findAll();
    Optional<Film> findById(Long id);
}
