package com.example.kinopoiskbmp.model;

import lombok.Getter;

@Getter
public enum ContentTypes {
    FILM("film"), SERIAL("serial");

    private final String name;


    ContentTypes(String name) {
        this.name = name;
    }

    public static ContentTypes getByName(String name) {
        try {
            return ContentTypes.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}

