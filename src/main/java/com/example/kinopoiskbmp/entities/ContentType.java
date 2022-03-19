package com.example.kinopoiskbmp.entities;

import lombok.Getter;

@Getter
public enum ContentType {
    FILM, SERIAL;


    ContentType() {

    }

    public static ContentType getByName(String name) {
        try {
            return ContentType.valueOf(name.trim().toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

}

