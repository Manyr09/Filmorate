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
public class User {

    private long id;

    private String email;

    private String login;

    private String name;

    private Date birthday;

    private Set<Long> friends = new HashSet<>();
}
