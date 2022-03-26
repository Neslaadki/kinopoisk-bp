package com.example.kinopoiskbmp.dto;

import com.example.kinopoiskbmp.model.Genres;
import lombok.Data;


@Data
public class ContentDTO {

    private String name;
    private String description;
    private ContentTypesDTO contentTypesDTO;
    private Genres genres;

}
