package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.ContentType;
import com.example.kinopoiskbmp.model.Genre;

import java.util.List;

public interface ContentService {

    Content getContentById(Long id);

    List<ContentType> getContentTypes();

    List<Genre> getContentGenres();

    List<Content> getContentTypeByContentTypeOrGenre(String g, String t);

}
