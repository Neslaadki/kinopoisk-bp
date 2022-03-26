package com.example.kinopoiskbmp.model;

import lombok.Getter;

@Getter
public enum Genres {
    ACTION(1, "action"),
    COMEDY(2, "comedy"),
    DRAMA(3, "drama"),
    FANTASY(4, "fantasy"),
    HORROR(5, "horror"),
    MYSTERY(6, "mystery"),
    THRILLER(7, "thriller"),
    ROMANCE(8, "romance"),
    WESTERN(9, "western");

    private final int id;
    private final String name;

    Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genres getByName(String name) {
        try {
            return Genres.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
