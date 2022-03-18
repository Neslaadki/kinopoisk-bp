package com.example.kinopoiskbmp.entities;

import lombok.Getter;

@Getter
public enum Genre {
    ACTION("Экшн"),
    COMEDY("Комедия"),
    DRAMA("Драма"),
    FANTASY("Фантастика"),
    HORROR("Хоррор"),
    MYSTERY("Детектив"),
    THRILLER("Триллер"),
    ROMANCE("Роман"),
    WESTERN("Вестерн");

    Genre(String translateName) {

    }

    public static Genre getByName(String name) {
        try {
            return Genre.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
