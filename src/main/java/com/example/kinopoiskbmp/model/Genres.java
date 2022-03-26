package com.example.kinopoiskbmp.model;

import lombok.Getter;

@Getter
public enum Genres {
    ACTION("action"),
    COMEDY("comedy"),
    DRAMA("drama"),
    FANTASY("fantasy"),
    HORROR("horror"),
    MYSTERY("mystery"),
    THRILLER("thriller"),
    ROMANCE("romance"),
    WESTERN("western");

    private final String name;

    Genres(String name) {
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
