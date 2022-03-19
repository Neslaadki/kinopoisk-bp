package com.example.kinopoiskbmp.entities;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION,
    COMEDY,
    DRAMA,
    FANTASY,
    HORROR,
    MYSTERY,
    THRILLER,
    ROMANCE,
    WESTERN;

    Genre() {}

    public static Genre getByName(String name) {
        try {
            return Genre.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
