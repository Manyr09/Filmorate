package com.practice.filmorate.storage;

import org.springframework.stereotype.Component;
import com.practice.filmorate.model.Film;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();
    private static Long idGlobal = 0L;

    @Override
    public Film create(Film film) {
        film.setId(getNextId());
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new NoSuchElementException("Film not found");
        }
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Optional<Film> findById(Long id) {
        return Optional.ofNullable(films.get(id));
    }

    private static Long getNextId(){
        return ++idGlobal;
    }
}