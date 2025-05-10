package com.practice.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    private long id;

    private String name;

    private String description;

    private Date releaseDate;

    private int duration;

    private Set<Long> likes = new HashSet<>();
}
