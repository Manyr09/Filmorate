package com.practice.filmorate.dao;
import com.practice.filmorate.exception.NotFoundException;
import com.practice.filmorate.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDbStorage {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Genre> genreMapper = (rs, rowNum) -> {
        Genre genre = new Genre();
        genre.setId(rs.getInt("id"));
        genre.setName(rs.getString("name"));
        return genre;
    };

    public List<Genre> findAll() {
        return jdbcTemplate.query("SELECT * FROM genres ORDER BY id", genreMapper);
    }

    public Genre findById(int id) {
        return jdbcTemplate.query("SELECT * FROM genres WHERE id = ?", genreMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
    }
}
