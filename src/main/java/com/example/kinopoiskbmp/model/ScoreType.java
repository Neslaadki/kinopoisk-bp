package com.example.kinopoiskbmp.model;

public enum ScoreType {
    NEGATIVE,
    NEUTRAL,
    POSITIVE;

    ScoreType() {}

    public static ScoreType getByName(String name) {
        try {
            return ScoreType.valueOf(name.trim().toUpperCase());
        }catch (Exception e){
            return null;
        }
    }
}
