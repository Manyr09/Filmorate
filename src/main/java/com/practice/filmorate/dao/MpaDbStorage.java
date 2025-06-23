package com.practice.filmorate.dao;
import com.practice.filmorate.exception.NotFoundException;
import com.practice.filmorate.model.Mpa;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MpaDbStorage {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Mpa> mpaMapper = (rs, rowNum) -> {
        Mpa mpa = new Mpa();
        mpa.setId(rs.getInt("id"));
        mpa.setName(rs.getString("name"));
        return mpa;
    };

    public List<Mpa> findAll() {
        return jdbcTemplate.query("SELECT * FROM mpa ORDER BY id", mpaMapper);
    }

    public Mpa findById(int id) {
        return jdbcTemplate.query("SELECT * FROM mpa WHERE id = ?", mpaMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Рейтинг MPA не найден"));
    }
}
