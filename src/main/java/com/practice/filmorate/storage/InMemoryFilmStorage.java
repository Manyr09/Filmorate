package com.practice.filmorate.storage;

import com.practice.filmorate.model.Film;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();
    private long idCounter = 1;

    @Override
    public Film createFilm(Film film) {
        film.setId(idCounter++);
        films.put(film.getId(), film);
        return film;
    }
    @Override
    public Film updateFilm(Film film) {
        films.put(film.getId(), film);
        return film;
    }
    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }
    @Override
    public Film getFilm(long filmId) {
        return films.get(filmId);
    }
}
