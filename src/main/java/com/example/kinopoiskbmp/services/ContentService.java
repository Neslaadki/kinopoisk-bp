package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.Genres;

import java.util.List;

public interface ContentService {

    Content getContentById(Long id);

    List<ContentTypesDTO> getContentTypes();

    List<GenresDTO> getContentGenres();

    List<Content> getContentTypeByContentTypeOrGenre(String g, String t);

}
