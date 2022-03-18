package com.example.kinopoiskbmp.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
public enum ContentType {
    FILM("Film"), SERIAL("Serial");

    private String translateName;

    ContentType(String translateName) {
        this.translateName = translateName;
    }

    public static ContentType getByName(String name) {
        try {
            return ContentType.valueOf(name.trim().toUpperCase());
        }catch (Exception e){
            return null;
        }
    }

}

