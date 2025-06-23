package com.practice.filmorate.controller;

import com.practice.filmorate.dao.MpaDbStorage;
import com.practice.filmorate.model.Mpa;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MpaController {
    private final MpaDbStorage mpaStorage;

    @GetMapping
    public List<Mpa> findAll() {
        return mpaStorage.findAll();
    }

    @GetMapping("/{id}")
    public Mpa findById(@PathVariable int id) {
        return mpaStorage.findById(id);
    }
}
